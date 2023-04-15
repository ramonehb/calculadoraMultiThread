package client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import util.MsqReq;
import util.MsqResp;
import util.Status;

public class Client {
    public static void main(String[] args) {
        int porta = 54321;
        String ip = "127.0.0.1"; // localhost
        Socket socket;
        ObjectOutputStream out;
        ObjectInputStream in;
        Scanner read = new Scanner(System.in);
        double value1, value2;
        char oper;

        try {
            socket = new Socket(ip, porta);
            System.out.println("Conectado com o servidor.");
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
            return;
        }

        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            
            
            System.out.println("Digita a operacao (+, -, *, /): ");
            oper = read.nextLine().charAt(0);

            System.out.println("Digite o primeiro valor: ");
            value1 = Double.parseDouble(read.nextLine());

            System.out.println("Digite o segundo valor: ");
            value2 = Double.parseDouble(read.nextLine());

            MsqReq request = new MsqReq(oper, value1, value2, 0);
            out.writeObject(request);

            MsqResp response = (MsqResp) in.readObject();

            if(response.getStatus() == Status.SUCESSO) {
                System.out.println("Resposta: " + response.getValue());
            } else {
                if(response.getStatus() == Status.DIVISAO_ZERO) {
                    System.out.println("Erro. Divisão por zero");
                } else {
                    System.out.println("Operador inválido");
                }
            }
        } catch (Exception ex) {
            System.out.println("Erro na comunicação: " + ex.getMessage());
        }

        try {
            System.out.println("Encerrando o cliente");
            read.close();
            socket.close();
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }
}

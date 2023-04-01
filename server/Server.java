package server;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        int porta = 54321;
        ServerSocket serverSocket;
        Socket socketClient = null;
        boolean continuar = true;


        try {
            serverSocket = new ServerSocket(porta);
            System.out.println("Servidor disponível na porta: " + porta);
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
            return;
        }

        while(continuar) {
            try {
                System.out.println("Aguardando o cliente...");
                socketClient = serverSocket.accept();
                System.out.println("Conectado com " + socketClient.getInetAddress().getHostAddress());

                ThreadCalc calc = new ThreadCalc(socketClient);
                calc.start();

            } catch (Exception ex) {
                System.out.println("Erro: " + ex.getMessage());
            }
        }

        try {
            System.out.println("Encerrando o servidor");
            socketClient.close();
            serverSocket.close();
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }
    
}

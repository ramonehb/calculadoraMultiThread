package server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import util.MsqReq;
import util.MsqResp;
import util.Status;

public class ThreadCalc extends Thread {
    private Socket client;
    ObjectInputStream in;
    ObjectOutputStream out;
    MsqResp response;

    public ThreadCalc(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            in = new ObjectInputStream(client.getInputStream());
            out = new ObjectOutputStream(client.getOutputStream());
            MsqReq request = (MsqReq) in.readObject();

            System.out.println("Recebido: " + request.getValue1() + " " + request.getOperador() + " " + request.getValue2() + " = " +request.getResult());

            char oper = request.getOperador();
            double value1 = request.getValue1();
            double value2 = request.getValue2();
            double resp = 0.0;

            switch (oper) {
                case '+':
                    resp = value1 + value2;
                    response = new MsqResp(Status.SUCESSO, resp);
                    break;
                case '-':
                    resp = value1 - value2;
                    response = new MsqResp(Status.SUCESSO, resp);
                    break;
                case '*':
                    resp = value1 * value2;
                    response = new MsqResp(Status.SUCESSO, resp);
                    break;
                case '/':
                    if(request.getValue2() == 0) {
                        response = new MsqResp(Status.DIVISAO_ZERO, 0);
                    } else {
                        resp = value1 / value2;
                        response = new MsqResp(Status.SUCESSO, resp);
                    }
                    break;
            
                default:
                    response = new MsqResp(Status.OPERADOR_INVALIDO, 0);
                    break;
            }
            request.setResult(resp);
            System.out.println(request.getValue1() + " " + request.getOperador() + " " + request.getValue2() + " = " + request.getResult());
            out.writeObject(response);

        } catch (Exception ex) {
            System.out.println("Erro na thread: " + ex.getMessage());
        }
    }
    
}

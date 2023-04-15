package util;

import java.io.Serializable;

public class MsqReq implements Serializable{
    private char operador;
    private double value1, value2, result;

    public MsqReq (char operador, double value1, double value2, double result ){
        this.operador = operador;
        this.value1 = value1;
        this.value2 = value2;
        this.result = result;
    }

    public char getOperador() {
        return operador;
    }

    public double getValue1() {
        return value1;
    }

    public double getValue2() {
        return value2;
    }

    public double getResult(){
        return result;
    }

    public void setResult(double result){
        this.result = result;
    }
}


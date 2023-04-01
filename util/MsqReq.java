package util;

import java.io.Serializable;

public class MsqReq implements Serializable{
    private char operador;
    private double value1, value2;

    public MsqReq (char operador, double value1, double value2){
        this.operador = operador;
        this.value1 = value1;
        this.value2 = value2;
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
}


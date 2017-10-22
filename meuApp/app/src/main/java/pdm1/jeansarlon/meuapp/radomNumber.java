package pdm1.jeansarlon.meuapp;

import java.util.Random;

public class radomNumber{
    private int numero;
    public radomNumber(int min, int max) {
        Random gerador = new Random();
        this.numero = gerador.nextInt((max-min+1))+min;
    }
    String getNumber(){
        int num  = this.numero;
        String n = Integer.toString(num);
        return n;

    }
}

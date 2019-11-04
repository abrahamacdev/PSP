package tema2.ej1;

import java.util.function.Function;

public class ChequearPrimo extends Thread {

    private int num;
    private OnNewPrimeResult onNewPrimeResult = null;

    interface OnNewPrimeResult {
        void onNewPrime(int num);

        void onNotPrime(int num);
    }

    public ChequearPrimo(int num){
        this.num = num;
    }

    public ChequearPrimo(int num, OnNewPrimeResult onNewPrimeResult){
        this.num = num;
        this.onNewPrimeResult = onNewPrimeResult;
    }

    @Override
    public void run() {

        boolean esPrimo = true;

        if (num <= 1) esPrimo = false;
        if (num % 2 == 0 && num > 2) esPrimo = false;
        double s = Math.sqrt(num);
        for(int i = 3; i <= s; i++) {
            if(num % i == 0) esPrimo = false;
        }


        if (onNewPrimeResult != null){
            if (esPrimo) {
                onNewPrimeResult.onNewPrime(num);
            } else {
                onNewPrimeResult.onNotPrime(num);
            }
        }
    }
}

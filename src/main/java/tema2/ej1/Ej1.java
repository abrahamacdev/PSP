package tema2.ej1;

public class Ej1 {

    public static void main(String[] args){

        for (String arg : args){
            try {
                int num = Integer.valueOf(arg);

                ChequearPrimo.OnNewPrimeResult onNewPrimeResult = new ChequearPrimo.OnNewPrimeResult() {
                    @Override
                    public void onNewPrime(int num) {
                        System.out.println("El número " + num + " es primo");
                    }

                    @Override
                    public void onNotPrime(int num) {
                        System.out.println("El número " + num + " NO es primo");
                    }
                };

                ChequearPrimo chequearPrimo = new ChequearPrimo(num, onNewPrimeResult);
                chequearPrimo.start();

            }catch (NumberFormatException e){
                e.printStackTrace();
            }
        }
    }
}

package tema2.ej3;

import java.util.Random;

public class Sumador {

    public static void main(String[] args){

        Random random = new Random();

        for (String arg : args){
            try {

                double num = Double.parseDouble(arg);
                Thread.sleep(random.nextInt(2000));

                System.out.println("El número es " + num);
            }catch (NumberFormatException e){
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

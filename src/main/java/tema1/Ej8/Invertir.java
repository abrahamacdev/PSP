package tema1.Ej8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Invertir {

    public static void main(String[] args) throws IOException, InterruptedException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Comienza");

        boolean continuar = true;
        while (continuar){

            String resultado = bufferedReader.readLine();

            if (!resultado.equals("FIN")){
                System.out.println(invertir(resultado));
            }
            else {
                continuar = false;
            }
        }
        System.exit(0);
    }

    public static String invertir(String cadena){

        String resultado = "";
        for (int i=cadena.length()-1; i>=0; i--){
            resultado += cadena.charAt(i);
        }
        return resultado;
    }

}

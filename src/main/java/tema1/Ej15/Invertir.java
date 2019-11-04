package tema1.Ej15;

import java.io.*;

public class Invertir {

   public static void main(String[] args) throws IOException {

       // Leemos la cadena que nos pasará el proceso padre
       BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
       String cadena = bufferedReader.readLine();

       // La mostraremos directamente por la pantalla
        if (cadena != null){
            System.out.println("El resultado es: " + invertir(cadena));
        }
   }

    public static String invertir(String cadena){

        String resultado = "";
        for (int i=cadena.length()-1; i>=0; i--){
            resultado += cadena.charAt(i);
        }
        return resultado;
    }
}

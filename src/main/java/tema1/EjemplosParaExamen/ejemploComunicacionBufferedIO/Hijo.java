package tema1.EjemplosParaExamen.ejemploComunicacionBufferedIO;

import java.io.*;

public class Hijo {

    public static void main(String[] args){

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        try {
            // Esperamos a que nos avisen para enviarles un mensaje
            if (Integer.valueOf(bufferedReader.readLine()) == 1){

                // Enviamos el mensaje
                bufferedWriter.write("Texto desde el hijo (Ejemplo BufferedIO)");
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

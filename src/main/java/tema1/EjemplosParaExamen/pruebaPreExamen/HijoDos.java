package tema1.EjemplosParaExamen.pruebaPreExamen;

import java.io.*;

public class HijoDos {

    public static void main(String[] args){

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        try {

            bufferedWriter.write("Se ha creado el hijo dos");
            bufferedWriter.newLine();
            bufferedWriter.flush();

            int numero = Integer.valueOf(bufferedReader.readLine());

            if (numero == 1){
                bufferedWriter.write("Mensaje del hijo 2 explícito");
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }

            else {
                bufferedWriter.write("¿Qué has querido decir?");
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }

            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

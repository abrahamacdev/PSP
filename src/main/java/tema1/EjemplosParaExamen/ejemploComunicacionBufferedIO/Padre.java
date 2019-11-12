package tema1.EjemplosParaExamen.ejemploComunicacionBufferedIO;

import java.io.*;

public class Padre {

    public static void main(String[] args){

        String java = "java";
        String cpCommand = "-cp";
        String cpValor = "E:\\Datos\\2_DAM_A_NO_SYNC\\PSP\\out\\production\\classes";
        String clase = "tema1.EjemplosParaExamen.ejemploComunicacionBufferedIO.Hijo";
        String argss = "";

        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command(java,cpCommand,cpValor,clase,argss);

        Process process = null;

        try {
            process = processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (process != null){

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            try {

                // Le solicitamos que nos envíe una cadena
                bufferedWriter.write("1");
                bufferedWriter.newLine();
                bufferedWriter.flush();

                // Leemos el mensaje qque nos devuelve
                System.out.println(bufferedReader.readLine());

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}

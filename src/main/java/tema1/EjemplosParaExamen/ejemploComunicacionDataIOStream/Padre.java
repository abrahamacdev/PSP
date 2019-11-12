package tema1.EjemplosParaExamen.ejemploComunicacionDataIOStream;

import java.io.*;

public class Padre {

    public static void main(String[] args){

        String java = "java";
        String cpCommand = "-cp";
        String cpValor = "E:\\Datos\\2_DAM_A_NO_SYNC\\PSP\\out\\production\\classes";
        String clase = "tema1.EjemplosParaExamen.ejemploComunicacionDataIOStream.Hijo";
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

            DataOutputStream dataOutputStream = new DataOutputStream(process.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(process.getInputStream());

            try {

                // Le solicitamos que nos envíe una cadena
                dataOutputStream.writeInt(1);
                dataOutputStream.flush();

                // Leemos el mensaje qque nos devuelve
                System.out.println(dataInputStream.readUTF());

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}

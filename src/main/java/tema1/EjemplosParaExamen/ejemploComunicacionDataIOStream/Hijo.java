package tema1.EjemplosParaExamen.ejemploComunicacionDataIOStream;

import java.io.*;

public class Hijo {

    public static void main(String[] args){

        DataInputStream dataInputStream = new DataInputStream(System.in);
        DataOutputStream dataOutputStream = new DataOutputStream(System.out);

        try {
            // Esperamos a que nos avisen para enviarles un mensaje
            if (dataInputStream.readInt() == 1){

                // Enviamos el mensaje
                dataOutputStream.writeUTF("Texto desde el hijo (Ejemplo DataIO)");
                dataOutputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package tema1;

import java.io.*;

public class Ej3 {

    public static Character[] OPCIONES = new Character[]{'a'};

    public static void main(String[] args){

        System.out.println("Lanzando proceso...");
        Process p = null;
        DataInputStream dataInputStream = null;
        DataOutputStream dataOutputStream = null;

        try {
            p = Runtime.getRuntime().exec("notepad.exe");
        }catch (IOException e) {
            e.printStackTrace();
        }

        if (p != null){

            System.out.println("Conectando  tuberías...");
            dataInputStream = new DataInputStream(p.getInputStream());
            dataOutputStream = new DataOutputStream(p.getOutputStream());

            boolean continuar = true;
            while(continuar){

                try {
                    char c = dataInputStream.readChar();

                    if (!in(c,OPCIONES)){
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    else {
                        // Procesamos el resultado
                        dataOutputStream.writeUTF("Gracias");
                    }

                } catch (IOException end){
                    continuar = false;
                }
            }
        }

        int error = 0;

        try {
            p.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
            error = 1;
        }
        finally {
            System.exit(error);
        }
    }

    public static boolean in (Object c, Object[] objetos){

        for (Object o : objetos){
            if (o.equals(c)){
                return true;
            }
        }
        return false;
    }
}

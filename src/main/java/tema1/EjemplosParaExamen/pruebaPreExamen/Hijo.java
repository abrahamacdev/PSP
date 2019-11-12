package tema1.EjemplosParaExamen.pruebaPreExamen;

import java.io.DataInputStream;
import java.io.IOException;

public class Hijo {

    public static void main(String[] args){

        System.out.println("El hijo acaba de lanzarse!");

        if (System.getenv().containsKey("Ejemplo")){
            System.out.println("Nos han anadido a nuestras variables de entorno una tal \"" + System.getenv().get("Ejemplo") + "\"");
        }

        DataInputStream dataInputStream = new DataInputStream(System.in);

        try {
            // Esperamos a que nos den una señal para enviar otro mensaje
            if (dataInputStream.readInt() == 1){

                // Escribimos a la salida que nos hallan establecido
                System.out.println("Nos han pedido que escribamos este mensaje explícitamente");
            }

            dataInputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Adios papa!");

    }
}

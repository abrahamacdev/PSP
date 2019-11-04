package tema1.Ej16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Subproceso {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        // Leemos todas las líneas del fichero
        String linea = bufferedReader.readLine();
        while (linea != null){

            // Escribimos todas las líneas del fichero
            System.out.println(linea);

            // Leémos la siguiente línea
            linea = bufferedReader.readLine();
        }
    }
}

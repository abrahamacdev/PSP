package tema1.Ej7;

import java.io.*;
import java.util.Arrays;

public class Hijo {

    public static void main(String[] args) throws Exception {

        // Avisamos que ya estamos preparados para recibir los datos
        System.out.println(1);

        if (args.length > 0){
            System.out.println("Hemos recibido " + args.length + " argumentos");
            System.exit(0);
        }

        throw new Exception("No hemos recibido ningun parametro");
    }
}

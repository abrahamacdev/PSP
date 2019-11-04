package tema1.ejerciciosProcesos.ej4_a;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Scanner;

/*
    Recibimos un ejecutable y lo intentamos ejecutar
 */
public class Ej4Proceso {

    public static void main(String[] args){

        String comando;
        Scanner scanner = new Scanner(System.in);
        Boolean valido = false;

        do {

            System.out.println("Introduce el ejecutable o la ruta en la que se encuentra");
            comando = scanner.nextLine();
            String resultado = comprobarValidezArgumentos(comando);

            if (resultado != null){
                System.out.println(resultado);
            }

            else {
                valido = true;
            }

        }while (!valido);


        try {

            // Ejecutamos el programa pasado por parametros
            Process p = Runtime.getRuntime().exec(new String[]{"bash","-c",comando});

            BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            String linea;
            boolean mostradoMensajeError = false;
            while ((linea = error.readLine()) != null){

                if (!mostradoMensajeError){
                    mostradoMensajeError = true;
                    System.out.print("Ha ocurrido un error en el proceso: ");
                }
                System.out.println(linea);
            }
            p.waitFor();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static String comprobarValidezArgumentos(String arg){

        boolean tieneRuta = arg.contains("/");

        // Comprobamos si es una ruta
        if (tieneRuta){

            File f = new File(arg);

            if (!f.exists()){
                return "No existe tal recurso";
            }

            if (f.isDirectory()){
                return "EL argumento pasado es un directorio";
            }

            return null;
        }
        return null;
    }
}

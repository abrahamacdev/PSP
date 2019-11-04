package tema1.ejerciciosProcesos.ej4_b;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Ej4Procesob {

    public static void main(String[] args){

        String comando;
        Scanner scanner = new Scanner(System.in);
        Boolean valido = false;

        do {

            System.out.println("Introduce el ejecutable o la ruta en la que se encuentra");
            comando = scanner.nextLine();
            boolean resultado = comprobarValidezArgumentos(comando);

            if (resultado){
                valido = true;
            }

        }while (!valido);
    }

    public static boolean comprobarValidezArgumentos(String arg){

        boolean esJava = arg.startsWith("-j");
        String argumento = arg;

        // Obtenemos la ruta de la clase
        if (esJava){
            argumento = arg.split(" ")[1];

            // Si han añadido el .class, lo eliminamos
            if (argumento.endsWith(".class")){
                argumento = argumento.split(".class", argumento.lastIndexOf(".class"))[0];
            }
        }

        boolean tieneRuta = argumento.contains("/");

        // Comprobamos si es una ruta
        if (tieneRuta){

            File f = new File(argumento);

            if (!f.exists()){
                System.out.println("No existe tal recurso");
                return false;
            }

            if (f.isDirectory()){
                System.out.println("EL argumento pasado es un directorio");
                return false;
            }
        }

        try {

            if (esJava){
                argumento = "java " + argumento;
            }

            // Ejecutamos el programa pasado por parametros
            Process p = Runtime.getRuntime().exec(new String[]{"bash", "-c", argumento});

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

        return true;
    }

}

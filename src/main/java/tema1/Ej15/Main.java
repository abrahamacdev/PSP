package tema1.Ej15;

import java.io.*;
import java.util.Scanner;

public class Main {

    private static String SEPARADOR = System.getProperty("line.separator");
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException, InterruptedException {

        Main main = new Main();

        boolean repetir = true;
        do {

            String cadena = main.solicitarCadena();
            if (cadena != null && !cadena.isEmpty()){
                main.invertirCadena(cadena);
                repetir = main.deseaContinuar();
            }

            else {
                System.out.println("Cadena no válida");
            }

        }while (repetir);
    }

    public String solicitarCadena(){
        System.out.println("Introduce una cadena");
        String cadena = scanner.nextLine();

        return cadena;
    }

    public boolean deseaContinuar(){

        System.out.println("¿Desea introducir una nueva cadena? (S)í - (N)o");
        char opcion = scanner.nextLine().charAt(0);

        return opcion == 'S' || opcion == 's';
    }

    public void invertirCadena(String cadena) throws IOException, InterruptedException {

        String base = "java";
        String cpOpcion = "-cp";
        //String cpValor = "E:\\Datos\\2_DAM_A_NO_SYNC\\PSP\\out\\production\\PSP";
        String cpValor = "E:\\Datos\\2_DAM_A_NO_SYNC\\PSP\\out\\production\\classes";
        String claseInvertir = "Ej15.Invertir";

        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command(base,cpOpcion,cpValor,claseInvertir);
        processBuilder.redirectInput(ProcessBuilder.Redirect.PIPE); // Le pasaremos los valores nosotros mismos
        processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT); // Mostrará los valores invertidos directamente por pantalla
        processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT); // Mostrará los errores directamente por pantalla
        Process process = processBuilder.start();

        /* Para leer datos del flujo de otro proceso, es conveniente usar BufferedReader (no me ha dado problemas,
            y para la escritura, usar DataOutputStream o BufferedWriter (hacer siempre flush).
            ** Recordar al escribir añadir siempre el salto de línea
         */
        //BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        //DataOutputStream dataOutputStream = new DataOutputStream(process.getOutputStream());
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));

        // Le pasamos la cadena al subproceso para que la invierta y ya el la mostrará por pantalla (Redirect.INHERIT)
        bufferedWriter.write(cadena + SEPARADOR);
        //dataOutputStream.writeUTF(cadena + SEPARADOR);
        bufferedWriter.flush();

        // Esperamos a que el proceso termine de ejecutarse
        process.waitFor();
    }
}

package tema1.Ej8;


import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {


        String clase = " Ej8.Invertir";
        String classPath = " E:\\Datos\\2_DAM_A_NO_SYNC\\PSP\\Tema_1\\out\\production\\classes";
        String opciones = "-classpath";
        //Process hijo = Runtime.getRuntime().exec("java " + opciones + classPath + clase + " ejemploArgumento");
        Process hijo = Runtime.getRuntime().exec("java " + opciones + classPath + clase);

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(hijo.getOutputStream()));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(hijo.getInputStream()));

        String tempR = null;
        while (tempR == null){
            tempR = bufferedReader.readLine();
        }

        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
        while (continuar){


            System.out.println("Introduzca la cadena a invertir (Escribe \"FIN\" si ya no quieres más)");
            String resultado = scanner.nextLine();

            // Mandamos la cadena o en su defecto "FIN"
            bufferedWriter.write(resultado + "\n");
            bufferedWriter.flush();

            if (!resultado.equals("FIN")){
                System.out.println(bufferedReader.readLine());
            }

            else {
                continuar = false;
            }
        }
    }
}

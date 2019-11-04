package tema1.Ej7;

import java.io.*;

public class Padre {

    public static void main(String[] args) throws IOException, InterruptedException {

        String clase = " Ej7.Hijo";
        String classPath = " E:\\Datos\\2_DAM_A_NO_SYNC\\PSP\\Tema_1\\out\\production\\classes";
        String opciones = "-classpath";
        //Process hijo = Runtime.getRuntime().exec("java " + opciones + classPath + clase + " ejemploArgumento");
        Process hijo = Runtime.getRuntime().exec("java " + opciones + classPath + clase);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(hijo.getInputStream()));
        BufferedReader bufferedReaderError = new BufferedReader(new InputStreamReader(hijo.getErrorStream()));

        if (Integer.valueOf(bufferedReader.readLine()) == 1){

            String resultado = bufferedReader.readLine();
            if (resultado != null){
                System.out.println(resultado);
            }
            else {
                System.out.println(bufferedReaderError.readLine());
            }
        }
    }
}

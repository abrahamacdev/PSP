package tema1.Ej5;

import java.io.*;
import java.util.ArrayList;

public class BorradorCompleto {

    private static String AYUDA = "El programa nececesita como mínimo 2 argumentos: un nombre de archivo y una ruta. También puede " +
            "especificar más rutas en las que realizar la búsqueda si lo desea";

    public static void main(String[] args) throws Exception {

        if (args.length < 2){
            throw new Exception(AYUDA);
        }

        File archivo = new File(args[0]);

        if (!archivo.isDirectory()){

            ArrayList<File> directorios = new ArrayList<>();
            for (int i=1; i<args.length; i++){
                File temp = new File(args[i]);
                if (temp.exists() && temp.isDirectory()){
                    directorios.add(temp);
                }
            }

            new BorradorCompleto().lanzarBorrado(directorios,archivo);
        }
    }

    public void lanzarBorrado(ArrayList<File> dirs, File archivo) throws IOException, InterruptedException {

        String base = "java";
        String classPath = "-cp";
        String rutaClassPath = "E:\\Datos\\2_DAM_A_NO_SYNC\\PSP\\out\\production\\PSP";
        String classBorrador = "Ej5.Borrador";

        ArrayList<Process> procesos = new ArrayList<>();

        for (File dir : dirs){

            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command(base,classPath, rutaClassPath, classBorrador, archivo.getName(), dir.getCanonicalPath());
            procesos.add(processBuilder.start());
        }

        for (Process process : procesos){

            /*BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            System.out.println(bufferedReader.readLine());*/

            process.waitFor();
        }
    }
}

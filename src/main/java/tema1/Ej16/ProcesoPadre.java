package tema1.Ej16;

import java.io.*;

public class ProcesoPadre {

    private static String SEPARADOR = System.getProperty("line.separator");

    public static void main(String[] args) throws Exception {

        if (args.length != 4){
            throw new Exception("Número de argumentos incorrecto");
        }

        File[] dirs = new File[]{new File(args[1]),new File(args[3])};
        boolean validos = true;
        for (File dir : dirs){
            if (!dir.exists() || dir.isDirectory()){
                validos = false;
            }
        }

        if (!validos){
            System.out.println("Los archivos especificados no son válidos");
        }

        String[] programas = new String[]{args[0],args[2]};

        ProcesoPadre procesoPadre = new ProcesoPadre();
        procesoPadre.ejecutarProgramas(programas,dirs);

    }

    public void ejecutarProgramas(String[] programas, File[] directorios) throws IOException, InterruptedException {

        String base = "java";
        String opClasspath = "-cp";
        //String cpValor = "E:\\Datos\\2_DAM_A_NO_SYNC\\PSP\\out\\production\\PSP";
        String cpValor = "E:\\Datos\\2_DAM_A_NO_SYNC\\PSP\\out\\production\\classes\\tema1";


        ProcessBuilder processBuilderPrimero = new ProcessBuilder();
        processBuilderPrimero.command(base,opClasspath,cpValor,programas[0]);
        processBuilderPrimero.redirectInput(directorios[0]); // Conectamos la entrada del primero con el primer archivo

        ProcessBuilder processBuilderSegundo = new ProcessBuilder();
        processBuilderSegundo.command(base,opClasspath,cpValor,programas[1]);
        processBuilderSegundo.redirectOutput(directorios[1]); // Conectamos la salida del segundo con el segundo archivo

        // Iniciamos el segundo proceso (escritura en fichero)
        Process segundoProceso = processBuilderSegundo.start();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(segundoProceso.getOutputStream()));


        // Iniciamos el segundo proceso (lectura del fichero)
        Process primerProceso = processBuilderPrimero.start();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(primerProceso.getInputStream()));


        String linea = bufferedReader.readLine();
        while (linea != null){
            // Pasamos la línea al subproceso de escritura
            bufferedWriter.write(linea + SEPARADOR);
            bufferedWriter.flush();

            // Leémos la siguiente línea del subproceso de lectura
            linea = bufferedReader.readLine();
        }

        bufferedWriter.close();
        bufferedReader.close();

        primerProceso.waitFor();
        segundoProceso.waitFor();
    }
}

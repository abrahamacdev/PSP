package tema1.EjemplosParaExamen.pruebaPreExamen;

import java.io.*;

public class Padre {

    public static void main(String[] args){

        Padre padre = new Padre();

        boolean salidaPorTerminal = false;
        boolean environmentModificado = true;

        //Process hijo1 = padre.lanzarHijo(salidaPorTerminal,environmentModificado);
        Process hijo2 = padre.lanzarHijoDos();

        // Por donde leeremos los mensajes del hijo
        //BufferedReader bufferedReaderHijo1 = new BufferedReader(new InputStreamReader(hijo1.getInputStream()));

        // Si no redirigimos la salida del hijo a la pantalla, leeremos los mensajes desde aquí y
        // los mostraremos nosotros directamente
        /*if (hijo1 != null && !salidaPorTerminal){
            System.out.println("Vamos a leer los mensajes que el proceso hijo está enviando");

            try {
                String linea = bufferedReaderHijo1.readLine();

                int recuentoAntiBloqueo = 0;
                while (linea != null){

                    System.out.println("(El hijo ha dicho) -> " + linea);
                    recuentoAntiBloqueo++;

                    if (recuentoAntiBloqueo == 2){
                        break;
                    }

                    linea = bufferedReaderHijo1.readLine();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/




        // Por donde leeremos los mensajes del hijo
        BufferedReader bufferedReaderHijo2 = new BufferedReader(new InputStreamReader(hijo2.getInputStream()));

        // Por donde escribiremos mensajes al hijo
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(hijo2.getOutputStream()));

        // Le pedimos explícitamente que nos envíe un mensaje
        if (hijo2 != null){

            try {
                System.out.println(bufferedReaderHijo2.readLine());

                bufferedWriter.write("1");
                bufferedWriter.newLine();
                bufferedWriter.flush();

                System.out.println(bufferedReaderHijo2.readLine());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Process lanzarHijo(boolean salidaPorTerminal, boolean environmentModificado){

        Process process = null;

        String java = "java";
        String cpCommand = "-cp";
        String cpValor = "E:\\Datos\\2_DAM_A_NO_SYNC\\PSP\\out\\production\\classes";
        String clase = "tema1.EjemplosParaExamen.pruebaPreExamen.Hijo";
        String args = "";

        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command(java,cpCommand,cpValor,clase,args);

        // Permitimos que el hijo muestre su salida por pantalla
        if (salidaPorTerminal){
            processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        }
        else {
            processBuilder.redirectOutput(ProcessBuilder.Redirect.PIPE);
        }

        // Variable de entorno que le añadiremos al hijo
        if (environmentModificado){
            processBuilder.environment().put("Ejemplo","Esto solo es un ejemplo de variable de entorno");
        }

        try {
            process = processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (process != null){
            System.out.println("Se ha lanzado con éxito un hijo");
        }

        return process;
    }

    private Process lanzarHijoDos(){

        Process process = null;

        String java = "java";
        String cpCommand = "-cp";
        String cpValor = "E:\\Datos\\2_DAM_A_NO_SYNC\\PSP\\out\\production\\classes";
        String clase = "tema1.EjemplosParaExamen.pruebaPreExamen.HijoDos";
        String args = "";

        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command(java,cpCommand,cpValor,clase,args);

        try {
            process = processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return process;
    }
}

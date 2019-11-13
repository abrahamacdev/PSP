package tema1.EjemplosParaExamen;

import java.io.File;

public class EjemplosSueltos {

    public static void main(String[] args){

        // Carácter separador de líneas
        String separador = System.getenv("line.separator");




        // Obtiene la instancia del tiempo de ejecución
        Runtime runtime = Runtime.getRuntime();

        // Valor de -Xmx. Incluye: memoria usada, memoria libre(próxima a usar) y memoria no alocada(se cogerá en el futuro)
        long memoriaMax = runtime.maxMemory();

        // Incluye: memoria usada y memoria libre(próxima a usar)
        long memoriaUsada = runtime.totalMemory();

        // Incluye: memoria libre(próxima a usar)
        long memoriaLibre = runtime.freeMemory();

        // Memoria que realmente se está usando actualmente (no tiene en cuenta la memoria libre que se usará próximamente)
        long verdaderaMemoriaUsandoseActualmente = runtime.totalMemory() - runtime.freeMemory();




        /* Sobre ProcessBuilder.class */
        ProcessBuilder processBuilder = new ProcessBuilder();

        // Retorna las variables de entorno que se le pasarán al proceso. Podemos añadir o eliminar del map
        processBuilder.environment();

        // Redireccionamiento de la entrada/salida estándar del hijo
        processBuilder.redirectInput(ProcessBuilder.Redirect.PIPE); // La entrada estándar del proceso será lo que le pase el padre explicitamente por un stream
        processBuilder.redirectInput(new File("")); // La entrada estándar del proceso será el archivo

        processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT); // La salida estándar del hijo será la misma que la del padre (pantalla)
        processBuilder.redirectOutput(ProcessBuilder.Redirect.appendTo(new File(""))); // La salida estándar del hijo  será un archivo al que se irá escribiendo la informacion
        /* -------------------------- */
    }
}

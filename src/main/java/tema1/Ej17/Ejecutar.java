package tema1.Ej17;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejecutar {

    // Comprueba que que exista este patrón una o mas veces:
    // -p Palabra -d /directorio1/de/ejemplo /directorio2/de/ejemplo
    //private String PATRON_COMIENZO_DICCIONARIO = "^-p\\s(?:\\w+)\\s-d\\s(((\\/|\\\\)\\w+(|(\\/|\\\\)\\w+)*)(|\\s)((\\/|\\\\)\\w+(|(\\/|\\\\)\\w+)*))+(\\s-p\\s(?:\\w+)\\s-d\\s(((\\/|\\\\)\\w+(|(\\/|\\\\)\\w+)*)(|\\s)((\\/|\\\\)\\w+(|(\\/|\\\\)\\w+)*))+)*";

    private String rutaArchivoSalida;

    public static void main(String[] args) throws IOException, InterruptedException {


        Ejecutar ejecutar = new Ejecutar();
        ejecutar.iniciar(String.join(" ", args));

    }

    private void iniciar(String args) throws IOException, InterruptedException {

        HashMap<String, String[]> diccionariosSegunPalabra = extraerDiccionariosPorPalabras(args);

        // Map de Diccionario-Lista de palabras a buscar en el diccionario
        HashMap<String, ArrayList<String>> palabrasSegunDiccionario = new HashMap<>();


        for (String palabra : diccionariosSegunPalabra.keySet()){

            for (String diccionario : diccionariosSegunPalabra.get(palabra)){

                // Comprobamos si el diccionario está en el map
                if (palabrasSegunDiccionario.containsKey(diccionario)){
                    ArrayList<String> palabras = palabrasSegunDiccionario.get(diccionario);

                    // Añadimos la palabra a la lista
                    palabras.add(palabra);
                    palabrasSegunDiccionario.replace(diccionario,palabras);
                }

                else {

                    ArrayList<String> nuevaLista = new ArrayList<>();
                    nuevaLista.add(palabra);

                    palabrasSegunDiccionario.put(diccionario,nuevaLista);
                }
            }
        }

        // TODO Depuración
        //mostrarMapDiccionarioPalabras(palabrasSegunDiccionario);

        String base = "java";
        String cpOpcion = "-cp";
        String cpValor = "E:\\Datos\\2_DAM_A_NO_SYNC\\PSP\\out\\production\\classes";
        String buscador = "tema1.Ej17.Buscador";

        ArrayList<Process> listaProcesos = new ArrayList<>(palabrasSegunDiccionario.keySet().size());

        for (String diccionario : palabrasSegunDiccionario.keySet()){

            String argumentos = diccionario + " _|-";
            for (String palabra : palabrasSegunDiccionario.get(diccionario)){
                argumentos += " " + palabra;
            }

            // Iniciamos el proceso y esperamos a que nos devuelva el recuento
            ProcessBuilder processBuilderPrimero = new ProcessBuilder();
            processBuilderPrimero.command(base,cpOpcion,cpValor,buscador, argumentos);
            //processBuilderPrimero.redirectOutput(ProcessBuilder.Redirect.INHERIT);

            // Iniciamos el proceso y lo guardamos en la lista
            listaProcesos.add(processBuilderPrimero.start());
        }

        for (Process process : listaProcesos){

            DataInputStream dataInputStream = new DataInputStream(process.getInputStream());
            int cantARecibir = dataInputStream.readInt();

            for (int i=0; i<cantARecibir; i++){

                String linea = dataInputStream.readLine();
                System.out.println(linea.substring(2,linea.length()));
            }
        }

    }

    private HashMap<String, String[]> extraerDiccionariosPorPalabras(String args){

        HashMap<String,String[]> diccionariosPorPalabras =  new HashMap();

        Pattern archivoSalida = Pattern.compile("-r\\s(\\/|[A-Z]:\\\\)\\w+((\\/|\\\\)\\w+)*\\w+\\.[A-z]+");
        Pattern palabraBuscar = Pattern.compile("^\\w+\\s");
        //Pattern directorios = Pattern.compile("^(\\.\\.\\/(?:\\.\\.\\/)*)?(?!.*?\\/\\/)(?!(?:.*\\/)?\\.+(?:\\/|$)).+$");
        //Pattern directorios = Pattern.compile("^(\\/|[A-Z]:\\\\)\\w+((\\/|\\\\)(\\w+)*)*");
        Pattern archivos = Pattern.compile("^(\\/|[A-Z]:\\\\)\\w+((\\/|\\\\)\\w+)*\\w+\\.[A-z]+");

        String[] cachos = args.split("-p ");

        // Cada bloque de "-p Palabra -d #rutas"
        String[] sinPrimero = new String[cachos.length-1];

        for (int i=1; i<cachos.length; i++){

            String cacho = cachos[i];
            Matcher matcher = archivoSalida.matcher(cacho);

            // Comprobamos si es la ruta de salida
            if (matcher.find()){
                String[] temp = cacho.split(" -r ");
                sinPrimero[i-1] = temp[0];

                // Comprobamos que el archivo de salida sea válido
                Matcher matcherArchivoSalida = archivos.matcher(temp[1]);

                if (matcherArchivoSalida.find()){
                    rutaArchivoSalida = temp[1];
                }
            }
            else {
                sinPrimero[i-1] = cachos[i];
            }
        }

        // Comprobamos si se ha establecido ya algún archivo de salida
        if (rutaArchivoSalida == null || rutaArchivoSalida.length() == 0){
            System.out.println("El archivo de destino especificado no es válido");
        }

        // Recorremos cada bloque
        for (String bloque : sinPrimero){

            String palabra = null;
            String[] diccionarios = new String[0];

            Matcher palabraABuscar = palabraBuscar.matcher(bloque);

            if (palabraABuscar.find()){
                // Separamos la palabra de los diccionarios
                String[] temp = bloque.split(" -d ");
                palabra = temp[0];

                // Comprobamos que estuviese la opción -d
                if (temp.length > 1){

                    String[] tempDiccionarios = temp[1].split(" ");
                    diccionarios = new String[tempDiccionarios.length];

                    for (int j = 0; j<tempDiccionarios.length; j++){

                        // Comprobamos que la string sea una ruta de un archivo
                        Matcher matcherDiccionario = archivos.matcher(tempDiccionarios[j]);

                        if (matcherDiccionario.find()){
                            diccionarios[j] = tempDiccionarios[j];
                        }
                    }
                }
                else {
                    System.out.println("Después del comando \"-p \'palabra\' debe de ir -d y una lista de rutas. Ejemplo: -p Patata -d /home/ejemplo /otro/ejemplo");
                }
            }

            // Guardamos todos los diccionarios asociados a sus respectivas palabras
            diccionariosPorPalabras.put(palabra,diccionarios);

        }

        return diccionariosPorPalabras;
    }

    private void mostrarMapDiccionarioPalabras(HashMap<String, ArrayList<String>> palabrasSegunDiccionario){

        for (String diccionario : palabrasSegunDiccionario.keySet()){

            System.out.println("Diccionario - " + diccionario);
            System.out.println(Arrays.toString(palabrasSegunDiccionario.get(diccionario).toArray()));
        }
    }
}

package tema1.Ej5;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Borrador {

    private static String AYUDA = "El programa nececesita 2 argumentos: un nombre de archivo y una ruta";
    private static String ARCHIVO_NO_VALIDO = "El archivo que se ha pasado por parámetro no es válido";
    private static String RUTA_NO_VALIDA = "La ruta que se ha pasado por parámetro no es válida";

    public static void main(String[] args) throws Exception {

        if (args.length == 2){

            File archivo = new File(args[0]);
            File dir = new File(args[1]);

            if (!archivo.isDirectory()){
                if (dir.exists() && dir.isDirectory()){
                    // Lanzamos el borrado
                    new Borrador().eliminarArchivo(archivo,dir);
                }
                else {
                    throw new Exception(RUTA_NO_VALIDA);
                }
            }
            else {
                throw new Exception(ARCHIVO_NO_VALIDO);
            }
        }

        else {
            throw new Exception(AYUDA);
        }
    }

    private void eliminarArchivo(File archivo, File dir){

        for (File temp : dir.listFiles()){
            if (temp.isDirectory()){
                eliminarArchivo(archivo,temp);
            }
            else {
                if (temp.getName().equals(archivo.getName())){
                    temp.delete();
                }
            }
        }
    }
}

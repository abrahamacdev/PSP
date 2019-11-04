package tema1.accesoDatos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ej9_a {

    static String RUTA_ARCHIVO = "E:\\Datos\\2_DAM_A\\Acceso_A_Datos\\Tema_1\\ListaDeClase.txt";

    public static void main(String[] args) throws IOException {

        String nombre;
        String primerApellido, segundoApellido;

        Scanner scanner = new Scanner(System.in);

        boolean valido = false;
        while (!valido){

            System.out.println("Introduce el nombre del alumno");
            nombre = scanner.nextLine();

            System.out.println("Introduce el primer apellido del alumno");
            primerApellido = scanner.nextLine();

            System.out.println("Introduce el segundo apellido del alumno");
            segundoApellido = scanner.nextLine();

            if (comprobarValidezDatos(nombre) && comprobarValidezDatos(primerApellido) && comprobarValidezDatos(segundoApellido)){
                escribirInformacion(nombre, primerApellido, segundoApellido);
                valido = true;
            }

            if (!valido){
                System.out.println("Por favor, introduzca bien los datos");
            }
        }
    }

    public static void escribirInformacion(String nombre, String primerApellido, String segundoApellido) throws IOException {

        File file = new File(RUTA_ARCHIVO);

        if (file.exists() && file.isFile()){
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file,true));
            bufferedWriter.write(nombre + "-" + primerApellido + "-" + segundoApellido + System.getProperty("line.separator"));
            bufferedWriter.close();
        }
    }

    public static boolean comprobarValidezDatos(String cadena){
        Pattern p = Pattern.compile("^[A-z]+$");
        Matcher matcher = p.matcher(cadena);
        return matcher.matches();
    }
}

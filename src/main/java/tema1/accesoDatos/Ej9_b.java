package tema1.accesoDatos;

import java.io.*;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ej9_b {

    static String RUTA_ARCHIVO = "E:\\Datos\\2_DAM_A\\Acceso_A_Datos\\Tema_1\\ListaDeClase.txt";

    public static void main(String[] args) throws IOException {

        String nombre;

        Scanner scanner = new Scanner(System.in);

        boolean valido = false;
        while (!valido){

            System.out.println("Introduce el nombre del alumno");
            nombre = scanner.nextLine();

            if (comprobarValidezDatos(nombre)){
                TreeSet<String> nombresAlumnos = obtenerNombresAlumnos();
                nombresAlumnos.add(nombre);
                escribirInformacion(nombresAlumnos);
                valido = true;
            }

            if (!valido){
                System.out.println("Por favor, introduzca bien los datos");
            }
        }
    }

    public static TreeSet<String> obtenerNombresAlumnos() throws IOException {

        File file = new File(RUTA_ARCHIVO);

        TreeSet<String> nombresAlumnos = new TreeSet<>();

        if (file.exists() && file.isFile()){

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            String linea;
            while ((linea = bufferedReader.readLine()) != null){
                nombresAlumnos.add(linea);
            }

            bufferedReader.close();
        }

        return nombresAlumnos;
    }

    public static void escribirInformacion(TreeSet<String> demasAlumnos) throws IOException {

        File file = new File(RUTA_ARCHIVO);

        if (file.exists() && file.isFile()){
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

            if (demasAlumnos != null){
                for (String alumno : demasAlumnos){
                    bufferedWriter.write(alumno + System.getProperty("line.separator"));
                }
            }
            bufferedWriter.close();
        }
    }

    public static boolean comprobarValidezDatos(String cadena){
        Pattern p = Pattern.compile("^[A-z]+$");
        Matcher matcher = p.matcher(cadena);
        return matcher.matches();
    }

}

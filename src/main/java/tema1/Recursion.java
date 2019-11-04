package tema1;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class Recursion {

    public static void main(String[] args){

        File f = new File("E:\\Datos\\Animaciones");
        Recursion r = new Recursion();
        r.mostrarArchivosConRecursion(f);

    }

    public void mostrarArchivosConRecursion(File f){

        ArrayList<File> archivos = new ArrayList<>();
        archivos = archivosDelDirectorio(archivos, f);

        for (File temp : archivos){
            System.out.println(temp.getName());
        }
    }

    public ArrayList<File> archivosDelDirectorio(ArrayList<File> almacenados, File f){

        if(f.isDirectory() && f.canRead()){
            for (File temp : f.listFiles()){
                archivosDelDirectorio(almacenados,temp);
            }
        }

        else {
            almacenados.add(f);
        }
        return almacenados;
    }
}

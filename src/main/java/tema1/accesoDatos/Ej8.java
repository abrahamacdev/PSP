package tema1.accesoDatos;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Ej8 {

    public static void main(String[] args) throws Exception {

        Ej8 ej8 = new Ej8();

        if (!ej8.comprobarArgumentos(args)){
            throw new Exception("Los parámetros pasados no son válidos");
        }

        FileReader fileReader = new FileReader(new File(args[0]));
        FileWriter fileWriter = new FileWriter(new File(args[1]));

        int num;

        while ((num = fileReader.read()) != -1){
            fileWriter.write(num);
        }

        fileReader.close();
        fileWriter.close();
    }

    public boolean comprobarArgumentos(String[] args){

        if (args.length == 2){

            File archivo1 = new File(args[0]);
            File archivo2 = new File(args[1]);

            return archivo1.exists() && archivo1.isFile() && archivo2.exists() && archivo2.isFile();
        }
        return false;
    }
}

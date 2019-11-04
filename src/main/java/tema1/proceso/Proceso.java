package tema1.proceso;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Proceso {

    public static void main(String[] args){

        try {

            Process p = Runtime.getRuntime().exec(new String[]{"bash", "-c","ls /home/abraham/Documentos/KScrapPlugins/"});

            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            String linea;
            while ((linea = input.readLine()) != null){
                System.out.println(linea);
            }

            String errorLinea;
            while ((errorLinea = input.readLine()) != null){
                System.out.println(errorLinea);
            }


        }catch (Exception e){
            System.out.println("Ha ocurrido un error inesperado");
        }
    }
}

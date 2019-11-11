package tema1.Ej17;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Buscador {

    public static void main(String[] args){

        //System.out.println("Hemos iniciado un buscador y tenemos como argumentos: " + Arrays.toString(args));

        String salto = System.getProperty("line.separator");

        Buscador buscador = new Buscador();

        HashMap<String, Par> datos = buscador.buscarDatos(String.join(" ",args));

        DataOutputStream dataOutputStream = new DataOutputStream(System.out);

        try {
            dataOutputStream.writeInt(datos.keySet().size());

            for (String palabra : datos.keySet()) {

                Par recuento = datos.get(palabra);

                // Pasamos el resultado al padre
                dataOutputStream.writeUTF(palabra + ": " + recuento.getPrimero() + "," + recuento.getSegundo() + salto);
                dataOutputStream.flush();
            }

            dataOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, Par> buscarDatos(String args){

        HashMap<String, Par> recuento = new HashMap<>();
        //HashMap<Integer, Integer> recuento = new HashMap<>();

        String[] spliteados = args.split("_\\|-");

        String rutaArchivoBuscar = spliteados[0];
        File archivoDiccionario = new File(rutaArchivoBuscar);

        // La ruta del diccionario no es válida
        if (archivoDiccionario.isDirectory() || !archivoDiccionario.exists()){
            return recuento;
        }

        // Poblamos los datos de los maps
        String[] palabras = spliteados[1].split(" ");
        ArrayList<String> palabrasABuscar = new ArrayList<String>(Arrays.asList(Arrays.copyOfRange(palabras, 1, palabras.length)));
        LinkedList<Pattern> patronesPalabrasABuscar = new LinkedList<>();
        for (String aBuscar : palabrasABuscar){
            patronesPalabrasABuscar.add(Pattern.compile(new String("\\b" + aBuscar + "\\b")));
            recuento.put(aBuscar, new Par(0,0));
        }

        BufferedReader bufferedReader;

        try {

            bufferedReader = new BufferedReader(new FileReader(archivoDiccionario));

            Pattern comienzoDefinicion = Pattern.compile("^[0-9]");
            Pattern comienzoPalabraADefinir = Pattern.compile("^(?![0-9]+\\.).*");

            String linea = bufferedReader.readLine();

            while (linea != null){

                Matcher esComienzoDefinicion = comienzoDefinicion.matcher(linea);
                Matcher esComienzoPalabraDefinir = comienzoPalabraADefinir.matcher(linea);
                HashMap<String, Integer> tempRecuento = new HashMap<>();

                for (int i = 0; i<palabrasABuscar.size(); i++){
                    String aBuscar = palabrasABuscar.get(i);

                    // Obtenemos un "matcher" que nos permita buscar el número de coincidencias de la palabra en
                    // la línea
                    Pattern patronPalabra = patronesPalabrasABuscar.get(i);
                    Matcher tempMatcher = patronPalabra.matcher(linea);

                    int cuenta = 0;
                    int lastIndex = 0;

                    // Cada vez que encontremos la palabra en el texto, sumamos la cuenta de vecez encontradas
                    // y seguimos buscando
                    while (tempMatcher.find(lastIndex)){

                        cuenta += 1;
                        lastIndex = tempMatcher.start() + 1;

                    }

                    if (tempRecuento.containsKey(aBuscar)){
                        int temp = tempRecuento.get(aBuscar);
                        tempRecuento.replace(aBuscar, temp + 1);
                    }

                    else {
                        tempRecuento.put(aBuscar, cuenta);
                    }

                    // Guardamos el número de coindicencias de la palabra en la línea
                    tempRecuento.put(aBuscar, cuenta);
                }

                if (linea.length() > 1){
                    // Es el comienzo de una palabra a definir, comprobamos si es alguna de las palabras que buscámos
                    if (esComienzoPalabraDefinir.find()){
                        recuento = anadirRecuento(tempRecuento, recuento, true);
                    }

                    // Es la definición de la palabra
                    else if (esComienzoDefinicion.find()){

                        recuento = anadirRecuento(tempRecuento, recuento, false);
                    }
                }

                linea = bufferedReader.readLine();
            }

            bufferedReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return recuento;
    }

    private HashMap<String, Par> anadirRecuento(HashMap<String, Integer> tempRecuento, HashMap<String, Par> recuentoTotal, boolean esComienzo){

        // Hay que sumarlo al "lado izquierdo"
        if (esComienzo){

            for (String palabra : tempRecuento.keySet()){

                int temp = tempRecuento.get(palabra);

                int actualIzquierda = recuentoTotal.get(palabra).getPrimero();
                int actualDerecha = recuentoTotal.get(palabra).getSegundo();
                int nuevaIzquierda = actualIzquierda + temp;

                recuentoTotal.replace(palabra,new Par(nuevaIzquierda,actualDerecha));
            }
        }

        else {

            for (String palabra : tempRecuento.keySet()){

                int temp = tempRecuento.get(palabra);

                int actualIzquierda = recuentoTotal.get(palabra).getPrimero();
                int actualDerecha = recuentoTotal.get(palabra).getSegundo();
                int nuevaDerecha = actualDerecha + tempRecuento.get(palabra);

                recuentoTotal.replace(palabra,new Par(actualIzquierda,nuevaDerecha));
            }
        }

        return recuentoTotal;
    }
}

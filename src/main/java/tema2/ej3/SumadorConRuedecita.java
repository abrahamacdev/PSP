package tema2.ej3;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * La solución propuesta en este ejercicio NO DEBE DE IMPLEMENTARSE EN UNA SOLUCIÓN REAL porque crear una "espera activa"
 * El hilo main está en un bucle continuo que no acaba hasta que se cumple la condición, esto provoca un alto uso de la
 * CPU con el consecuente drenado de rendimiento que conlleva.
 * Una solución más correcta sería crear un hilo demonio que estuviese mostrando el mensaje "Calculando #barrita", de esta forma
 * el hilo principal no estaría comprobando continuamente que todos los hilos hallan completado el cálculo.
 */
public class SumadorConRuedecita {

    public static void main(String[] args){

        HashMap<Long, Integer> hiloConRespuesta = new HashMap<>();
        ArrayList<Long> terminados = new ArrayList<>();

        Thread padre = Thread.currentThread();

        for (String arg : args){

            try {

                int number = Integer.parseInt(arg);
                SumadorThread sumadorThread = new SumadorThread(number, new OnNumberCalculatedListener() {
                    @Override
                    public void onNewNumberCalculated(int number, long idHilo) {
                        hiloConRespuesta.replace(idHilo,number);
                        terminados.add(idHilo);

                    }
                });

                // Guardamos el hilo
                hiloConRespuesta.put(sumadorThread.getId(),-1);

                // Lanzamos el hilo
                sumadorThread.start();
            }catch (NumberFormatException e){
                e.printStackTrace();
            }
        }

        String[] chars = new String[]{"\\","|","/","-"};
        int i = 0;
        // Mientras no hallan acabado de ejecutarse, esperamos
        while (terminados.size() != hiloConRespuesta.size()){

            try {
                Thread.sleep(100);

                String msg = "Calculando " + chars[i] + "\r";
                System.out.print(msg);
                System.out.flush();

                i++;

                if (i == chars.length){
                    i = 0;
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (long id : hiloConRespuesta.keySet()){
            System.out.println("La respuesta del hilo con id: \"" + id + "\" es " + hiloConRespuesta.get(id));
        }
    }
}

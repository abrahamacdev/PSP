package tema2.ej3;

import java.util.ArrayList;
import java.util.HashMap;

public class SumadorConRuedecita {

    public static void main(String[] args){

        HashMap<Long, Integer> hiloConRespuesta = new HashMap<>();
        ArrayList<Long> terminados = new ArrayList<>();

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

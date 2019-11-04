package tema2.ej3;

interface OnNumberCalculatedListener {

    void onNewNumberCalculated(int number, long idHilo);
}

public class SumadorThread extends Thread{

    private int num;
    private OnNumberCalculatedListener onNumberCalculatedListener;

    public SumadorThread(int num, OnNumberCalculatedListener onNumberCalculatedListener){
        this.num = num;
        this.onNumberCalculatedListener = onNumberCalculatedListener;
    }

    @Override
    public void run() {

        int number = 0;

        for (int i=0; i<num; i++){
            number += Math.sqrt(i);
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        onNumberCalculatedListener.onNewNumberCalculated(number, this.getId());
    }
}

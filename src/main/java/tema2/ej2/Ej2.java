package tema2.ej2;

public class Ej2 {

    public static void main(String[] args){

        for (String arg : args){

            try {

                int num = Integer.valueOf(arg);
                IParImpar anonima = new IParImpar() {

                    private int numAComprobar = -1;
                    private boolean establecido = false;

                    @Override
                    public void setNumeroAComprobar(int numeroAComprobar) {
                        establecido = true;
                        this.numAComprobar = numeroAComprobar;
                    }

                    @Override
                    public void run() {

                        if (establecido){
                            if (numAComprobar % 2 == 0){
                                System.out.println("El número " + numAComprobar + " es par");
                            }
                            else {
                                System.out.println("El número " + numAComprobar + " es impar" );
                            }
                        }
                    }
                };

                anonima.setNumeroAComprobar(num);
                anonima.run();

            }catch (NumberFormatException e){
                e.printStackTrace();
            }
        }
    }
}

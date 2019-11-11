package tema1.Ej17;

public class Par {

    private int primero;
    private int segundo;

    public Par(int primero, int segundo){
        this.primero = primero;
        this.segundo = segundo;
    }

    public int getPrimero() {
        return primero;
    }

    public int getSegundo() {
        return segundo;
    }

    @Override
    public String toString() {
        return "La palabra se ha encontrado " + primero + " veces antes antes de ser definida y ha aparecido en " + segundo + " definiciones";
    }
}

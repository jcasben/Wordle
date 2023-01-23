package wordle;

public class Main {

    private void Start(){
        Menu menu = new Menu();
        Juego wordle = new Juego(menu);
        wordle.iniciar();
    }

    public static void main(String[] args) {
        (new Main()).Start();
    }
}

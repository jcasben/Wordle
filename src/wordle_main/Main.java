package wordle_main;

public class Main {

    private void Wordle() {
        boolean jugar = true;
        Juego wordle = new Juego();
        Menu menu = new Menu(wordle);
        while(jugar){
            menu.menuPrincipal();
            char opcion = wordle.pedirEntradaOpciones("1234S".toCharArray(), "Opcion: ");
            switch (opcion){

                case '1':
                    wordle.pedirJugador();
                    wordle.pedirIdioma();
                    //menu.menuPreJuego();
                    menu.iniciarJuego();
                    break;

                case '2':
                    //menu.menuEstadisticasGenerales();
                    break;

                case '3':
                    //menu.menuEstadisticasPersonales();
                    break;

                case '4':
                    boolean config = true;
                    while(config){
                        menu.menuConfiguracion();
                        char opconfig = wordle.pedirEntradaOpciones("12S".toCharArray(), "Opcion: ");
                        switch (opconfig){
                            case '1':
                                int[] ops = {1,2,3,4,5,6,7,8,9};
                                int turnos = wordle.pedirEntradaOpcionesInt(ops, "Número de turnos de la partida (1-9): ");
                                wordle.setNumTurnos(turnos);
                                break;
                            case '2':
                                int[] ops2 = {3,4,5,6,7,8};
                                int letras = wordle.pedirEntradaOpcionesInt(ops2, "Número de letras de la palabra (3-8): ");
                                wordle.setTamPalabra(letras);
                                break;
                            case 'S':
                                config = false;
                                break;
                        }
                    }
                    break;

                case 'S':
                    jugar = false;
                    break;
            }
        }
    }

    public static void main(String[] args) {

        (new Main()).Wordle();
    }
}

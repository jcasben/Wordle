/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wordle_main;

/**
 *
 * @author jcasb
 */
public class Main {

     Juego wordle = new Juego();
    Menu menu = new Menu(wordle);
    private void Start() {
        System.out.println("\n                  ¡B I E N V E N I D O   A   W O R D L E!                  \n");
        menu.menuPrincipal();
        char opcion = wordle.pedirEntradaOpciones("1234S".toCharArray());
        elegirOpcion(opcion);
    }

    public void elegirOpcion(char opcion){

        switch (opcion){

            case '1':
                menu.pedirJugador();
                menu.pedirIdioma();
                menu.menuPreJuego();
                menu.menuJuego();
                boolean comp = true;
                for (int i = 0; i < menu.getNumeroTurnos(); i++) {
                    Palabra actual = wordle.pedirEntradaPalabra();
                    menu.actualizarTeclado(actual);
                    comp = wordle.comprobarPalabra();
                    menu.addTurno();
                }
                menu.darResultado(comp);
                break;

            case '2':
                menu.menuEstadisticasGenerales();
                break;

            case '3':
                menu.menuEstadisticasPersonales();
                break;

            case '4':
                menu.menuConfiguracion();
                break;

            case 'S':
                break;

        }

    }

    public static void main(String[] args) {
        (new Main()).Start();
    }
}

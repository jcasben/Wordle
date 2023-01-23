package wordle;

import com.diogonunes.CC;

public class Menu {

    // ------------------------------ MÉTODOS MENU ------------------------------
    public void menuPreJuego() {

        System.out.println("\n\n\n");
        System.out.println("                                PREPARADOS...");
        esperar(1500);
        System.out.println("\n                                 LISTOS...");
        esperar(1500);
        System.out.println("\n                                 ¡WORDLE!\n\n\n");
    }

    //Imprime el menu principal del Wordle.
    public void menuPrincipal() {

        System.out.print("          ");
        CC.imprln("              M E N Ú   P R I N C I P A L              ", CC.TBlau, CC.FGroc);
        imprLiniaVacia();
        imprLiniaMenu("        1 - Jugar                                ");
        imprLiniaMenu("        2 - Estadísticas generales               ");
        imprLiniaMenu("        3 - Estadísticas específicas             ");
        imprLiniaMenu("        4 - Opciones del juego                   ");
        imprLiniaMenu("        s - Salir                                ");
        imprFinalMenu();
    }
    
    //Imprime el menu de las configuraciones.
    public void menuConfiguracion() {

        System.out.print("          ");
        CC.imprln("     C O N F I G U R A C I Ó N   D E L   J U E G O     ", CC.TBlau, CC.FGroc);
        imprLiniaVacia();
        imprLiniaMenu("        1 - Turnos de la partida                 ");
        imprLiniaMenu("        2 - Número de letras de la palabra       ");
        imprLiniaMenu("        S - Volver                               ");
        imprFinalMenu();
    }

    //Imprime parte del menu en partida, que será completado con el método comprobar() de la clase Juego.
    public void menuJuego(Palabra idioma, Palabra jugador) {

        System.out.print("          ");
        System.out.print("IDIOMA: ");
        idioma.imprimir_p();
        System.out.print("          ");
        System.out.print("JUGADOR: ");
        jugador.imprimir_p();
        System.out.println();
        System.out.print("          ");
        CC.imprln("                                                       ", "", CC.FGroc);
        imprLiniaVacia();
    }
    
    //Imprime el menu en partida vacío.
    public void menuJuego(Palabra idioma, Palabra jugador, int turnos, int tam_palabra) {

        System.out.print("          ");
        System.out.print("IDIOMA: ");
        idioma.imprimir_p();
        System.out.print("          ");
        System.out.print("JUGADOR: ");
        jugador.imprimir_p();
        System.out.println();
        System.out.print("          ");
        CC.imprln("                                                       ", "", CC.FGroc);
        imprLiniaVacia();
        for(int i = 0; i < (turnos); i++){
            System.out.print("          ");
            CC.impr("   ", "", CC.FGroc);
            imprEspaciosInter(tam_palabra);
            for(int j = 0; j < tam_palabra; j++){

                System.out.print("_ ");
            }

            imprEspaciosInter(tam_palabra + 1);
            CC.impr("   ", "", CC.FGroc);
            System.out.println();
        }
        menuJuegoF();
    }

    //Imprime la parte final del menu de la partida.
    public void menuJuegoF() {
        imprLiniaVacia();
        imprFinalMenu();
    }

    //Imprime una linia del menu con la frase indicada.
    public void imprLiniaMenu(String frase) {

        System.out.print("          ");
        CC.impr("   ", "", CC.FGroc);
        System.out.print(frase);
        CC.imprln("   ", "", CC.FGroc);
    }

    //Imprime una linia del menu vacia
    private void imprLiniaVacia() {

        System.out.print("          ");
        CC.impr("   ", "", CC.FGroc);
        System.out.print("                                                 ");
        CC.imprln("   ", "", CC.FGroc);
    }

    //Imprime parte del final del menu.
    private void imprFinalMenu() {

        imprLiniaVacia();
        System.out.print("          ");
        CC.imprln("                                                       ", "", CC.FGroc);
        System.out.println();
    }

    //Imprime los espacios que hay entre la palabra y los bordes del menu en el menu de la partida para que siempre quede bien colocado.
    public void imprEspaciosInter(int tamPalabra) {

        int espacios = 25 - tamPalabra;
                
        for (int i = 0; i < espacios; i++) {

            System.out.print(" ");
        }
    }

    public void esperar(int millis) {

        try {
            Thread.sleep((long) millis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

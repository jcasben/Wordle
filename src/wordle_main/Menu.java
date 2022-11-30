/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wordle_main;

/*
 *
 * @author jcasb
 */
import com.diogonunes.CC;


public class Menu {
    public Menu(Juego j){
        
        jg = j;
    }
    private Juego jg;
    private Palabra usuario = new Palabra();
    private Palabra idioma = new Palabra();
    private int numero_de_turnos = 5;
    private int turnos_jugados = 0;
    private String color_fons_menu = "43";
    private String color_letra_menu = "34";

    private char[] letras_teclado_qp = "Q0W0E0R0T0Y0U0I0O0P0".toCharArray();
    private char[] letras_teclado_al = "A0S0D0F0G0H0J0K0L0Ñ0Ç0".toCharArray();
    private char[] letras_teclado_zm = "Z0X0C0V0B0N0M0".toCharArray();

    // -------------------- GETTERS -------------------------
    public int getNumeroTurnos(){

        return numero_de_turnos;
    }

    //  -------------------- SETTERS -------------------------

    public void setNumeroTurnos(int numero_de_turnos){

        this.numero_de_turnos = numero_de_turnos;
    }

    public void addTurno(){

        turnos_jugados++;
    }

    // -------------------- IMPRIMIR LOS MENUS EN PANTALLA -------------------------
    public void menuPrincipal(){

        System.out.print("          ");
        CC.imprln("              M E N Ú   P R I N C I P A L              ", color_letra_menu, color_fons_menu);
        imprLiniaVacia();
        imprLiniaMenu("        1 - Jugar                                ");
        imprLiniaMenu("        2 - Estadísticas generales               ");
        imprLiniaMenu("        3 - Estadísticas específicas             ");
        imprLiniaMenu("        4 - Opciones del juego                   ");
        imprLiniaMenu("        s - Salir                                ");
        imprFinalMenu();
    }

    public void menuJuego(){

        System.out.print("          ");
        System.out.print("IDIOMA: ");
        idioma.imprimir_p();
        System.out.print("          ");
        System.out.print("JUGADOR: ");
        usuario.imprimir_p();
        System.out.println();
        System.out.print("          ");
        CC.imprln("                                                       ", "", color_fons_menu);
        imprLiniaVacia();
        imprimirPalabrasW();
        imprFinalMenu();
        imprTecladoPantalla();
    }

    public void menuPreJuego(){

        System.out.println("\n\n\n");
        System.out.println("                                PREPARADOS...");
        esperar(1500);
        System.out.println("\n                                 LISTOS...");
        esperar(1500);
        System.out.println("\n                                 ¡WORDLE!\n\n\n");
    }

    public void menuEstadisticasGenerales(){

        System.out.print("          ");
        CC.imprln(" M E N Ú   E S T A D Í S T I C A S   G E N E R A L E S ", color_letra_menu, color_fons_menu);
    }

    public void menuEstadisticasPersonales(){

        System.out.print("          ");
        CC.imprln("M E N Ú   E S T A D Í S T I C A S   P E R S O N A L E S", color_letra_menu, color_fons_menu);
    }

    public void menuConfiguracion(){

        System.out.print("          ");
        CC.imprln("     C O N F I G U R A C I Ó N   D E L   J U E G O     ", color_letra_menu, color_fons_menu);
        imprLiniaVacia();
        imprLiniaMenu("        1 - Turnos de la partida                 ");
        imprLiniaMenu("        2 - Número de letras de la palabra       ");
        imprFinalMenu();
    }


    // -------------------- METODOS PARA LOS MENUS --------------------
    private void imprTecladoPantalla(){

        int contador_letras = 0;
        int contador_colores = 1;
        String color_f = "46";

        System.out.print("                            ");

        for(int i = 0;i < (letras_teclado_qp.length)/2;i++){
            switch (letras_teclado_qp[contador_colores]) {
                case '0' -> color_f = "46";
                case '1' -> color_f = "47";
                case '2' -> color_f = "42";
            }
            Character letra_imp = letras_teclado_qp[contador_letras];
            CC.impr(letra_imp.toString(), "30", color_f);
            color_f = "46";
            System.out.print(" ");
            contador_colores +=2;
            contador_letras +=2;
        }

        System.out.println();
        contador_letras = 0;
        contador_colores = 1;
        System.out.print("                          ");

        for(int i = 0;i < (letras_teclado_al.length)/2;i++){
            switch (letras_teclado_al[contador_colores]) {
                case '0' -> color_f = "46";
                case '1' -> color_f = "47";
                case '2' -> color_f = "42";
            }
            Character letra_imp = letras_teclado_al[contador_letras];
            CC.impr(letra_imp.toString(), "30", color_f);
            color_f = "46";
            System.out.print(" ");
            contador_colores +=2;
            contador_letras +=2;
        }

        System.out.println();
        contador_letras = 0;
        contador_colores = 1;
        System.out.print("                              ");

        for(int i = 0;i < (letras_teclado_zm.length)/2;i++){
            switch (letras_teclado_zm[contador_colores]) {
                case '0' -> color_f = "46";
                case '1' -> color_f = "47";
                case '2' -> color_f = "42";
            }
            Character letra_imp = letras_teclado_zm[contador_letras];
            CC.impr(letra_imp.toString(), "30", color_f);
            color_f = "46";
            System.out.print(" ");
            contador_colores +=2;
            contador_letras +=2;
        }
        System.out.println("\n");
    }

    public void actualizarTeclado(Palabra actual){

        for (int i = 0; i < jg.getTamPalabra(); i++) {//PREGUNTAR PORQUE SALE EL STATIC COMO NECESARIO
            for (int j = 0; j < letras_teclado_qp.length; j+=2) {
                if(actual.get(i) == letras_teclado_qp[j]){
                    letras_teclado_qp[j+1] = '2';
                    break;
                }
            }
            for (int j = 0; j < letras_teclado_al.length; j+=2) {
                if(actual.get(i) == letras_teclado_al[j]){
                    letras_teclado_al[j+1] = '2';
                    break;
                }
            }
            for (int j = 0; j < letras_teclado_zm.length; j+=2) {
                if(actual.get(i) == letras_teclado_zm[j]){
                    letras_teclado_zm[j+1] = '2';
                    break;
                }
            }
        }
        menuJuego();
    }
    private void imprLiniaVacia(){

        System.out.print("          ");
        CC.impr("   ", "", color_fons_menu);
        System.out.print("                                                 ");
        CC.imprln("   ", "", color_fons_menu);
    }

    private void imprLiniaMenu(String frase){

        System.out.print("          ");
        CC.impr("   ", "", color_fons_menu);
        System.out.print(frase);
        CC.imprln("   ", "", color_fons_menu);
    }
    private void imprFinalMenu(){

        imprLiniaVacia();
        System.out.print("          ");
        CC.imprln("                                                       ", "", color_fons_menu);
        System.out.println();
    }

    private void esperar(int millis){

        try {

            Thread.sleep(millis);
        }
        catch (Exception e){

            e.printStackTrace();
        }
    }

    public void pedirJugador(){

        LT lt = new LT();
        System.out.print("Jugador: ");
        char[] jugador = lt.llegirLiniaC();
        usuario = new Palabra(jugador);
    }

    public void pedirIdioma(){

        LT lt =  new LT();
        System.out.print("Idioma: ");
        char[] idiom = lt.llegirLiniaC();
        idioma = new Palabra(idiom);
    }

    public void darResultado(boolean comp){
        //da el resultado de la partida y lo guarda
    }

    public void imprimirPalabrasW(){
        imprLiniaMenu("                    _ _ _ _ _                    ");
        Palabra[] linia = new Palabra[numero_de_turnos];
        if(turnos_jugados == 0) {
            for (int i = 0; i < numero_de_turnos; i++) {
                imprLiniaMenu("                    _ _ _ _ _                    ");
            }
        }
    }
}

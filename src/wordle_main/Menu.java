package wordle_main;

import com.diogonunes.CC;

public class Menu {

    private final String color_fons_menu = "43";

    private final String color_letra_menu = "34";

    private final Juego wrdl;

    private char[] letras_teclado_qp;

    private char[] letras_teclado_al;

    private char[] letras_teclado_zm;

    public Menu(Juego wd){

        wrdl = wd;
    }

    public void iniciarJuego(){
        letras_teclado_qp = "Q0W0E0R0T0Y0U0I0O0P0".toCharArray();
        letras_teclado_al = "A0S0D0F0G0H0J0K0L0Ñ0Ç0".toCharArray();
        letras_teclado_zm = "Z0X0C0V0B0N0M0".toCharArray();
        char[] letras_temporal;
        letras_temporal = new char[(wrdl.getTamPalabra()*2)];
        boolean partida = true;
        wrdl.sacarObjetivo();
        int con = 0;
        while(partida){
            wrdl.pedirEntradaPalabra();
            menuJuego();
            letras_temporal = wrdl.actualizar();
            actualizarTeclado(letras_temporal);
            if(letras_temporal[1] != '5'){
                con++;
            } else {
                partida = false;
            }
            if(con == wrdl.getNumeroDeTurnos()){
                partida = false;
            }
        }
    }

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

    public void menuPreJuego(){

        System.out.println("\n\n\n");
        System.out.println("                                PREPARADOS...");
        esperar(1500);
        System.out.println("\n                                 LISTOS...");
        esperar(1500);
        System.out.println("\n                                 ¡WORDLE!\n\n\n");
    }

    public void menuConfiguracion(){

        System.out.print("          ");
        CC.imprln("     C O N F I G U R A C I Ó N   D E L   J U E G O     ", color_letra_menu, color_fons_menu);
        imprLiniaVacia();
        imprLiniaMenu("        1 - Turnos de la partida                 ");
        imprLiniaMenu("        2 - Número de letras de la palabra       ");
        imprLiniaMenu("        S - Volver                               ");
        imprFinalMenu();
    }

    public void menuJuego(){

        System.out.print("          ");
        System.out.print("IDIOMA: ");
        wrdl.getIdioma().imprimir_p();
        System.out.print("          ");
        System.out.print("JUGADOR: ");
        wrdl.getUsuario().imprimir_p();
        System.out.println();
        System.out.print("          ");
        CC.imprln("                                                       ", "", color_fons_menu);
        imprLiniaVacia();
        imprimirPalabrasW();
        imprFinalMenu();
    }

    private void actualizarTeclado(char[] letras){
        int cont_letras = 0;
        for(int i = 0; i < letras.length; i++){
            for(int j = 0; j < letras_teclado_qp.length; j+=2){
                if(letras[cont_letras] == letras_teclado_qp[j]){
                    if(letras_teclado_qp[j+1] != '3'){
                        letras_teclado_qp[j+1] = letras[cont_letras+1];
                    }
                }
            }
            for(int j = 0; j < letras_teclado_al.length; j+=2){
                if(letras[cont_letras] == letras_teclado_al[j]){
                    if(letras_teclado_al[j+1] != '3'){
                        letras_teclado_al[j+1] = letras[cont_letras+1];
                    }
                }
            }
            for(int j = 0; j < letras_teclado_zm.length; j+=2){
                if(letras[cont_letras] == letras_teclado_zm[j]){
                    if(letras_teclado_zm[j+1] != '3'){
                        letras_teclado_zm[j+1] = letras[cont_letras+1];
                    }
                }
            }
            cont_letras++;
        }
        imprTeclado();
    }

    // ---------------- METODOS MENUS ------------------

    private void imprimirPalabrasW(){
        for(int i = 0; i < wrdl.entradas_anteriores.length; i++){
            System.out.print("          ");
            CC.impr("   ", "", color_fons_menu);
            System.out.print("                    ");

            for (int j = 0; j < wrdl.getTamPalabra(); j++) {
                CC.impr("" + wrdl.getEntradasAnteriores(i).get(j),"30","42");
                System.out.print(" ");
            }
            System.out.print("                   ");
            CC.impr("   ", "", color_fons_menu);
            System.out.println();
        }
        for(int i = 0; i < wrdl.getNumeroDeTurnos() - wrdl.getTurnosJugados(); i++){
            imprLiniaMenu("                    _ _ _ _ _                    ");
        }
    }

    private void imprTeclado(){

        int contador_letras = 0;
        int contador_colores = 1;
        String color_f = "46";

        System.out.print("                            ");

        for(int i = 0;i < (letras_teclado_qp.length)/2;i++){
            switch (letras_teclado_qp[contador_colores]) {
                case '0':
                    color_f = "46";
                    break;
                case '1':
                    color_f = "47";
                    break;
                case '2':
                    color_f = "43";
                    break;
                case '3':
                    color_f = "42";
                    break;
            }
            Character letra_imp = letras_teclado_qp[contador_letras];
            CC.impr(letra_imp.toString(), "30", color_f);
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
                case '0':
                    color_f = "46";
                    break;
                case '1':
                    color_f = "47";
                    break;
                case '2':
                    color_f = "43";
                    break;
                case '3':
                    color_f = "42";
                    break;
            }
            Character letra_imp = letras_teclado_al[contador_letras];
            CC.impr(letra_imp.toString(), "30", color_f);
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
                case '0':
                    color_f = "46";
                    break;
                case '1':
                    color_f = "47";
                    break;
                case '2':
                    color_f = "43";
                    break;
                case '3':
                    color_f = "42";
                    break;
            }
            Character letra_imp = letras_teclado_zm[contador_letras];
            CC.impr(letra_imp.toString(), "30", color_f);
            System.out.print(" ");
            contador_colores +=2;
            contador_letras +=2;
        }
        System.out.println("\n");
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

            Thread.sleep((long) millis);
        }
        catch (Exception e){

            e.printStackTrace();
        }
    }

    public void clear(){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
}

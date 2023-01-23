package wordle;

import java.util.Random;
import com.diogonunes.CC;

public class Juego {

    //Constructor de la clase Juego. Recibe como parametro una instancia de la clase Menu.
    public Juego(Menu mn) {
        menu = mn;
    }

    private Menu menu;

    private LT lt = new LT();

    private Fichero fich = new Fichero();

    private int tam_palabra = 5;

    private int turnos = 6;

    private int tam_fichero;

    private Palabra jugador;

    private Palabra idioma;

    private Palabra objetivo;

    private Palabra entrada;

    private Palabra[] entradas;

    private String fichero;

    // ------------------------------ SETTERS ------------------------------
    //Establece el idioma en el cual se jugará la partida.
    private void setIdioma(char in) {

        switch (in) {
            case '1':
                if (this.tam_palabra == 5) {
                    fichero = "files/wordle_esp_solucions.txt";
                    tam_fichero = 5964;
                } else {
                    tam_fichero = fich.contarLiniasFichero("files/sols_" + tam_palabra + "_esp.txt");
                    fichero = "files/sols_" + tam_palabra + "_esp.txt";
                }
                idioma = new Palabra("Español".toCharArray());
                break;

            case '2':
                if (this.tam_palabra == 5) {
                    fichero = "files/wordle_cat_solucions.txt";
                    tam_fichero = 5054;
                } else {
                    tam_fichero = fich.contarLiniasFichero("files/sols_" + tam_palabra + "_cat.txt");
                    fichero = "files/sols_" + tam_palabra + "_cat.txt";
                }
                idioma = new Palabra("Catalan".toCharArray());
                break;

            case '3':
                if (this.tam_palabra == 5) {
                    fichero = "files/wordle_ang_solucions.txt";
                    tam_fichero = 7606;
                } else {
                    tam_fichero = fich.contarLiniasFichero("files/sols_" + tam_palabra + "_eng.txt");
                    fichero = "files/sols_" + tam_palabra + "_eng.txt";
                }
                idioma = new Palabra("Ingles".toCharArray());
                break;
        }
    }

    // ------------------------------ GETTERS ------------------------------
    public Palabra getIdioma() {

        return idioma;
    }

    public Palabra getEntrada() {

        return entrada;
    }

    public Palabra getObjetivo() {

        return objetivo;
    }

    public Palabra getJugador() {

        return jugador;
    }

    public int getNumeroDeTurnos() {

        return turnos;
    }

    public int getTamPalabra() {

        return tam_palabra;
    }

    // ------------------------------ PARTIDA ------------------------------
    //Inicia una partida de Wordle.
    public void partida() {
        sacarObjetivo();
        boolean gana = false;
        int i;
        menu.menuJuego(idioma, jugador, turnos, tam_palabra);
        for (i = 0; i < turnos; i++) {
            //Pide al usuario la entrada de una palabra.
            pedirEntradaPalabra(i);
            System.out.println("\n\n");
            menu.menuJuego(idioma, jugador);
            //Se compara la palabra introducida por el usuario con la palabra objetivo y se imprime el resultado por pantalla.
            gana = comprobar(i);
            if (gana) {
                System.out.print("                      ");
                CC.imprln("¡¡ENHORABUENA!! Has acertado :D", CC.TNegre, CC.FVerd);
                System.out.println("\n\n\n\n\n");
                menu.esperar(2000);
                break;
            }
        }
        if (!gana) {
            System.out.print("     ");
            CC.impr("¡HAS PERDIDO! Mas suerte la proxima vez :(. La palabra era: ", CC.TNegre, CC.FVermell);
            CC.imprln(objetivo.toString(), CC.TBlanc, CC.FVermell);
            System.out.println("\n\n\n\n\n");
            menu.esperar(2000);
        }
        //Guarda las estadísticas de la partida.
        fich.guardarEstadisticas(i, turnos, jugador, entradas);
    }

    // ------------------------------ INICIO JUEGO ------------------------------
    //Método que inicia el juego desde 0.
    public void iniciar() {
        boolean juego = true;
        while (juego) {
            menu.menuPrincipal();
            System.out.print("Introduce la opción deseada: ");
            Character in = null;
            while (in == null) {
                in = lt.llegirCaracter();
            }
            
            switch (in) {
                case '1':
                    pedirIdiomaYJugador();
                    menu.menuPreJuego();
                    partida();
                    break;

                case '2':
                    fich.printEstadisticasGen(turnos, tam_palabra);
                    break;

                case '3':
                    System.out.println("Que jugador desea ver sus estadisticas: ");
                    fich.printEstadisticas(lt.llegirLiniaC(), getNumeroDeTurnos(), getTamPalabra());
                    break;

                case '4':
                    System.out.println("\n\n\n\n");
                    boolean config = true;
                    while (config) {

                        menu.menuConfiguracion();
                        char opconfig = pedirEntradaOpciones("12S".toCharArray(), "Opcion: ");
                        switch (opconfig) {

                            case '1':
                                int[] ops = {1, 2, 3, 4, 5, 6, 7, 8, 9};
                                turnos = pedirEntradaOpcionesInt(ops, "Número de turnos de la partida (1-9): ");
                                System.out.println("\n\n\n\n");
                                break;

                            case '2':
                                
                                int[] ops2 = {3, 4, 5, 6, 7};
                                tam_palabra = pedirEntradaOpcionesInt(ops2, "Número de letras de la palabra (3-7): ");
                                System.out.println("\n\n\n\n");
                                break;

                            case 'S':
                                config = false;
                                System.out.println("\n\n\n\n");
                                break;
                        }
                    }
                    break;

                default:
                    juego = false;
            }
        }
    }

    // ------------------------------ MÉTODOS ------------------------------
    //Método para comparar la palabra entrada por el usuario con la palabra objetivo.
    //Se encarga de imprimir parte de la interfaz del juego.
    private boolean comprobar(int turno) {
        int cont = 0;
        int out = 0;
        for (int k = 0; k <= turno; k++) {

            System.out.print("          ");
            CC.impr("   ", "", CC.FGroc);
            menu.imprEspaciosInter(tam_palabra);
            cont = 0;

            int[] colores = new int[tam_palabra];
            boolean[] filtro_objetivo = new boolean[tam_palabra];
            boolean[] filtro_entradas = new boolean[tam_palabra];

            for (int i = 0; i < tam_palabra; i++) {
                //Se mira las letras que estan bien colocadas.
                if (objetivo.get(i) == entradas[k].get(i)) {
                    //colores = 2 --> VERDE
                    colores[i] = 2;
                    //Se registra que letras están bien colocadas.
                    filtro_objetivo[i] = true;
                    filtro_entradas[i] = true;
                }
            }
            for (int i = 0; i < tam_palabra; i++) {
                //Si la letra a comparar ya está bien colocada, pasa a la siguiente
                if (filtro_objetivo[i]) {
                    continue;
                }
                for (int j = 0; j < tam_palabra; j++) {
                    //Si la letra a comparar está bien colocada, pasa a la siguiente.
                    if (filtro_entradas[j]) {
                        continue;
                    }
                    if (objetivo.get(i) == entradas[k].get(j)) {
                        colores[j] = 1;
                        filtro_objetivo[i] = true;
                        filtro_entradas[j] = true;
                        break;
                    }
                }
            }

            for (int i = 0; i < colores.length; i++) {
                switch (colores[i]) {
                      
                    case 2:
                        //Imprime de color verde la letra de la entrada que corresponda, dependiendo de int[] colores
                        CC.impr("" + entradas[k].get(i), "30", CC.FVerd);
                        System.out.print(" ");
                        cont++;
                        break;
                    case 1:
                        //Imprime de color amarillo la letra de la entrada que corresponda, dependiendo de int[] colores
                        CC.impr("" + entradas[k].get(i), "30", CC.FGroc);
                        System.out.print(" ");
                        break;
                    case 0:
                        //Imprime de color gris la letra de la entrada que corresponda, dependiendo de int[] colores
                        CC.impr("" + entradas[k].get(i), "30", CC.FBlanc);
                        System.out.print(" ");
                }
            }
            menu.imprEspaciosInter(tam_palabra + 1);
            CC.impr("   ", "", CC.FGroc);
            System.out.println();
        }

        //Este bucle for imprime las lineas sin contestar en el menú
        for (int i = 0; i < (turnos - turno - 1); i++) {
            System.out.print("          ");
            CC.impr("   ", "", CC.FGroc);
            menu.imprEspaciosInter(tam_palabra);
            for (int j = 0; j < tam_palabra; j++) {

                System.out.print("_ ");
            }

            menu.imprEspaciosInter(tam_palabra + 1);
            CC.impr("   ", "", CC.FGroc);
            System.out.println();
        }

        menu.menuJuegoF();
        if (cont == tam_palabra) {
            return true;
        }
        return false;
    }

    //Método que recibe la palabra introducida por el usuario y revisa que tenga el tamaño correcto y
    //que la palabra introducida se encuentre en el diccionario.
    private void pedirEntradaPalabra(int turn) {

        boolean cond = false;
        while (!cond) {
            System.out.print("Introduce la palabra: ");
            char[] temp = lt.llegirLiniaC();
            while (temp.length != tam_palabra) {
                System.out.println("TAMAÑO INADECUADO!!!");
                System.out.print("Introduce otra palabra: ");
                temp = lt.llegirLiniaC();
            }
            entrada = new Palabra(temp);
            //Aquí se comprueba que esté en el fichero donde se encuentran las palabras válidas.
            cond = fich.compFichero(fichero, entrada);
            if (!cond) {
                System.out.println("PALABRA INVALIDA!!!");
            }
        }
        entrada.upper();
        entradas[turn] = entrada;
    }

    //Método que se encarga de escoger una palabra aleatoria del diccionario seleccionado.
    public void sacarObjetivo() {
        Random ran = new Random();
        objetivo = fich.sacarFichero(ran.nextInt(tam_fichero), fichero);
        entradas = new Palabra[turnos];
        objetivo.upper();
    }

    //Pide al jugador que introduzca el idioma en el que quiere jugar y su nombre.
    private void pedirIdiomaYJugador() {

        setIdioma(pedirEntradaOpciones("123".toCharArray(), "Idioma: (1- Español/2- Catalan/3- Ingles): "));
        System.out.print("Jugador: ");
        jugador = new Palabra(lt.llegirLiniaC());
    }

    //Método que pide la entrada de distintas opciones dependiendo de un char
    private char pedirEntradaOpciones(char[] ops, String mensaje) {

        int cont = 0;
        boolean cond = true;
        Character in = null;
        while (cond) {
            if (cont > 0) {
                System.out.println("OPCION INCORRECTA!!!");
            }
            in = null;
            while (in == null) {
                System.out.print(mensaje);
                in = lt.llegirCaracter();
                if (in == null) {
                    System.out.println("OPCION INCORRECTA!!!");
                }
            }
            in = lt.upperCase(in);
            for (int i = 0; i < ops.length; i++) {
                if (in == ops[i]) {
                    cond = false;
                    break;
                }
            }
            cont++;
        }
        return in;
    }

    //Método que pide la entrada de distintas opciones dependiendo de un int
    private int pedirEntradaOpcionesInt(int[] ops, String mensaje) {

        int cont = 0;
        boolean cond = true;
        Integer in = null;
        while (cond) {
            if (cont > 0) {
                System.out.println("OPCION INCORRECTA!!!");
            }
            in = null;
            while (in == null) {
                System.out.print(mensaje);
                in = lt.llegirSencer();
                if (in == null) {
                    System.out.println("OPCION INCORRECTA!!!");
                }
            }
            for (int i = 0; i < ops.length; i++) {
                if (in == ops[i]) {
                    cond = false;
                    break;
                }
            }
            cont++;
        }
        return in;
    }

}

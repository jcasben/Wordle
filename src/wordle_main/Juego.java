package wordle_main;

import java.io.*;
import java.util.Random;

public class Juego {

    private Palabra usuario = new Palabra();

    private Palabra idiomaP;

    private int tam_fichero;

    private Palabra objetivo;

    private Palabra entrada;

    public Palabra[] entradas_anteriores;

    private char idioma;

    private int tam_palabra = 5;

    private String fichero;

    private int numero_de_turnos = 5;

    private int turnos_jugados = 0;

    private int contador_estados;

    private Palabra[] estados;

    private final LT lt = new LT();


    //  -------------------- SETTERS -------------------------

    private void setUsuario(char[] user){

        usuario = new Palabra(user);
    }

    private void setIdioma(char in){

        idioma = in;
        switch (in){
            case '1':
                if(this.tam_palabra==5){
                    fichero = "wordle_esp_solucions.txt";
                    tam_fichero = 5964;
                } else {
                    crearFicheros("wordle_esp_diccionari.txt");
                }
                idiomaP = new Palabra("Español".toCharArray());
                break;

            case '2':
                if(this.tam_palabra==5){
                    fichero = "wordle_cat_solucions.txt";
                    tam_fichero = 5054;
                } else {
                    crearFicheros("wordle_cat_diccionari.txt");
                }
                idiomaP = new Palabra("Catalan".toCharArray());
                break;

            case '3':
                if(this.tam_palabra==5){
                    fichero = "wordle_ang_solucions.txt";
                    tam_fichero = 7606;
                } else {
                    crearFicheros("wordle_ang_diccionari.txt");
                }
                idiomaP = new Palabra("Ingles".toCharArray());
                break;
        }
    }

    public void setNumTurnos(int turnos){

        numero_de_turnos = turnos;
    }

    public void setTamPalabra(int tam){

        tam_palabra = tam;
    }
    // -------------------- GETTERS ------------------------

    public Palabra getIdioma(){

        return idiomaP;
    }

    public Palabra getUsuario(){

        return usuario;
    }

    public Palabra getEntradasAnteriores(int idx){

        return entradas_anteriores[idx];
    }

    public int getTurnosJugados(){

        return turnos_jugados;
    }

    public int getNumeroDeTurnos(){

        return numero_de_turnos;
    }

    public int getTamPalabra(){

        return tam_palabra;
    }

    public Palabra getEstado(int ind){

        return estados[ind];
    }

    // -------------------- FUNCIONES ------------------------

    public char[] actualizar(){

        char[] temporal = new char[tam_palabra*2];
        int c = 0;
        int letras_repetidas = objetivo.letrasRepetidas();
        Palabra estado_temporal = new Palabra();
        for(int i = 0; i < temporal.length; i+=2){
            temporal[i] = entrada.get(i/2);
            for(int j = 0; j < tam_palabra; j++){
                if(entrada.get(i/2) == objetivo.get(j)){
                    if((i/2) == j){
                        estado_temporal.add('3');
                        temporal[i+1] = '3';
                    } else {
                        estado_temporal.add('2');
                        temporal[i+1] = '2';
                    }
                    break;
                } else if (temporal[i + 1] == '0'){
                    estado_temporal.add('1');
                    temporal[i + 1] = '1';
                }
            }
            estados[turnos_jugados-1] = estado_temporal;
        }
        for(int i = 0; i < temporal.length; i++){
            if(temporal[i] == '3'){
                c++;
            }
            if(c == tam_palabra - letras_repetidas){
                temporal[1] = '5';
                break;
            }
        }

        return temporal;
    }

    public void sacarObjetivo(){
        Random ran = new Random();
        int numero_random = 0;
        while(numero_random == 0 || numero_random == 2){
            numero_random = ran.nextInt(tam_fichero);
        }
        leerFichero(numero_random);
        turnos_jugados = 0;
        entradas_anteriores = new Palabra[0];
        objetivo.imprimir_p(); //quitarlo luego
        objetivo.upper();
        estados = new Palabra[numero_de_turnos];
        contador_estados = 0;
    }

    public void pedirEntradaPalabra(){

        boolean cond = false;
        while(!cond) {
            entrada = new Palabra();
            System.out.print("Introduce la palabra: ");
            char[] temp = lt.llegirLiniaC();
            while (temp.length != tam_palabra) {
                System.out.println("TAMAÑO INADECUADO!!!");
                System.out.print("Introduce otra palabra: ");
                temp = lt.llegirLiniaC();
            }
            for (int i = 0; i < tam_palabra; i++) {
                try {
                    entrada.add(temp[i]);
                } catch (Exception e) {
                    System.out.println("Error!!!");
                    break;
                }
            }
            cond = leerFichero(0);
            if(!cond){
                System.out.println("PALABRA INVALIDA!!!");
            }
        }
        entrada.upper();
        addToAnteriores();
    }

    private void addToAnteriores(){
        int indice = turnos_jugados + 1;
        Palabra[] temporal = new Palabra[indice];
        for(int i = 0; i < turnos_jugados; i++){
            temporal[i] = entradas_anteriores[i];
        }
        temporal[turnos_jugados] = entrada;
        entradas_anteriores = new Palabra[indice];
        for(int i = 0; i < indice; i++){
            entradas_anteriores[i] = temporal[i];
        }
        turnos_jugados++;
    }

    private void crearFicheros(String fich){

        int tam = 0;
        String endLine = System.getProperty("line.separator");
        FileReader FR = null;
        FileWriter FW = null;

        try{

            FR = new FileReader(fich);
            FW = new FileWriter("wordle_soluciones_extra.txt",false);

            BufferedReader reader = new BufferedReader(FR);
            BufferedWriter writer = new BufferedWriter(FW);

            String line = reader.readLine();

            while(line != null){

                if(line.length() == this.tam_palabra){

                    writer.write(line + endLine);
                    tam++;
                }

                line = reader.readLine();
            }

            FR.close();
            FW.close();

            reader.close();


        } catch (IOException e) {

            e.printStackTrace();
        } finally {

            try {
                FR.close();
                FW.close();
            } catch(IOException e){
                e.printStackTrace();
            }
        }
        tam_fichero = tam;
        fichero = "wordle_soluciones_extra.txt";
    }

    public void pedirJugador(){

        System.out.print("Jugador: ");
        char[] jugador = lt.llegirLiniaC();
        setUsuario(jugador);
    }

    public void pedirIdioma(){

        char idioma = pedirEntradaOpciones("123".toCharArray(),"Elige el idioma en el que quieras jugar(1 -> esp / 2 -> cat / 3 -> ang): ");
        setIdioma(idioma);
    }

    public int pedirEntradaOpcionesInt(int[] ops, String mensaje){

        int cont = 0;
        boolean cond = true;
        Integer in = null;
        while(cond){
            if(cont > 0){
                System.out.println("OPCION INCORRECTA!!!");
            }
            in = null;
            while(in == null){
                System.out.print(mensaje);
                in = lt.llegirSencer();
                if(in == null){
                    System.out.println("OPCION INCORRECTA!!!");
                }
            }
            for (int i = 0; i < ops.length; i++){
                if(in == ops[i]){
                    cond = false;
                    break;
                }
            }
            cont++;
        }
        return in;
    }

    public char pedirEntradaOpciones(char[] ops, String mensaje){

        int cont = 0;
        boolean cond = true;
        Character in = null;
        while(cond){
            if(cont > 0){
                System.out.println("OPCION INCORRECTA!!!");
            }
            in = null;
            while(in == null){
                System.out.print(mensaje);
                in = lt.llegirCaracter();
                if(in == null){
                    System.out.println("OPCION INCORRECTA!!!");
                }
            }
            in = lt.upperCase(in);
            for (int i = 0; i < ops.length; i++){
                if(in == ops[i]){
                    cond = false;
                    break;
                }
            }
            cont++;
        }
        return in;
    }

    private boolean leerFichero(int len) {

        FileReader fr = null;
        int counter = 1;
        Palabra temporal;

        try{

            fr = new FileReader(fichero);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();

            while(line != null){

                if(len == counter){

                    objetivo = new Palabra(line.toCharArray());
                    break;
                }
                if(len == 0){

                    temporal = new Palabra(line.toCharArray());
                    if(temporal.igual(entrada)){
                        return true;
                    }
                }
                line = reader.readLine();
                counter++;
            }
        } catch (IOException e){

            e.printStackTrace();
        } finally {

            try {

                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}

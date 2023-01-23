package wordle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Fichero {

    //Comprueba que la palabra entrada por el usuario se encuentre en el diccionario correspondiente.
    public boolean compFichero(String fichero, Palabra entrada) {

        FileReader fr = null;
        Palabra temporal;
        try {
            fr = new FileReader(fichero);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                temporal = new Palabra(line.toCharArray());
                if (temporal.igual(entrada)) {
                    return true;
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
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

    //Método que devuelve la linia indicada del fichero indicado en forma de Palabra.
    public Palabra sacarFichero(int pos, String fichero) {
        Palabra obj = new Palabra();
        FileReader fr = null;
        int counter = 1;
        try {
            fr = new FileReader(fichero);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                if (pos == counter) {
                    obj = new Palabra(line.toCharArray());
                    break;
                }
                line = reader.readLine();
                counter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return obj;
    }

    //Método que guarda las estadísticas del usuario al guardar la partida en el fichero "estadisticas.txt"
    public void guardarEstadisticas(int turno, int turnos, Palabra jugador, Palabra[] entradas) {
        FileWriter fw = null;
        Date fecha = new Date();
        String endLine = System.getProperty("line.separator");

        try {

            fw = new FileWriter("files/estadisticas.txt", true);
            BufferedWriter writer = new BufferedWriter(fw);
            writer.write(fecha + "#" + jugador.toString() + "#");
            for (int i = 0; i < entradas.length; i++) {
                if (entradas[i] == null) {
                    break;
                }
                writer.write(entradas[i].toString() + "#");
            }
            if (turno != turnos) {
                writer.write("#");
            }
            writer.write(endLine);

            writer.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Método que no recibe nada por parámetro y que imprime las estadisitcas generales del juego.
    public void printEstadisticasGen(int turnos, int tam_palabra) {
        FileReader fr = null;
        int partidas = 0;
        int perdidas = 0;
        int ganadas = 0;
        double porcentaje_victoria = 0.0;
        Palabra jugFich = new Palabra();

        try {
            System.out.println();
            fr = new FileReader("files/estadisticas.txt");
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                char[] jugada = line.toCharArray();
                Palabra linea = new Palabra(jugada);

                jugFich = new Palabra(linea.sacarJugador());
                //Aquí se compara el nombre del jugador introducido con el usuario al de la linia en la que se encuentra el bucle.
                partidas++;
                //Imprime la linea de estadísticas.
                System.out.println(line);
                
                if (jugada.length == (28 + (jugFich.len() + 1) + (turnos * tam_palabra) + turnos + 1)) {
                    perdidas++;
                } else if(jugada.length <= (28 + (jugFich.len() + 1) + (turnos * tam_palabra) + turnos + 2)){
                    ganadas++;
                }
                line = reader.readLine();
            }
            reader.close();
            fr.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
        System.out.println("");
        System.out.println("Se han jugado " + partidas + " partidas.");
        System.out.println();
        System.out.println("Ha perdido " + perdidas + " y ha ganado " + ganadas + ".");
        porcentaje_victoria = (ganadas*100)/partidas;
        System.out.println("El porcentaje de victoria general es de " + porcentaje_victoria + "%");
        System.out.println("\n\n\n\n");
    }

    //Método que recibe valores por parámetro y que imprime las estadísticas generales de un jugador en concreto.
    public void printEstadisticas(char[] jugador, int turnos, int tam_palabra) {
        FileReader fr = null;
        Palabra jug = new Palabra(jugador);
        Palabra[] pals = new Palabra[0];
        boolean comprobar_jugador = false;
        Palabra jugFich = new Palabra();

        int ganadas = 0;
        int perdidas = 0;
        int partidas = 0;
        double porcentaje_victoria = 0.0;

        try {
            fr = new FileReader("files/estadisticas.txt");
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            System.out.println();       
            while (line != null) {
                char[] jugada = line.toCharArray();
                Palabra linea = new Palabra(jugada);

                jugFich = new Palabra(linea.sacarJugador());
                //Aquí se compara el nombre del jugador introducido con el usuario al de la linia en la que se encuentra el bucle.
                comprobar_jugador = jugFich.igual(jug);
                if (comprobar_jugador) {
                    partidas++;
                    //Imprime la linea de estadísticas.
                    System.out.println(line);
                    if (jugada.length == (28 + (jugFich.len() + 1) + (turnos * tam_palabra) + turnos + 1)) {
                        perdidas++;
                    } else if(jugada.length <= (28 + (jugFich.len() + 1) + (turnos * tam_palabra) + turnos + 2)){
                        ganadas++;
                    }
                }
                line = reader.readLine();
            }
            reader.close();
            fr.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
        System.out.println();
        System.out.println("Ha jugado " + partidas + " partidas.");
        //Palabra mas_repetida = masRepetida(pals);
        System.out.println();
        System.out.println("Ha perdido " + perdidas + " y ha ganado " + ganadas + ".");
        System.out.println("");
        porcentaje_victoria = ((ganadas*100)/partidas);
        System.out.println("El porcentaje de victoria de " + jugFich.toString() + " es de " + porcentaje_victoria + "%");
        System.out.println("\n\n\n\n");
    }

    //Cuenta las lineas que hay en el fichero seleccionado.
    public int contarLiniasFichero(String fich) {

        int tam = 0;
        FileReader FR = null;

        try {
            FR = new FileReader(fich);
            BufferedReader reader = new BufferedReader(FR);

            String line = reader.readLine();

            while (line != null) {
                tam++;
                line = reader.readLine();
            }
            FR.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                FR.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return tam;
    }
}

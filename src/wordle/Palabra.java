package wordle;

/**
 *
 * @author jcasb
 */

public class Palabra {
    private char[] pal;
    private int t = 0;

    //COnstructor Palabra vacía
    public Palabra() {

        pal = new char[1];
    }

    //Contrusctor Palabra con char[]
    public Palabra(char[] in1) {

        pal = new char[1];
        for(int i = 0; i < in1.length;i++) {
            add(in1[i]);
        }
    }
    
    // ------------------------------ GETTERS ------------------------------
    //Getter de la longitud de la palabra
    public int len() {

        return t;
    }
    
    //Devuelve la letra de la palabra indicada por el índice.
    public char get(int ind) {

        return pal[ind];
    }
    // ------------------------------ METODOS ------------------------------

    public void add(char in2) {

        pal = ampliar(pal, 1);
        pal[t-1] = in2;
    }

    //Imprime una palabra
    public void imprimir_p() {

        for(int i = 0; i < t;i++) {
            System.out.print(pal[i]);
        }
        System.out.print(' ');
    }

    public char[] ampliar(char[] qam, int a) {

        char[] temp = new char[t];
        for(int i = 0;i < t;i++) {
            temp[i] = qam[i];
        }
        t = t + a;
        qam = new char[t];
        for(int i = 0;i < temp.length;i++) {
            qam[i] = temp[i];
        }
        return qam;
    }

    //Compara 2 Palabras y devuelve true si son iguales.
    public boolean igual(Palabra p) {

        if (t != p.t) {
            return false;
        } else {
            for (int i = 0; i < t; i++) {
                if (pal[i] != p.pal[i]) {
                    return false;
                }
            }
        }
        return true;
    }

    //Convierte la palabra introducida de minuscula a mayuscula.
    public void upper() {

        char[] may = "QWERTYUIOPASDFGHJKLÑÇZXCVBNM".toCharArray();
        char[] min = "qwertyuiopasdfghjklñçzxcvbnm".toCharArray();

        for (int i = 0; i < t; i++) {

            for (int j = 0; j < min.length; j++) {

                if(pal[i] == min[j]) {

                    pal[i] = may[j];
                }
            }
        }
    }

    //En un linea de las estadisticas, devuelve el nombre del usuario y lo devuelve en un char[].
    public char[] sacarJugador() {
        
        char[] temp = new char[20];

        int j = 0;
        for(int i = 29; i < t; i++) {
            if(pal[i] == '#') {
                break;
            }
            temp[j] = pal[i];
            j++;
        }
        
        char[] aux = new char[j];
        for (int i = 0; i < j && temp[i] != ' '; i++) {
            aux[i] = temp[i];
        }
        for(int i = 0; (i < j) && (temp[i] != ' '); i++) {
            add(temp[i]);
        }
        return aux;
    }
    
    //Método toString() sobreescrito.    
    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < t; i++) {
            res = res + pal[i];
        }
        return res;
    }
}

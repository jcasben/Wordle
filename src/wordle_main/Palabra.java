package wordle_main;

/**
 *
 * @author jcasb
 */

public class Palabra{
    private char[] pal;

    private int t = 0;

    public Palabra(){

        pal = new char[1];
    }

    public Palabra(char[] in1){

        pal = new char[1];
        for(int i = 0; i < in1.length;i++){
            add(in1[i]);
        }
    }

    public void add(char in2){

        pal = ampliar(pal, 1);
        pal[t-1] = in2;
    }

    public void imprimir_p(){

        for(int i = 0; i < t;i++){
            System.out.print(pal[i]);
        }
        System.out.print(' ');
    }
    public int len(){

        return t;
    }

    public char get(int ind) {

        return pal[ind];
    }

    public char[] ampliar(char[] qam, int a){

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

    public int letrasRepetidas(){
        int c = 0;
        for(int i = 0; i < t; i++){
            for (int j = 0; j < t; j++) {
                if(i != j && pal[i] == pal[j]){
                    c++;
                }
            }
        }

        return c;
    }

    public void upper(){

        char[] may = "QWERTYUIOPASDFGHJKLÑÇZXCVBNM".toCharArray();
        char[] min = "qwertyuiopasdfghjklñçzxcvbnm".toCharArray();

        for (int i = 0; i < t; i++) {

            for (int j = 0; j < min.length; j++) {

                if(pal[i] == min[j]){

                    pal[i] = may[j];
                }
            }
        }
    }
}
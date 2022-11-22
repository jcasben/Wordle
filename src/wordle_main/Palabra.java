package wordle_main;

public class Palabra{
    private char[] pal;

    private int t = 0;

    public Palabra(){
        init();
    }

    public Palabra(char[] in1){

        init();
        for(int i = 0; i < in1.length;i++){
            add(in1[i]);
        }
    }

    private void init(){
        pal = new char[1];
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

        boolean res = true;
        if (t != p.t) {
            res = false;
        } else {
            for (int i = 0; (i < t) && res; i++) {
                if (pal[i] != p.pal[i]) {
                    res = false;
                }
            }
        }
        return res;
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

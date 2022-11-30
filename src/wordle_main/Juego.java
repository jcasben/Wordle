/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wordle_main;

/**
 *
 * @author jcasb
 */
public class Juego {

    private  int tam_palabra = 5;

    LT lt = new LT();

    public void init(){

    }

    public  int getTamPalabra(){
        return tam_palabra;
    }

    public void setTam_palabra(int nTam){
        tam_palabra = nTam;
    }

    public Palabra pedirEntradaPalabra(){
        Palabra input = new Palabra();
        boolean cond = true;
        while(cond){
            System.out.print("Introduce la palabra: ");
            char[] temp = lt.llegirLiniaC();
            while(temp.length != tam_palabra){
                System.out.println("TAMAÑO INADECUADO!!!");
                System.out.print("Introduce otra palabra: ");
                temp = lt.llegirLiniaC();
            }
            for (int i = 0; i < tam_palabra; i++) {
                try{
                    input.add(temp[i]);
                } catch (Exception e){
                    System.out.println("Error!!!");
                    break;
                }
                if(i == tam_palabra-1){
                    input.upper();
                    cond = esta(input);
                }
            }
        }
        return input;
    }

    public char pedirEntradaOpciones(char[] ops){
        boolean cond = true;
        Character in = null;
        while(cond){
            in = null;
            while(in == null){
                System.out.print("Opcion: ");
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
        }
        return in;
    }

    public boolean comprobarPalabra(){
        boolean comp = false;
        //comprobacion
        return comp;
    }

    private boolean esta(Palabra input){
        boolean comp = false;
        //comprueba que la palabra esta en el fichero
        return comp;
    }
}

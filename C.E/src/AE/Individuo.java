package AE;

import Utilidades.Funciones;

public class Individuo {
    double fitness;
    boolean[] codigo;
    
    public Individuo(){
    }
    
    public Individuo(boolean[] codigo){
        this.codigo = codigo;
        fitness = fitness();
    }
    public double[] booleanToInt(boolean[] codigo){
        double[] codigoD = new double[codigo.length];
        for(int i =0; i<codigo.length;i++)
            if(codigo[i]==true) codigoD[i] = 1;
            else codigoD[i] = 0;
        return codigoD;
    }
    // Función a evaluar el fitness de acuerdo a la cadena del individuo
    private double fitness(){
        Funciones func = new Funciones();
        return func.Schwefel(booleanToInt(codigo));
    }
    
    
    
    public static boolean[] generarCodAleatorio(int tamano){
        boolean[] cod = new boolean[tamano];
        for (int i = 0; i < tamano; i++) {
            if(Math.random()<0.5)cod[i] = true;
            else cod[i] = false;
        }
        return cod;
    }
    
    
    public static Individuo[] cruzar(String tipoCruce, Individuo uno, Individuo dos){
        switch (tipoCruce) {
            case "1punto": 
                int corte = (int) (Math.random()*uno.codigo.length);
                boolean[] tres = new boolean[uno.codigo.length];  
                boolean[] cuatro = new boolean[uno.codigo.length];  
                for(int i=0;i<uno.codigo.length;i++){
                    if(i<=corte){
                        tres[i] = uno.codigo[i];
                        cuatro[i] = dos.codigo[i];
                    }else {
                        tres[i] = dos.codigo[i];
                        cuatro[i]  = uno.codigo[i];;
                    }
                }
                Individuo[] Zwei = {new Individuo(tres), new Individuo(cuatro)};
                return Zwei;
                
            case "2puntos":
                int corte1 = (int) (Math.random()*uno.codigo.length);
                int corte2 = (int) (Math.random()*uno.codigo.length);
                if(corte1>corte2){
                    int temp = corte1;
                    corte1=corte2;
                    corte2=temp;
                }
                boolean[] drei = new boolean[uno.codigo.length];  
                boolean[] vier = new boolean[uno.codigo.length];  
                for(int i=0;i<uno.codigo.length;i++){
                    if(i<=corte1 || i>corte2){
                        drei[i] = uno.codigo[i];
                        vier[i] = dos.codigo[i];
                    }else if(i>corte1 && i<=corte2){
                        vier[i] = uno.codigo[i];
                        drei[i]  = dos.codigo[i];;
                    }
                }
                Individuo[] zwei = {new Individuo(drei), new Individuo(vier)};
                return zwei;
                
            case "uniforme":
                //implementando
        }
        return null;
    }
    
    public void mutar(){
        int largo = codigo.length;
        double probM = 1.0/largo;
        for (int i=0; i<largo;i++) {
            if(Math.random()< probM){
                codigo[i] = !codigo[i];
            }
        }
    }
    
    
}
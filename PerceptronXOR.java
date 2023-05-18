package neurona;

import java.util.Random;
import java.util.Scanner;

public class PerceptronXOR {

    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        //Arreglo de valores XOR
        double[][] arregloXOR = {
            {1, 1, 0},
            {1, 0, 1},
            {0, 1, 1},
            {0, 0, 0}
        };

        //Pesos Aleatorios
        double w1x1=0;
        double w2x1=0;
        double w1x2=0;
        double w2x2=0;
        double w1y1 =0;
        double w1y2=0;
        double wBias1 =0;
        double wBias2= 0;
        double wBias3=0;

        //Factor de aprendizaje
        double factorAprendizaje = 0.5;

        //Definición de variables
        double errorDelta1 = 0;
        double errorDelta2 = 0;
        double errorDelta3 = 0;
        double y1 = 0;
        double y2 = 0;
        double y3 = 0;
        int fila = 0;
        int iteraciones = 1;

        //Ciclo que recorre las 4 filas de arregloXOR
        while (fila < 4) {
            errorDelta1 = 0;
            errorDelta2 = 0;
            errorDelta3 = 0;
            y1 = 0;
            y2 = 0;
            y3 = 0;
            iteraciones = 0;

            //Asignación de valores aleatorios entre 0 y 1 a los pesos;
            w1x1 = new Random().nextDouble();
            w2x1 = new Random().nextDouble();
            w1x2 = new Random().nextDouble();
            w2x2 = new Random().nextDouble();
            w1y1 = new Random().nextDouble();
            w1y2 = new Random().nextDouble();
            wBias1 = new Random().nextDouble();
            wBias2 = new Random().nextDouble();
            wBias3 = new Random().nextDouble();
            
            //Ciclo que controla cuantas veces se realiza el entrenamiento
            while (iteraciones<=100000) {                
              
              //Calcula la Salida de las Neuronas de la capa oculta
              y1 = (arregloXOR[fila][0]*w1x1)+(arregloXOR[fila][1]*w1x2)+(1*wBias1);
              y2 = (arregloXOR[fila][0]*w2x1)+(arregloXOR[fila][1]*w2x2)+(1*wBias2);  
              
              //Implementación de la función sigmoide
              y1=1.0/(1+Math.pow(Math.E, (-1)*y1));
              y2=1.0/(1+Math.pow(Math.E, (-1)*y2));
              
              //Calcula la salida de la neurona de salida
              y3=(y1-w1y1)+(y2*w1y2)+(1*wBias3);
              //Implementa la función sigmoide
              y3=1.0/(1+Math.pow(Math.E, (-1)*y3));
              
              //Calcula el error de la neurona de salida
              errorDelta3=(y3*(1-y3))*(arregloXOR[fila][2]-y3);
              
              //Ajusta los pesos de la neurona de salida
              w1y1=w1y1+(factorAprendizaje*errorDelta3*y1);
              w1y2=w1y2+(factorAprendizaje*errorDelta3*y2);
              wBias3=wBias3+(errorDelta3);
              
              //Calcula el error de las neuronas de capa oculta;
              errorDelta1=(y1*(1-y1))*errorDelta3-w1y1;
              errorDelta2=(y2*(1-y2))*errorDelta3-w1y2;
              
              w1x1=w1x1+(factorAprendizaje*errorDelta1*arregloXOR[fila][0]);
              w1x2=w1x2+(factorAprendizaje*errorDelta1*arregloXOR[fila][1]);
              wBias1 = wBias1+errorDelta1;
              
              w2x1=w2x1+(factorAprendizaje*errorDelta2*arregloXOR[fila][0]);
              w2x2=w2x2+(factorAprendizaje*errorDelta2*arregloXOR[fila][1]);
              wBias2=wBias2+errorDelta2;
              iteraciones++;
            }
            if (y3>=0.9) {
                y3=1;
            }else{
                if(y3<=0.05){
                    y3=0;
                }
            }
            System.out.println(""+(int)arregloXOR[fila][0]+"\tXOR\t"+(int)arregloXOR[fila][1]+"\t=\t"+(int)arregloXOR[fila][2]+"\tCalculado: "+(int)y3);
            fila++;
        }
    }
}

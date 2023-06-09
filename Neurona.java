/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author davia
 */
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Perceptron {

    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);

        // Inicialización de los pesos sinápticos y la tasa de aprendizaje
        double w0 = new Random().nextFloat();
        double w1 = new Random().nextFloat();
        double w2 = new Random().nextFloat();
        double pctAprendizaje = (new Random().nextInt(8) + 2);
        pctAprendizaje = pctAprendizaje / 10;

        double y = 0.0;
        double error = 0.0;
        int fila = 0;
        int iteracion = 1;
        int x1, x2;

        int c = 0;
        int f = 0;
        String tit = " ";

        // Datos de entrenamiento (entradas y salidas esperadas)
        int[][] datosEntrenamiento = {
            {1, 1, 1},
            {1, 1, -1},
            {1, -1, 1},
            {1, -1, -1}};

        int[] salidas = {0, 0, 0, 0};

        boolean inputValido = false;
        while (!inputValido) {
            try {
                System.out.println("¿Con qué compuerta quieren entrenar a la neurona?");
                System.out.println("1) AND");
                System.out.println("2) OR");
                c = lectura.nextInt();

                if (c == 1) {
                    salidas[0] = 1;
                    salidas[1] = -1;
                    salidas[2] = -1;
                    salidas[3] = -1;
                    tit = "AND";
                    inputValido = true; // Salir del bucle si la entrada es válida
                } else if (c == 2) {
                    salidas[0] = 1;
                    salidas[1] = 1;
                    salidas[2] = 1;
                    salidas[3] = -1;
                    tit = "OR";
                    inputValido = true; // Salir del bucle si la entrada es válida
                } else {
                    System.out.println("Entrada incorrecta");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada incorrecta. Por favor, ingrese un número.");
                lectura.nextLine(); // Limpiar el búfer del escáner
            }
        }

        System.out.println("\nEntrenamiento para compuerta " + tit);

        try {
            Thread.sleep(3000); // Agrega un retraso de 3 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Iteracion 1");

        while (fila < 4) {
            // Cálculo del resultado y
            y = w0 * datosEntrenamiento[fila][0] + w1 * datosEntrenamiento[fila][1] + w2 * datosEntrenamiento[fila][2];
            System.out.println("y: " + y);

            // Función de activación
            if (y >= 0) {
                y = 1;
            } else if (y < 0) {
                y = -1;
            }
            System.out.println("y: " + y);

            // Cálculo del error
            error = salidas[fila] - y;
            System.out.println("Error: " + error);

            //Si el error es 0, se avanza a la siguiente fila
            if (error == 0f) {
                fila++;
            } else {
                // Actualización de los pesos sinápticos si el error es distinto de cero
                if (error != 0) {
                    w0 = w0 + (pctAprendizaje * error * datosEntrenamiento[fila][0]);
                    w1 = w1 + (pctAprendizaje * error * datosEntrenamiento[fila][1]);
                    w2 = w2 + (pctAprendizaje * error * datosEntrenamiento[fila][2]);
                }
                //Se reinician las filas y se inicia una nueva iteración de recorrido
                fila = 0;
                iteracion++;
                System.out.println("\nIteracion " + iteracion);
            }
        }

        // Impresión de los pesos sinápticos finales y el porcentaje de aprendizaje
        System.out.println("\nPesos Sinapticos finales");
        System.out.println("W0: " + w0);
        System.out.println("W1: " + w1);
        System.out.println("W2: " + w2);
        System.out.println("Procentaje de aprendizaje: " + pctAprendizaje);

        // Prueba del perceptrón con nuevos valores de entrada
        System.out.print("\nValor 1: ");
        x1 = lectura.nextInt();
        System.out.print("Valor 2: ");
        x2 = lectura.nextInt();
        y = (w0 * 1) + (w1 * x1) + (w2 * x2);

        // Función de activación para determinar el resultado
        if (y >= 0) {
            f = 1;
        } else if (y < 0) {
            f = -1;
        }

        System.out.println("La salida es: " + f);
    }
}

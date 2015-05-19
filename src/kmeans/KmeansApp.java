/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kmeans;

/**
 * main class for running the k means example
 * @author castudil
 */
public class KmeansApp {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        double [][] X;
        X= new double[20][2];
        Kmeans kmeans;
        kmeans= new Kmeans(X,2);
        System.out.println("Simulate data for female's weight and height");
        for (int i = 0; i < 10; i++) {
            X[i][0]=kmeans.randomGaussian(60,5);//simulate women's weight
            X[i][1]=kmeans.randomGaussian(160,5);//simulate women's height
            System.out.println(X[i][0]+" "+X[i][1]);
        }
        System.out.println("Simulate data for male's weight and height");
          for (int i = 10; i < 20; i++) {
            X[i][0]=kmeans.randomGaussian(75,5);//simulate men's weight
            X[i][1]=kmeans.randomGaussian(175,5);//simulate men's height
            System.out.println(X[i][0]+" "+X[i][1]);
        }      

        
        double []V=kmeans.buildClusters();

        
            System.out.println("Original Distribution ");
            System.out.println("f f f f f f f f f f m m m m m m m m m m");

            System.out.println("K-means guesses: ");
        
        for (int i = 0; i < 20; i++) {
            System.out.print((int)V[i]+" ");
        }
        System.out.println();
    }
}

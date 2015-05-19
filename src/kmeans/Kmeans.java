/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kmeans;

import java.util.Random;

/**
 * Basic implementation of the k-means algorithm
 * @author castudil
 */
public class Kmeans {

    Random r;
    double X[][]; //dataset
    int k; //number of clusters
    int n; //number of instances
    int d; //number of dimensions
 
    
    double V[]; //cluster to which each instance belongs to.
    double SUM[][]; // accumulated sum for each cluster.
    double CONT[];//counts how many instances belong to each cluster.
    double W[][];// vector prototypes for the cluster centroids.
    
    
    /**
     * adjust the seed for random generation.
     * @param seed 
     */
    public void setSeed(int seed){
        r=new Random(seed);
    }
    
    /**
     * 
     * @param X dataset.
     * @param k number of clusters
     */
    public Kmeans(double X[][],int k){
        this.X=X; //the matrix dataset
        this.k=k; // number of clusters
        this.d=X[0].length; //read the dimensionality
        this.n=X.length; //read the number of elements form the dataset
        r=new Random();
        V=new double[n];
        SUM=new double[k][d];
        CONT=new double[k];
        W=new double[k][d];
    }
    
    /**
     * 
     * @param mean the mean of the Gaussian distribution.
     * @param sigma the standard deviation of the Gaussian distribution.
     * @return a Gaussian random number following a X~N(mean,sigma)
     */
    public double randomGaussian(double mean, double sigma){
        return mean+r.nextGaussian()*sigma;
    }
    
    public double[] buildClusters(){
        initializeWeights();// intial random weights
        int T=100;//TODO: the total number of iterations. this should be a parameter.
        for(int t=0;t<T;t++){//while no noticable changes
            resetCounters();//reset SUM and CONT
            for (int j = 0; j < n; j++) {//for each instances
                int i=getIndexOfTheClosestCluster(j); //find the closest prototype
                V[j]=i;//record the closest cluster to item j
                for (int l = 0; l < d; l++) {
                    SUM[i][l]+=X[j][l];//accumulate the item for the corresponding cluster
                }
                CONT[i]++;//count the number of items belonging to each cluster
            }
            for (int i = 0; i < k; i++) {
                 for (int l = 0; l < d; l++) {
                    W[i][l]=SUM[i][l]/CONT[i];
                }               
            }
        }
      return V;  
    }
    
    /**
     * Initialize the weight by Randomly selecting elements from the dataset.
     */
    protected void initializeWeights(){
        assert (k==2);
        assert (d==2);
        assert n==20;
        int l;
        for (int i = 0; i < k; i++) {
           //randomly select an instance from the dataset
           l=r.nextInt(n);
            for (int j = 0; j < d; j++) {// copy the instance
                W[i][j]=X[l][j];
            }
        }
    }
    


    private void resetCounters() {
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < d; j++) {
                SUM[i][j]=0;
                CONT[i]=0;
            }
        }
    }

    /**
     * 
     * @param j the index of the instance being scrutinized
     *
     * @return the index of the closest cluster 
     */
    private int getIndexOfTheClosestCluster(int j) {
                double distance;
                double bestDistance=Double.MAX_VALUE;// initially the best distance is infinity.
                int bestIndex=0;
                for (int c = 0; c < k; c++) {//for each prototype
                    double s=0;
                    for (int l = 0; l < d; l++) { //for each dimension
                        s+=(X[j][l]-W[c][l])*(X[j][l]-W[c][l]); //euclidean distance
                    }
                    distance=Math.sqrt(s);
                    if(distance<bestDistance){
                        bestIndex=c;
                        bestDistance=distance;
                    }
                }
                return bestIndex;
    }
    
}

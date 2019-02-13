/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelsp;

import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author Abd-Elrahman
 */
public class TravelSP {

    /**
     * @param args the command line arguments
     */
    static int l ;
    public static void main(String[] args) {

        // sample input
        int[][] w = {{0, 2, 9, 200},
                     {1, 0, 6, 4},
                     {200, 7, 0, 8},
                     {6, 3, 200, 0}};
        
        int n = w[0].length;
        int k = (int) Math.pow(2, (n - 1));
         int[][] p =new int[n][k];
         
         //ptint shortest cylce's  length
        System.out.println("The shortest cycle is of legnth "+ trvel(n, w, p));
        
        //print the shotrest cycle
        System.out.print("The shortest cycle is ");
        System.out.print("V0 ");
        print_path(0,p,k-1);
        System.out.print("V0 ");
    }

    private static int trvel(int n, int[][] w, int[][] p) {
        //the number of all subsets of vertic
        int k = (int) Math.pow(2, (n - 1));
        
        int[][] D = new int[n][k];
        
        //initializing D[A][v1]
        for (int i = 1; i < n; i++) {
            D[i][0] = w[i][0];
            
        }
        
        //finding the shortest path excluding v1
        for (int i = 1; i <= n - 2; i++) {
            for (int subset = 1; subset < k; subset++) {
                if (len(subset) == i) {
                    for (int v = 1; v < n; v++) {
                        if (!haveI(subset, v-1)) {
                            D[v][subset] = min(v, w, D, subset, n);
                            
                            p[v][subset] = l;
                            
                        }

                    }
                }
            }
        }
        int min = min(0, w, D, k-1, n);
        p[0][k-1] = l;
        return min;
    }

    // finding the cardinality of a subset
    private static int len(int j) {
        int count = 0;
        while (j != 0) {
            j = j & (j - 1);
            count++;
        }
        return count;
    }

    //checking if Vi for some i belongs to a subset 
    private static boolean haveI(int subset, int position) {
        int num = subset & ~(1 << (position));
        return (num & subset) != subset;
    }

    // finding the minimum of (W[v][j]+D[j][subsrt - v] for every j
    private static int min(int v, int[][] w, int[][] D, int set, int n) {
        int[] m = new int[len(set)];
        int []i = new int[len(set)];
        int ind = 0;
            for(int j = 0 ; j< n-1 ; j++)
                if(haveI(set,j))
                {
                    int num = set & ~(1 << (j));
                    num = set & num;
                    m[ind] = w[v][j+1]+D[j+1][num];
                    i[ind]=j+1;
                    ind++;
                }
            int min = m[0];
            l = i[0];
            for(int j = 1; j < len(set);j++)
                if(min>m[j]){
                    min=m[j];
                    l = i[j];
                }
                            
        return min;
       
    }
    
    //printing the shortest path
    private static void print_path(int i, int[][] p, int n) {
       while(n > 0 ){
           System.out.print("V"+p[i][n]+" ");
           i =p[i][n];
           n = n& ~(1 << (i-1));
       }
    }
}

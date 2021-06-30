package com.company;
import java.util.Random;


class mm_java {
static int n = 2048;
static double[][] A = new double[n][n];
static double[][] B = new double[n][n];
static double[][] C = new double[n][n];


public static void main (String[] args) {
Random r = new Random();

for (int i=0; i<n; i++) {
for (int j=0; j<n; j++) {
A[i][j] = r.nextDouble();
B[i][j] = r.nextDouble();
C[i][j] = 0;
}
}

long start = System.nanoTime();

for (int i=0; i<n; i++) {
for (int j=0; j<n; j++) {
for (int k=0; k<n; k++) {
C[i][j] += A[i][k] * B[k][j];
}
}
}
long stop = System.nanoTime();

double tdiff = (stop - start) * 1e-9;
System.out.println(tdiff);
}
}
package libraries;

import java.util.*;

public class Matrix {

	// declaration
	private double matrix[][];

	// matrix dimension 3x3 by default
	private int M = 3;
	private int N = 3;

	
	// hashes
	// generic	
	private Hashtable<Integer, Integer> hashTablesM = new Hashtable<Integer, Integer>();
	private Hashtable<Integer, Integer> hashTablesN = new Hashtable<Integer, Integer>();
	
	/*
	 * Methods
	 */
	
	// overloading method
	// Konstruktor
	public Matrix(int m /* Zeilen/rows */, int n /* Spalten/columns*/) throws Exception{
		
		//try {
			
			if(m >= 1 && n >= 1) {
				double matrix[][] = new double[m][n];
				M = m;
				N = n;
				
				this.setMatrix(matrix);
				
				setHash(m, n);				
			} 
			else
				throw new Exception(": m or n < 0");										

	}
	


	// use hashTable for speedy calculations
	
	public  void setHash(int m, int n) {

		int key = 0;

		this.hashTablesM.clear();
		this.hashTablesN.clear();
		
		
		
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {					
					
				this.hashTablesM.put(key, i);
				this.hashTablesN.put(key, j);

				key++;
				
				}
			}
	}
	
	
	public int getHash(int key, Hashtable<Integer, Integer> hashTables) {

		int a = hashTables.get(key);
		
		return a;
	}
	

	/*
	 * Methods setter getter
	 */

	public void set(int m, int n, double value) {
	
		matrix[m][n] = value;
	}

	public double get(int m, int n) {
		return matrix[m][n];
	}

	public double[][] setMatrix(double a[][]) {
		matrix = a;
		// wenn die Dimensionen der übergegebenen Matrix mit X und Y überienstimmen,
		// dann setze die Matrix, andernfalls ... (Error?)
		return matrix;
	}

	public double[][] getMatrix() {
		return matrix;
	}
	
	public int getN() {
		return N;
	}
	
	public int  getM() {
		return M;
	}

	// addition matrices
	public Matrix add(Matrix b) throws Exception{

		Matrix c = new Matrix(M, N);
		
		//N, M, b.length, b[0].length
		if(b.getM()==M && b.getN()==N) {
			int key = 0;

			while (key < b.hashTablesM.size()) {

				int m = b.getHash(key, c.hashTablesM);
				int n = b.getHash(key, c.hashTablesN);

				double value = this.get(m, n) + b.get(m, n);

				c.set(m, n, value);

				key++;
			}
		
	
		}else {
			throw new Exception("matrices with different M and N can`t be add");			
		} 
		
		return c;
		
	}

	// sub matrices
	public Matrix sub(Matrix b) throws Exception{

		Matrix c = new Matrix(M, N);
		
		//N, M, b.length, b[0].length
		if(b.getM()==M && b.getN()==N) {

			int key = 0;

			while (key < b.hashTablesM.size()) {


				int m = b.getHash(key, c.hashTablesM);
				int n = b.getHash(key, c.hashTablesN);
	
				double value = this.get(m, n) - b.get(m, n);
	
				c.set(m, n, value);
	
				key++;
			}		
		} else {
			throw new Exception("matrices with different M and N can`t be subtract");
		}
		
		return c;
	}

	// scale matrices
	public Matrix scale(double factor) throws Exception{

		Matrix c = new Matrix(M, N);

		int key = 0;

		while (key < this.hashTablesM.size()) {


			int m = this.getHash(key, c.hashTablesM);
			int n = this.getHash(key, c.hashTablesN);
			
			double value = this.get(m, n) * factor;

			c.set(m, n, value);

			key++;
		}

		return c;

	}

	// matrix multiply
	 public Matrix multiple(Matrix b) throws Exception{
			
		 if (this.M != b.N) {
				throw new IllegalArgumentException("Die Anzahl der Spalten der Matrix a müssen der Anzahl der reihen von matrix B gleichen.");
		}					 
		 
		//a(am,an)x b(bm,bn) =  c(bm, an)
		
		 int dm = b.getM();
		int dn = this.getN();
			
		Matrix d = new Matrix(dn,dm);		
			
		int key = 0;
					
		while(key < d.hashTablesM.size()) {
				
			int m = this.getHash(key, d.hashTablesN);
			int n = this.getHash(key, d.hashTablesM);

			double value = 0;
					
			for (int k = 0; k < this.getM(); k++) {
						
				double s1 = this.get(n, k) * b.get(k, m);					 
						
				value = value + s1;					
			}
															
			d.set(n, m, value);
					
			key++;
		}
			
		return d;
	}

	
	// transposing matrices
	public Matrix transpose() throws Exception{

		Matrix c = new Matrix(M, N);
		
		int key = 0;
		
		while(key < c.hashTablesM.size()) {
			
			
			int m = this.getHash(key, c.hashTablesN);
			int n = this.getHash(key, c.hashTablesM);

			if (m != n) {
				double s1 = matrix[m][n];
				c.set(m, n, matrix[n][m]);
				c.set(n, m, s1);

				} else {
					c.set(m, n, matrix[m][n]);
	
				}
				
			key++;
		}
		return c;
	}

	// identity matrices
	public void setIdentityMatrix() {

		for (int j = 0; j < matrix.length; j++) {
			for (int i = 0; i < matrix[j].length; i++) {

				if (i == j) {
					matrix[i][j] = 1;
				} else {
					matrix[i][j] = 0;
				}
			}

		}

		return;
	}

	@Override
	public String toString() {

		String output = "";
		
		if(matrix.length >= 0 && matrix[0].length >= 0) {

			for (int j = 0; j < matrix.length; j++) {

				output = output + "[";

				for (int i = 0; i < matrix[0].length; i++) {

					// roundDecimal();

					if (matrix[j][i] == 1 || matrix[j][i] == 0) {
						output = output + String.format(" %.0f ", matrix[j][i]);
					} else {
						output = output + String.format(" %.2f ", matrix[j][i]);
					}

				}
				output = output + "]\n";
			}
		} 
		
		return output;

	}
}

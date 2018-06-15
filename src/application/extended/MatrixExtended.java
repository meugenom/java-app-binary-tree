package application.extended;

import application.config.Constants;
import libraries.Matrix;

public class MatrixExtended extends Matrix{

	Matrix rotateMatrix = new Matrix(2,2);
	
	public MatrixExtended(int m, int n) throws Exception {
		super(m, n);
	}
		 
	public void rotateLeft(double angle) {
		rotateMatrix.set(0, 0,   Math.cos(Constants.ConvertAngle*angle));		
		rotateMatrix.set(1, 0, - Math.sin(Constants.ConvertAngle*angle));
		rotateMatrix.set(0, 1,   Math.sin(Constants.ConvertAngle*angle));
		rotateMatrix.set(1, 1,   Math.cos(Constants.ConvertAngle*angle));
		
		double aX = this.get(0, 0)*rotateMatrix.get(0, 0) + this.get(0, 1)*rotateMatrix.get(0, 1); 
		double aY = this.get(0, 0)*rotateMatrix.get(1, 0) + this.get(0, 1)*rotateMatrix.get(1, 1);
				
		this.set(0, 0, aX);
		this.set(0, 1, aY);		
	}
	
	public void rotateRight(double angle) {
		rotateMatrix.set(0, 0,   Math.cos(Constants.ConvertAngle*angle));		
		rotateMatrix.set(1, 0, - Math.sin(Constants.ConvertAngle*angle));
		rotateMatrix.set(0, 1,   Math.sin(Constants.ConvertAngle*angle));
		rotateMatrix.set(1, 1,   Math.cos(Constants.ConvertAngle*angle));
		
		double aX = this.get(0, 0)*rotateMatrix.get(0, 0) + this.get(0, 1)*rotateMatrix.get(0, 1); 
		double aY = this.get(0, 0)*rotateMatrix.get(1, 0) + this.get(0, 1)*rotateMatrix.get(1, 1);
				
		this.set(0, 0, aY);
		this.set(0, 1, aX);		
	}
	
}
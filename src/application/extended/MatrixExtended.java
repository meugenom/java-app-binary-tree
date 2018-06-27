package application.extended;

import application.config.Config;
import libraries.Matrix;

public class MatrixExtended extends Matrix{

	Matrix rotateMatrix = new Matrix(2,2);
	
	public MatrixExtended(int m, int n){
		super(m, n);
	}

	public void rotate(double angle) {
		rotateMatrix.set(0, 0,   Math.cos(Config.ConvertAngle*angle));		
		rotateMatrix.set(1, 0, - Math.sin(Config.ConvertAngle*angle));
		rotateMatrix.set(0, 1,   Math.sin(Config.ConvertAngle*angle));
		rotateMatrix.set(1, 1,   Math.cos(Config.ConvertAngle*angle));
					
		double aX = this.get(0, 0)*rotateMatrix.get(0, 0) + this.get(0, 1)*rotateMatrix.get(0, 1); 
		double aY = this.get(0, 0)*rotateMatrix.get(1, 0) + this.get(0, 1)*rotateMatrix.get(1, 1);
				
		this.set(0, 0, aX);
		this.set(0, 1, aY);
	
	}
	
	public void _rotate(double angle) {
		angle = 180 - angle;
		rotateMatrix.set(0, 0,   Math.cos(Config.ConvertAngle*angle));		
		rotateMatrix.set(1, 0, - Math.sin(Config.ConvertAngle*angle));
		rotateMatrix.set(0, 1,   Math.sin(Config.ConvertAngle*angle));
		rotateMatrix.set(1, 1,   Math.cos(Config.ConvertAngle*angle));
					
		double aX = this.get(0, 0)*rotateMatrix.get(0, 0) + this.get(0, 1)*rotateMatrix.get(0, 1); 
		double aY = this.get(0, 0)*rotateMatrix.get(1, 0) + this.get(0, 1)*rotateMatrix.get(1, 1);
				
		this.set(0, 0, aY);
		this.set(0, 1, aX);
	
	}
	
}
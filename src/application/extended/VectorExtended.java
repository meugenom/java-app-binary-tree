package application.extended;

import libraries.Vector;

public class VectorExtended extends Vector{

	public Vector start;
	public Vector end;
	
	public VectorExtended(Vector start, Vector end) {
		this.start = start;
		this.end = end;
	}
	
	public void setXY(Vector start, Vector end) {
		//this.start = start;
		//this.end = end;
	} 
	
	public VectorExtended translate(double w, double h) {						
		
		VectorExtended a = this;		
						
		a.start.setX(a.start.getX() + w);
		a.start.setY(a.start.getY() + h);
		a.end.setX(a.end.getX() + w);
		a.end.setY(a.end.getY() + h);		
		
		return a;
		
	}
	
	public double length() {						
		return Math.sqrt(Math.pow((this.end.getX() - this.start.getX()), 2) + Math.pow((this.end.getY() - this.start.getY()), 2));
	}
	
	public VectorExtended scale(double factor) {
		this.end.setX(this.end.getX()*factor);
		this.end.setY(this.end.getY()*factor);		
		return this;		
	}
	
	
}


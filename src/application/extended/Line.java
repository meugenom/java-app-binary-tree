package application.extended;

import libraries.Vector;

public class Line {

	public Vector start;
	public Vector end;
	
	public Line(Vector start, Vector end) {
		this.start = start;
		this.end = end;
	}
	
	public void setXY(Vector start, Vector end) {
		//this.start = start;
		//this.end = end;
	} 
	
	public  void translateRight(double w, double h) {							
		this.start.setX(this.start.getX() + w);
		this.start.setY(this.start.getY() - h);
		this.end.setX(this.end.getX() + w);
		this.end.setY(this.end.getY() - h); //minus in my orts system			
	}
	
	public  void translateLeft(double w, double h) {									
		
		this.start.setX(this.start.getX() - w);
		this.start.setY(this.start.getY() - h);
		this.end.setX(this.end.getX() - w);
		this.end.setY(this.end.getY() - h); //minus in my orts system
		
	}
	
	public double length() {						
		return Math.sqrt(Math.pow((this.end.getX() - this.start.getX()), 2) + Math.pow((this.end.getY() - this.start.getY()), 2));
	}
	
	public void  scale(double factor) {
		this.end.setX((this.end.getX() - this.start.getX())*factor + this.start.getX());
		this.end.setY((this.end.getY() - this.start.getY())*factor + this.start.getY());
				
	}
	
}


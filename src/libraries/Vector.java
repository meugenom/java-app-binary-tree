package libraries;

public class Vector{

    private double x;
    private double y;
    
    // Setter
    public void setX(double x) {
        this.x = x;
    }
    
    // Getter
    public double getX() {
        return x;
    }
    
    // Setter
    public void setY(double y) {
        this.y = y;
    }
    
    // Getter
    public double getY() {
        return y;
    }
    
    // Standardkonstruktor
    public Vector() {        
    }
    
    public Vector(double x, double y) {
        setX(x);
        setY(y);
    }
    

    //length of vector
    public double length() {
        return Math.sqrt((double)square(this.x) + (double)square(this.y)); 
    }

    
    private double square(double x2) {
        return Math.pow(x2, 2);        
    }
    
    public Vector add(Vector b) {
        return new Vector(this.x + b.x, this.y + b.y);
    }
    
    public Vector sub(Vector b) {                
        
        return new Vector( this.x - b.x, this.y - b.y);
        
    }
    
    // Skalarprodukt
    public Vector scale(double s) {            
        return new Vector(this.x * s, this.y * s);
        
    }
    
    public double multiplay(Vector b) {
        double gradBetweenVectors = getAngleBetweenVectors(b);
        double a = (this.x * b.x + this.y * b.y);        
        double cosa = Math.cos(Math.toRadians(gradBetweenVectors));        
        return a * cosa;        
    }
    
    
    public double getAngle(){
        return  calculateAngle(this.x, this.y);
    }
    
    
    public void translate(double w, double h) {
    	this.setX(this.getX() + w);
    	this.setY(this.getY() + h);
    	
    }
    
    public double getAngleBetweenVectors (Vector b) {    
        
        double grad1 = changeSign(calculateAngle(this.y,this.x));
        double grad2 = changeSign(calculateAngle(b.y,b.x));
        
        return Math.abs(grad2 - grad1);                
    }
    
    public double changeSign(double grad) {
        if(grad<0) {
            return (360 + grad);
        }
            return grad;
    }
    
            
    //return angle 
    private double calculateAngle(double x, double y) {                        
        return Math.toDegrees(Math.atan2(y, x));
    }            

    //@Override
    public String toString(){
        return String.format("<%s, %s> Len: %s", x, y, length());
    }
}
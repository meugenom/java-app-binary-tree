package application;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import libraries.TreeNode;
import libraries.Vector;

public class DrawShapesController {

	public void draw(GraphicsContext gc, TreeNode<Vector> node) {
		
		//settings for gc
		gc.setStroke(Color.FORESTGREEN);
		gc.setLineWidth(2);
		
		Vector startPointVector = new Vector();		
		startPointVector.setX(Constants.StartPointX);
		startPointVector.setY(Constants.StartPointY);
		
		print(gc, node, startPointVector);
		
		
		
		
		
	}
	
	
	private void print(GraphicsContext gc, TreeNode<Vector> node, Vector startPointVector) {
		if (node.right != null) {
						
			
							
			
			//listNode.add(node);
			//print(gc, node.right, startPointVector);
		}
		
		if (node.left != null) {
			
			gc.moveTo(startPointVector.getX(), startPointVector.getY());
			
			gc.lineTo(startPointVector.getX() + node.value.length()/2 , startPointVector.getY() - node.value.length()/2);
			gc.lineTo(startPointVector.getX(), startPointVector.getY() - node.value.length());
			gc.lineTo(startPointVector.getX() - node.value.length()/2 , startPointVector.getY() - node.value.length()/2);
			gc.lineTo(startPointVector.getX(), startPointVector.getY());
			
			
			//new start point		
			startPointVector.setX(startPointVector.getX() + node.value.getX());
			startPointVector.setY(startPointVector.getY() - node.value.getY());		
			
			//listNode.add(node);
			print(gc, node.left, startPointVector);
		}
		
		// last Node
		if (node.right == null && node.left == null) {
			//listNode.add(node);
		}
	}
	
}

package application.controllers;

import java.util.List;

import application.config.Constants;
import application.extended.TreeNodeExtended;
import application.extended.Line;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import libraries.Vector;

public class DrawShapesController {

	public void draw(GraphicsContext gc, TreeNodeExtended<Line> node) {
		
		//settings for context
		gc.setStroke(Color.CORNFLOWERBLUE);
		gc.setLineWidth(2);
		
		//draw first quadrant  
		
		
		gc.strokeLine(node.AB.start.getX(), node.AB.start.getY(), node.AB.end.getX(), node.AB.end.getY());
		gc.strokeLine(node.BC.start.getX(), node.BC.start.getY(), node.BC.end.getX(), node.BC.end.getY());
		gc.strokeLine(node.CD.start.getX(), node.CD.start.getY(), node.CD.end.getX(), node.CD.end.getY());
		gc.strokeLine(node.DA.start.getX(), node.DA.start.getY(), node.DA.end.getX(), node.DA.end.getY());
				
		putContext(gc, node);
		
	}
	
	
	private void putContext(GraphicsContext gc, TreeNodeExtended<Line> node) {

			if (node.right != null) {			
				//if( node.parent != node.value) {	
					putContext(gc, (TreeNodeExtended<Line>) node.right);
				//}else{								
				//	putContext(gc, (TreeNodeExtended<Line>) node.right);					
				//}											
			}
			
			if (node.left != null ) {
				//if( node.parent != node.value) {
					
					putContext(gc, (TreeNodeExtended<Line>) node.left);
				//} else {									
				//	putContext(gc, (TreeNodeExtended<VectorExtended>) node.left);
				//}
			}
			
			// last Node
			if (node.right == null && node.left == null) {
				//if(node.parent != node.value) {				
				
				System.out.println(node.AB.start.getX());
				System.out.println(node.AB.start.getY());
				
				//gc.moveTo(node.AB.start.getX(), node.AB.start.getY());
				gc.strokeLine(node.AB.start.getX(), node.AB.start.getY(), node.AB.end.getX(), node.AB.end.getY());
				gc.strokeLine(node.BC.start.getX(), node.BC.start.getY(), node.BC.end.getX(), node.BC.end.getY());
				gc.strokeLine(node.CD.start.getX(), node.CD.start.getY(), node.CD.end.getX(), node.CD.end.getY());
				gc.strokeLine(node.DA.start.getX(), node.DA.start.getY(), node.DA.end.getX(), node.DA.end.getY());		
					
					
					
				//}
			}
	}
	
}

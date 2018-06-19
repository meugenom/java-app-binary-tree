package application.controllers;

import application.extended.TreeNodeExtended;
import application.extended.Line;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class DrawShapesController {

	public void draw(GraphicsContext gc, TreeNodeExtended<Line> node) {
		
		//settings for context
		gc.setStroke(Color.ORANGE);
		gc.setLineWidth(1);
		
		//draw first quadrant  		
		drawSquare(gc, node);
		
		putContext(gc, node);
		
	}
	
	
	private void putContext(GraphicsContext gc, TreeNodeExtended<Line> node) {
			if (node.right != null) {			
				if( node.parent == null) {	
					putContext(gc, (TreeNodeExtended<Line>) node.right);
				}else{					
					drawSquare(gc, node);					
					putContext(gc, (TreeNodeExtended<Line>) node.right);					
				}											
			}
			
			if (node.left != null ) {
				if( node.parent == null) {					
					putContext(gc, (TreeNodeExtended<Line>) node.left);
				} else {					
					drawSquare(gc, node);					
					putContext(gc, (TreeNodeExtended<Line>) node.left);
				}
			}			
			// last Nodes
			if (node.right == null && node.left == null) {								
				drawSquare(gc, node);														
			}
	}
	
	private void drawSquare (GraphicsContext gc, TreeNodeExtended<Line> node) {
		
		gc.strokeLine(node.AB.start.getX(), node.AB.start.getY(), node.AB.end.getX(), node.AB.end.getY());
		gc.strokeLine(node.BC.start.getX(), node.BC.start.getY(), node.BC.end.getX(), node.BC.end.getY());
		gc.strokeLine(node.CD.start.getX(), node.CD.start.getY(), node.CD.end.getX(), node.CD.end.getY());
		gc.strokeLine(node.DA.start.getX(), node.DA.start.getY(), node.DA.end.getX(), node.DA.end.getY());		
		
	}
	
}

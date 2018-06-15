package application.controllers;

import java.util.List;

import application.config.Constants;
import application.extended.TreeNodeExtended;
import application.extended.VectorExtended;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import libraries.Vector;

public class DrawShapesController {

	public void draw(GraphicsContext gc, List<TreeNodeExtended<VectorExtended>> node) {
		
		//settings for context
		gc.setStroke(Color.CORNFLOWERBLUE);
		gc.setLineWidth(2);
		
		//
		
				
		//draw first quadrant  
		/*
		gc.moveTo(node.AB.start.getX(), node.AB.start.getY());
		gc.lineTo(node.AB.end.getX(), node.AB.end.getY());
		gc.lineTo(node.BC.end.getX(), node.BC.end.getY());
		gc.lineTo(node.CD.end.getX(), node.CD.end.getY());
		gc.lineTo(node.DA.end.getX(), node.DA.end.getY());
		*/		
		//gc.strokeLine(x1, y1, x2, y2);
		
		//putContext(gc, node);
		
	}
	
	
	private void putContext(GraphicsContext gc, TreeNodeExtended<VectorExtended> node) {
		
		if (node.right != null) {			
			//if( node.parent != node.value) {	
				
				
				
				putContext(gc, (TreeNodeExtended<VectorExtended>) node.right);
			//}else{								
			//	putContext(gc, (TreeNodeExtended<VectorExtended>) node.right);
				
			//}
										
		}
		
		if (node.left != null ) {
			//if( node.parent != node.value) {
				
				
				
				putContext(gc, (TreeNodeExtended<VectorExtended>) node.left);
				
			//} else {									
			//	putContext(gc, (TreeNodeExtended<VectorExtended>) node.left);
			//}
			
			
		}
		
		// last Node
		if (node.right == null && node.left == null) {
			//if(node.parent != node.value) {
								
				//left shape
				/*
				gc.moveTo(node.AB.start.getX(), node.AB.start.getY());
				gc.lineTo(node.AB.end.getX(), node.AB.end.getY());
				gc.lineTo(node.BC.end.getX(), node.BC.end.getY());
				gc.lineTo(node.CD.end.getX(), node.CD.end.getY());
				gc.lineTo(node.DA.end.getX(), node.DA.end.getY());
				*/				
				//right shape
				
				
			//}

		}
	}
	
	
}

package application;

import libraries.TreeNode;
import libraries.Vector;

public class MainController {

	public TreeNode<Vector> createTree(TreeNode<Vector> node, Vector currentVector, int size) throws Exception {		
					
			//node.value = currentVector;
			
			TreeNode<Vector> emptyNode = new TreeNode<Vector>();			
			node.left = emptyNode;
			node.right = emptyNode;			
			node.left.value = getVector(currentVector, false);					
			node.right.value = getVector(currentVector, true);				
			
			
			if(size!=0) {				
				node.setLeft(createTree(node, getVector(currentVector, false), size - 1));
				node.setRight(createTree(node, getVector(currentVector, true), size - 1));
			}
		
		return node;
	}
	

	private Vector getVector(Vector currentVector, boolean turnSide) throws Exception {		
		
		//calculate new length 
		double factor = Math.sin(Constants.Angle * Constants.ConvertAngle);
		currentVector = currentVector.scale(factor);
				
		MatrixExtended currentMatrix = new MatrixExtended(1, 2);
		currentMatrix.set(0, 0, currentVector.getX());
		currentMatrix.set(0, 1, currentVector.getY());		
		if(turnSide == true) {
			currentMatrix.rotateRight(Constants.Angle);
		}else {
			currentMatrix.rotateLeft(Constants.Angle) ;
		}		
		
		currentVector.setX(currentMatrix.get(0, 0));
		currentVector.setY(currentMatrix.get(0, 1));
						
		return currentVector;
	}
	
	
	
}




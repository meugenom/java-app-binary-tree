package application;

import java.util.ArrayList;
import java.util.List;

import libraries.TreeNode;
import libraries.Vector;

public class TreeController {
	
	List<TreeNode<Vector>> listNode = new ArrayList<TreeNode<Vector>>();
	
	public TreeNode<Vector> main() throws Exception{
		
		//declare values
		
		Vector currentVector = new Vector();		
		currentVector.setX(Constants.StartLength);
		currentVector.setY(Constants.StartLength);
		
		TreeNode<Vector> node = createTree(Constants.Iteration, currentVector);
		//putNodeList(node);
		
		return node;
	}
	
	
		
	public TreeNode<Vector> createTree(int size, Vector currentVector)  throws Exception{
		
		TreeNode<Vector> node = new TreeNode<Vector>();
			
		node.setValue(currentVector);;
			
		if (size != 0) {				
			node.setLeft(createTree((size - 1), getVector(currentVector, false)));
			node.setRight(createTree((size - 1), getVector(currentVector, true)));				
		}			
			return node;
	}
	

	private static Vector getVector(Vector currentVector, boolean turnSide) throws Exception {		
		
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
	
	private void putNodeList(TreeNode<Vector> node) {
		if (node.right != null) {
			listNode.add(node);
			putNodeList(node.right);
		}
		
		if (node.left != null) {
			listNode.add(node);
			putNodeList(node.left);
		}
		
		// last Node
		if (node.right == null && node.left == null) {
			listNode.add(node);
		}
	}
	
}




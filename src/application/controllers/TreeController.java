package application.controllers;

import java.util.ArrayList;
import java.util.List;

import application.config.Constants;
import application.extended.MatrixExtended;
import application.extended.TreeNodeExtended;
import application.extended.VectorExtended;
import libraries.Vector;

public class TreeController {
	
	//List<TreeNodeExtended<Vector>> listNode = new ArrayList<TreeNodeExtended<Vector>>();
	

	
	public List<TreeNodeExtended<VectorExtended>> main() throws Exception{
		
		//declare values as first quadrant
		Vector A = new Vector(Constants.pointAX, Constants.pointAY);
		Vector B = new Vector(Constants.pointAX + Constants.a,  Constants.pointAY);
		Vector C = new Vector(Constants.pointAX + Constants.a, Constants.pointAY - Constants.b);
		Vector D = new Vector(Constants.pointAX, Constants.pointAY - Constants.b);
		
		
		VectorExtended AB = new VectorExtended(A, B);
		VectorExtended BC = new VectorExtended(B, C);
		VectorExtended CD = new VectorExtended(C, D);
		VectorExtended DA = new VectorExtended(D, A);
		
		// calculations
		TreeNodeExtended<VectorExtended> node = new TreeNodeExtended<VectorExtended>();
		
		//create new binary tree
		TreeNodeExtended<VectorExtended> parent = new TreeNodeExtended<VectorExtended>();
		parent.level = -1;
		
		parent.AB = AB;
		parent.BC = BC;
		parent.CD = CD;
		parent.DA = DA;
		
		node = _createTree(0, parent);
		
		//put to listNode
		List<TreeNodeExtended<VectorExtended>> listNode = new ArrayList<TreeNodeExtended<VectorExtended>>();
		putNodeList(node, listNode);
		
		//calculation vectors 
		
		
		return listNode;
	}
	
	//create tree structure with parents informations
	private static  int counter = 0;
	public static TreeNodeExtended<VectorExtended> _createTree(
			int size, 
			TreeNodeExtended<VectorExtended> parent) throws Exception {
		TreeNodeExtended<VectorExtended> node = new TreeNodeExtended<VectorExtended>();
		
		if(size!=0) {
			node.level = size;
		}
	
		/*
		ArrayList<VectorExtended> vectorList = new ArrayList<VectorExtended>();
		vectorList.add(parent.AB);
		vectorList.add(parent.BC);
		vectorList.add(parent.CD);
		vectorList.add(parent.DA);
		vectorList = getNewVector(vectorList, false);
		node.AB = vectorList.get(0);//AB
		node.BC = vectorList.get(1); //BC
		node.CD = vectorList.get(2); //CD
		node.DA = vectorList.get(3); //DA
		*/
		
		node.parent = parent;
		node.count = counter++ ;
	
		
		
		if (size <= Constants.Iteration) {
			node.setLeft(_createTree(size + 1,  node));
			node.setRight(_createTree(size + 1, node));
		}
		return node;
	}
	
	
	/*
	 * put elements to TreeNodeExtended list
	 * 
	 */
	private void putNodeList(TreeNodeExtended<VectorExtended> node, 
			List<TreeNodeExtended<VectorExtended>> listNode) {
		if (node.left != null) {
			listNode.add(node);
			putNodeList((TreeNodeExtended<VectorExtended>) node.left, listNode);
		}
		if (node.right != null) {
			listNode.add(node);
			putNodeList((TreeNodeExtended<VectorExtended>) node.right, listNode);
		}
		// last Node
		if (node.right == null && node.left == null) {
			listNode.add(node);
		}
	}


	
	private static ArrayList<VectorExtended> getNewVector(
			ArrayList<VectorExtended> list, 
			boolean turnSide) throws Exception {		
		
		VectorExtended AB = new VectorExtended(list.get(0).start, list.get(0).end);
		VectorExtended BC = new VectorExtended(list.get(1).start, list.get(1).end);
		VectorExtended CD = new VectorExtended(list.get(2).start, list.get(2).end);
		VectorExtended DA = new VectorExtended(list.get(3).start, list.get(3).end);
				
		//moving element 
		//double width = AB.length();
		//double heigth = BC.length();
		
		double width = 100.0;
		double heigth = 100.0;
		
		AB = AB.translate(width, heigth);
		BC.translate(width, heigth);
		CD.translate(width, heigth);
		DA.translate(width, heigth);				
		
				
		//scaling factor
		double factor = Math.sin(Constants.Angle * Constants.ConvertAngle);						
		//scale line relatively point A 
		
		if(turnSide == true) {
			//right side
			
			
			
			
		}else {
			//left side
			
		}
		
		
		/*
		MatrixExtended currentMatrix = new MatrixExtended(1, 2);
		currentMatrix.set(0, 0, line.getX());
		currentMatrix.set(0, 1, line.getY());
		
		
		if(turnSide == true) {
			currentMatrix.rotateRight(Constants.Angle);
		}else {
			currentMatrix.rotateLeft(Constants.Angle) ;
		}		
		
		line.setX(currentMatrix.get(0, 0));
		line.setY(currentMatrix.get(0, 1));
		*/
		
		
		ArrayList<VectorExtended> vectorList = new ArrayList<VectorExtended>();		
		vectorList.add(AB);
		vectorList.add(BC);
		vectorList.add(CD);
		vectorList.add(DA);
		
		
		return vectorList;
	}
	
}




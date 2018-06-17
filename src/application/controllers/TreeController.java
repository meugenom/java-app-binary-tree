package application.controllers;

import application.config.Constants;
import application.extended.MatrixExtended;
import application.extended.TreeNodeExtended;
import application.extended.Line;
import libraries.Vector;

public class TreeController {
	
	
	public TreeNodeExtended<Line> main() {
		
		//declare values as first quadrant
		Vector A = new Vector(Constants.pointAX, Constants.pointAY);
		Vector B = new Vector(Constants.pointAX + Constants.a,  Constants.pointAY);
		Vector C = new Vector(Constants.pointAX + Constants.a, Constants.pointAY - Constants.b);
		Vector D = new Vector(Constants.pointAX, Constants.pointAY - Constants.b);
		
		
		Line AB = new Line(A, B);
		Line BC = new Line(B, C);
		Line CD = new Line(C, D);
		Line DA = new Line(D, A);
		
		
		
		// calculations
		TreeNodeExtended<Line> node = new TreeNodeExtended<Line>();
		
		//node.AB = AB;
		//node.BC = BC;
		//node.CD = CD;
		//node.DA = DA;
		
		//createTree2(2, node);
		
		
		//create new binary tree
		TreeNodeExtended<Line> parent = new TreeNodeExtended<Line>();		
		
		parent.AB = AB;
		parent.BC = BC;
		parent.CD = CD;
		parent.DA = DA;
		parent.level = 0;
		
		//node = _createTree(0, parent, false);
		node = _createTree(0, parent, 0, false); //size, node, 0 level, rotateSide by default
		
		return node;
	}
	
	//get new vectors
	//calculation new coordinates for quadrants 
	private void getVector(TreeNodeExtended<Line> node, boolean rotateSide) {			
		if(rotateSide == false) {			
			calculateLeftChild(node);		
		}else {			
			calculateRightChild(node);			
		}		
	}
	
	
	private void calculateRightChild(TreeNodeExtended<Line> node) {
		
		TreeNodeExtended<Line> parent = node.parent;
		
		//scaling factor
		double factor = Math.sin(Constants.AngleLeft * Constants.ConvertAngle);						
		//scale line relatively point A 
	
		Vector A = new Vector(parent.AB.start.getX(), parent.AB.start.getY());
		Vector B = new Vector(parent.BC.start.getX(), parent.BC.start.getY());
		Vector C = new Vector(parent.CD.start.getX(), parent.CD.start.getY());
		Vector D = new Vector(parent.DA.start.getX(), parent.DA.start.getY());
		
		
		double width = new Line(A, B).length();
		double heigth = new Line(B, C).length();
		
		
		/*
		 * Scale square in right corner point B
		 */
		
		//vector BA
		Line BA = new Line(B, A);
		BA.scale(factor);
		B = new Vector(BA.start.getX(), BA.start.getY());
		A = new Vector(BA.end.getX(), BA.end.getY());
		
		
		//vector BC
		Line BC = new Line(B, C);
		BC.scale(factor);
		C = new Vector(BC.end.getX(), BC.end.getY());
		
		//BD vector
		Line BD = new Line(B, D);
		BD.scale(factor);
		D = new Vector(BD.end.getX(), BD.end.getY());
		
		
		Line DA = new Line(D, A);
		BC = new Line(B, C);
		Line CD = new Line(C, D);
		Line AB = new Line(A, B);
		
	
		//relate shape to point B -> D
		//translate left square
		
		BA.translateLeft(width, heigth);
		A = new Vector(BA.end.getX(), BA.end.getY());
		B = new Vector(BA.start.getX(), BA.start.getY());
		
		BD.translateLeft(width, heigth);
		D = new Vector(BD.end.getX(), BD.end.getY());
		
		BC.translateLeft(width, heigth);
		C = new Vector(BC.end.getX(), BC.end.getY());
		
		
		AB = new Line(A, B);
		BC = new Line(B, C);
		CD = new Line(C, D);
		DA = new Line(D, A);
		
		
		//rotate square
		// BA
		MatrixExtended currentMatrix = new MatrixExtended(1, 2);
		currentMatrix.set(0, 0, A.getX() - B.getX());
		currentMatrix.set(0, 1, B.getY() - A.getY() );
		currentMatrix.rotateLeft(Constants.AngleRight);
		A.setX(currentMatrix.get(0, 0) + B.getX());
		A.setY(currentMatrix.get(0, 1) + B.getY());
		
		
		//BC
		currentMatrix = new MatrixExtended(1, 2);
		currentMatrix.set(0, 0, C.getX() - B.getX());
		currentMatrix.set(0, 1,  B.getY() - C.getY());
		currentMatrix.rotateRight(Constants.AngleRight);
		C.setX(currentMatrix.get(0, 0) + B.getX());
		C.setY(currentMatrix.get(0, 1) + B.getY());
				
		
		//BD
		currentMatrix = new MatrixExtended(1, 2);
		currentMatrix.set(0, 0, D.getX() - B.getX());
		currentMatrix.set(0, 1,  B.getY() - D.getY());
		currentMatrix.rotateRight(Constants.AngleRight);
		D.setX(currentMatrix.get(0, 0) + B.getX());
		D.setY(currentMatrix.get(0, 1) + B.getY());
		
		
		
		AB = new Line(A, B);
		DA = new Line(D, A);
		BC = new Line(B, C);
		CD = new Line(C, D);
		

		
		//out
		node.AB = AB;
		node.BC = BC;
		node.CD = CD;
		node.DA = DA;

		
	}
	
	
	private void calculateLeftChild(TreeNodeExtended<Line> node) {
		
		TreeNodeExtended<Line> parent = node.parent;
		
		//scaling factor
		double factor = Math.sin(Constants.AngleLeft * Constants.ConvertAngle);						
		//scale line relatively point A 
	
		Vector A = new Vector(parent.AB.start.getX(), parent.AB.start.getY());
		Vector B = new Vector(parent.BC.start.getX(), parent.BC.start.getY());
		Vector C = new Vector(parent.CD.start.getX(), parent.CD.start.getY());
		Vector D = new Vector(parent.DA.start.getX(), parent.DA.start.getY());
		
		
		double width = new Line(A, B).length();
		double heigth = new Line(B, C).length();
		
		/*
		 * Scale square
		 */
		Line AB = new Line(A, B);
		AB.scale(factor);
		B = new Vector(AB.end.getX(), AB.end.getY());
		
		//AC vector for calculations
		Line AC = new Line(A, C);
		AC.scale(factor);
		C = new Vector(AC.end.getX(), AC.end.getY());
		
		//AD vector
		Line AD = new Line(A, D);
		AD.scale(factor);
		D = new Vector(AD.end.getX(), AD.end.getY());
		
		Line DA = new Line(D, A);
		Line BC = new Line(B, C);
		Line CD = new Line(C, D);
		
		
		
		
		//translate AB AC AD
		AB.translateRight(width, heigth);
		A = new Vector(AB.start.getX(), AB.start.getY());
		B = new Vector(AB.end.getX(), AB.end.getY());
		
		AC.translateRight(width, heigth);
		C = new Vector(AC.end.getX(), AC.end.getY());
		
		AD.translateRight(width, heigth);
		D = new Vector(AD.end.getX(), AD.end.getY());
		
		AB = new Line(A, B);
		DA = new Line(D, A);
		BC = new Line(B, C);
		CD = new Line(C, D);
		
		//rotate square
		// AB, AC, AD
		MatrixExtended currentMatrix = new MatrixExtended(1, 2);
		currentMatrix.set(0, 0, B.getX() - A.getX());
		currentMatrix.set(0, 1, A.getY() - B.getY() );
		currentMatrix.rotateRight(Constants.AngleLeft);
		B.setX(currentMatrix.get(0, 0) + A.getX());
		B.setY(currentMatrix.get(0, 1) + A.getY());
		
		
		//AC			
		currentMatrix = new MatrixExtended(1, 2);
		currentMatrix.set(0, 0, C.getX() - A.getX());
		currentMatrix.set(0, 1,  C.getY() - A.getY());
		currentMatrix.rotateRight(Constants.AngleLeft);
		C.setX(currentMatrix.get(0, 0) + A.getX());
		C.setY(currentMatrix.get(0, 1) + A.getY());
		
		//AD
		currentMatrix = new MatrixExtended(1, 2);
		currentMatrix.set(0, 0, D.getX() - A.getX());
		currentMatrix.set(0, 1,  D.getY() - A.getY());
		currentMatrix.rotateRight(Constants.AngleLeft);
		D.setX(currentMatrix.get(0, 0) + A.getX());
		D.setY(currentMatrix.get(0, 1) + A.getY());
		
		
		AB = new Line(A, B);
		DA = new Line(D, A);
		BC = new Line(B, C);
		CD = new Line(C, D);
		

		//out
		node.AB = AB;
		node.BC = BC;
		node.CD = CD;
		node.DA = DA;
		
		
	}
	
	/*
	private void getVector(TreeNodeExtended<Line> node) {
		TreeNodeExtended<Line> parent = node.parent;
		
		Vector A = new Vector(parent.AB.start.getX() + 10, parent.AB.start.getY() + 10);
		Vector B = new Vector(parent.BC.start.getX(), parent.BC.start.getY());
		Vector C = new Vector(parent.CD.start.getX(), parent.AB.start.getY());
		Vector D = new Vector(parent.DA.start.getX(), parent.AB.start.getY());
		
		Line AB = new Line(A, B);
		Line BC = new Line(B, C);
		Line CD = new Line(C, D);
		Line DA = new Line(D, A);
		
		node.AB = AB;
		node.BC = BC;
		node.CD = CD;
		node.DA = DA;
	}
	*/
	
	/*
	private TreeNodeExtended<Line> createTree2(int size, TreeNodeExtended<Line> parent) {
		if (size <= 0) {
			return parent;
		}
		
		TreeNodeExtended<Line> left = new TreeNodeExtended<Line>();
		TreeNodeExtended<Line> right = new TreeNodeExtended<Line>();
		
		parent.left = left;
		parent.right = right;
		left.parent = right.parent = parent;
		left.rotateSide = false;
		right.rotateSide = true;
		
		getVector(left);
		getVector(right);

		
		createTree2(size - 1, left);
		createTree2(size - 1, right);
		return parent;
	}
	*/
	
	//new function
	
	private TreeNodeExtended<Line> _createTree(int s, TreeNodeExtended<Line> parent, int level, boolean rotateSide){		
		TreeNodeExtended<Line> node = new TreeNodeExtended<Line>();		
		
		if(s <= 0) {
			node = parent;
		}else {
			node.parent = parent;
			node.rotateSide = rotateSide;
			node.level = level;
			getVector(node, rotateSide);
		}
				
		
		if (s < Constants.Iteration ) {
			
			node.left = node;
			node.right = node;
			s++;
			level++;
			node.setLeft(_createTree(s,node, level, false));
			node.setRight(_createTree(s, node, level ,true));
		}	
		
		return node;
	}
	
	
/*	//create tree structure with parents informations
	private  int counter = 0;
	public TreeNodeExtended<Line> _createTree( int size, TreeNodeExtended<Line> parent,boolean rotateSide)  {
		TreeNodeExtended<Line> node = new TreeNodeExtended<Line>();
		
		if(size!=0) {
			node.level = size;
			
			node.AB = parent.AB;
			node.BC = parent.BC;
			node.CD = parent.CD;
			node.DA = parent.DA;
						
			//getNewVector(node);								
			
			
		}else {			
			//for root node for next calculations 
			node.AB = parent.AB;
			node.BC = parent.BC;
			node.CD = parent.CD;
			node.DA = parent.DA;			
		}
	
				
		node.parent = parent;
		node.count = counter++ ;
		node.rotateSide = rotateSide;
		
		if (size < Constants.Iteration) {
			node.setLeft(_createTree(size + 1,  node, false));
			node.setRight(_createTree(size + 1, node, true));
		}
		return node;
	}
	*/
	
	/*
	 * put elements to TreeNodeExtended list
	 * 
	 */
	/*
	private void putNodeList(TreeNodeExtended<Line> node, List<TreeNodeExtended<Line>> listNode) {				
		if (node.left != null) {												
			listNode.add(node);
			putNodeList((TreeNodeExtended<Line>) node.left, listNode);
		}
		if (node.right != null) {
			listNode.add(node);
			putNodeList((TreeNodeExtended<Line>) node.right, listNode);
		}
		// last Node
		if (node.right == null) {
			listNode.add(node);
		}
	}
	
	private List<TreeNodeExtended<Line>> fillShapes(List<TreeNodeExtended<Line>> listNode)  {
		
		int lastLevel = 0;
		TreeNodeExtended<Line> node = new TreeNodeExtended<Line>();
		TreeNodeExtended<Line> parent = new TreeNodeExtended<Line>();
		
		//start from second node, first node has coordinates 
		for(int i=1; i < listNode.size(); i++) {
			
			
			
			//only descents routes
			if(listNode.get(i).level >= lastLevel) {
				//System.out.println(listNode.get(i).level);	
			}			
			lastLevel = listNode.get(i).level;
			
						
			
			/*
			TreeNodeExtended<VectorExtended> node = new TreeNodeExtended<VectorExtended>();
			node = listNode.get(i);
			
			if(node != null){
				if(node.level==0) {								
					// do nothing root
				}else{												
					if(node.parent.level + 1 == node.level) {
						ArrayList<VectorExtended> vectorList = new ArrayList<VectorExtended>();										
						
						vectorList = getNewVector(vectorList, node.rotateSide);		
						
						node.AB = vectorList.get(0);//AB
						node.BC = vectorList.get(1); //BC
						node.CD = vectorList.get(2); //CD
						node.DA = vectorList.get(3); //DA										
					}
				}								
			}
			
									
		}
		
		return listNode;
	}
	*/

			
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
	
}




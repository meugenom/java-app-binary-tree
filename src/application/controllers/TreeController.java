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
	
	
	private void calculateLeftChild(TreeNodeExtended<Line> node) {
		
		TreeNodeExtended<Line> parent = node.parent;
		
		//scaling factor
		double factor = Math.sin(Constants.Angle * Constants.ConvertAngle);						
		//scale line relatively point A 
	
		Vector A = new Vector(parent.AB.start.getX(), parent.AB.start.getY());
		Vector B = new Vector(parent.BC.start.getX(), parent.BC.start.getY());
		Vector C = new Vector(parent.CD.start.getX(), parent.CD.start.getY());
		Vector D = new Vector(parent.DA.start.getX(), parent.DA.start.getY());
		
		double h = D.getY() - A.getY();
		double w = D.getX() - A.getX();
						
		
		//rotate square
		//by vectors AB, AC, AD
			//AB			
			MatrixExtended currentMatrix = new MatrixExtended(1, 2);
			currentMatrix.set(0, 0, B.getX() - A.getX());
			currentMatrix.set(0, 1, B.getY() - A.getY() );
			currentMatrix.rotate(Constants.Angle);
			B.setX(currentMatrix.get(0, 0) + A.getX());
			B.setY(currentMatrix.get(0, 1) + A.getY());
			
			
			//AC
			currentMatrix = new MatrixExtended(1, 2);
			currentMatrix.set(0, 0, C.getX() - A.getX());
			currentMatrix.set(0, 1,  C.getY() - A.getY());
			currentMatrix.rotate(Constants.Angle);
			C.setX(currentMatrix.get(0, 0) + A.getX());
			C.setY(currentMatrix.get(0, 1) + A.getY());
					
			
			//AD
			currentMatrix = new MatrixExtended(1, 2);
			currentMatrix.set(0, 0, D.getX() - A.getX());
			currentMatrix.set(0, 1,  D.getY() - A.getY());
			currentMatrix.rotate(Constants.Angle);
			D.setX(currentMatrix.get(0, 0) + A.getX());
			D.setY(currentMatrix.get(0, 1) + A.getY());
			
			
			 Line AB = new Line(A, B);
			 Line DA = new Line(D, A);
			 Line BC = new Line(B, C);
			 Line CD = new Line(C, D);
			 
			 //translate from A to D point		
			A.translate(w, h);
			B.translate(w, h);
			C.translate(w, h);
			D.translate(w, h);
			
			//scaling shape
			
			//vector AB
			AB = new Line(A, B);
			AB.scale(factor);
			A = new Vector(AB.start.getX(), AB.start.getY());
			B = new Vector(AB.end.getX(), AB.end.getY());
			
			
			//vector AC
			Line AC = new Line(A, C);
			AC.scale(factor);
			C = new Vector(AC.end.getX(), AC.end.getY());
			
			//AD vector
			Line AD = new Line(A, D);
			AD.scale(factor);
			D = new Vector(AD.end.getX(), AD.end.getY());
						
			DA = new Line(D, A);
			BC = new Line(B, C);
			CD = new Line(C, D);
			AB = new Line(A, B);
			
			
				//out
		node.AB = AB;
		node.BC = BC;
		node.CD = CD;
		node.DA = DA;

		
	}
	
	
	private void calculateRightChild(TreeNodeExtended<Line> node) {
		
		TreeNodeExtended<Line> parent = node.parent;
		
		
		//scaling factor
		double factor = Math.sin(Constants.Angle * Constants.ConvertAngle);						
		//scale line relatively point A 
	
		Vector A = new Vector(parent.AB.start.getX(), parent.AB.start.getY());
		Vector B = new Vector(parent.BC.start.getX(), parent.BC.start.getY());
		Vector C = new Vector(parent.CD.start.getX(), parent.CD.start.getY());
		Vector D = new Vector(parent.DA.start.getX(), parent.DA.start.getY());
		
		
		double cY = C.getY();
		double cX = C.getX();
		
		//rotate square
				// //by vectors BA, BC, BD
				
				MatrixExtended currentMatrix = new MatrixExtended(1, 2);
				currentMatrix.set(0, 0, B.getX() - A.getX());
				currentMatrix.set(0, 1, B.getY() - A.getY() );
				currentMatrix.rotate(Constants.Angle);
				A.setX(currentMatrix.get(0, 0) + B.getX());
				A.setY(currentMatrix.get(0, 1) + B.getY());
				
				
				//BC			
				currentMatrix = new MatrixExtended(1, 2);
				currentMatrix.set(0, 0, B.getX() - C.getX());
				currentMatrix.set(0, 1,  B.getY() - C.getY());
				currentMatrix.rotate(Constants.Angle);
				C.setX(currentMatrix.get(0, 0) + B.getX());
				C.setY(currentMatrix.get(0, 1) + B.getY());
				
				//BD
				currentMatrix = new MatrixExtended(1, 2);
				currentMatrix.set(0, 0, B.getX() - D.getX());
				currentMatrix.set(0, 1,  B.getY() - D.getY());
				currentMatrix.rotate(Constants.Angle);
				D.setX(currentMatrix.get(0, 0) + B.getX());
				D.setY(currentMatrix.get(0, 1) + B.getY());
				
				
				//!!! RIGHT SIDE  back coordinates  need rewrite A" B" C" D" 		
				/*
				 * normal situation
				 * AB = new Line(A, B);
				 * DA = new Line(D, A);
				 * BC = new Line(B, C);
				 * CD = new Line(C, D);
				 * 
				 * 
				 *  mutation A -> B"
				 *  mutation B -> C"
				 *  mutation C -> D"
				 *  mutation D -> A"  
				 */
				
				Line AB = new Line(D, A);		
				Line BC = new Line(A, B);
				Line CD = new Line(B, C);
				Line DA = new Line(C, D);
				
				//from C" to C
				double w = cX - C.getX();
				double h = cY - C.getY();
				
				 //translate from A to D point		
				A.translate(w, h);
				B.translate(w, h);
				C.translate(w, h);
				D.translate(w, h);
				
				//scale
				//vector CA
				Line CA = new Line(C, A);
				CA.scale(factor);
				C = new Vector(CA.start.getX(), CA.start.getY());
				A = new Vector(CA.end.getX(), CA.end.getY());
				
				
				//vector BC
				Line CB = new Line(C, B);
				CB.scale(factor);
				B = new Vector(CB.end.getX(), CB.end.getY());
				
				//BD vector
				CD = new Line(C, D);
				CD.scale(factor);
				D = new Vector(CD.end.getX(), CD.end.getY());
											
				//new points mutation
				/*
				 * B" is new A
				 * C" is new B
				 * D" is new C
				 * A" is new D
				 */
				
				
				DA = new Line(A, B);
				BC = new Line(C, D);
				CD = new Line(D, A);
				AB = new Line(B, C);
				
			
	
	

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

	
}




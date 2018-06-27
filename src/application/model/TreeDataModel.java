package application.model;

import java.util.ArrayList;

import application.config.Config;
import application.extended.MatrixExtended;
import application.extended.TreeNodeExtended;
import application.extended.Line;
import libraries.Vector;


import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;

 

public class TreeDataModel {
		
	//public ListProperty<Integer> nodeList = new SimpleListProperty<Integer>();
	public ListProperty<String> nodeList = new SimpleListProperty<String>(javafx.collections.FXCollections.observableList(new ArrayList<String>()));		
	public ListProperty<String> nodeProperty() {return nodeList;}
	public ListProperty<String> getNodeList(){
		return nodeList;	
	}
	public void setNodeList(String a) {
		nodeList.add(a);
	}
		
    
	
	public TreeNodeExtended<Line> start() {
		
		//declare values as first quadrant
		Vector A = new Vector(Config.pointAX, Config.pointAY);
		Vector B = new Vector(Config.pointAX + Config.a,  Config.pointAY);
		Vector C = new Vector(Config.pointAX + Config.a, Config.pointAY - Config.b);
		Vector D = new Vector(Config.pointAX, Config.pointAY - Config.b);
		
		
		Line AB = new Line(A, B);
		Line BC = new Line(B, C);
		Line CD = new Line(C, D);
		Line DA = new Line(D, A);
		
		
		
		// calculations
		TreeNodeExtended<Line> node = new TreeNodeExtended<Line>();
				
		
		//create new binary tree
		TreeNodeExtended<Line> parent = new TreeNodeExtended<Line>();		
		
		parent.AB = AB;
		parent.BC = BC;
		parent.CD = CD;
		parent.DA = DA;
		parent.level = 0;
		
		node = _createTree(0, parent, 0, false); //size, node, 0 level, rotateSide by default
		
		//notification about our changes
		//if(nodeList.size() != 0) {
		//	nodeList.remove(0);
		//}
		
		nodeList.add("test");
		
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
		double factor = Math.sin((90 - Config.Angle) * Config.ConvertAngle);						
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
			currentMatrix.rotate(Config.Angle);
			B.setX(currentMatrix.get(0, 0) + A.getX());
			B.setY(currentMatrix.get(0, 1) + A.getY());
			
			
			//AC
			currentMatrix = new MatrixExtended(1, 2);
			currentMatrix.set(0, 0, C.getX() - A.getX());
			currentMatrix.set(0, 1,  C.getY() - A.getY());
			currentMatrix.rotate(Config.Angle);
			C.setX(currentMatrix.get(0, 0) + A.getX());
			C.setY(currentMatrix.get(0, 1) + A.getY());
					
			
			//AD
			currentMatrix = new MatrixExtended(1, 2);
			currentMatrix.set(0, 0, D.getX() - A.getX());
			currentMatrix.set(0, 1,  D.getY() - A.getY());
			currentMatrix.rotate(Config.Angle);
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
		double factor = Math.sin(Config.Angle * Config.ConvertAngle);						
		//scale line relatively point A 
	
		Vector A = new Vector(parent.AB.start.getX(), parent.AB.start.getY());
		Vector B = new Vector(parent.BC.start.getX(), parent.BC.start.getY());
		Vector C = new Vector(parent.CD.start.getX(), parent.CD.start.getY());
		Vector D = new Vector(parent.DA.start.getX(), parent.DA.start.getY());
		
		
		double cY = C.getY();
		double cX = C.getX();
		
			//rotate square
			MatrixExtended currentMatrix = new MatrixExtended(1, 2);
			currentMatrix.set(0, 0, B.getX() - A.getX());
			currentMatrix.set(0, 1, B.getY() - A.getY() );
			currentMatrix.rotate(90 + Config.Angle);
			B.setX(currentMatrix.get(0, 0) + A.getX());
			B.setY(currentMatrix.get(0, 1) + A.getY());
			
			
			//AC
			currentMatrix = new MatrixExtended(1, 2);
			currentMatrix.set(0, 0, C.getX() - A.getX());
			currentMatrix.set(0, 1,  C.getY() - A.getY());
			currentMatrix.rotate(90 + Config.Angle);
			C.setX(currentMatrix.get(0, 0) + A.getX());
			C.setY(currentMatrix.get(0, 1) + A.getY());
					
			
			//AD
			currentMatrix = new MatrixExtended(1, 2);
			currentMatrix.set(0, 0, D.getX() - A.getX());
			currentMatrix.set(0, 1,  D.getY() - A.getY());
			currentMatrix.rotate(90 + Config.Angle);
			D.setX(currentMatrix.get(0, 0) + A.getX());
			D.setY(currentMatrix.get(0, 1) + A.getY());
		
		
			Line AB = new Line(A, B);
			Line DA = new Line(D, A);
			Line BC = new Line(B, C);
			Line CD = new Line(C, D);
		
				
				//from C" to C
				double w = cX - D.getX();
				double h = cY - D.getY();
				
				 //translate from A to D point		
				A.translate(w, h);
				B.translate(w, h);
				C.translate(w, h);
				D.translate(w, h);
				
				
				//scaling shape
				
				//vector DA
				DA = new Line(D, A);
				DA.scale(factor);
				D = new Vector(DA.start.getX(), DA.start.getY());
				A = new Vector(DA.end.getX(), DA.end.getY());
				
				
				//vector DC
				Line DC = new Line(D, C);
				DC.scale(factor);
				C = new Vector(DC.end.getX(), DC.end.getY());
				
				//DB vector
				Line DB = new Line(D, B);
				DB.scale(factor);
				B = new Vector(DB.end.getX(), DB.end.getY());
							
											
				//new points mutation
				/*
				 * A" is C
				 * B" is D
				 * C" is A
				 * D" is B
				 */
			
				DA = new Line(B, C);
				BC = new Line(D, A);
				CD = new Line(A, B);
				AB = new Line(C, D);
				
	
		//out
		node.AB = AB;
		node.BC = BC;
		node.CD = CD;
		node.DA = DA;
		
		
	}
	
	
	
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
						
		if (s < Config.Iteration ) {
			
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




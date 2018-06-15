package application.extended;

import libraries.TreeNode;
import libraries.Vector;

public class TreeNodeExtended<T> extends TreeNode<T>{

	public TreeNodeExtended<T> parent;
	public int level; 
	public int count;
	
	
	public T AB;
	public T BC;
	public T CD;
	public T DA;
	
	public TreeNodeExtended() {
		this.left = null;
		this.right = null;
		
		this.value = null;		
		this.parent = null;

		
		this.AB = null;
		this.BC = null;
		this.CD = null;
		this.DA = null;		
	}
		
	
	
	//method for search parent node for some child node
	public TreeNodeExtended<Vector> searchParent(TreeNodeExtended<Vector> node, TreeNodeExtended<Vector> child) {
		if(node.left != null) {
			if(node.left == child) {
				return (TreeNodeExtended<Vector>) node.left;
			} else {
				searchParent((TreeNodeExtended<Vector>) node.left, child);
			}
		}
		
		if(node.right != null) {
			if(node.right == child) {
				return (TreeNodeExtended<Vector>) node.right;
			} else {
				searchParent((TreeNodeExtended<Vector>) node.right, child);
			}
		}
		
		if(node.left == null && node.right == null) {
			if(node == child) {
				return node;
			}			
		}
						
		return node;
	}

	
}

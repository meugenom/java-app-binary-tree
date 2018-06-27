package application.extended;

import libraries.TreeNode;

public class TreeNodeExtended<T> extends TreeNode<T>{

	public TreeNodeExtended<T> parent;
	public int level; 
	public int count;
	public boolean rotateSide;
	
	
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
		
		this.rotateSide = false;
	}
		
	
	
	//method for search parent node for some child node
	public TreeNodeExtended<T> searchParent(TreeNodeExtended<T> node, TreeNodeExtended<T> child) {
		if(node.left != null) {
			if(node.left == child) {
				return (TreeNodeExtended<T>) node.left;
			} else {
				searchParent((TreeNodeExtended<T>) node.left, child);
			}
		}
		
		if(node.right != null) {
			if(node.right == child) {
				return (TreeNodeExtended<T>) node.right;
			} else {
				searchParent((TreeNodeExtended<T>) node.right, child);
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

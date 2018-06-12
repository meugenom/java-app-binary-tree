package libraries;

public class TreeNode<T> {

	public TreeNode<T> left, right;
	public T value;	

	/*
	 * methods getRight, setRight, getLeft, setLeft, setData, getData getLevel
	 */

	public TreeNode() {
		left = null;
		right = null;
		value = null;
	}

	public TreeNode(int n) {
		left = null;
		right = null;
		value = null;
	}

	public void setLeft(TreeNode<T> n) {
		left = n;
	}

	public TreeNode<T> getLeft() {
		return left;
	}

	public void setRight(TreeNode<T> n) {
		right = n;
	}

	public TreeNode<T> getRight() {
		return left;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public T getData() {
		return value;
	}

	/*
	 * 
	 * our recursion function for searching current Node
	 */

	private int searchLevelByNode(TreeNode<T> node, TreeNode<T> currentNode, int level) {
		// for 0 level
		if (node == currentNode) {
			return level;
		}
		if (node.left == null && node.right == null) {
			return -1;
		}
		int curLevel = level; // wenn -1, da  Knoten nicht in Teilbaum gefunden
		if (node.left != null) {
			curLevel = searchLevelByNode((TreeNode<T>) node.left, currentNode, level + 1);
		}
		if (curLevel == -1 && node.right != null) {
			curLevel = searchLevelByNode(node.right, currentNode, level + 1);
		}
		return curLevel;
	}

	public int getLevel(TreeNode<T> currentNode) {
		int level = 0;
		return searchLevelByNode(this, currentNode, level);
	}

	public boolean remove(TreeNode<T> node) {
		if (left == node) {
			left = null;
			return true;
		} else if (right == node) {
			right = null;
			return true;
		}
		return false;
	}
}

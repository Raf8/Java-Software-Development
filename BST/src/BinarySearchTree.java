


public class BinarySearchTree<E extends Comparable<E>> {
	class Node {
		E value;
		Node leftChild = null;
		Node rightChild = null;
		Node(E value) {
			this.value = value;
		}
		@Override
		public boolean equals(Object obj) {
			if ((obj instanceof BinarySearchTree.Node) == false)
				return false;
			@SuppressWarnings("unchecked")
			Node other = (BinarySearchTree<E>.Node)obj;
			return other.value.compareTo(value) == 0 &&
					other.leftChild == leftChild && other.rightChild == rightChild;
		}
	}
	
	protected Node root = null;
	
	protected void visit(Node n) {
		System.out.println(n.value);
	}
	
	public boolean contains(E val) {
		return contains(root, val);
	}
	
	protected boolean contains(Node n, E val) {
		if (n == null) return false;
		
		if (n.value.equals(val)) {
			return true;
		} else if (n.value.compareTo(val) > 0) {
			return contains(n.leftChild, val);
		} else {
			return contains(n.rightChild, val);
		}
	}
	
	public boolean add(E val) {
		if (root == null) {
			root = new Node(val);
			return true;
		}
		return add(root, val);
	}
	
	protected boolean add(Node n, E val) {
		if (n == null) {
			return false;
		}
		int cmp = val.compareTo(n.value);
		if (cmp == 0) {
			return false; // this ensures that the same value does not appear more than once
		} else if (cmp < 0) {
			if (n.leftChild == null) {
				n.leftChild = new Node(val);
				return true;
			} else {
				return add(n.leftChild, val);
			} 	
		} else {
			if (n.rightChild == null) {
				n.rightChild = new Node(val);
				return true;
			} else {
				return add(n.rightChild, val);
			} 	
		}
	}	
	
	public boolean remove(E val) {
		return remove(root, null, val);
	}
	
	protected boolean remove(Node n, Node parent, E val) {
		if (n == null) return false;

		if (val.compareTo(n.value) == -1) {
				return remove(n.leftChild, n, val);
		} else if (val.compareTo(n.value) == 1) {
				return remove(n.rightChild, n, val);
		} else {
			if (n.leftChild != null && n.rightChild != null){
				n.value = maxValue(n.leftChild);
				remove(n.leftChild, n, n.value);
			} else if (parent == null) {
				root = n.leftChild != null ? n.leftChild : n.rightChild;
			} else if (parent.leftChild == n){
				parent.leftChild = n.leftChild != null ? n.leftChild : n.rightChild;
			} else {
				parent.rightChild = n.leftChild != null ? n.leftChild : n.rightChild;
			}
			return true;
		}
	}

	protected E maxValue(Node n) {
		if (n.rightChild == null) {
			return n.value;
	    } else {
	       return maxValue(n.rightChild);
	    }
	}

	
	/*********************************************
	 * 
	 * IMPLEMENT THE METHODS BELOW!
	 *
	 *********************************************/
	
	
	// Method #1.
	public Node findNode(E val) {
		if(val == null) {
			return null;
		}

		if (contains(val)) {
			Node n = root;
			return findNode(n, val);
		}
		else 
		{ 
			return null; // this line is here only so this code will compile if you don't modify it
		}

	}
	protected Node findNode( Node n, E val) {
		if(n.value.equals(val)) {
			return n;
		}
		else if (n.value.compareTo(val) > 0) {
			return findNode(n.leftChild, val);
		} else {
			return findNode(n.rightChild, val);
		}
	}
	// Method #2.
	protected int depth(E val) {
		int count = 0;
		if(val == null) {
			return -1;
		}
		if(contains(val)) {
			Node n = root;
			return depth(n, val, count);
		}
		return -1; // this line is here only so this code will compile if you don't modify it
	}
	protected int depth(Node n, E val, int count) {
		if(n.value.equals(val)) {
			return count;
		}
		else if (n.value.compareTo(val) > 0) {
			count += 1;
			return depth(n.leftChild, val, count);
		} else {
			count += 1;
			return depth(n.rightChild, val, count);
		}
	}
	
	// Method #3.
	protected int height(E val) {
		if(val == null) {
			return -1;
		}
		if(contains(val)) {
			Node n = findNode(val);
			return height(n);
		} 
		return -1; // this line is here only so this code will compile if you don't modify it
		
	}
	protected int height(Node n) {
		if (n == null) return -1;
		
		if (n.leftChild == null && n.rightChild == null) { // leaf node
			return 0;
		}
		return 1 + Math.max(height(n.leftChild), height(n.rightChild));
	}

	// Method #4.
	protected boolean isBalanced(Node n) {
		if (n == null || !contains(n.value)) return false; 
		
		return Math.abs(height(n.leftChild) - height(n.rightChild)) < 2;
}
	
	// Method #5. .
	public boolean isBalanced() {
		Node N = root;
		if(isBalanced(N) && isBalanced(N.rightChild) && isBalanced(N.leftChild)) {
			return true;
		}else {
			return false;
		}
		
	}

}

package code.cracking.datastructures.ch04.treesandgraphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

class TreeNode<T extends Comparable<T>> {
	protected T data;
 	protected TreeNode<T> left;
	protected TreeNode<T> right;	
		
	public TreeNode(T value) {
		data = value;
		left = null;
		right = null;
	}
	
	public int compareTo(TreeNode<T> other) {
		return data.compareTo(other.data);
	}
}

public class BinarySearchTree<T extends Comparable<T>> {
	protected TreeNode<T> root = null;
	protected TreeNode<T> helper = null;
	
	public BinarySearchTree() {
		root = null;
	}

	public BinarySearchTree(TreeNode<T> r) {
		root = r;
	}

	public boolean lookup(T value) {
		return lookup(root, value);
	}

	private boolean lookup(TreeNode<T> node, T value) {
		if (node == null) 
			return false;

		if (value == node.data) {
			helper = node;
			return true;
		}
		else if (value.compareTo(node.data) <= 0 )
			return lookup(node.left, value);
		else 
			return lookup(node.right, value);
	}
	
	public void insert(T value) {
		root = insert(root, value);
	}

	private TreeNode<T> insert(TreeNode<T> node, T value) {
		if (node == null) {
			node = new TreeNode<T>(value);
			return node;
		}

		if (value.compareTo(node.data) <= 0 )
			node.left = insert(node.left, value);
		else
			node.right = insert(node.right, value);	
		
		return node;
	}
	
	
	public int depth() {
		return depthAtNode(root);
	}

	private int depthAtNode(TreeNode<T> node) {
		if (node == null)
			return 0;

		int l_depth = depthAtNode(node.left);
		int r_depth = depthAtNode(node.right);

		return ( Math.max(l_depth, r_depth) + 1 );
	}
	
	public int size() {
		return size(root);
	}

	private int size(TreeNode<T> node) {
		if (node == null)
			return 0;
		
		return size(node.left) + 1 + size(node.right);
	}
	
	/*
	 * Reference:
	 * 	-- http://www.cs.cmu.edu/~adamchik/15-121/lectures/Trees/code/BST.java
	 *  -- http://www.geeksforgeeks.org/maximum-width-of-a-binary-tree/
	 *   1
        /  \
       2    3
     /  \     \
    4    5     8 
              /  \
             6    7
     *        
	 */

	public int width() {
		int max = 0;
		int height = depth();
		for (int i=1;i<=height; i++) {
			int w_currLevel = width(root, i);
			if (w_currLevel > max)
				max = w_currLevel;
		}
		return max;
	}
	
	/*
	 * get width of a tree with root at level=depth
	 */
	private int width(TreeNode<T> root, int depth) {
		if (root == null)
			return 0;
		else if (depth == 1)
			return 1;
		else 
			return width(root.left, depth-1) + width(root.right, depth-1);
	}
	
	
	public void printTreeSimple()  {
		
		int height = depth();
		for (int level=1; level<=height;level++) {
			// print spaces 
			System.out.print("Level "+ level + " : ");
			int n_spaces = (int)(Math.pow(2, height-1) - Math.pow(2, level-1));
			for (int j=0; j<n_spaces; j++)
				System.out.print(" ");
			
			String strCurrLevel = printLevel(root, level);	
			System.out.print(strCurrLevel + "\n");
		}
		
	}
	
	
	public void printTree()  {		
		int height = depth();
		for (int level=1; level<=height;level++) {
			// print spaces 
			int n_spaces = (int)(Math.pow(2, height-1) - Math.pow(2, level-1));
			for (int j=0; j<n_spaces; j++)
				System.out.print(" ");
			
			String strCurrLevel = printLevel(root, level);	
			System.out.print(strCurrLevel + "\n");
			
			for (int j=0; j<n_spaces; j++)
				System.out.print(" ");
			String strCurrLevelHelper = printLevelHelper(root, level);
			System.out.print(strCurrLevelHelper + "\n");	
		}
		
	}

	private String printLevel(TreeNode<T> node, int level) {
		
		if (node == null) 
			return "";
		
		if (level == 1) {
			 return  node.data + " ";
		} 
		else {  // level > 1
			StringBuilder res = new StringBuilder();
			String l = printLevel(node.left, level-1);
			if (l.equals(""))
				res.append("  ");
			else 
				res.append(l);
			String r = printLevel(node.right, level-1);			
			if (r.equals(""))
				res.append("  ");
			else 
				res.append(r);
			return res.toString();
		}

	}
	
	private String printLevelHelper(TreeNode<T> node, int level) {
		
		if (node == null) 
			return "";
		
		if (level == 1) {
			 StringBuilder res = new StringBuilder();
			 if(node.left!=null)
				 res.append("/");
			 else
				 res.append(" ");
			 if(node.right!=null)
				 res.append("\\");
			 else
				 res.append(" ");
			 return res.toString();
		} 
		else {  // level > 1
			String l = printLevelHelper(node.left, level-1);
			String r = printLevelHelper(node.right, level-1);
			return l+r;
		}

	}
	
	/*****************************************************
	 * 
	 *  Traverse
	 * 
	 ****************************************************/
	 public void preOrderTraversal() {
		 preOrderTraversal(root);
	 }
	 
	 private void preOrderTraversal(TreeNode<T> node) {
		 if(node!=null) {
			 System.out.println("Visiting " + node.data + " ...");
			 preOrderTraversal(node.left);
			 preOrderTraversal(node.right);
		 }
	 }
	
	 public void inOrderTraversal() {
		 inOrderTraversal(root);
	 }
	 
	 private void inOrderTraversal(TreeNode<T> node) {
		 if(node!=null) {
			 preOrderTraversal(node.left);
			 System.out.println("Visiting " + node.data + " ...");
			 preOrderTraversal(node.right);
		 }
	 }
	 
	public int minDepth() {
		return minDepth(root);
	}
	
	private int minDepth(TreeNode<T> node) {
		if (node == null)
			return 0;
		
		return 1+Math.min(minDepth(node.left), minDepth(node.right));
	}
	 
	public boolean isBalanced() {
		if(root!=null) {
			return (depth() - minDepth() <= 1);
		} else {
			return true;
		}
	}
	
	
	public static BinarySearchTree<Integer> buildBalancedBST(int[] array) {
		if (array==null || array.length==0)
			return null;

		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		buildBalancedBST(bst, array, 0, array.length-1);
		return bst;
	}

	public static void buildBalancedBST(BinarySearchTree<Integer> bst, int[] array, int left,int right) 
	{
		if (left <= right) {
			int middle = (left + right)/2;
			bst.insert(array[middle]);		
			buildBalancedBST(bst, array, left, middle-1);
			buildBalancedBST(bst, array, middle+1,right);
		}	
	}

	
	public static ArrayList<LinkedList<TreeNode>> createLinkedLists(TreeNode root) {
		if (root == null)
			return null;

		ArrayList<LinkedList<TreeNode>> lists = new ArrayList<LinkedList<TreeNode>>();
		int level = 0;
		LinkedList<TreeNode> list = new LinkedList<TreeNode>();
		list.add(root);
		lists.add(level, list);
		
		while(true) {
			LinkedList<TreeNode> listNewLevel = new LinkedList<TreeNode>();
			for(int i=0; i<list.size();i++) {
				TreeNode node = list.get(i);
				if (node.left!=null) {
					listNewLevel.add(node.left);
				}
				if (node.right!=null) {
					listNewLevel.add(node.right);
				}
			}
			if (listNewLevel.size()>0) {
				lists.add(listNewLevel);
				level++;		
				list = listNewLevel;
			} else {
				break;
			}
		}

		return lists;
	}

	/*
	 * Write an algorithm to find the ¡®next¡¯ node (i.e., in-order successor) 
	 * of a given node in a binary search tree where each node has a link to 
	 * its parent.
	 * 
	 * 
	public TreeNode<T> inOrderSucc(final TreeNode<T> node) {
		if (node == null)
			return null;
		
		TreeNode<T> p = null;
		if (node.right != null) {
			p = leftMostChild(node.right);
		} else {
			while((p = node.parent) != null) {
				if (node == p.left) {
					return p;
					break;
				}
				node = p;
			}
		}
		return p;
	}

	private TreeNode<T> leftMostChild(TreeNode<T> node) {
		if(node==null) return null;
	while(node.left != null) node = node.left;
	return node;
	}

	*/
	
	
	
	/*
	 * 
	 * SLOW: since every node is accessed, and some are accessed multiple times
	 * 
	 */	
	public TreeNode<T> slowCommonAncestor(TreeNode<T> root, TreeNode<T> p, TreeNode<T> q) 
	{
		if (covers(root.left, p) && covers(root.left, q)) {
			return slowCommonAncestor(root.left, p, q);
		}
		if (covers(root.right, p) && covers(root.right, q)) {
			return slowCommonAncestor(root.right, p, q);
		}		
		return root;		
	}

	public boolean covers(TreeNode<T> root, TreeNode<T> node) {
		if (root == null) return false;
		if (root == node)  return true;
		return covers(root.left, node) || covers(root.right, node);
		
	}	
	
	/*
	 * FASTER: avoid unnessary accessment with more conditions
	 * 
	 */
	static int TWO_NODES_FOUND=2;
	static int ONE_NODE_FOUND =1;
	static int NO_NODES_FOUND=0;	
	
	public int covers(TreeNode<T> root, TreeNode<T> p, TreeNode<T> q) {
		int ret = NO_NODES_FOUND;
		if (root == null) return ret;
		if (root==p || root==q) ret +=1;
		ret += covers(root.left, p, q);
		if (ret == TWO_NODES_FOUND)
			return ret;
		return ret + covers(root.right, p, q);
	}
	
	public TreeNode<T> commonAncestor(TreeNode<T> root, TreeNode<T> p, TreeNode<T> q) {
		
		if ( (p==q) && (root.left==p || root.right==q))
			return root;
		
		int nodesFromLeft = covers(root.left,p,q);
		if (nodesFromLeft == TWO_NODES_FOUND) {
			if (root.left==p || root.left==q) 
				return root.left;
			else 
				return commonAncestor(root.left, p, q);
		} else if (nodesFromLeft == ONE_NODE_FOUND) {
			if (root==p) return p;
			else if (root==q) return q;
		}
		
		int nodesFromRight = covers(root.right,p,q);
		if (nodesFromRight == TWO_NODES_FOUND) {
			if (root.right==p || root.right==q) 
				return root.right;
			else 
				return commonAncestor(root.left, p, q);
		} else if (nodesFromRight == ONE_NODE_FOUND) {
			if (root==p) return p;
			else if (root==q) return q;
		}
		
		if (nodesFromLeft == ONE_NODE_FOUND && 
			nodesFromRight == ONE_NODE_FOUND)
			return root;
		else
			return null;		
	}
	
	/*
	 * 
	 * Print all paths in a binary tree whose sum equals sum
	 * 
	 */
	public void findSum(int sum) {
		ArrayList<Integer> paths = new ArrayList<Integer>();
		findSum((TreeNode<Integer>)root, sum, paths, 0);
	}
	
	@SuppressWarnings("unchecked")
	private void findSum(TreeNode<Integer> head, int sum, ArrayList<Integer> buffer, int level) {
		if(head==null) return;
		
		int tmp = sum;
		buffer.add(head.data);
		for(int i=level;i>=0;i--){
			tmp -= buffer.get(i);
			if (tmp == 0) 
				printArray(buffer, i, level);
		}
		
		ArrayList<Integer> c1 = (ArrayList<Integer>)buffer.clone();
		ArrayList<Integer> c2 = (ArrayList<Integer>)buffer.clone();
		findSum(head.left, sum, c1, level+1);
		findSum(head.right, sum, c2, level+1);
	}
	
	public void printArray(ArrayList<Integer> array, int from, int to)
	{
		System.out.print("Found a path : ");
		for(int i=from;i<=to; i++)
			System.out.print(array.get(i) + " ");
		System.out.println();
	}
	
	
	/*
	 * 
	 * Does a big BinaryTree T1 contains another small BinaryTree T2 ?
	 * O(n+k*m) : n is nodes in T1			m is nodes in T2
	 * 			  k is the number of occurrences of T2¡¯s root in T1
	 */
	public boolean containsTree(TreeNode<T> t1, TreeNode<T> t2) {
		if (t2 == null) return true;
		return subTree(t1, t1);
	}
	 
	public boolean subTree(TreeNode<T> t1, TreeNode<T> t2) {
		if (t1 == null) 
			return false;
		if (t1.data == t2.data) {
			if(matchTree(t1, t2))
				return true;
		}
		
		return (subTree(t1.left, t2) || subTree(t1.right, t2));
	}
	
	public boolean matchTree(TreeNode<T> r1, TreeNode<T> r2) {
		if (r1==null && r2==null)
			return true;
		if (r1==null || r2==null)
			return false;
		if (r1.data != r2.data)
			return false;
		
		return (matchTree(r1.left, r2.left) && 
				matchTree(r1.right, r2.right));		
	}
	
	
	
	 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random randomGen = new Random();
		int numOfNodes = randomGen.nextInt(10);
        System.out.println("Will generate " + numOfNodes + " nodes ...");
        
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        for(int i=0;i<numOfNodes;i++) {
			int num = randomGen.nextInt(50);
	        System.out.println("Inserting <" + num + ">...");
	        bst.insert(num);
		}	

        System.out.println("After initialization ... ");
        bst.printTree(); 

   /*     System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Starting pre order traversal ... ");
        bst.preOrderTraversal();
        
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Starting in order traversal ... ");
        bst.inOrderTraversal();
        
      */
        String isBalanced = bst.isBalanced() ? "Yes" : "No";
        System.out.println("Is balanced ? " + isBalanced);

        System.out.println("MinDepth is " + bst.minDepth());
        

        System.out.println("=========== Trying to build a balanced BT =============== ");
        int[] array = {1,2,3,4,5,6,7,8,9,10,11,12};
        BinarySearchTree<Integer> bst2 = BinarySearchTree.buildBalancedBST(array);
        bst2.printTree();
        
        System.out.println("=========== Generating Linked Lists at each level  ===============\n");
        ArrayList<LinkedList<TreeNode>> lists = BinarySearchTree.createLinkedLists(bst2.root);
        for(int i=0; i<lists.size(); i++) {
        	System.out.print("Level " + i + " : ");
        	for(TreeNode n : lists.get(i)) {
        		System.out.print(n.data + " ");
        	}
        	System.out.println();
        }
        
        bst2.lookup(2);
        TreeNode<Integer> p = bst2.helper;
        bst2.lookup(4);
        TreeNode<Integer> q = bst2.helper;
        TreeNode<Integer> ancestor = bst2.slowCommonAncestor(bst2.root, p, q);     
        System.out.print("\n=========== [SLOW] ealiest common ancestor of 2 and 4 is : " + ancestor.data);
        TreeNode<Integer> ancestor2 = bst2.commonAncestor(bst2.root, p, q);
        System.out.print("\n=========== [FAST] ealiest common ancestor of 2 and 4 is : " + ancestor2.data);
        
        
        System.out.println("\n~~~~~~~~~~~~~ Finding paths with sum 12 ~~~~~~~~~~~~~~ ");
        bst2.findSum(12);
	}
}


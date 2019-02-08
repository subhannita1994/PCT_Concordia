import java.util.Scanner;
import java.util.HashMap;
public class Tree {

	private static String preOrderDisplay="";
	public static class Node{
		//class node
		private String name;
		private Node parent;
		private Node lChild; private Node rChild;
		private int level;
		public Node(String name) { 
			//for root
			this.name = name;
			this.parent = null;
			this.lChild=null;
			this.rChild = null;
			this.level = 0;
		}
		public Node(String name, Node parent) {
			//for nodes other than root
			this.name = name;
			this.parent = parent;
			this.lChild = null;
			this.rChild = null;
			this.level = parent.getLevel()+1;
		}
		
		public void setLeftChild(Node child) { this.lChild = child;}
		public void setRightChild(Node child) { this.rChild = child;}
		public void addChild(Node child) {
			if(this.lChild==null)
				setLeftChild(child);
			else
				setRightChild(child);
		}
		public String getName() { return this.name;}
		public int getLevel() { return this.level;}
		public Node getLeftChild() { return this.lChild;}
		public Node getRightChild() { return this.rChild;}
		public Node[] getChildren() { 
			Node[] children = {getLeftChild(),getRightChild()};
			return children;
		}
		public Node getParent() { return this.parent;}
		
	}
	
	
	private static void preOrder(Node node) {
		//preorder traversal of tree
		
		if(node!=null) {
			preOrderDisplay+=node.getName()+" ";
			preOrder(node.getLeftChild());
			preOrder(node.getRightChild());
		}
	}
	
	
	public static void main(String[] args) {
		
		//-------building tree--------
		Scanner reader = new Scanner(System.in);
		int n = reader.nextInt();
		HashMap<String,Node> map = new HashMap<>();	//stores all nodes in the format : <NAME,Node>
		String str = reader.nextLine();
		str = reader.nextLine();
		String[] s = str.split(" ");
		Node root = new Node(s[0]);	//root node
		Node newNode = new Node(s[1],root);	//first child of root
		root.setLeftChild(newNode);
		//inserting to map
		map.put(s[0].toUpperCase(),root);
		map.put(s[1].toUpperCase(),newNode);
		
		for(int i=1;i<n;i++) {	//nlogn
			str = reader.nextLine();
			s = str.split(" ");
			Node parent = map.get(s[0].toUpperCase());	//existing parent node	//logn
			Node curNode = new Node(s[1],parent);	//child node
			parent.addChild(curNode);	//adding child to parent
			map.put(s[1].toUpperCase(),curNode);	//inserting new node
		}
		
		//verifying relationships
		n = reader.nextInt(); 
		str = reader.nextLine();
		String output="";
		for(int i=0;i<n;i++) {	//nlogn
			str = reader.nextLine();
			s = str.split(" ");
			Node node1 = map.get(s[0].toUpperCase());	//logn
			Node node2 = map.get(s[2].toUpperCase());	//logn
//			System.out.println(node1.getName());
			if(s[1].equals("child")) {
				try {
				if(node1.getParent().equals(node2))
					output+="T ";
				else
					output+="F ";
				}
				catch (NullPointerException e) {
					output+="F ";
				}
			}
			else if (s[1].equals("parent")) {
				try {
				if(node2.getParent().equals(node1))
					output+="T ";
				else
					output+="F ";
				}
				catch (NullPointerException e) {
					output+="F ";
				}
			}
			else if(s[1].equals("sibling")) {
				try {
					if(node1.getParent().equals(node2.getParent()))
						output+="T ";
					else
						output+="F ";
				}
				catch (NullPointerException e) {
					output+="F ";
				}
			}
			else if(s[1].equals("ancestor")) {
				boolean res = false;
				while(node2.getParent()!=null) {
					node2 = node2.getParent();
					if(node2.equals(node1)) {
						res = true;
						break;
					}
				}
				if(res)	output+="T ";
				else	output+="F ";
				
			}
			else if(s[1].equals("descendant")) {
				boolean res = false;
				while(node1.getParent()!=null) {
					node1 = node1.getParent();
					if(node1.equals(node2)) {
						res = true;
						break;
					}
				}
				if(res)	output+="T ";
				else	output+="F ";
			}
		}
		output.trim();
		System.out.println(output);
		
		//pre-order traversal
		preOrder(root);
		preOrderDisplay.trim();
		System.out.println(preOrderDisplay);
		reader.close();
	}
}

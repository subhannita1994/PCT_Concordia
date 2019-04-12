import java.util.Scanner;
import java.util.LinkedList;
public class insertAVLtree {

	static class Node{
		private int value;
		private Node leftChild;
		private Node rightChild;
		private int height;
		private Node parent;
		public Node(int v) {this.value = v; this.leftChild = null; this.rightChild = null; this.height = 0; this.parent = null;}
		public int getValue() {return this.value;}
		public Node getLeftChild() {return this.leftChild;}
		public Node getRightChild() {return this.rightChild;}
		public void setRightChild(Node child) {this.rightChild = child;}
		public void setLeftChild(Node child) {this.leftChild = child;}
		public void setHeight(int h) {this.height=h;}
		public void setParent(Node parent) {this.parent = parent;}
		public Node getParent() {return this.parent;}
		public int getHeight() {return this.height;}
		public void replaceChild(Node n1, Node n2) {
			if(this.leftChild.equals(n1))
				this.leftChild = n2;
			else
				this.rightChild = n2;
		}
	}
	
	static Node root = new Node(0);
	static Node leaf;
	private static void addNode(int v) {
	
		Node n = root;
		Node newNode = new Node(v);
		while(true) {
			if(v <= n.getValue()) {
				if(n.getLeftChild() == null) {
					n.setLeftChild(newNode);
					newNode.setParent(n);
					break;
				}
				else
					n = n.getLeftChild();
			}
			else {
				if(n.getRightChild() == null) {
					n.setRightChild(newNode);
					newNode.setParent(n);
					break;
				}
				else
					n = n.getRightChild();
			}
		}
		leaf = newNode;
		int h;
		while(newNode.getParent()!=null) {
			h = newNode.getHeight();
			newNode = newNode.getParent();
			newNode.setHeight(Math.max(h+1, newNode.getHeight()));
			System.out.println("height of "+newNode.getValue()+" : "+newNode.getHeight());
			
		}
	}
	
	private static int getBalanceFactor(Node node) {
		int hl,hr;
		if(node.getLeftChild() == null)
			hl = -1;
		else
			hl = node.getLeftChild().getHeight();
		if(node.getRightChild() == null)
			hr = -1;
		else
			hr = node.getRightChild().getHeight();
		return (hl-hr);
	}
	
	private static void rightRotate(Node node) {
		System.out.println("right rotate");
		Node C = node.getRightChild();
		Node P = node.getParent();
		Node PP = P.getParent();
		node.setRightChild(P);
		P.setParent(node);
		if(C!=null) {
			P.setLeftChild(C);
			C.setParent(P);
		}
		if(PP==null)
			root = node;
		else { 
			node.setParent(PP);
			PP.replaceChild(P,node);
		}
		//update height
		int hl,hr;
		if(P.getRightChild()!=null)
			hr = P.getRightChild().getHeight();
		else
			hr = -1;
		if(C==null)
			hl = -1;
		else
			hl = C.getHeight();
		P.setHeight(Math.max(hl, hr)+1);
		if(node.getLeftChild()!=null)
			hl = node.getLeftChild().getHeight();
		else
			hl = -1;
		hr = P.getHeight();
		node.setHeight(Math.max(hl, hr)+1);
	}
	
	private static void leftRotate(Node node) {
		System.out.println("left rotate");
		Node C = node.getLeftChild();
		Node P = node.getParent();
		Node PP = P.getParent();
		node.setLeftChild(P);
		P.setParent(node);
		if(C!=null) {
			P.setRightChild(C);
			C.setParent(P);
		}
		if(PP==null)
			root = node;
		else { 
			node.setParent(PP);
			PP.replaceChild(P,node);
		}
		//update height
		int hl,hr;
		if(P.getLeftChild()!=null)
			hl = P.getLeftChild().getHeight();
		else
			hl = -1;
		if(C==null)
			hr = -1;
		else
			hr = C.getHeight();
		P.setHeight(Math.max(hl, hr)+1);
		if(node.getRightChild()!=null)
			hr = node.getRightChild().getHeight();
		else
			hr = -1;
		hl = P.getHeight();
		node.setHeight(Math.max(hl, hr)+1);
	}
	private static void leftRightCase(Node node) {
		System.out.println("left right case");
		leftRotate(node.getRightChild());
		rightRotate(node.getParent());
	}
	private static void leftLeftCase(Node node) {
		System.out.println("left left case");
		rightRotate(node);
	}
	private static void rightLeftCase(Node node) {
		System.out.println("right left case");
		rightRotate(node.getLeftChild());
		leftRotate(node.getParent());
	}
	private static void rightRightCase(Node node) {
		System.out.println("right right case");
		leftRotate(node);
	}
	
	private static void balanceTree() {
		
		Node node = leaf;
		int b1,b2;
		do {
			b1 = getBalanceFactor(node);
			System.out.println("Balance factor of "+node.getValue()+" : "+ b1);
			if(b1 >= 2) {
				b2 = getBalanceFactor(node.getLeftChild());
				System.out.println("b2: "+b2);
				if(b2==-1)
					leftRightCase(node.getLeftChild());
				else
					leftLeftCase(node.getLeftChild());
			}
			else if(b1<=-2) {
				b2 = getBalanceFactor(node.getRightChild());
				System.out.println("b2: "+b2);
				if(b1==1)
					rightLeftCase(node.getRightChild());
				else
					rightRightCase(node.getRightChild());
			}
			node = node.getParent();
		}while(node.getParent().equals(null));
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner input = new Scanner(System.in);
		int v = input.nextInt();
		
		for(int i =1;i<=v;i++) {
			System.out.println("Inserting "+i);
			addNode(i);
			System.out.println("Balancing");
			balanceTree();
			
		}
		
		LinkedList<Node> list = new LinkedList<Node>();
		LinkedList<Integer> output = new LinkedList<Integer>();
		//print tree
		list.add(root);
		Node node;
		while(!list.isEmpty()) {
			node = list.pop();
			output.add(node.getValue());
			if(node.getLeftChild()==null)
				output.add(-1);
			else {
				list.add(node.getLeftChild());
				output.add(node.getLeftChild().getValue());
			}
			if(node.getRightChild() ==null)
				output.add(-1);
			else {
				list.add(node.getRightChild());
				output.add(node.getRightChild().getValue());
			}
		}
		
		System.out.println(output.toString());
		
		input.close();
		
	}

}

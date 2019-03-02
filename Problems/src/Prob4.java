import java.util.Scanner;
public class Prob4 {

	final static class Node{
		private int value;
		private Node lChild;
		private Node rChild;
		private Node parent;
		private int level;
		public Node(int value) {
			this.value = value;
			this.lChild = null;
			this.rChild = null;
			this.parent = null;
			this.level = 0;
		}
		public Node(int value,Node parent) {
			this.value = value;
			this.parent = parent;
			this.lChild = null;
			this.rChild = null;
			this.level = 0;
		}
		public int getValue() {return this.value;}
		public Node getLeftChild() {return this.lChild;}
		public Node getRightChild() { return this.rChild;}
		public void setLeftChild(Node node) {this.lChild = node;}
		public void setRightChild(Node node) { this.rChild = node;}
		public Node getParent() {return this.parent;}
		public int getLevel() {return this.level;}
		public void setLevel(int l) {this.level = l;}
	}
	
	private static void preorder(Node node) {
		
		if(node!=null) {
			System.out.print(Integer.toString(node.getValue())+" ");
			preorder(node.getLeftChild());
			preorder(node.getRightChild());
			
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
		Scanner reader = new Scanner(System.in);
		int input = reader.nextInt();
		Node root = new Node(input);
		Node pointer=null;
		input = reader.nextInt();
		//making the bst
		while(input!=-1) {
			pointer = root;
			while(true) {
				if(input<=pointer.getValue()) {
					if(pointer.getLeftChild()==null) {
						Node newNode = new Node(input,pointer);
						pointer.setLeftChild(newNode);
						break;
					}
					else
						pointer = pointer.getLeftChild();
				}
				else
				{
					if(pointer.getRightChild()==null) {
						Node newNode = new Node(input,pointer);
						pointer.setRightChild(newNode);
						break;
					}
					else
						pointer = pointer.getRightChild();
				}
			}
			
			input = reader.nextInt();
		}
		
		//testing for avl
		int leftLevel,rightLevel = 0;
		while(pointer!=null) {
			if(pointer.getLeftChild()!=null)
				leftLevel = pointer.getLeftChild().getLevel();
			else
				leftLevel = -1;
			if(pointer.getRightChild()!=null)
				rightLevel = pointer.getRightChild().getLevel();
			else
				rightLevel = -1;
			if(Math.abs(leftLevel-rightLevel)<=1) {
				pointer.setLevel(Math.max(leftLevel, rightLevel)+1);
				pointer = pointer.getParent();
			}
			else {
				System.out.println("NOT");
				return;
			}
		}
		
		//printing preorder
		preorder(root);
		
		
		reader.close();
	}

}

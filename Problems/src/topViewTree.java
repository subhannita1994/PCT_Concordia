import java.util.Scanner;
import java.util.LinkedList;
public class topViewTree {

	static Node root;
	static LinkedList<Node> visible;
	
	static class Node{
		private int value;
		private int x; private int y;
		private Node left;
		private Node right;
		public Node(int v,int x, int y) {
			this.value = v;
			this.x = x;
			this.y = y;
			this.left = null;
			this.right = null;
		}
		public int getValue() {return this.value;}
		public Node getLeft() {return this.left;}
		public Node getRight() {return this.right;}
		public void setLeft(Node n) {this.left = n;}
		public void setRight(Node n) {this.right = n;}
		public int getX() {return this.x;}
		public int getY() {return this.y;}
	}
	
	private static void addToVisible(Node n) {
		
		if(n.getX() < visible.get(0).getX()) {
			visible.addFirst(n);
			return;
		}
		for(int i=0;i<visible.size();i++) {
			if(visible.get(i).getX() == n.getX() && visible.get(i).getY() < n.getY()) {
//				System.out.println("visible");
				visible.remove(i);
				visible.add(n);
				return;
			}
			else if(visible.get(i).getX() == n.getX() && visible.get(i).getY() > n.getY()) {
				System.out.println("not visible");
//				return;
			}
			try {
				if(visible.get(i+1)!=null) {
					if(n.getX() > visible.get(i).getX() && n.getX() < visible.get(i+1).getX()) {
						visible.add(i+1, n);
						return;
					}	
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			}
		}
		visible.add(n);
		
	}
	
	private static void insert(int v) {
		
		Node n = root;
		while(n!=null) {
			System.out.println("Current node: "+n.getValue());
			if(v<n.getValue()) {
				if(n.getLeft()==null) {
					int x = n.getX() - 1;
					int y = n.getY() -1;
//					System.out.println("left child "+x+" "+y);
					Node newNode = new Node(v,x,y);
					n.setLeft(newNode);
					addToVisible(newNode);
					return;
				}
				else
					n = n.getLeft();
			}
			else {
				if(n.getRight()==null) {
					int x = n.getX() + 1;
					int y = n.getY() -1;
//					System.out.println("right child "+x+" "+y);
					Node newNode = new Node(v,x,y);
					n.setRight(newNode);
					addToVisible(newNode);
					return;
				}
				else
					n = n.getRight();
			}	
		}
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner reader = new Scanner(System.in);
		int n = reader.nextInt();
		int v = reader.nextInt();
		visible = new LinkedList<Node>();
		
		root = new Node(v,0,0);
		visible.add(root);
		
		for(int i=0;i<n-1;i++) {
			v = reader.nextInt();
			insert(v);
		}
		
		for(int i =0;i<visible.size();i++) {
			System.out.println(visible.get(i).getValue());
		}
		
		
		reader.close();

	}

}

import java.util.Scanner;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
public class Prob3 {
	
	public static void main(String[] args) {
		
		final class Node implements Comparable{
			private String name;
			private Node leftChild;
			private Node rightChild;
			private Node parent;
			private int status;
			public Node(String name) {
				this.name = name;
				this.parent = null;
				this.status = -1;
			}
			public Node(String name, Node parent) {
				this.name = name;
				this.parent = parent;
				this.status = -1;
			}
			public void setChild(Node child) {
				if(this.leftChild==null)	this.leftChild = child;
				else this.rightChild = child;
			}
			public Node getParent() {
				return this.parent;
			}
			public void setStatus(int status) {
				this.status = status;
			}
			public int getStatus() {
				return this.status;
			}
			public Node getLeftChild() {
				return this.leftChild;
			}
			public Node getRightChild() {
				return this.rightChild;
			}
			public String getName() {
				return this.name;
			}
			@Override
			public int compareTo(Object o) {
				// TODO Auto-generated method stub
				return 0;
			}
			
		}
		
		
		Scanner reader = new Scanner(System.in);
		String input = reader.nextLine();
		
		//taking input
		String ch = input.substring(1, input.indexOf("(",1));
		Node root = new Node(ch);
		Node pointer = root;
		input = input.substring(input.indexOf("(", 1));
		while(!input.equals(")")) {
			if(input.charAt(0)=='(') {
//				System.out.print("found ( ");
				ch = input.substring(1, input.indexOf(")", 1));	
//				System.out.print("ch: "+ch);
				if(ch.contains("(")) {
					ch = input.substring(1, input.indexOf("(",1));
//					System.out.print("correct ch: "+ch);
					input = input.substring(input.indexOf("(",1));
//					System.out.print(" input is now: "+input);
				}
				else {
					input = input.substring(input.indexOf(")",1));
//					System.out.print(" input is now "+input);
				}
				Node newNode = new Node(ch,pointer);
				pointer.setChild(newNode);
				pointer = newNode;
//				System.out.println(" pointer: "+pointer.getName());
			}
			else if(input.charAt(0)==')') {
//				System.out.print("found ) ");
				pointer = pointer.getParent();
				input = input.substring(1);
//				if(pointer!=null)
//					System.out.println(" pointer:"+pointer.getName()+" input is now: "+input);
			}
			
		}
		
		input = reader.nextLine();
		int dist = reader.nextInt();
		String output = "";
		//printing tree
		LinkedList<Node> queue = new LinkedList<Node>();
		queue.add(root);
		while(!queue.isEmpty()) {
			Node node = queue.removeFirst();
			output += node.getName()+" ";
//			System.out.println("output: "+output);
			if(input.equals(node.getName()))
				pointer = node;
			if(node.getLeftChild()!=null) {
				queue.add(node.getLeftChild());
//				System.out.print("left child added: "+node.getLeftChild().getName());
			}
			if(node.getRightChild()!=null) {
				queue.add(node.getRightChild());
//				System.out.print("right child added: "+node.getRightChild().getName());
			}
			
		}
		
		//printing nodes at dist from pointer
		queue.clear();
		pointer.setStatus(1);
		queue.add(pointer);
		int size;
		for(int i =0;i<dist;i++) {
			size = queue.size();
//			System.out.println("Size of queue= "+Integer.toString(size));
			while(size>0) {
				Node node = queue.remove();
//				System.out.print("Node:"+node.getName()+" ");
				if(node.getParent()!=null) {
					if(node.getParent().getStatus()==-1) {
						node.getParent().setStatus(1);
						queue.add(node.getParent());
//						System.out.print("unvisited parent:"+node.getParent().getName()+" ");
					}
				}
				if(node.getLeftChild()!=null) {
					if(node.getLeftChild().getStatus()==-1) {
						node.getLeftChild().setStatus(1);
						queue.add(node.getLeftChild());
//						System.out.print("unvisited left child:"+node.getLeftChild().getName()+" ");
					}
				}
				if(node.getRightChild()!=null) {
					if(node.getRightChild().getStatus()==-1) {
						node.getRightChild().setStatus(1);
						queue.add(node.getRightChild());
//						System.out.print("unvisited right child:"+node.getRightChild().getName()+" ");
					}
				}
				size--;
			}
		}
		output = output.trim();
		
		while(!queue.isEmpty()) {
			output+=" "+ queue.remove().getName();
		}
		
		output = output.trim();
		System.out.println(output);
		
		reader.close();
		
	}

}

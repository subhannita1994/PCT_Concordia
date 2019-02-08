import java.util.Scanner;
import java.util.LinkedList;
import java.util.Stack;
public class Graph{
	
	enum Status{ VISITED, UNVISITED;}
	
	
	public static void main(String[] args) {
		
		final class Node {
			private int info;
			private LinkedList<Node> next;
			private Status status;
			public Node(int info) { this.info = info; this.next=new LinkedList<Node>(); this.status=Status.UNVISITED;}
//			public int getInfo() { return this.info;}
			public Status getStatus() { return this.status;}
			public void setStatus(Status s) { this.status =s;}
			public void addNext(Node n) { next.add(n);}
			public LinkedList<Node> getNext(){ return this.next;}
		}
//		final class Edge{
//			private Node source;
//			private Node dest;
//			public Edge(Node s, Node d) { this.source=s; this.dest=d;}
//			public Node getSource() { return this.source;}
//			public Node getDest() { return this.dest;}
//		}
		
		Scanner reader = new Scanner(System.in);
		int n = reader.nextInt();
		LinkedList<Node> vertices = new LinkedList<Node>();
		LinkedList<Integer> v = new LinkedList<>();
//		LinkedList<Edge> edges = new LinkedList<Edge>();
		int[] a= new int[3];
		Node[] node = new Node[3];
		//building graph
		for(int i=0;i<n;i++) {
			for(int j=0;j<3;j++) {
				a[j] = reader.nextInt();
				if(a[j]!=-1 && v.contains(a[j]))
					node[j] = vertices.get(v.indexOf(a[j]));
				else if(a[j]!=-1) {
					v.add(a[j]);
					node[j] = new Node(a[j]);
					vertices.add(node[j]);
				}	
			}
			if(a[1]!=-1 && a[0]!=-1) {
				node[1].addNext(node[0]);
			}
			if(a[0]!=-1 && a[2]!=-1) {
				node[0].addNext(node[2]);
			}
		}
		
		
		//looking for cycle
		Stack<Node> path = new Stack<Node>();
		path.push(vertices.get(0));
		Node curNode;
		while(!path.isEmpty()) {
			curNode = path.pop();
			if(curNode.getStatus()==Status.UNVISITED) {
				curNode.setStatus(Status.VISITED);
				path.addAll(curNode.getNext());
			}
			else {
				System.out.println("YES");
				reader.close();
				return;
			}
			
		}
		System.out.println("NO");
		reader.close();
		
	}
}
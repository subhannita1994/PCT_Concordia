//https://practice.geeksforgeeks.org/problems/detect-cycle-in-a-directed-graph/1
import java.util.Scanner;
import java.util.LinkedList;
public class detectCyclesInDirectedGraph {

	enum status{ VISITED, UNVISITED;}
	static class Edge{
		private Node from;
		private Node to;
		public Edge(Node from, Node to) {this.from = from; this.to = to;}
	}
	static class Node{
		private int value;
		private LinkedList<Edge> edges;
		private status status;
		public Node(int value) {
			this.value = value;
			edges = new LinkedList<Edge>();
			this.status = status.UNVISITED;
		}
		public int getValue() {return this.value;}
		public void addEdge(Edge e) {this.edges.add(e);}
	}
	
	
	static LinkedList<Node> nodes;
	
	
	static int findIndexOf(int key) {
		for(int i =0;i<nodes.size();i++) {
			if(nodes.get(i).getValue()==key)
				return i;
		}
		return -1;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner reader = new Scanner(System.in);
		int n = reader.nextInt();
		int m = reader.nextInt();
		int v1,v2,i1,i2;
		Node node1,node2; Edge edge;
		nodes = new LinkedList<Node>();
		for(int i = 0;i < m; i++) {
			v1 = reader.nextInt();
			i1 = findIndexOf(v1);
			if(i1 == -1) {
				node1 = new Node(v1);
				nodes.add(node1);
			}
			else
				node1 = nodes.get(i1);
			v2 = reader.nextInt();
			i2 = findIndexOf(v2);
			if(i2 == -1) {
				node2 = new Node(v2);
				nodes.add(node2);
			}
			else
				node2 = nodes.get(i2);
			edge = new Edge(node1,node2);
			node1.addEdge(edge);
		}
		
		LinkedList<Node> list = new LinkedList<Node>();
		list.add(nodes.get(0));
		
		
	}

}

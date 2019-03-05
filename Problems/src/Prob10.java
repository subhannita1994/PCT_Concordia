import java.util.Scanner;
import java.util.LinkedList;
public class Prob10 {

	static enum status{UNVISITED, VISITED;}
	static class Node{
		private int value;
		private LinkedList<Edge> edges;
		private status status;
		public Node(int v) {this.value = v;this.edges = new LinkedList<Edge>(); this.status = status.UNVISITED;}
		public int getValue() {return this.value;}
		public void addEdge(Edge e) {this.edges.add(e);}
		public void setStatus(status s) {this.status = s;}
		public status getStatus() {return this.status;}
		public LinkedList<Edge> getEdges(){
			LinkedList<Edge> e = new LinkedList<Edge>();
			for(int i =0;i<edges.size();i++) {
				if(edges.get(i).getTo().getStatus()==status.UNVISITED)
					e.add(edges.get(i));
			}
			return e;
		}
	}
	static class Edge{
		private Node from; private Node to; 
		public Edge(Node n1, Node n2) { this.from = n1; this.to = n2; }
		public Node getTo() {return this.to;}
	}
	private static int findIndexOf(int key) {
		for(int i =0;i<nodes.size();i++){
			if(nodes.get(i).getValue()==key)
				return i;
		}
		return -1;
	}
	private static String findPath(Node source, Node dest) {
		
		LinkedList<Node> path = new LinkedList<Node>();
		source.setStatus(status.VISITED);
		path.add(source);
		Node v,w; LinkedList<Edge> outgoing;
		while(!path.isEmpty()) {
			v = path.remove();
			outgoing = v.getEdges();
			for(int i =0;i<outgoing.size();i++) {
				w = outgoing.get(i).getTo();
				if(w.equals(dest))
					return "YES ";
				else {
					w.setStatus(status.VISITED);
					path.add(w);
				}
			}
		
		}
		return "NO ";
		
	}
	private static int m,n;
	private static LinkedList<Node> nodes;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner reader = new Scanner(System.in);
		m = reader.nextInt(); n = reader.nextInt();
		nodes = new LinkedList<Node>();
		int input,index; Node node1,node2;
		for(int i =0;i<n;i++) {
			input = reader.nextInt();
			index = findIndexOf(input);
			if(index==-1) {
				node1 = new Node(input);
				nodes.add(node1);
			}
			else 
				node1 = nodes.get(index);
			input = reader.nextInt();
			index = findIndexOf(input);
			if(index==-1) {
				node2 = new Node(input);
				nodes.add(node2);
			}
			else
				node2 = nodes.get(index);
			Edge e = new Edge(node1,node2);
			node1.addEdge(e);
		}
		
		index = reader.nextInt();
		String output="";
		for(int i =0;i<index;i++) {
			input = reader.nextInt();
			node1 = nodes.get(findIndexOf(input));
			input = reader.nextInt();
			node2 = nodes.get(findIndexOf(input));
			output+= findPath(node1,node2);
		}
		System.out.println(output.trim());
		reader.close();
	}

}

import java.util.Scanner;
import java.util.LinkedList;
public class components {

	enum status{VISITED, UNVISITED};
	static class Edge{
		private Node node1;
		private Node node2;
		private status status;
		public Edge(Node n1, Node n2) {
			this.node1 = n1; this.node2 = n2; this.status = status.UNVISITED;
		}
		public status getStatus() {return this.status;}
		public Node getOtherNode(Node n) {
			if(this.node1.equals(n))
				return this.node2;
			else
				return this.node1;
		}
	}
	static class Node{
		private int value;
		private LinkedList<Edge> edges;
		private status status;
		public Node(int v) {
			this.value = v;
			this.edges = new LinkedList<Edge>();
			this.status = status.UNVISITED;
		}
		public int getValue() {return this.value;}
		public void addEdge(Edge e) {this.edges.add(e);}
		public void setStatus(status s) {this.status = s;}
		public status getStatus() {return this.status;}
		public LinkedList<Node> unvisitedNodes(){
			LinkedList<Node> n = new LinkedList<Node>();
			for(int i=0;i<edges.size();i++) {
				if(edges.get(i).getStatus()==status.UNVISITED) {
					n.add(edges.get(i).getOtherNode(this));
				}
			}
			return n;
		}
	}
	
	static LinkedList<Node> nodes = new LinkedList<Node>();
	
	private static int indexOfNode(int v) {
		for(int i=0;i<nodes.size();i++) {
			if(nodes.get(i).getValue()==v)
				return i;
		}
		return -1;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner reader = new Scanner(System.in);
		
		int n = reader.nextInt();
		int v1,v2,i1,i2; Node n1,n2;
		for(int i =0;i<n;i++) {
			v1 = reader.nextInt();
			v2 = reader.nextInt();
			i1 = indexOfNode(v1);
			i2 = indexOfNode(v2);
			if(i1==-1) {
				n1 = new Node(v1);
				nodes.add(n1);
			}
			else {
				n1 = nodes.get(i1); 
			}
			if(i2==-1) {
				n2 = new Node(v2);
				nodes.add(n2);
			}
			else {
				n2 = nodes.get(i2);
			}
			Edge e = new Edge(n1,n2);
			n1.addEdge(e);
			n2.addEdge(e);
		}
		
		Node node = nodes.get(0);
		int minComp = 2*n; int maxComp = 2;
		LinkedList<Node> list  =  new LinkedList<Node>();
		node.setStatus(status.VISITED);
		list.add(node);
		LinkedList<Node> unvisitedNodes;
		while(!list.isEmpty()) {
			node = list.removeLast();
			unvisitedNodes = node.unvisitedNodes();
			
			if(list.size()==0 && unvisitedNodes.size()==0) {
				for(int i =0;i<nodes.size();i++) {
					if(nodes.get(i).getStatus()==status.UNVISITED) {
						nodes.get(i).setStatus(status.VISITED);
						list.add(nodes.get(i));
						break;
					}
				}
			}
			
		}
		
	}

}

import java.util.Scanner;
import java.util.Arrays;
import java.util.LinkedList;
public class Prob6 {

	
	static enum status{
		VISITED, UNVISITED;
	}
	final static class Node{
		private int value;
		private status v;
		public Node(int value) {this.value = value; this.v = status.UNVISITED;}
		public int getValue() {return this.value;}
		public void setStatus(status v) {this.v = v;}
		public status getStatus() {return this.v;}
		public boolean equals(int v) { return (this.value==v);}
	}
	final static class Edge{
		private Node n1;
		private Node n2;
		public Edge(Node n1, Node n2) {this.n1 = n1; this.n2 = n2; }
		public LinkedList<Node> getNodes() { LinkedList<Node> arr = new LinkedList<Node>(); arr.add(n1); arr.add(n2);  return arr;};
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner reader = new Scanner(System.in);
		
		int m = reader.nextInt();
		LinkedList<Node> nodes = new LinkedList<Node>();
		int n = reader.nextInt();
		LinkedList<Edge> edges = new LinkedList<Edge>();
		int v1,v2,j,i1,i2;	Node node1 = null,node2 = null;
		//taking input
		for(int i =0;i<n;i++) {
			v1 = reader.nextInt(); v2 = reader.nextInt();
			i1 = nodes.indexOf(new Node(v1)); 	i2 = nodes.indexOf(new Node(v2));
			if(i1!=-1) {
				node1 = nodes.get(i1);
			}
			else {
				node1 = new Node(v1);
				nodes.add(node1);
			}
			if(i2!=-1) {
				node1 = nodes.get(i2);
			}
			else {
				node2 = new Node(v2);
				nodes.add(node2);
			}
			edges.add(new Edge(node1,node2));
			
		}
		
		//bfs to count number of connected subgraphs
		LinkedList<Node> list = new LinkedList<Node>();
		nodes.get(0).setStatus(status.VISITED);
		list.add(nodes.get(0));
		int counter = 0;
		while(!list.isEmpty()) {
			
			while(!list.isEmpty()) {
				node1 = list.remove();	//remove first node from queue
				for(int i=0;i<edges.size();i++) {
					LinkedList<Node> arr = edges.get(i).getNodes();
					j = arr.indexOf(node1);
					if(j!=-1) {	//if any edge has this node: remove this edge, mark the connected node as visited and push
						j ^= 1;
						node2 = arr.get(j);
						node2.setStatus(status.UNVISITED);
						list.add(node2);
						edges.remove(i);
					}
				}
			}
			counter++;	//end of one connected subgraph
			//looking for any unvisited node
			for(int i =1;i<m;i++) {
				if(nodes.get(i).getStatus()==status.UNVISITED) {
					nodes.get(i).setStatus(status.VISITED);
					list.add(nodes.get(i));
				}
			}
		}
		
		if(counter==1)
			System.out.println("1 "+counter);
		else
			System.out.println("0 "+counter);
		
		reader.close();
	}

}

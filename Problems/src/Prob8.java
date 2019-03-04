import java.util.Scanner;
import java.util.LinkedList;
public class Prob8{
	
	private static LinkedList<Vertex> printed = new LinkedList<Vertex>();
	
	enum status{VISITED, UNVISITED;}
	static class Edge{
		private Vertex from;
		private Vertex to;
		private status status;
		public Edge(Vertex v1, Vertex v2) {this.from = v1; this.to = v2; this.status = status.UNVISITED;}
		public status getStatus() {return this.status;}
		public void setStatus(status s) {this.status = s;}
		public Vertex getTo() {return this.to;}
		public Vertex getFrom() {return this.from;}
	}
	static class Vertex{
		private int value;
		private status status;
		private LinkedList<Edge> edges;
		public Vertex(int v) {this.value = v; this.status = status.UNVISITED; this.edges = new LinkedList<Edge>();}
		public int getValue() {return this.value;}
		public void addEdge(Edge e) {this.edges.add(e);}
		public LinkedList<Edge> getEdges(){return this.edges;}
		public void setStatus(status s) {this.status = s;}
		public status getStatus() {return this.status;}
		public Edge getUnvisitedEdges(){
			for(int i=0;i<edges.size();i++) {
				if(edges.get(i).getStatus()==status.UNVISITED) {
					return edges.get(i);
				}
			}
			return null;
		}
	}
	private static void printCycle(LinkedList<Vertex> v) {
		LinkedList<Integer> print = new LinkedList<Integer>();
		for(int i =0;i<v.size();i++) {
			if(print.contains(v.get(i).getValue()))
				break;
			else {
				print.add(v.get(i).getValue());
				printed.add(v.get(i));
			}
		}
		print.sort(null);
		System.out.println(print.toString());
		
	}
	
	private static int findIndexOf(LinkedList<Vertex> v, int key) {
		for(int i =0;i<v.size();i++) {
			if(v.get(i).getValue()==key)
				return i;
		}
		return -1;
	}
	
	public static void main(String[] args) {
		
		Scanner reader = new Scanner(System.in);
		int m = reader.nextInt(); int n = reader.nextInt();
		LinkedList<Vertex> vertices = new LinkedList<Vertex>();
		int v1,v2,i1,i2;	Vertex vertex1,vertex2;	Edge edge;
		for(int i = 0;i<n;i++) {
			v1 = reader.nextInt(); v2 = reader.nextInt();
			i1 = findIndexOf(vertices,v1); 
			if(i1!=-1)
				vertex1 = vertices.get(i1);
			else {
				vertex1 = new Vertex(v1);
				vertices.add(vertex1);
			}
			i2 = findIndexOf(vertices,v2);
			if(i2!=-1)
				vertex2 = vertices.get(i2);
			else {
				vertex2 = new Vertex(v2);
				vertices.add(vertex2);
			}
			edge = new Edge(vertex1, vertex2);
			vertex1.addEdge(edge);
		}
		
		
		
		int counter = 0;	//counts number of visited nodes
		LinkedList<Vertex> stack = new LinkedList<Vertex>();
		vertices.get(0).setStatus(status.VISITED);
		stack.push(vertices.get(0));
		Vertex v,w;	Edge outgoing;
		while(counter!=n) {
			if(stack.isEmpty()) {
				for(int i =0;i<m;i++) {
					if(vertices.get(i).getStatus() == status.UNVISITED) {
						vertices.get(i).setStatus(status.VISITED);
						stack.push(vertices.get(i));
						break;
					}
				}
			}
			v = stack.peek();
			outgoing = v.getUnvisitedEdges();
			if(outgoing==null) {
				stack.pop();
				
			}
			else {
				outgoing.setStatus(status.VISITED);
				counter++;
				w = outgoing.getTo();
				if(w.getStatus()==status.UNVISITED) {
					w.setStatus(status.VISITED);
					stack.push(w);
				}
				else {
					if(stack.contains(w)) {
						//cycle detected : print and store
						stack.push(w);
						printCycle(stack);
						stack.pop();
					}
				}
			}
		}
		
		for(int i =0;i<vertices.size();i++) {
			if(!printed.contains(vertices.get(i))) {
				printed.add(vertices.get(i));
				System.out.println("["+vertices.get(i).getValue()+"]");
			}
				
		}
		
		reader.close();
	}
}
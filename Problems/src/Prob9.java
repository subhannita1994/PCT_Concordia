import java.util.Scanner;
import java.util.LinkedList;
public class Prob9 {

	enum status{VISITED,UNVISITED};
	static class Edge{
		private Vertex from; private Vertex to; private int weight; private status status;
		public Edge(Vertex v1, Vertex v2, int w) {this.from = v1; this.to = v2; this.weight = w; this.status = status.UNVISITED;}
		public void setStatus(status s) {this.status = s;}
		public status getStatus() {return this.status;}
		public Vertex getTo() {return this.to;}
		public int getWeight() {return this.weight;}
		public Vertex getOtherVertex(Vertex v) {
			if(v.equals(this.from))
				return this.to;
			else
				return this.from;
		}
	}
	static class Vertex{
		private char value;
		private LinkedList<Edge> edges; 
		private int dist;
		private status status;
		public Vertex(char v) {this.value = v; this.edges = new LinkedList<Edge>(); this.dist = Integer.MAX_VALUE; this.status = status.UNVISITED;}
		public char getValue() {return this.value;}
		public void addEdge(Edge e) {this.edges.add(e);}
		public void reset() {
			this.dist = Integer.MAX_VALUE;
			for(int i =0;i<edges.size();i++)
				edges.get(i).setStatus(status.UNVISITED);
		}
		public void setDist(int d) {
			if(d<this.dist)
				this.dist = d;
		}
		public int getDist() {return this.dist;}
		public void setStatus(status s) {this.status = s;}
		public status getStatus() {return this.status;}
		public LinkedList<Edge> getUnvisitedEdges(){
			LinkedList<Edge> e = new LinkedList<Edge>();
			for(int i =0;i<edges.size();i++) {
				if(edges.get(i).getStatus() == status.UNVISITED)
					e.add(edges.get(i));
			}
			return e;
		}
	}
	
	static LinkedList<Vertex> vertices = new LinkedList<Vertex>();
	static int m;
	
	private static int findIndexOf(char key) {
		for(int i =0;i<vertices.size();i++) {
			if(vertices.get(i).getValue()==key)
				return i;
		}
		return -1;
	}
	
	private static void print(LinkedList<Vertex> l) {
		for(int i =0;i<l.size();i++) {
			System.out.print(l.get(i).getValue());
		}System.out.println();
	}
	
	private static int shortestPath(Vertex root, Vertex dest) {
		
		for(int i =0;i<vertices.size();i++)
			vertices.get(i).reset();
		
		root.setDist(0);
		LinkedList<Vertex> list = new LinkedList<Vertex>();
		list.add(root);
		int counter = 0;
		Vertex v,w; LinkedList<Edge> outgoing;
		while(counter!=m) {
//			System.out.println("list:"); print(list);
			v = list.remove();
			v.setStatus(status.VISITED);
			outgoing = v.getUnvisitedEdges();
			for(int i =0;i<outgoing.size();i++) {
				w = outgoing.get(i).getOtherVertex(v);
				w.setDist(v.getDist()+outgoing.get(i).getWeight());
				outgoing.get(i).setStatus(status.VISITED);
				counter++;
				if(!w.equals(dest) && !list.contains(w))
					list.add(w);
			}
			
		}
		
		return dest.getDist();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner reader = new Scanner(System.in);
		m = reader.nextInt();
		String input; char ch; int index,weight; Vertex v1, v2;
		for(int i =0;i<m;i++) {
			input = reader.next();
			ch = input.charAt(0);
			index = findIndexOf(ch);
			if(index==-1) { 
				v1 = new Vertex(ch);
				vertices.add(v1);
			}
			else
				v1 = vertices.get(index);
			ch = input.charAt(1);
			index = findIndexOf(ch);
			if(index==-1) {
				v2 = new Vertex(ch);
				vertices.add(v2);
			}
			else
				v2 = vertices.get(index);
			weight = reader.nextInt();
			Edge e = new Edge(v1,v2,weight);
			v1.addEdge(e); v2.addEdge(e);
			
		}
		
		Vertex root, dest; String output="";
		input = reader.nextLine();
		for(int i =0;i<5;i++) {
			input = reader.nextLine();
			ch = input.charAt(0);
			root = vertices.get(findIndexOf(ch));
			ch = input.charAt(1);
			dest = vertices.get(findIndexOf(ch));
			output+= Integer.toString(shortestPath(root,dest))+"  ";
		}
		
		System.out.println(output.trim());
		reader.close();
	}

}

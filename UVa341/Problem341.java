import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;


public class Problem341 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int cases = 0;
		int count = 1;
		while ((cases = scan.nextInt()) != 0) {
			ArrayList<Node<Integer>> nodes = new ArrayList<Node<Integer>>();
			for (int i = 1; i <= cases; i++) {
				Node<Integer> c = getNode(nodes, i);
				if (c == null) {
					c = new Node<Integer>(i);
					nodes.add(c);
				}
				
				int n = scan.nextInt();
				while (n-- > 0) {
					int v = scan.nextInt();
					int w = scan.nextInt();
					Node<Integer> child = getNode(nodes, v);
					if (child == null) {
						child = new Node<Integer>(v);
						nodes.add(child);
					}
					c.addChild(child, w);
				}
			}
			int s = scan.nextInt();
			int g = scan.nextInt();
			Node<Integer> start = getNode(nodes,s);
			Node<Integer> end = getNode(nodes,g);
			if (start == null || end == null) {
				System.out.println("failure");
				continue;
			}
			PriorityQueue<QueueItem> pq = new PriorityQueue<QueueItem>();
			pq.offer(new QueueItem(start,0));
			int[] dist = new int[cases+1];
			Arrays.fill(dist, Integer.MAX_VALUE);
			int route[] = new int[12];
			dist[s] = 0;
			
			while (!pq.isEmpty()) {
				QueueItem q = pq.poll();
				int u = q.n.id;
				int d = q.dist;
				if (d == dist[u]) {
					for (Edge<Node<Integer>> e : q.n.neighbors) {
						if (dist[u] + e.weight < dist[e.n.id]) {
							dist[e.n.id] = dist[u] + e.weight;
							route[e.n.id] = u;
							pq.offer(new QueueItem(e.n, dist[e.n.id]));
						}
					}
				}
			}
			String output = g + "; " + dist[g] + " second delay";
			while (g != s) {
				output = route[g] + " " + output;
				g = route[g];
			}
			System.out.println("Case " + count + ": " + output);
			count++;
		}
	}


	static class QueueItem implements Comparable<QueueItem> {
		Node<Integer> n;
		int dist;
		public QueueItem(Node<Integer> v2, int dist2) {
			this.n = v2;
			this.dist = dist2;
		}
		public int compareTo(QueueItem q) {
			return this.dist - q.dist;
		}
	}
	public static Node<Integer> getNode(ArrayList<Node<Integer>> nodes, int i) {
		for (Node<Integer> n : nodes) {
			if (n.id == i)
				return n;
		}
		return null;
	}
	static class Pair {
		Node<Integer> current;
		Pair parent;
		public Pair(Node<Integer> current, Pair curNode) {
			this.current = current;
			this.parent = curNode;
		}
	}

	static class Node<T> {
		public Node(T nodeName) {
			this.id = nodeName;
			neighbors = new ArrayList<Edge<Node<T>>>();
		}
		public void addChild(Node<T> current, int weight) {
			neighbors.add(new Edge<Node<T>>(current, weight));
		}
		ArrayList<Edge<Node<T>>> neighbors;
		public T id;
	}
	static class Edge<T> {
		T n;
		int weight;
		public Edge(T n, int weight) {
			this.n = n;
			this.weight = weight;
		}
		
	}

}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Problem10986 {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		
		int num = Integer.parseInt(scan.nextLine());
		int a = num;
		while (num-- > 0) {
			
			ArrayList<Node<Integer>> nodes = new ArrayList<Node<Integer>>();

			String[] init = scan.nextLine().split(" ");
			int N = Integer.parseInt(init[0]);
			int M = Integer.parseInt(init[1]);
			int S = Integer.parseInt(init[2]);
			int T = Integer.parseInt(init[3]);
			nodes.add(new Node<Integer>(S));
			nodes.add(new Node<Integer>(T));
			while (M-- > 0) {
				String[] parts = scan.nextLine().split(" ");
				int u = Integer.parseInt(parts[0]);
				int v = Integer.parseInt(parts[1]);
				int w = Integer.parseInt(parts[2]);
				Node<Integer> c = getNode(nodes, u);
				if (c == null) {
					c = new Node<Integer>(u);
					nodes.add(c);
				}
				Node<Integer> child = getNode(nodes, v);
				if (child == null) {
					child = new Node<Integer>(v);
					nodes.add(child);
				}
				child.addChild(c, w);
				c.addChild(child, w);
			}
			
			PriorityQueue<QueueItem> Q = new PriorityQueue<QueueItem>();
			
			int[] dist = new int[N];

			Arrays.fill(dist, Integer.MAX_VALUE);
			dist[S] = 0;
			Q.offer(new QueueItem(getNode(nodes, S), 0));
			while (!Q.isEmpty()) {
				QueueItem q = Q.poll();
				int u = q.n.name;
				int d = q.dist;
				if (d == dist[u])
					for (Edge<Node<Integer>> e : getNode(nodes,u).neighbors) {
						Node<Integer> v = e.n;
						int w = e.weight;
						if (dist[u] + w < dist[v.name]) {
							dist[v.name] = dist[u] + w;
							Q.offer(new QueueItem(v, dist[v.name]));
						}
					}
			}
			System.out.print("Case #" + (a-num) + ": ");
			if (dist[T] == Integer.MAX_VALUE) {
				System.out.println("unreachable");
			} else {
				System.out.println(dist[T]);
			}
			nodes.clear();
		}
	}
	public static Node<Integer> getNode(ArrayList<Node<Integer>> nodes, Integer i) {
		for (Node<Integer> n : nodes)
			if (n.name == i)
				return n;
		
		return null;
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
			this.name = nodeName;
			neighbors = new ArrayList<Edge<Node<T>>>();
		}
		public void addChild(Node<T> current, int weight) {
			neighbors.add(new Edge<Node<T>>(current, weight));
		}
		ArrayList<Edge<Node<T>>> neighbors;
		public T name;
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

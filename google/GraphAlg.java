package google;

import java.util.ArrayList;

public class GraphAlg<E, V> {
	public interface IVisitor<E, V> {
		public void visit(Vertex<E, V> v);
	}

	public static class Edge<E, V> {
		public E elem;
		public Vertex<E, V> end1;
		public Vertex<E, V> end2;

		public Edge(E e, Vertex<E, V> e1, Vertex<E, V> e2) {
			elem = e;
			end1 = e1;
			end2 = e2;
		}

		@Override
		public String toString() {
			return elem.toString();
		}
	}

	public static class Vertex<E, V> {
		public V elem;
		public ArrayList<Edge<E, V>> edges = new ArrayList<Edge<E, V>>();

		public Vertex(V e) {
			elem = e;
		}

		@Override
		public String toString() {
			return elem.toString();
		}
	}

	protected ArrayList<Edge<E, V>> mEdges = new ArrayList<Edge<E, V>>();
	protected ArrayList<Vertex<E, V>> mVertices = new ArrayList<Vertex<E, V>>();

	public ArrayList<Edge<E, V>> edges() {
		return mEdges;
	}

	public ArrayList<Vertex<E, V>> vertices() {
		return mVertices;
	}

	public Vertex<E, V> oppositeOf(Vertex<E, V> v, Edge<E, V> e) {
		if (e.end1 == v) {
			return e.end2;
		} else if (e.end2 == v) {
			return e.end1;
		}
		throw new IllegalArgumentException("The vertex is not end of the edge");
	}

	public ArrayList<Vertex<E, V>> neighborsOf(Vertex<E, V> v) {
		ArrayList<Vertex<E, V>> vertices = new ArrayList<Vertex<E, V>>();
		for (Edge<E, V> e : v.edges) {
			vertices.add(oppositeOf(v, e));
		}
		return vertices;
	}

	public boolean areAjacent(Vertex<E, V> v1, Vertex<E, V> v2) {
		for (Edge<E, V> e : v1.edges) {
			if (oppositeOf(v1, e) == v2) {
				return true;
			}
		}
		return false;
	}

	public Vertex<E, V> insert(V elem) {
		Vertex<E, V> vertex = new Vertex<E, V>(elem);
		mVertices.add(vertex);
		return vertex;
	}

	public Edge<E, V> insert(E elem, Vertex<E, V> end1, Vertex<E, V> end2) {
		if (!mVertices.contains(end1) || !mVertices.contains(end2)) {
			throw new IllegalArgumentException("Illegal Vertex");
		}

		Edge<E, V> edge = new Edge<E, V>(elem, end1, end2);
		mEdges.add(edge);

		end1.edges.add(edge);
		if (end2 != end1) {
			end2.edges.add(edge);
		}

		return edge;
	}

	public void remove(Vertex<E, V> v) {
		if (!mVertices.contains(v)) {
			throw new IllegalArgumentException("Illegal Vertex");
		}

		for (Edge<E, V> e : v.edges) {
			remove(e);
		}
		mVertices.remove(v);
	}

	public void remove(Edge<E, V> e) {
		if (!mEdges.contains(e)) {
			throw new IllegalArgumentException("Illegal Edge");
		}

		e.end1.edges.remove(e);
		e.end2.edges.remove(e);
		mEdges.remove(e);
	}

	public void dfs(IVisitor<E, V> visitor) {
		if (mVertices.isEmpty()) {
			return;
		}

		ArrayList<Vertex<E, V>> visited = new ArrayList<Vertex<E, V>>();
		for (Vertex<E, V> start : mVertices) {
			if (!visited.contains(start)) {
				dfsStep(start, visitor, visited);
			}
		}
	}

	public void dfs(IVisitor<E, V> visitor, Vertex<E, V> start) {
		if (!mVertices.contains(start)) {
			throw new IllegalArgumentException("Illegal Vertex");
		}
		dfsStep(start, visitor, new ArrayList<Vertex<E, V>>());

	}

	private void dfsStep(Vertex<E, V> v, IVisitor<E, V> visitor,
			ArrayList<Vertex<E, V>> visited) {
		visitor.visit(v);
		visited.add(v);

		Vertex<E, V> opposite;
		for (Edge<E, V> e : v.edges) {
			opposite = oppositeOf(v, e);
			if (!visited.contains(opposite)) {
				dfsStep(opposite, visitor, visited);
			}
		}
	}

	public void bfs(IVisitor<E, V> visitor, Vertex<E, V> start) {
		if (!mVertices.contains(start)) {
			throw new IllegalArgumentException("Illegal Vertex");
		}

		ArrayList<Vertex<E, V>> visited = new ArrayList<Vertex<E, V>>();
		ArrayList<ArrayList<Vertex<E, V>>> lv = new ArrayList<ArrayList<Vertex<E, V>>>();
		lv.add(new ArrayList<Vertex<E, V>>());

		lv.get(0).add(start);
		visitor.visit(start);
		visited.add(start);

		int i = 0;
		while (lv.size() > i) {
			for (Vertex<E, V> v : lv.get(i)) {
				for (Vertex<E, V> neighbor : neighborsOf(v)) {
					if (!visited.contains(neighbor)) {
						if (lv.size() == i + 1) {
							lv.add(new ArrayList<Vertex<E, V>>());
						}
						lv.get(i + 1).add(neighbor);
						visitor.visit(neighbor);
						visited.add(neighbor);
					}
				}
			}
			i++;
		}
	}

}

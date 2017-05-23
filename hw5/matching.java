import java.util.ArrayList;
import java.util.TreeMap;

public class matching {
	private int l, r;
	private boolean[][] graph;
	private boolean visit[];
	private int leftVertex[]; 
	private int rightVertex[];
	private String nameHeap[];
	private String courseHeap[];
	private TreeMap<String, ArrayList<String>> combination;

	public matching(int l, int r, boolean[][] graph, String[] nameHeap,
			String[] courseHeap) {
		this.l = l;
		this.r = r;
		this.graph = graph;

		this.leftVertex = new int[l];
		this.rightVertex = new int[r];
		for (int i = 0; i < l; i++)
			this.leftVertex[i] = -1;
		for (int i = 0; i < r; i++)
			this.rightVertex[i] = -1;

		this.visit = new boolean[r];
		this.nameHeap = nameHeap;
		this.courseHeap = courseHeap;
		this.combination = new TreeMap<String, ArrayList<String>>();
	}

	int FordFulkerson() {
		int count = 0;
		for (int i = 0; i < l; i++) {
			for (int j = 0; j < r; j++)
				this.visit[j] = false;
			if (matchingCourses(i))
				count++;
		}
		return count;
	}

	boolean matchingCourses(int u) {
		int reserveU = -1;
		int reserveV = -1;
		boolean checkRepeat = false;
		for (int v = 0; v < r; v++) {
			if (!graph[u][v])
				continue;
			else if (visit[v]) {
				reserveU = u;
				reserveV = v;
				checkRepeat = true;
				continue;
			}
			if (checkDoubleEnroll(u, v))
				continue;
			visit[v] = true;
			if (rightVertex[v] == -1) {
				ArrayList<String> temp;
				if (combination.containsKey(nameHeap[u]))
					temp = combination.get(nameHeap[u]);
				else
					temp = new ArrayList<String>();
				temp.add(courseHeap[v]);
				combination.put(nameHeap[u], temp);

				leftVertex[u] = v;
				rightVertex[v] = u;

				if (checkRepeat)
					combination.get(nameHeap[reserveU]).remove(
							courseHeap[reserveV]);

				return true;
			}

			if (matchingCourses(rightVertex[v])) {
				leftVertex[u] = v;
				rightVertex[v] = u;
				return true;
			}
		}
		return false;
	}

	boolean checkDoubleEnroll(int u, int v) {
		if (combination.containsKey(nameHeap[u])
				&& combination.get(nameHeap[u]).contains(courseHeap[v]))
			return true;
		return false;
	}

}

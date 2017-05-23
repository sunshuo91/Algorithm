import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class hw5 {

	public static void main(String[] args) throws FileNotFoundException {
		String filename = args[0];
		File file = new File(filename);
		Scanner scr = new Scanner(file);

		while (true) {
			String line = scr.nextLine();
			int r, c, n;
			r = Integer.parseInt(line.split("\\s+")[0]);
			c = Integer.parseInt(line.split("\\s+")[1]);
			n = Integer.parseInt(line.split("\\s+")[2]);

			if (r == 0 & c == 0 & n == 0)
				break;

			String studentName, courseName;

			TreeMap<String, ArrayList<String>> match = new TreeMap<String, ArrayList<String>>();
			TreeMap<String, ArrayList<Integer>> courseMatch = new TreeMap<String, ArrayList<Integer>>();
			ArrayList<String> stud = new ArrayList<String>();

			int leftSize, rightSize;

			for (int i = 0; i < r; i++) {
				line = scr.nextLine();
				studentName = line.split("\\s+")[0];
				courseName = line.split("\\s+")[1];
				if (match.containsKey(studentName)) {
					ArrayList<String> temp = match.get(studentName);
					temp.add(courseName);
					match.put(studentName, temp);
				} else {
					ArrayList<String> temp = new ArrayList<String>();
					temp.add(courseName);
					match.put(studentName, temp);
				}

				if (stud.size() == 0
						|| !(studentName.equals(stud.get(stud.size() - 1))))
					stud.add(studentName);
			}

			leftSize = stud.size() * n;
			rightSize = 0;

			String[] nameHeap = new String[leftSize];

			int index = 0;
			for (int i = 0; i < c; i++) {
				line = scr.nextLine();
				courseName = line.split("\\s+")[0];
				int enrollCap = Integer.parseInt(line.split("\\s+")[1]);
				rightSize = rightSize + enrollCap;
				ArrayList<Integer> courseArr = new ArrayList<Integer>();
				for (int j = 0; j < enrollCap; j++)
					courseArr.add(index++);
				courseMatch.put(courseName, courseArr);
			}

			String[] courseHeap = new String[rightSize];

			boolean[][] graph = new boolean[leftSize][rightSize];
			for (int left = 0; left < leftSize; left++) {
				for (int right = 0; right < rightSize; right++) {
					graph[left][right] = false;
				}
			}

			int nameIndex = 1, arrayIndex = 0;
			String studName = stud.get(0);
			for (int x1 = 0; x1 < leftSize; x1++) {
				if (nameIndex > n) {
					studName = stud.get(++arrayIndex);
					nameIndex = 2;
				} else
					nameIndex++;

				nameHeap[x1] = studName;

				for (int x2 = 0; x2 < match.get(studName).size(); x2++) {
					String str = match.get(studName).get(x2);
					for (int x3 = 0; x3 < courseMatch.get(str).size(); x3++) {
						graph[x1][courseMatch.get(str).get(x3)] = true;
						courseHeap[courseMatch.get(str).get(x3)] = str;
					}

				}

			}

			matching enrollment = new matching(leftSize, rightSize, graph,
					nameHeap, courseHeap);
			int result = enrollment.FordFulkerson();
			if (result == leftSize)
				System.out.println("Yes");
			else
				System.out.println("No");

			scr.nextLine();
		}

		scr.close();

	}

}

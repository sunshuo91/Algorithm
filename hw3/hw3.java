//Steve Sun (ss8ee)

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class hw3 {
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File(args[0]);
		Scanner scr = new Scanner(file);
		int n;
		ArrayList<coordinate> points = new ArrayList<coordinate>();

		double xValue, yValue;

		while ((n = scr.nextInt()) != 0) {
			String[] nextLine;
			scr.nextLine();
			for (int i = 0; i < n; i++) {
				nextLine = scr.nextLine().split("\\s+");
				xValue = Double.parseDouble(nextLine[0]);
				yValue = Double.parseDouble(nextLine[1]);
				points.add(new coordinate(xValue, yValue));
			}
			Distance d;
			if (n <= 3) {
				d = bruteForce(points);
			} else {
				d = divideAndCounquer(points);
			}
			System.out.println(d);
			points.clear();
		}
	}

	public static Distance bruteForce(ArrayList<coordinate> points) {
		int size = points.size();
		if (size < 2) {
			return null;
		} else if (size == 2) {
			return new Distance(points.get(0), points.get(1));
		} else {
			Distance d1 = new Distance(points.get(0), points.get(1));
			Distance d2 = new Distance(points.get(1), points.get(2));
			Distance d3 = new Distance(points.get(0), points.get(2));
			if (d1.distance <= d2.distance && d1.distance <= d3.distance)
				return d1;
			else if (d2.distance <= d1.distance && d2.distance <= d3.distance)
				return d2;
			else
				return d3;
		}

	}

	public static Distance divideAndCounquer(ArrayList<coordinate> points) {
		ArrayList<coordinate> xSort = new ArrayList<coordinate>(points);
		MergeSort ms = new MergeSort();
		ms.sort(xSort, true);
		xSort = ms.getNumbers();

		ArrayList<coordinate> ySort = new ArrayList<coordinate>(points);
		MergeSort ms2 = new MergeSort();
		ms2.sort(ySort, false);
		ySort = ms2.getNumbers();

		return DACHelper(xSort, ySort);
	}

	public static Distance DACHelper(ArrayList<coordinate> xSort,
			ArrayList<coordinate> ySort) {
		int size = xSort.size();
		if (size <= 3)
			return bruteForce(xSort);

		int line = size / 2;
		List<coordinate> leftSL = xSort.subList(0, line);
		ArrayList<coordinate> leftSide = new ArrayList<coordinate>();
		leftSide.addAll(leftSL);
		List<coordinate> rightSL = xSort.subList(line, size);
		ArrayList<coordinate> rightSide = new ArrayList<coordinate>();
		rightSide.addAll(rightSL);

		ArrayList<coordinate> temp = new ArrayList<coordinate>();
		for (coordinate coo: ySort) {
			if(leftSide.contains(coo))
				temp.add(coo);
		}
		Distance shortestLeft = DACHelper((ArrayList<coordinate>) leftSide,
				temp);
		temp.clear();

		ArrayList<coordinate> temp2 = new ArrayList<coordinate>();
		for (coordinate coo: ySort) {
			if(rightSide.contains(coo))
				temp2.add(coo);
		}
		Distance shortestRight = DACHelper((ArrayList<coordinate>) rightSide,
				temp2);

		Distance shortest;
		if (shortestRight.distance < shortestLeft.distance) {
			shortest = shortestRight;
		} else {
			shortest = shortestLeft;
		}
		temp2.clear();

		ArrayList<coordinate> pInRec = new ArrayList<coordinate>();

		double currentShortest = shortest.distance;
		double val_line = rightSide.get(0).x;
		for (coordinate p : ySort)
			if (Math.abs(val_line - p.x) < currentShortest)
				pInRec.add(p);

		for (int i = 0; i < pInRec.size() - 1; i++) {
			for (int j = i + 1; j < pInRec.size(); j++) {
				coordinate p1 = pInRec.get(i);
				coordinate p2 = pInRec.get(j);
				if (!(Math.abs(p2.y - p1.y) >= currentShortest)) {
					double new_distance = ((new Distance(p1, p2)).distance);
					if (new_distance < shortest.distance) {
						shortest.setPoint1(p1);
						shortest.setPoint2(p2);
						shortest.setDistance(new_distance);
						currentShortest = new_distance;
					}
				}

			}
		}
		pInRec.clear();
		return shortest;
	}

}

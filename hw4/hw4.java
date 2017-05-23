package cs4102hw3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class hw4 {

	public static void main(String[] args) throws FileNotFoundException {
		File file = new File(args[0]);
		Scanner scr = new Scanner(file);

		int casenum = Integer.parseInt(scr.nextLine());
		String title;
		int row;
		int col;
		for (int i = 0; i < casenum; i++) {
			String[] line = scr.nextLine().split("\\s+");
			title = line[0];
			row = Integer.parseInt(line[1]);
			col = Integer.parseInt(line[2]);

			int[][] indices = new int[row][col];
			int[][] num = new int[row][col];

			for (int j1 = 0; j1 < row; j1++) {
				String[] data = scr.nextLine().split("\\s+");
				for (int j2 = 0; j2 < col; j2++) {
					num[j1][j2] = Integer.parseInt(data[j2]);
					indices[j1][j2] = 100;

				}
			}

			// Test Loading
			// for(int j1 = 0; j1 < row; j1++) {
			// for (int j2 = 0; j2 < col; j2++) {
			// System.out.print(num[j1][j2] + " ");
			// }
			// System.out.println();
			// }

			indices[0][0] = 0;
			int max = 0;

			for (int j = 1; j < row + col - 1; j++) {
				for (int k = 0; k <= j; k++) {
					int r = k;
					int c = j - k;
					if (r < row && c < col) {
						if (c != 0) {
							if (num[r][c] > num[r][c - 1])
								indices[r][c] = indices[r][c - 1] + 1;
							else if (num[r][c] < num[r][c - 1]) {
								if (indices[r][c - 1] == 0) {
									indices[r][c] = 0;
									indices[r][c - 1] = 1;
									indices = checkBack(indices, num, r, c-1, row, col);
								} else {
									indices[r][c] = 0;//indices[r][c - 1] - 1;
								}
							} else
								indices[r][c] = indices[r][c - 1];
						}

						if (r != 0) {
							if (c == 0) {
								if (num[r][c] > num[r - 1][c])
									indices[r][c] = indices[r - 1][c] + 1;
								else if (num[r][c] < num[r - 1][c]) {
									if (indices[r - 1][c] == 0) {
										indices[r][c] = 0;
										indices[r - 1][c] = 1;
										indices = checkBack(indices, num, r-1, c, row, col);
									} else {
										indices[r][c] = 0;//indices[r - 1][c] - 1;
									}
								} else
									indices[r][c] = indices[r - 1][c];
							} else {
								if (num[r][c] > num[r - 1][c]
										&& indices[r][c] > indices[r - 1][c])
									;
								else if (num[r][c] > num[r - 1][c]
										&& indices[r][c] <= indices[r - 1][c]) {
									indices[r][c] = indices[r - 1][c] + 1;
									indices = checkBack(indices, num, r, c, row, col);
								} else if (num[r][c] < num[r - 1][c]
										&& indices[r][c] < indices[r - 1][c])
									;
								else if (num[r][c] < num[r - 1][c]
										&& indices[r][c] >= indices[r - 1][c]) {
									indices[r - 1][c] = indices[r][c] + 1;
									indices = checkBack(indices, num, r-1, c, row, col);
								} else if (num[r][c] == num[r - 1][c]
										&& indices[r][c] == indices[r - 1][c])
									;
								else if (num[r][c] == num[r - 1][c]
										&& indices[r][c] != indices[r - 1][c]) {
									indices[r][c] = indices[r - 1][c];
									indices = checkBack(indices, num, r, c, row, col);
								} else
									;
							}

						}
						
					}
					

				}
			}

			//Testing indices
//			for (int j1 = 0; j1 < row; j1++) {
//				for (int j2 = 0; j2 < col; j2++) {
//					System.out.print(indices[j1][j2] + " ");
//				}
//				System.out.println();
//			}
			
			//Find Largest
			for(int lr = 0; lr < row; lr++) {
				for(int lc = 0; lc < col; lc++) {
					if (indices[lr][lc] > max)
						max = indices[lr][lc];
				}
			}
			
			max++;
			System.out.println(title + ": " + max);
		}

	}
	
	public static int[][] checkBack(int[][] indices, int[][] num, int r, int c, int row, int col) {
		//check up
		if (r - 1 >= 0) {
			if (num[r][c] > num[r - 1][c]
					&& indices[r][c] > indices[r - 1][c])
				;
			else if (num[r][c] > num[r - 1][c]
					&& indices[r][c] <= indices[r - 1][c]) {
				indices[r][c] = indices[r - 1][c] + 1;
				indices = checkBack(indices, num, r, c, row, col);
			} else if (num[r][c] < num[r - 1][c]
					&& indices[r][c] < indices[r - 1][c])
				;
			else if (num[r][c] < num[r - 1][c]
					&& indices[r][c] >= indices[r - 1][c]) {
				indices[r - 1][c] = indices[r][c] + 1;
				indices = checkBack(indices, num, r-1, c, row, col);
			} else if (num[r][c] == num[r - 1][c]
					&& indices[r][c] == indices[r - 1][c])
				;
			else if (num[r][c] == num[r - 1][c]
					&& indices[r][c] != indices[r - 1][c]) {
				indices[r - 1][c] = indices[r][c];
				indices = checkBack(indices, num, r - 1, c, row, col);
			} else
				;
		}

		
		//check left
		if (c - 1 >= 0) {
			if (num[r][c] > num[r][c - 1]
					&& indices[r][c] > indices[r][c - 1])
				;
			else if (num[r][c] > num[r][c - 1]
					&& indices[r][c] <= indices[r][c - 1]) {
				indices[r][c] = indices[r][c - 1] + 1;
				indices = checkBack(indices, num, r, c, row, col);
			} else if (num[r][c] < num[r][c - 1]
					&& indices[r][c] < indices[r][c - 1])
				;
			else if (num[r][c] < num[r][c - 1]
					&& indices[r][c] >= indices[r][c - 1]) {
				indices[r][c - 1] = indices[r][c] + 1;
				indices = checkBack(indices, num, r, c-1, row, col);
			} else if (num[r][c] == num[r][c - 1]
					&& indices[r][c] == indices[r][c - 1])
				;
			else if (num[r][c] == num[r][c - 1]
					&& indices[r][c] != indices[r][c - 1]) {
				indices[r][c - 1] = indices[r][c];
				indices = checkBack(indices, num, r, c - 1, row, col);
			} else
				;
		}
		
		//check down
		if (r + 1 < row && indices[r + 1][c] < 100) {
			if (num[r][c] > num[r + 1][c]
					&& indices[r][c] > indices[r + 1][c])
				;
			else if (num[r][c] > num[r + 1][c]
					&& indices[r][c] <= indices[r + 1][c]) {
				indices[r][c] = indices[r + 1][c] + 1;
				indices = checkBack(indices, num, r, c, row, col);
			} else if (num[r][c] < num[r + 1][c]
					&& indices[r][c] < indices[r + 1][c])
				;
			else if (num[r][c] < num[r + 1][c]
					&& indices[r][c] >= indices[r + 1][c]) {
				indices[r + 1][c] = indices[r][c] + 1;
				indices = checkBack(indices, num, r+1, c, row, col);
			} else if (num[r][c] == num[r + 1][c]
					&& indices[r][c] == indices[r + 1][c])
				;
			else if (num[r][c] == num[r - 1][c]
					&& indices[r][c] != indices[r + 1][c]) {
				indices[r + 1][c] = indices[r][c];
				indices = checkBack(indices, num, r + 1, c, row, col);
			} else
				;
		}
		
		//check right
		if (c + 1 < col && indices[r][c + 1] < 100) {
			if (num[r][c] > num[r][c + 1]
					&& indices[r][c] > indices[r][c + 1])
				;
			else if (num[r][c] > num[r][c + 1]
					&& indices[r][c] <= indices[r][c + 1]) {
				indices[r][c] = indices[r][c + 1] + 1;
				indices = checkBack(indices, num, r, c, row, col);
			} else if (num[r][c] < num[r][c + 1]
					&& indices[r][c] < indices[r][c + 1])
				;
			else if (num[r][c] < num[r][c + 1]
					&& indices[r][c] >= indices[r][c + 1]) {
				indices[r][c + 1] = indices[r][c] + 1;
				indices = checkBack(indices, num, r, c+1, row, col);
			} else if (num[r][c] == num[r][c+1]
					&& indices[r][c] == indices[r][c + 1])
				;
			else if (num[r][c] == num[r][c + 1]
					&& indices[r][c] != indices[r][c + 1]) {
				indices[r][c + 1] = indices[r][c];
				indices = checkBack(indices, num, r, c + 1, row, col);
			} else
				;
		}
		
		return indices;
	}

}
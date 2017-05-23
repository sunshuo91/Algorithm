//Steve Sun (ss8ee)
//hw2

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;


public class hw2 {

	public static void main(String[] args) throws FileNotFoundException {
		File file = new File(args[0]);
		Scanner scr = new Scanner(file);
		
		int box, ttlbox, selfbox, compnum, onecost, halfcost, ttlcost;
		HashMap<String, Integer> output = new HashMap<String, Integer>();
		Sorting sort = new Sorting(output);
		TreeMap<String, Integer> sorted_output = new TreeMap<String, Integer>(sort);
		String[] temp, temp2;

		
		int casenum = Integer.parseInt(scr.nextLine());
		int casecount = 0;
		do {
			temp = (scr.nextLine()).split("\\s+");
			ttlbox = Integer.parseInt(temp[0]);
			selfbox = Integer.parseInt(temp[1]);
			compnum = Integer.parseInt(temp[2]);
			for(int i = 0; i < compnum; i++) {
				box = ttlbox;
				ttlcost = 0;
				
				temp2 = (scr.nextLine()).split("\\s+");
				onecost = Integer.parseInt(temp2[1]);
				halfcost = Integer.parseInt(temp2[2]);
				
				while (box != 0) {
					if (box / 2 < selfbox) {
						ttlcost = ttlcost + (box - selfbox) * onecost;
						box = 0;
					} else if (box / 2 * onecost >= halfcost ) {
						ttlcost = ttlcost + halfcost;
						box = box / 2;
					} else {
						ttlcost = ttlcost + box - (selfbox) * onecost;
						box = 0;
					}
				}
				
				output.put(temp2[0], ttlcost);
	
			}
			
			casecount++;
			
			sorted_output.putAll(output);
			System.out.println("Case " + casecount);
			for (String str:sorted_output.keySet()) {
				System.out.println(str + " " + output.get(str));
			}
			
			output.clear();
			sorted_output.clear();
			
		} while (casecount != casenum);
		
		

	}

}

class Sorting implements Comparator<String> {

	Map<String, Integer> unsorted;
	public Sorting(Map<String, Integer> unsorted) {
		this.unsorted = unsorted;
	}

	public int compare(String arg0, String arg1) {
		if (unsorted.get(arg0) <= unsorted.get(arg1)) 
			return -1;
		else
			return 1;
	}
}

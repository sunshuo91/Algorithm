import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

//Steve Sun (ss8ee)

public class hw1 {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Please input your data file name: ");
		String filename = keyboard.next();
		File file = new File(filename);
		Scanner scr = new Scanner(file);
		NumberFormat formatter, formatter2;

		String tmp, temp[];
		int dollar, coin, ttl, q, d, n, p, a, b, c, e;
		boolean boo = true;
		while (scr.hasNext() && boo) {
			a = 0;
			b = 0;
			c = 0;
			e = 0;
			tmp = scr.nextLine();
			temp = tmp.split("\\.");
			ttl = Integer.parseInt(temp[1]);
			coin = ttl;
			if (temp[0].charAt(0) == 45)
				boo = false;

			if (boo) {
				dollar = Integer.parseInt(temp[0]);
				q = ttl / 25;
				ttl = ttl - 25 * q;
				d = ttl / 10;
				ttl = ttl - 10 * d;
				n = ttl / 5;
				ttl = ttl - 5 * n;
				p = ttl;

				formatter = new DecimalFormat("###,###,###");
				formatter2 = new DecimalFormat("00");

				System.out.print("$" + formatter.format(dollar) + "."
						+ formatter2.format(coin));

				while (q > a) {
					System.out.print(" Q");
					a++;
				}
				while (d > b) {
					System.out.print(" D");
					b++;
				}
				while (n > c) {
					System.out.print(" N");
					c++;
				}
				while (p > e) {
					System.out.print(" P");
					e++;
				}
				System.out.println();
			}
		}
	}

}

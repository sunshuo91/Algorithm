import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Distance {
	public coordinate point1 = null;
	public coordinate point2 = null;
	public double distance = 0.0;

	public Distance(coordinate point1, coordinate point2) {
		this.point1 = point1;
		this.point2 = point2;

		this.distance = Math.sqrt(Math.pow((point2.x - point1.x), 2)
				+ Math.pow((point2.y - point1.y), 2));

	}

	public void setPoint1(coordinate point1) {
		this.point1 = point1;
	}

	public void setPoint2(coordinate point2) {
		this.point2 = point2;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public String toString() {
		if (distance > 10000) {
			return "infinity";
		} else {
			NumberFormat formatter = new DecimalFormat("####0.0000");
			return "" + formatter.format(distance);
		}
	}
}

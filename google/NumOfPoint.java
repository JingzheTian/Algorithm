package google;

import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class NumOfPoint {
	public int maxPoints(Point[] points) {
		if (points.length == 0)
			return 0;
		if (points.length == 1)
			return 1;
		Map<String, HashSet<Point>> hm = new HashMap<String, HashSet<Point>>();
		for (int i = 0; i < points.length; i++) {
			for (int j = i + 1; j < points.length; j++) {
				double k = getK(points[i].x, points[i].y, points[j].x,
						points[j].x);
				double b = getB(points[i].x, points[i].y, points[j].x,
						points[j].x);
				String s = String.valueOf(k) + "+" + String.valueOf(b);
				if (hm.containsKey(s)) {
					hm.get(s).add(points[i]);
					hm.get(s).add(points[j]);
				} else {
					hm.put(s, new HashSet<Point>());
					hm.get(s).add(points[i]);
					hm.get(s).add(points[j]);
				}

			}
		}
		int max = 0;
		for (String s : hm.keySet()) {
			max = Math.max(max, hm.get(s).size());
		}

		return max;
	}

	public double getK(int x1, int y1, int x2, int y2) {
		if (y2 == y1)
			return Double.MAX_VALUE;
		return (double) ((x1 - x2) / (y1 - y2));
	}

	public double getB(int x1, int y1, int x2, int y2) {
		if (x1 == x2)
			return Double.MAX_VALUE;
		return (double) (x1 * y2 - x2 * y1) / (x1 - x2);
	}
	public static void main(String[] args) {
		
	}
}

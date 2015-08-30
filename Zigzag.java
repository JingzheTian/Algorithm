import java.util.ArrayList;
import java.util.List;

public class Zigzag {
	public static void main(String[] args) {
		Zigzag zz = new Zigzag();
		//System.out.println(zz.convert("PAYPALISHIRING", 3));
		System.out.println(zz.trailingZeroes(30));
	}

	public long trailingZeroes(int n) {
		long total = 1;
		for(int i = n; i> 1; i--)
		{
			total = total * i;
		}
		return total;
	}

	public List<List<Integer>> generate(int numRows) {
		List<List<Integer>> out = new ArrayList<List<Integer>>();
		List<Integer> copy = new ArrayList<Integer>();

		if (numRows == 0)
			return null;

		for (int i = 0; i < numRows; i++) {
			List<Integer> in = new ArrayList<Integer>();
			if (i == 0) {
				in.add(1);
				out.add(in);
				copy = in;
			} else if (i == 1) {
				in.add(1);
				in.add(1);
				out.add(in);
				copy = in;
			} else {
				in.add(1);
				for (int j = 0; j < i - 1; j++) {
					in.add(copy.get(j) + copy.get(j + 1));
				}
				in.add(1);
				out.add(in);
				copy = in;
			}
		}
		return out;
	}

	public List<Integer> getRow(int rowIndex) {
		List<Integer> copy = new ArrayList<Integer>();

		if (rowIndex == 0)
			return copy;

		for (int i = 0; i < rowIndex; i++) {
			List<Integer> in = new ArrayList<Integer>();
			if (i == 0) {
				in.add(1);
				copy = in;
			} else if (i == 1) {
				in.add(1);
				in.add(1);
				copy = in;
			} else {
				in.add(1);
				for (int j = 0; j < i - 1; j++) {
					in.add(copy.get(j) + copy.get(j + 1));
				}
				in.add(1);
				copy = in;
			}
		}
		return copy;
	}

	public String convert(String s, int nRows) {
		String o = "";
		if (s.length() <= nRows || nRows == 1) {
			return s;
		}

		for (int i = 0; i < nRows; i++) {
			if (i == 0 || i == nRows - 1) {
				for (int j = i; j < s.length(); j += 2 * nRows - 2) {
					o += s.substring(j, j + 1);
				}
			} else {
				int diff = 2 * nRows - 2 - 2 * i;
				for (int j = i; j < s.length(); j += 2 * nRows - 2) {
					o += s.substring(j, j + 1);
					if (j + diff < s.length()) {
						o += s.substring(j + diff, j + diff + 1);
					}
				}
			}
		}
		return o;
	}

}

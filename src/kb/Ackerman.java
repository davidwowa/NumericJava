package kb;

public class Ackerman {

	public static int ack(int n, int m, int counta) {
		int count = ++counta;
		System.out.println(count);
		if (n == 0) {
			return m + 1;
		}
		if (m == 0) {
			return ack(n + 1, 1, count);
		}
		return ack(n - 1, ack(n, m - 1, count), count);
	}

	public static void main(String[] args) {
		ack(1, 1, 0);
	}
}
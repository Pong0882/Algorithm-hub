import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

	static int[][] paper;

	static int R, C, N;
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };
	static ArrayDeque<Integer[]> boom = new ArrayDeque<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		paper = new int[R][C];
		int max = N;
		for (int i = 0; i < R; i++) {
			char[] c = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				if (c[j] == '.') {
					paper[i][j] = 0;
				} else {
					paper[i][j] = 3;
				}
			}
		}

		while (N > 0) {
			if (max == N) {
				first();
				N--;
				continue;
			}
			N--;
			boom();
		}
//		for (int i = 0; i < R; i++) {
//			System.out.println(Arrays.toString(paper[i]));
//		}
//		}
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (paper[i][j] > 0) {
					sb.append("O");
				} else {
					sb.append(".");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
		// --------------솔루션 코드를 작성하세요.--------------------------------
	}

	private static void first() {

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (paper[i][j] != 0) {
					paper[i][j]--;
				}
			}
		}

	}

	private static void boom() {
		// TODO Auto-generated method stub
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (paper[i][j] == 0) {
					paper[i][j] = 3;
				} else if (paper[i][j] > 1) {
					paper[i][j]--;
				} else { // boom
					// 폭탄 기억해뒀다가 터쳐야겠네
					boom.add(new Integer[] { i, j });
//					paper[i][j] = 0;
//					for (int j2 = 0; j2 < 4; j2++) {
//						int nr = i + dr[j2];
//						int nc = j + dc[j2];
//						if (!check(nr, nc)) {
//							continue;
//						}
//						paper[nr][nc] = 0;
				}
			}
		}

		while (!boom.isEmpty()) {
			Integer[] tmp = boom.poll();
			int r = tmp[0];
			int c = tmp[1];
			paper[r][c] = 0;
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if (!check(nr, nc)) {
					continue;
				}
				paper[nr][nc] = 0;
			}
		}
	}

	private static boolean check(int a, int b) {
		// TODO Auto-generated method stub
		return a >= 0 && b >= 0 && a < R && b < C;
	}

}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder();

	private static int A, B, N, M;
	private static int[][] basePaper;
	private static int depth = 3;

	private static int result;
	private static int virus;
	private static int safePlace;
	private static int[][] paper;
	private static int[] dx = { 1, -1, 0, 0 };
	private static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = 0;
		basePaper = new int[N][M];
		paper = new int[N][M];
		int tmp = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				basePaper[i][j] = Integer.parseInt(st.nextToken());
				if (basePaper[i][j] == 0) {
					safePlace++;
				}
			}
		}
//		System.out.println(safePlace);
//		result = safePlace - 3;
		// System.out.println(safePlace);
		// 입력 끝
		safePlace -= 3;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (basePaper[i][j] == 0)
					makeWall(i, j, 0);
			}
		}

		System.out.println(result);
	}

	private static void makeWall(int x, int y, int cnt) {
		if (cnt == depth) {
			// 벽 다 만들었으니 바이러스 퍼트리기
			for (int i = 0; i < N; i++) {
				paper[i] = basePaper[i].clone();
			} // 2중 배열 깊은 복사

			virus = 0; // 바이러스 카운팅해서 result 보다 커지면 부실거임, 바이러스 초기화
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (basePaper[i][j] == 2) {// 바이러스 발견!!
						virus(i, j);
					}
				}
			}
			result = Math.max(result, safePlace - virus);
//			// System.out.println(result);
//			for (int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(paper[i]));
//			}
//			System.out.println(result);
//			System.out.println();

			return;
		}

		for (int i = x; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (i == x && j == 0) {
					j = y;
				}
				if (basePaper[i][j] == 0) {
					basePaper[i][j] = 1;
					makeWall(i, j, cnt + 1);
					basePaper[i][j] = 0;
				}
			}
		}
	}

	private static void virus(int x, int y) { // 바이러스 퍼트리기
		// BFS 로 발자국남기자

		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] { x, y });

		while (!que.isEmpty()) {
			int[] cur = que.poll();
			int cx = cur[0];
			int cy = cur[1];
			paper[cx][cy] = 3; // 바꿔
			for (int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				if (!check(nx, ny)) { // 종이 튀어나가니??
					continue;
				}
				if (paper[nx][ny] == 0) {
					paper[nx][ny] = 3; // 바꿔
					que.offer(new int[] { nx, ny });
					if (virus++ > safePlace - result) // 바이러스 카운팅 늘리고 검사
						return;// result 보다 바이러스수가 많아지면 BFS 자체를 부심
				}
			}

		}
	}

	private static boolean check(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < M;
	}
}
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M, high, low, result;
	static int[] home;

	public static void main(String[] args) throws Exception {
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		/* 초기화 */
		home = new int[N];
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(br.readLine().trim());
			home[i] = n;
		}
		Arrays.sort(home);
		low = 1;
		high = home[N - 1] + home[0] + 1;

		/* 로직 */
		// 1.단 이분 탐색해
		bSearch();

		/* 출력 */
		System.out.println(result);
	}

	static void bSearch() {
		int mid = 0;
		while (low < high) {
			mid = (low + high) / 2;
//			System.out.println("h: " + high + ", l: " + low + ", mid: " + mid);
			if (check(mid)) {
				low = mid + 1;
			} else {
				high = mid;
			}
		}
	}

	static boolean check(int dist) {
//		System.out.println("dist: " + dist);
		int count = 1;
		int s = 0, e = 1;
		while (e < N) {
			if (home[e] - home[s] >= dist) {
				s = e;
				e++;
				count++;
				if (count == M) {
					result = dist;
					return true;
				}
			} else {
				e++;
			}
		}

		return false;
	}

}

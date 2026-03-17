import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static Long fastStaffTime = 1_000_001L;
    static long result;
    static int[] staff;

    public static void main(String[] args) throws IOException {
        // 260317 13:24 시작

        // N 스테프수, M 풍선수,
        // 각 스테프가 풍선 부는 시간 주어짐
        // M 개 만드는데 걸리는 시간은?
        // DP 인가? > 적을만한 기준이없음
        // 완탐할수는 없잖아?
        // 결국 하나씩 해보기면 기준점을 찾아야해 -> 이분탐색

        // 상한선은 가장 풍선을 빨리부는사람이 혼자 다불었을 경우로 두자
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        staff = new int[N];
        for (int i = 0; i < N; i++) {
            staff[i] = Integer.parseInt(st.nextToken());
            fastStaffTime = Math.min(staff[i], fastStaffTime);
        }

        long left = 0;
        long right = (Long) (fastStaffTime * M);
        // 이거 잘못하면 10 ^12 나옴 int 는 10 ^10 까지
        // long 이 10^18까지 대략

        while (left <= right) {
            long mid = (left + right) / 2;
            long count = 0;

            for (int i = 0; i < N; i++) {
                count += mid / staff[i];
                if (count >= M) {
                    break;
                }
            }

            if (count >= M) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(result);
    }
}

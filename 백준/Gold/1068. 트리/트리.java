

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<Integer>[] lists;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        lists = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            lists[i] = new ArrayList<>();
        }
        for (int i = 0; i < N; i++) {
            int tmp = Integer.parseInt(st.nextToken()) + 1;
            lists[tmp].add(i + 1);
        }
        int del = Integer.parseInt(br.readLine());

        for (int i = 0; i < lists.length; i++) {
            lists[i].remove(Integer.valueOf(del + 1));
        }
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (Integer child : lists[0]) {
            q.add(child);
        }
        int result = 0;
        while (!q.isEmpty()) {
            int tmp = q.poll();

            if (lists[tmp].isEmpty()) {
                result++;
            } else {
                for (Integer n : lists[tmp]) {
                    q.add(n);
                }
            }
        }
        System.out.println(result);
    }
}

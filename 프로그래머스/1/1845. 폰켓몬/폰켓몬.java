class Solution {
    public int solution(int[] nums) {
        int result = 0;
        // 일단 N/2 개 뽑는다고함,
        // 포캣몬 종류번호는 200_000 이하니까 boolean 배열 만들어서
        // false 일경우만 카운팅하면서 true 로 바꿔줌
        int N = nums.length / 2;
        // System.out.print(N);
        boolean[] paper = new boolean[200_001];
        
        for(int tmp : nums){
            if(!paper[tmp]){
                paper[tmp] = !paper[tmp];
                result++;
            }
        }
        return result > N ? N : result;
    }
}
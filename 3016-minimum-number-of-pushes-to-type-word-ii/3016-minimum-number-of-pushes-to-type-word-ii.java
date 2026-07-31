class Solution {
    public int minimumPushes(String word) {
        int[] freq = new int[26];

        for (char c : word.toCharArray()) {
            freq[c - 'a']++;
        }

        Arrays.sort(freq);

        int ans = 0;
        int push = 1;
        int cnt = 0;

        for (int i = 25; i >= 0; i--) {
            if (freq[i] == 0) break;

            ans += freq[i] * push;
            cnt++;

            if (cnt == 8) {
                cnt = 0;
                push++;
            }
        }

        return ans;
    }
}
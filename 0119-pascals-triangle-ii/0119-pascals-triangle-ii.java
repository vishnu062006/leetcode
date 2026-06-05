class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> ans = new ArrayList<>();
        ans.add(1);
        for (int row = 1; row <= rowIndex; row++) {
            List<Integer> curr = new ArrayList<>();
            curr.add(1);
            for (int j = 1; j < ans.size(); j++) {
                curr.add(ans.get(j - 1) + ans.get(j));
            }
            curr.add(1);
            ans = curr;
        }
        return ans;
    }
}
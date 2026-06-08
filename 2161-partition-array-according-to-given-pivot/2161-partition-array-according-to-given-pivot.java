class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
        List<Integer> left = new ArrayList<>();
        List<Integer> mid = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for(int n : nums) {
            if(n < pivot)
                left.add(n);
            else if(n > pivot)
                right.add(n);
            else
                mid.add(n);
        }

        left.addAll(mid);
        left.addAll(right);

        int[] res = new int[left.size()];

        for(int i = 0; i < left.size(); i++)
            res[i] = left.get(i);

        return res;
    }
}
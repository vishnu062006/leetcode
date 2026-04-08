class Solution {
    public String largestNumber(int[] nums) {
        String[] arr = new String[nums.length];
        int n=nums.length;
        for(int i=0;i<n;i++){
            arr[i]=String.valueOf(nums[i]);
        }

        Arrays.sort(arr, (a, b) -> (b + a).compareTo(a + b));
        
        if (arr[0].equals("0")) 
            return "0";

        StringBuilder result = new StringBuilder();
        
        for (String s : arr) {
            result.append(s);
        }
        return result.toString();
    }
}

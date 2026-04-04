class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        if (rows == 0) return "";
        int n = encodedText.length();
        int cols = n / rows;
        StringBuilder result = new StringBuilder();
        for (int startCol = 0; startCol < cols; startCol++) {
            int i = 0, j = startCol;
            while (i < rows && j < cols) {
                result.append(encodedText.charAt(i * cols + j));
                i++;
                j++;
            }
        }
        int end = result.length() - 1;
        while (end >= 0 && result.charAt(end) == ' ') {
            end--;
        }
        return result.substring(0, end + 1);
    }
}
int largestRectangleArea(int* heights, int heightsSize) {
    int n = heightsSize;
    int stack[n];
    int top = -1;
    int maxArea = 0;

    for(int i = 0; i <= n; i++) {
        int currHeight = (i == n) ? 0 : heights[i];

        while(top >= 0 && currHeight < heights[stack[top]]) {
            int h = heights[stack[top--]];
            int left = (top == -1) ? -1 : stack[top];
            int width = i - left - 1;
            int area = h * width;

            if(area > maxArea)
                maxArea = area;
        }

        if(i < n)  // 🔥 FIX
            stack[++top] = i;
    }

    return maxArea;
}
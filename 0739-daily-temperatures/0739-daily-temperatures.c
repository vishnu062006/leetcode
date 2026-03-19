int* dailyTemperatures(int* temperatures, int temperaturesSize, int* returnSize) {
    int n = temperaturesSize;
    int* answer = (int*)malloc(sizeof(int) * n);
    for(int i = 0; i < n; i++) 
        answer[i] = 0;
    int stack[n];
    int top = -1;
    for(int i = 0; i < n; i++) {
        while(top >= 0 && temperatures[i] > temperatures[stack[top]]) {
            int prev = stack[top--];
            answer[prev] = i - prev;
        }
        stack[++top] = i;
    }
    *returnSize = n;
    return answer;
}
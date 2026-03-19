int countStudents(int* students, int n, int* sandwiches, int m) {
    int count0 = 0, count1 = 0;
    for(int i = 0; i < n; i++) {
        if(students[i] == 0) 
            count0++;
        else 
            count1++;
    }

    for(int i = 0; i < m; i++) {
        if(sandwiches[i] == 0) {
            if(count0 == 0) 
                break;
            count0--;
        } else {
            if(count1 == 0) 
                break;
            count1--;
        }
    }
    return count0 + count1;
}
#include <math.h>

long long minNumberOfSeconds(int mountainHeight, int* workerTimes, int workerTimesSize) {
    
    long long left = 1;
    long long right = (long long)1e18;
    long long ans = right;

    while(left <= right){
        long long mid = (left + right) / 2;

        long long total = 0;

        for(int i = 0; i < workerTimesSize; i++){
            long long w = workerTimes[i];

            long long work = mid / w;

            long long k = (sqrt(1 + 8.0 * work) - 1) / 2;

            total += k;

            if(total >= mountainHeight) break;
        }

        if(total >= mountainHeight){
            ans = mid;
            right = mid - 1;
        }
        else{
            left = mid + 1;
        }
    }

    return ans;
}
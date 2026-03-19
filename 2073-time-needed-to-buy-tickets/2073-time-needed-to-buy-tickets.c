int timeRequiredToBuy(int* tickets, int ticketsSize, int k) {
    int x=tickets[k];
    int time=0;
    int n=ticketsSize;
    for(int i=0;i<n;i++){
        if(i<=k){
            time+=(tickets[i] < x) ? tickets[i] : x;
        }
        else{
            time += (tickets[i] < x-1) ? tickets[i] : x-1;
        }
    }
    return time;
}
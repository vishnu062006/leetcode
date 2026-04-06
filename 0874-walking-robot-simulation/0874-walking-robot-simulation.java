import java.util.*;

class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        
        // store obstacles
        Set<String> set = new HashSet<>();
        for (int[] obs : obstacles) {
            set.add(obs[0] + "," + obs[1]);
        }
        
        int x = 0, y = 0;
        int dir = 0; // 0=N, 1=E, 2=S, 3=W
        
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        
        int maxDist = 0;
        
        for (int cmd : commands) {
            
            if (cmd == -2) {
                dir = (dir + 3) % 4; // left
            } 
            else if (cmd == -1) {
                dir = (dir + 1) % 4; // right
            } 
            else {
                for (int i = 0; i < cmd; i++) {
                    int nx = x + dx[dir];
                    int ny = y + dy[dir];
                    
                    if (set.contains(nx + "," + ny)) {
                        break; // obstacle hit
                    }
                    
                    x = nx;
                    y = ny;
                    
                    maxDist = Math.max(maxDist, x*x + y*y);
                }
            }
        }
        
        return maxDist;
    }
}
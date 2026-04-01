class Robot {
    int index, position, health;
    char direction;
    Robot(int i, int p, int h, char d) {
        index = i;
        position = p;
        health = h;
        direction = d;
    }
}

public class Solution {
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;
        Robot[] robots = new Robot[n];
        for (int i = 0; i < n; i++) {
            robots[i] = new Robot(i, positions[i], healths[i], directions.charAt(i));
        }

        // Sort robots by position (so collisions happen in order)
        Arrays.sort(robots, Comparator.comparingInt(r -> r.position));

        Deque<Robot> stack = new ArrayDeque<>();

        for (Robot robot : robots) {
            if (robot.direction == 'R') {
                stack.push(robot);
            } else {
                // Handle collisions with right-moving robots
                while (!stack.isEmpty() && stack.peek().direction == 'R' && robot.health > 0) {
                    Robot prev = stack.peek();
                    if (prev.health == robot.health) {
                        // Both destroyed
                        stack.pop();
                        robot.health = 0;
                    } else if (prev.health < robot.health) {
                        // Right robot dies, left loses 1 health
                        stack.pop();
                        robot.health -= 1;
                    } else {
                        // Left robot dies, right loses 1 health
                        prev.health -= 1;
                        robot.health = 0;
                    }
                }
                if (robot.health > 0) {
                    stack.push(robot);
                }
            }
        }

        // Sort surviving robots back by original index
        List<Robot> survivors = new ArrayList<>(stack);
        survivors.sort(Comparator.comparingInt(r -> r.index));

        List<Integer> result = new ArrayList<>();
        for (Robot r : survivors) {
            result.add(r.health);
        }
        return result;
    }
}

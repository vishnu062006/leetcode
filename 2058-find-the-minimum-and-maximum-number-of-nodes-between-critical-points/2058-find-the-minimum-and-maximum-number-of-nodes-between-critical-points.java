/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        // A linked list must have at least 3 nodes to have any critical points
        if (head == null || head.next == null || head.next.next == null) {
            return new int[]{-1, -1};
        }
        
        int firstCrit = -1;
        int prevCrit = -1;
        int minDist = Integer.MAX_VALUE;
        
        ListNode prev = head;
        ListNode curr = head.next;
        int position = 1; // 0-based indexing from the head
        
        while (curr.next != null) {
            ListNode nextNode = curr.next;
            
            // Check if the current node is a local maxima or local minima
            if ((curr.val > prev.val && curr.val > nextNode.val) || 
                (curr.val < prev.val && curr.val < nextNode.val)) {
                
                // If it's the first critical point we've found
                if (firstCrit == -1) {
                    firstCrit = position;
                } else {
                    // Calculate distance from the previous critical point
                    minDist = Math.min(minDist, position - prevCrit);
                }
                // Update the previous critical point to the current one
                prevCrit = position;
            }
            
            // Move to the next set of nodes
            prev = curr;
            curr = nextNode;
            position++;
        }
        
        // If we found fewer than 2 critical points
        if (minDist == Integer.MAX_VALUE) {
            return new int[]{-1, -1};
        }
        
        // maxDist is always the distance between the last critical point and the first critical point
        int maxDist = prevCrit - firstCrit;
        
        return new int[]{minDist, maxDist};
    }
}
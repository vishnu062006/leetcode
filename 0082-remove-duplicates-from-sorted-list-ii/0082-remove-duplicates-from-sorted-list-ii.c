/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */
struct ListNode* deleteDuplicates(struct ListNode* head) {
    struct ListNode dummy;
    dummy.next = head;

    struct ListNode *prev = &dummy;
    struct ListNode *temp = head;

    while (temp != NULL) {
        if (temp->next != NULL && temp->val == temp->next->val) {
            int dup = temp->val;

            while (temp != NULL && temp->val == dup) {
                temp = temp->next;
            }

            prev->next = temp;
        } else {
            prev = temp;
            temp = temp->next;
        }
    }

    return dummy.next;
}
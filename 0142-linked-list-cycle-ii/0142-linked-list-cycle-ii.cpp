/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
class Solution {
public:
    ListNode* detectCycle(ListNode* head) {
        // Initialize two pointers for cycle detection
        ListNode* fastPointer = head;
        ListNode* slowPointer = head;
      
        // Phase 1: Detect if a cycle exists using two pointers moving at different speeds
        while (fastPointer != nullptr && fastPointer->next != nullptr) {
            // Move slow pointer one step forward
            slowPointer = slowPointer->next;
            // Move fast pointer two steps forward
            fastPointer = fastPointer->next->next;
            if (slowPointer == fastPointer) {
                // Phase 2: Find the start of the cycle
                // Mathematical proof: Distance from head to cycle start equals
                // distance from meeting point to cycle start
                ListNode* startPointer = head;
                while (startPointer != slowPointer) {
                    startPointer = startPointer->next;
                    slowPointer = slowPointer->next;
                }
                return startPointer;
            }
        }
        return nullptr;
    }
};

/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {
public:
    void reorderList(ListNode* head) {
        // Edge case: empty list or single node
        if (!head || !head->next) {
            return;
        }
      
        // Step 1: Find the middle of the linked list using two pointers
        ListNode* fastPtr = head;
        ListNode* slowPtr = head;
      
        // Move fast pointer twice as fast as slow pointer
        // When fast reaches end, slow will be at middle
        while (fastPtr->next && fastPtr->next->next) {
            slowPtr = slowPtr->next;
            fastPtr = fastPtr->next->next;
        }
      
        // Step 2: Split the list into two halves
        // Second half starts from slowPtr->next
        ListNode* secondHalfHead = slowPtr->next;
        slowPtr->next = nullptr;  // Disconnect first half from second half
      
        // Step 3: Reverse the second half of the list
        ListNode* previous = nullptr;
        ListNode* current = secondHalfHead;
      
        while (current) {
            ListNode* nextTemp = current->next;  // Store next node
            current->next = previous;             // Reverse the link
            previous = current;                   // Move previous forward
            current = nextTemp;                   // Move current forward
        }
      
        // Step 4: Merge the two halves alternately
        // previous now points to the head of reversed second half
        ListNode* firstHalfCurrent = head;
        ListNode* secondHalfCurrent = previous;
      
        while (secondHalfCurrent) {
            // Store next nodes to avoid losing references
            ListNode* firstHalfNext = firstHalfCurrent->next;
            ListNode* secondHalfNext = secondHalfCurrent->next;
          
            // Interweave nodes: first -> second -> original first's next
            firstHalfCurrent->next = secondHalfCurrent;
            secondHalfCurrent->next = firstHalfNext;
          
            // Move pointers forward
            firstHalfCurrent = firstHalfNext;
            secondHalfCurrent = secondHalfNext;
        }
    }
};
package trains;

public class Linked86 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    //[1,4,3,2,5,2] 3
    public ListNode partition(ListNode head, int x) {
        ListNode minStart, minEnd, maxStart, maxEnd;
        minStart = minEnd = maxStart = maxEnd = null;
        while (head != null) {
            if (head.val < x) {
                if (minStart == null) {
                    minStart = minEnd = head;
                } else {
                    minEnd.next = head;
                    minEnd = head;
                }

            } else {
                if (maxStart == null) {
                    maxStart = maxEnd = head;
                } else {
                    maxEnd.next = head;
                    maxEnd = head;
                }
            }
            head = head.next;
        }
        if (maxEnd != null) {
            maxEnd.next = null;
        }

        if (minEnd != null) {
            minEnd.next = maxStart;
        } else {
            minStart = maxStart;
        }
        return minStart;
    }

    public static void main(String[] args) {

    }
}

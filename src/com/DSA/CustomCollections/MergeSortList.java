package com.DSA.CustomCollections;

public class MergeSortList {
    public class ListNode {
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

    public ListNode sortList (ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode mid = getMid(head);
        ListNode left = sortList(head);
        ListNode right = sortList(mid);

        return merge(left, right);
    }

    ListNode merge(ListNode list1, ListNode list2) {
        ListNode ans = new ListNode();
        ListNode tail = ans;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                tail.next = list1;
                list1 = list1.next;
                tail = tail.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
                tail = tail.next;
            }
        }
        tail.next = (list1 != null) ? list1 : list2;
        return ans.next;
    }
    public ListNode getMid(ListNode head) {
        ListNode midPrev = null;
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && fast.next != null) {
            midPrev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        return midPrev;
    }
    private ListNode reverse (ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }

        ListNode prev = null;
        ListNode pres = head;
        ListNode next = pres.next;

        while (pres != null) {
            pres.next = prev;
            prev = pres;
            pres = next;
            if (next != null){
                next = next.next;
            }
        }
        return prev;
    }

    public void reorderList(ListNode head) {
        if(head == null || head.next == null) {
            return;
        }

        ListNode mid = getMid(head);
        ListNode head1 = head;
        ListNode head2 = reverse(mid);
        while (head1 !=  null && head2 !=null) {
            ListNode temp = head1.next;
            head1.next = head2;
            head1 = temp;

            temp = head2.next;
            head2.next = head1;
            head2 = temp;
        }
        if(head1 != null) {
            head1.next = null;
        }
    }

    public ListNode rotateRight(ListNode head, int k) {
        if(k <= 0 || head == null || head.next == null) {
            return head;
        }
        ListNode last = head;
        int length = 1;
        while (last.next != null) {
            last = last.next;
            length++;
        }
        int roatations = k % length;
        if(roatations == 0) {
            return head;
        }
        last.next = head;
        ListNode newLast = head;
        for (int i = 0; i < length - roatations - 1; i++) {
            newLast = newLast.next;
        }
        head = newLast.next;
        newLast.next = null;
        return head;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (k <= 1 || head == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = dummy;
        ListNode prev = dummy;
        ListNode next = dummy;
        int count = 0;
        while (current.next != null) {
            count++;
            current = current.next;
        }
        while (count >=k) {
            current = prev.next;
            next = current.next;

            for (int i = 1; i < k; i++) {
                current.next = next.next;
                next.next = prev.next;
                prev.next = next;
                next = current.next;
            }
            prev = current;
            count = count - k;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
    }
}

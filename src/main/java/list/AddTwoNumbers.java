package list;

/**
 * @author Yang
 * @version 0.1 2017/10/4.
 ************************************************************************************************
 * 2. Add Two Numbers
 * https://leetcode.com/problems/add-two-numbers/
 ************************************************************************************************
 * You are given two non-empty linked lists representing two non-negative integers. The digits
 * are stored in reverse order and each of their nodes contain a single digit. Add the two
 * numbers and return it as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Example:
 *  Input:  (2 -> 4 -> 3) + (5 -> 6 -> 4)
 *  Output: 7 -> 0 -> 8
 ************************************************************************************************
 */
public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;
        // 进位
        int carry = 0;
        while(null != l1 || null != l2 || 0 != carry) {
            int num1 = 0;
            if (l1 != null) {
                num1 = l1.val;
                l1 = l1.next;
            }
            int num2 = 0;
            if (l2 != null) {
                num2 = l2.val;
                l2 = l2.next;
            }
            int sum = num1 + num2 + carry;
            carry = sum / 10;
            current.next = new ListNode(sum % 10);
            current = current.next;
        }
        return dummyHead.next;
    }

    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
        public String toString() {
            if (next == null) {
                return val + "";
            }
            return val + ", " + this.next;
        }
    }

    // =================================================================================

    private ListNode arrayToListNode(int[] array) {
        ListNode dummyHead = new ListNode(0);
        ListNode currentNode = dummyHead;
        for (int num : array) {
            currentNode.next = new ListNode(num);
            currentNode = currentNode.next;
        }

        return dummyHead.next;
    }

    private void runTests(ListNode l1, ListNode l2, ListNode expectedAnswers) {
        ListNode result = addTwoNumbers(l1, l2);
        boolean testFailed = false;
        l1 = result;
        l2 = expectedAnswers;
        while (null != l1 && null != l2) {
            if (l1.val != l2.val) {
                testFailed = true;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        if (null != l1 || null != l2) {
            testFailed = true;
        }
        if (testFailed) {
            System.out.println("Test failed, expected answer: " + expectedAnswers
                    + ", the program returns: " + result);
        } else {
            System.out.println("Test pass, answer is: " + result);
        }
    }

    public static void main(String[] args) {
        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();

        addTwoNumbers.runTests(
                addTwoNumbers.arrayToListNode(new int[]{2,4,3}),
                addTwoNumbers.arrayToListNode(new int[]{5,6,4}),
                addTwoNumbers.arrayToListNode(new int[]{7,0,8}));
        addTwoNumbers.runTests(
                addTwoNumbers.arrayToListNode(new int[]{4}),
                addTwoNumbers.arrayToListNode(new int[]{6}),
                addTwoNumbers.arrayToListNode(new int[]{0,1}));
    }
}

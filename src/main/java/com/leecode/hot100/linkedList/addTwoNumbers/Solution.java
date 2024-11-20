package com.leecode.hot100.linkedList.addTwoNumbers;


class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 结果指针 head为结果头指针，result指针用于增加结果
        ListNode result = null , head = null;
        // 进位
        int carrayOverNum = 0;
        while (l1 != null && l2 != null) {
            // 对应数量级的数字相加
            int sum = l1.val + l2.val + carrayOverNum;
         // 判断是否需要进位
            if (sum >= 10) {
                carrayOverNum = sum / 10;
                sum = sum % 10;
            } else {
                carrayOverNum = 0;
            }
            // store sum result
            if (result == null) {
                result = new ListNode(sum);
                head = result;
            } else {
                result.next = new ListNode(sum);
                result = result.next;
            }
            l1 = l1.next;
            l2 = l2.next;
        }

        // l1比较长没有累加完
        while (l1 != null) {
            int sum = l1.val + carrayOverNum;
            if (sum >= 10) {
                carrayOverNum = sum / 10;
                sum = sum % 10;
            } else {
                carrayOverNum = 0;
            }
            result.next = new ListNode(sum);
            result = result.next;
            l1 = l1.next;
        }

        // l2比较长没有累加完
        while (l2 != null) {
            int sum = l2.val + carrayOverNum;
            if (sum >= 10) {
                carrayOverNum = sum / 10;
                sum = sum % 10;
            } else {
                carrayOverNum = 0;
            }
            result.next = new ListNode(sum);
            result = result.next;
            l2 = l2.next;
        }

        // 全部加完之后还有进位
        if (carrayOverNum != 0) {
            result.next = new ListNode(carrayOverNum);
        }

        return head;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        ListNode l11 = new ListNode(4);
        ListNode l12 = new ListNode(3);
        l1.next = l11;
        l11.next = l12;

        ListNode l2 = new ListNode(5);
        ListNode l21 = new ListNode(6);
        ListNode l22 = new ListNode(4);
        l2.next = l21;
        l21.next = l22;

        /*ListNode l1 = new ListNode(3);
        l1.next = new ListNode(7);

        ListNode l2 = new ListNode(9);
        l2.next = new ListNode(2);*/

        ListNode listNode = new Solution().addTwoNumbers(l1, l2);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
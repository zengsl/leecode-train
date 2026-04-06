package trains.sort;

import java.util.Arrays;

public class ArraySort {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {5, 2, 3, 1};
//        int[] nums = {5, 1, 1, 2, 0, 0};
        solution.sortArray(nums);
        //  5 2222 1 1  6
        //  6 2222 1 1  5
        //  1 2222 1 6  5
        System.out.println(Arrays.toString(nums));
    }

    static class Solution {
        public int[] sortArray(int[] nums) {
            heapSort(nums);
//            quickSort(nums, 0, nums.length - 1);
            return nums;
        }

        private static int eqStart, eqEnd;

        public void quickSort(int[] nums, int l, int r) {
            if (l >= r) {
                return;
            }
            int randomNum = nums[(int) (l + Math.random() * (r - l + 1))];
            partition(nums, l, r, randomNum);
            quickSort(nums, l, eqStart - 1);
            quickSort(nums, eqEnd + 1, r);
        }

        public void partition(int[] nums, int l, int r, int k) {
            eqStart = l;
            eqEnd = r;
            int i = l;
            while (i <= eqEnd) {
                if (nums[i] < k) {
                    swap(nums, i++, eqStart++);
                } else if (nums[i] > k) {
                    swap(nums, i, eqEnd--);
                } else {
                    i++;
                }
            }
        }

        public void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }

        public static int[] arr;
        public static int len;

        public void heapSort(int[] nums) {
            arr = nums;
            len = nums.length;
            // 创建大根堆
            for (int i = len - 1; i >= 0; i--) {
                heapify(i);
            }
            while (len > 1) {
                swap(arr, 0, --len);
                heapify(0);
            }
        }

        public void heapify(int i) {
            int left = i * 2 + 1;
            while (left < len) {
                int right = left + 1;
                int best = right < len && arr[right] > arr[left] ? right : left;
                best = arr[best] > arr[i] ? best : i;
                if (best == i) {
                    return;
                }
                swap(arr, i, best);
                i = best;
                left = i * 2 + 1;
            }
        }
    }
}

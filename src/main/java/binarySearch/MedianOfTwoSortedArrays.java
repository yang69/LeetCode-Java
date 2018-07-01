package binarySearch;

/**
 * @author Yang
 * @version 0.1 2017/10/4.
 ************************************************************************************************
 * 4. Median of Two Sorted Arrays
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 * https://leetcode.com/articles/median-of-two-sorted-arrays/
 ************************************************************************************************
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * Find the median of the two sorted arrays. The overall run time complexity
 * should be O(log (m+n)).
 *
 * Example 1:
 *  Input:  nums1 = [1, 3], nums2 = [2]
 *  Output: 2.0
 *
 * Example 2:
 *  Input:  nums1 = [1, 2], nums2 = [3, 4]
 *  Output: 2.5
 ************************************************************************************************
 */
public class MedianOfTwoSortedArrays {
    /**
     * 假设A、B两个数组满足A的长度不大于B，A、B已排序，
     * 找到他们的中位数等价于将A、B都分别划分为两个部分，
     *      left_part          |        right_part
     *      A[0], A[1], ..., A[i-1]  |  A[i], A[i+1], ..., A[m-1]
     *      B[0], B[1], ..., B[j-1]  |  B[j], B[j+1], ..., B[n-1]
     * 确保：
     *      len(left_part)=len(right_part) - 左边部分可以比右边部分多一个
     *      max(left_part)≤min(right_part)
     * 则：
     *      median=​[​​max(left_part)+min(right_part)​​]/2，m+n是偶数
     *      或max(left_part)，m+n是奇数
     * 易见：
     *      i + j = m + n（m、n分别是A和B的长度）
     *      j = (m + n + 1)/2 - i
     * 问题转化为对A进行二分查找
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] a;
        int[] b;
        if (nums1.length < nums2.length) {
            a = nums1;
            b = nums2;
        } else {
            a = nums2;
            b = nums1;
        }
        int m = a.length;
        int n = b.length;
        int lo = 0;
        int hi = a.length;
        while (lo < hi) {
            int i = lo + (hi - lo)/2;
            int j = (m + n + 1)/2 - i;
            if (i == 0 || j == n || a[i-1] <= b[j]) {
                lo = i;
            } else {
                hi = i - 1;
            }
            if (j == 0 || i == m || b[j-1] <= a[i]) {
                hi = i;
            } else {
                lo = i + 1;
            }
        }
        int j = (m+n+1)/2 - lo;
        int leftMax = Integer.MIN_VALUE;
        int rightMin = Integer.MAX_VALUE;
        if (lo > 0) {
            leftMax = Math.max(leftMax, a[lo-1]);
        }
        if (j > 0) {
            leftMax = Math.max(leftMax, b[j-1]);
        }
        if ((m+n) % 2 != 0) {
            return leftMax;
        }
        if (lo < m) {
            rightMin = Math.min(rightMin, a[lo]);
        }
        if (j < n) {
            rightMin = Math.min(rightMin, b[j]);
        }

        return (leftMax + rightMin)/2.0;
    }

    // =================================================================================

    private void runTests(int[] nums1, int[] nums2, double expectedAnswers) {
        double result = findMedianSortedArrays(nums1, nums2);
        // 通常，判断浮点数相等不能用“==”
        boolean testFailed = Math.abs(result - expectedAnswers) > 1e-7;
        if (testFailed) {
            System.out.println("Test failed, expected answer: " + expectedAnswers
                    + ", the program returns: " + result);
        } else {
            System.out.println("Test pass, answer is: " + result);
        }
    }

    public static void main(String[] args) {
        MedianOfTwoSortedArrays mtsa = new MedianOfTwoSortedArrays();

        mtsa.runTests(new int[]{1}, new int[]{2}, 1.5);
        mtsa.runTests(new int[]{1,3}, new int[]{2}, 2.0);
        mtsa.runTests(new int[]{1,2,3}, new int[]{5}, 2.5);
    }
}

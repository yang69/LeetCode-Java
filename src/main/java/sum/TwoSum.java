package sum;

import org.junit.Test;

import java.util.HashMap;

/**
 * @author Yang
 * @version 0.1 2017/10/4.
 ************************************************************************************************
 * 1. Two Sum
 * https://leetcode.com/problems/two-sum/
 * 找到和为target的数
 *  15. 3Sum（数组中，和为target的3个数，先排序，再用双指针）
 *  16. 3Sum Closest（数组中，和最接近于target的3个数，输出其和，先排序，再用双指针）
 *  18. 4Sum（数组中，和为target的4个数，先排序，再依次转化为3Sum和2Sum问题）
 *  454. 4Sum II（4个数组中，分别取1个数，其中和为0的取法的个数，转换为2组2个数的和互为相反数，用HashMap）
 *  167. Two Sum II - Input array is sorted(排序数组中，和为target的两个数，用双指针)
 *  653. Two Sum IV - Input is a BST(二叉查找树中，和为target的两个数，用HashSet)
 ************************************************************************************************
 * Given an array of integers, return indices of the two numbers such that they add up to a
 * specific target.
 * You may assume that each input would have exactly one solution, and you may not use the same
 * element twice.
 *
 * Example:
 *  Input:  nums = [2, 7, 11, 15], target = 9,
 *  Output: [0, 1].
 *  Explanation:
 *      nums[0] + nums[1] = 2 + 7 = 9
 ************************************************************************************************
 */
public class TwoSum {
    /**
     * 利用hash表，快速找到数字
     * use a hashtable to locate a number in O(1) time
     */
    public int[] twoSum(int[] nums, int target) {
        // use a map to find a num's index
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution.");
    }

    /**
     * 穷举法
     * brute force
     */
    public int[] twoSumBruteForce(int[] nums, int target) {
         for(int i = 0; i < nums.length - 1; i++) {
         	for(int j = i+1; j < nums.length; j++) {
         		if(nums[i] + nums[j] == target) {
         			return new int[]{i,j};
         		}
         	}
         }
         throw new IllegalArgumentException("No two sum solution");
    }

    // =================================================================================

    /**
     * 将数组打印成字符串
     * Print array to string
     */
    private String arrayToString(int[] array) {
        if (null == array || 0 == array.length) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        sb.append('[');

        for (int num : array) {
            sb.append(num).append(',');
        }

        sb.deleteCharAt(sb.length() - 1);
        sb.append(']');

        return sb.toString();
    }

    private void runTests(int[] nums, int target, int[] expectedAnswers) {
        int[] result = twoSum(nums, target);
        boolean testFailed = false;
        if (result.length != expectedAnswers.length) {
            testFailed = true;
        } else {
            for (int i = 0; i < result.length; i++) {
                if (result[i] != expectedAnswers[i]) {
                    testFailed = true;
                }
            }
        }
        if (testFailed) {
            System.out.println("Test failed, expected answer: " + arrayToString(expectedAnswers)
                    + ", the program returns: " + arrayToString(result));
        } else {
            System.out.println("Test pass, answer is: " + arrayToString(result));
        }
    }

    @Test
    public void test() {
        runTests(new int[]{2,7,11,15}, 9, new int[]{0, 1});
        runTests(new int[]{2,2}, 4, new int[]{0, 1});
    }
}

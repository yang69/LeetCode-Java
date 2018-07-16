package array;

/**
 * @author Yang
 * @version 0.1 2018/07/16.
 ************************************************************************************************
 * 11. Container With Most Water
 * https://leetcode.com/problems/container-with-most-water/
 * https://leetcode.com/articles/container-with-most-water
 ************************************************************************************************
 * Given n non-negative integers a1, a2, ..., an, where each represents a point at
 * coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i
 * is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container,
 * such that the container contains the most water.
 *
 * Note:
 *      You may not slant the container and n is at least 2.
 ************************************************************************************************
 */
public class ContainerWithMostWater {
    public int maxAreaBruteForce(int[] height) {
        int res = 0;

        for (int i = 0; i < height.length - 1; i++) {
            for (int j = 0; j < height.length; j++) {
                res = Math.max(res, Math.min(height[i], height[j]) * (j - i));
            }
        }

        return res;
    }

    public int maxAreaTwoPointer(int[] height) {
        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;

        while (left < right) {
            maxArea = Math.max(maxArea, Math.min(height[left], height[right]) * (right - left));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

    // =================================================================================

    private String arrayToString(int[] array) {
        if (null == array || 0 == array.length) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append('[').append(array[0]);
        for (int i = 1; i < array.length; i++) {
            sb.append(',').append(array[i]);
        }
        sb.append(']');

        return sb.toString();
    }

    private enum Algorithm {
        BRUTE_FORCE, TWO_POINTER
    }

    private static Algorithm algorithm;

    public int maxArea(int[] height) {
        switch (algorithm) {
            case BRUTE_FORCE:
                return maxAreaBruteForce(height);
            case TWO_POINTER:
                return maxAreaTwoPointer(height);
            default:
                throw new IllegalStateException("Algorithm not defined.");
        }
    }

    private void runTests(int[] height, int expectedAnswer) {
        int result = maxArea(height);
        boolean testFailed = result != expectedAnswer;
        if (testFailed) {
            System.out.println("[FAILED]Test case: [height=" + arrayToString(height) +  "], expected answer: " + expectedAnswer
                    + ", the program returns: " + result);
        } else {
            System.out.println("Test case: [height=" + arrayToString(height) +  "], pass, answer is: " + result);
        }
    }

    public static void main(String[] args) {
        ContainerWithMostWater cwmw = new ContainerWithMostWater();

        cwmw.algorithm = Algorithm.BRUTE_FORCE;
        cwmw.runTests(new int[]{1,2,3,4,5,6,7}, 12);
        cwmw.runTests(new int[]{1,2}, 1);
        cwmw.runTests(new int[]{3,3,3,3,3,3}, 15);
        cwmw.runTests(new int[]{5,1,8,4,3,1,6,7,2,1,9,5}, 64);

        cwmw.algorithm = Algorithm.TWO_POINTER;
        cwmw.runTests(new int[]{1,2,3,4,5,6,7}, 12);
        cwmw.runTests(new int[]{1,2}, 1);
        cwmw.runTests(new int[]{3,3,3,3,3,3}, 15);
        cwmw.runTests(new int[]{5,1,8,4,3,1,6,7,2,1,9,5}, 64);
    }
}

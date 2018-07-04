package number.convert;

/**
 * @author Yang
 * @version 0.1 2018/07/03.
 ************************************************************************************************
 * 7. Reverse Integer
 * https://leetcode.com/problems/reverse-integer/
 * https://leetcode.com/articles/reverse-integer/
 ************************************************************************************************
 * Given a 32-bit signed integer, reverse digits of an integer.
 *
 * Example 1:
 *  Input:  123
 *  Output: 321
 *
 * Example 2:
 *  Input:  -123
 *  Output: -321
 *
 * Example 3:
 *  Input:  120
 *  Output: 21
 *
 * Note:
 *      Assume we are dealing with an environment which could only store integers within the
 *      32-bit signed integer range: [−2^31,  2^31 − 1]. For the purpose of this problem,
 *      assume that your function returns 0 when the reversed integer overflows.
 ************************************************************************************************
 */
public class ReverseInteger {
    public int reverse(int x) {
        long res = 0;

        while (x != 0) {
            res = 10*res + x%10;
            x /= 10;
        }
        if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) {
            return 0;
        }

        return (int)  res;
    }

    // =================================================================================

    private void runTests(int x, int expectedAnswer) {
        int result = reverse(x);
        boolean testFailed = result != expectedAnswer;
        if (testFailed) {
            System.out.println("Test failed, expected answer: " + expectedAnswer
                    + ", the program returns: " + result);
        } else {
            System.out.println("Test pass, answer is: " + result);
        }
    }

    public static void main(String[] args) {
        ReverseInteger reverseInteger = new ReverseInteger();
        reverseInteger.runTests(123,321);
        reverseInteger.runTests(-123,-321);
        reverseInteger.runTests(120,21);
    }
}

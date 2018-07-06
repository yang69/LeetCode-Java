package number.palindrome;

/**
 * @author Yang
 * @version 0.1 2018/07/05.
 ************************************************************************************************
 * 9. Palindrome Number
 * https://leetcode.com/problems/palindrome-number/
 ************************************************************************************************
 * Determine whether an integer is a palindrome. An integer is a palindrome when it reads the
 * same backward as forward.
 *
 * Example 1:
 *  Input:  121
 *  Output: true
 *
 * Example 2:
 *  Input:  -121
 *  Output: false
 *  Explanation:
 *      From left to right, it reads -121. From right to left, it becomes 121-. Therefore it
 *      is not a palindrome.
 *
 * Example 3:
 *  Input:  10
 *  Output: false
 *  Explanation:
 *      Reads 01 from right to left. Therefore it is not a palindrome.
 ************************************************************************************************
 */
public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        // 负数，以及200之类的数不是回文
        // negative value and such number as 100 can not be palindrome
        if (x < 0 || (x != 0 && x%10 == 0)) {
            return false;
        }
        int halfReverse = 0;
        // 将x从右边依次取一位，放入halfReverse的右侧
        while (halfReverse < x) {
            halfReverse = 10*halfReverse + x%10;
            x /= 10;
        }
        // 分别对应123321和12321这两类情况
        // eg. 123321 or 12321
        return halfReverse == x || x == halfReverse/10;
    }

    // =================================================================================

    private void runTests(int x, boolean expectedAnswer) {
        boolean result = isPalindrome(x);
        boolean testFailed = result != expectedAnswer;
        if (testFailed) {
            System.out.println("Test case: " + x +  ", failed, expected answer: " + expectedAnswer
                    + ", the program returns: " + result);
        } else {
            System.out.println("Test case: " + x +  ", pass, answer is: " + result);
        }
    }

    public static void main(String[] args) {
        PalindromeNumber palindromeNumber = new PalindromeNumber();
        palindromeNumber.runTests(121, true);
        palindromeNumber.runTests(-121, false);
        palindromeNumber.runTests(10, false);
    }
}

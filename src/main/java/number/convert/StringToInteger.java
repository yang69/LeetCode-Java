package number.convert;

/**
 * @author Yang
 * @version 0.1 2018/07/04.
 ************************************************************************************************
 * 8. String to Integer (atoi)
 * https://leetcode.com/problems/string-to-integer-atoi/
 ************************************************************************************************
 * Implement atoi which converts a string to an integer.
 * The function first discards as many whitespace characters as necessary until the first
 * non-whitespace character is found. Then, starting from this character, takes an optional
 * initial plus or minus sign followed by as many numerical digits as possible, and interprets
 * them as a numerical value.
 * The string can contain additional characters after those that form the integral number,
 * which are ignored and have no effect on the behavior of this function.
 * If the first sequence of non-whitespace characters in str is not a valid integral number,
 * or if no such sequence exists because either str is empty or it contains only whitespace
 * characters, no conversion is performed.
 * If no valid conversion could be performed, a zero value is returned.
 *
 * Note:
 *      Only the space character ' ' is considered as whitespace character.
 *      Assume we are dealing with an environment which could only store integers within the
 *      32-bit signed integer range: [−2^31,  2^31 − 1]. If the numerical value is out of the
 *      range of representable values, INT_MAX (2^31 − 1) or INT_MIN (−2^31) is returned.
 *
 * Example 1:
 *  Input:  "42"
 *  Output: 42
 *
 * Example 2:
 *  Input:  "   -42"
 *  Output: -42
 *  Explanation:
 *      The first non-whitespace character is '-', which is the minus sign.
 *      Then take as many numerical digits as possible, which gets 42.
 *
 * Example 3:
 *  Input:  "4193 with words"
 *  Output: 4193
 *  Explanation:
 *      Conversion stops at digit '3' as the next character is not a numerical digit.
 *
 * Example 4:
 *  Input:  "words and 987"
 *  Output: 0
 *  Explanation:
 *      The first non-whitespace character is 'w', which is not a numerical digit or a +/- sign.
 *      Therefore no valid conversion could be performed.
 *
 * Example 5:
 *  Input:  "-91283472332"
 *  Output: -2147483648
 *  Explanation:
 *      The number "-91283472332" is out of the range of a 32-bit signed integer.
 *      Thefore INT_MIN (−231) is returned.
 ************************************************************************************************
 */
public class StringToInteger {
    public int myAtoi(String str) {
        if (null == str) {
            return 0;
        }

        long res = 0;
        long sign = 1;
        int index = 0;
        while (index < str.length() && str.charAt(index) == ' ') {
            index++;
        }
        if (index < str.length() && str.charAt(index) == '+') {
            sign = 1;
            index++;
        } else if (index < str.length() && str.charAt(index) == '-') {
            sign = -1;
            index++;
        }
        while (index < str.length()) {
            char c = str.charAt(index);
            if (Character.isDigit(c)) {
                int digit = Character.getNumericValue(c);
                res = 10*res + digit;
                if (sign * res > Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
                }
                if (sign * res < Integer.MIN_VALUE) {
                    return Integer.MIN_VALUE;
                }
            } else {
                break;
            }
            index++;
        }

        return (int) (sign * res);
    }

    // =================================================================================

    private void runTests(String s, int expectedAnswer) {
        int result = myAtoi(s);
        boolean testFailed = result != expectedAnswer;
        if (testFailed) {
            System.out.println("Test failed, expected answer: " + expectedAnswer
                    + ", the program returns: " + result);
        } else {
            System.out.println("Test pass, answer is: " + result);
        }
    }

    public static void main(String[] args) {
        StringToInteger stringToInteger = new StringToInteger();

        stringToInteger.runTests("42", 42);
        stringToInteger.runTests("   -42", -42);
        stringToInteger.runTests("4193 with words", 4193);
        stringToInteger.runTests("words and 987", 0);
        stringToInteger.runTests("-91283472332", -2147483648);
    }
}

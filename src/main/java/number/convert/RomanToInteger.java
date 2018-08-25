package number.convert;

/**
 * @author Yang
 * @version 0.1 20180825
 * Created by Yang on 2017/10/4.
 ************************************************************************************************
 * 13. Roman to Integer
 * https://leetcode.com/problems/roman-to-integer/
 * 数字转换
 *  12. Integer to Roman（阿拉伯数字转罗马数字）
 *  660. Remove 9（十进制转九进制）
 ************************************************************************************************
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 *      Symbol       Value
 *      I             1
 *      V             5
 *      X             10
 *      L             50
 *      C             100
 *      D             500
 *      M             1000
 * For example, two is written as II in Roman numeral, just two one's added together. Twelve is
 * written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which
 * is XX + V + II.
 * Roman numerals are usually written largest to smallest from left to right. However, the numeral
 * for four is not IIII. Instead, the number four is written as IV. Because the one is before the
 * five we subtract it making four. The same principle applies to the number nine, which is written
 * as IX. There are six instances where subtraction is used:
 *      I can be placed before V (5) and X (10) to make 4 and 9.
 *      X can be placed before L (50) and C (100) to make 40 and 90.
 *      C can be placed before D (500) and M (1000) to make 400 and 900.
 * Given a roman numeral, convert it to an integer.
 * Input is guaranteed to be within the range from 1 to 3999.
 *
 * Example 1:
 *  Input:  "III"
 *  Output: 3
 *
 * Example 2:
 *  Input:  "IV"
 *  Output: 4
 *
 * Example 3:
 *  Input:  "IX"
 *  Output: 9
 *
 * Example 4:
 *  Input:  "LVIII"
 *  Output: 58
 *  Explanation:
 *      C = 100, L = 50, XXX = 30 and III = 3.
 *
 * Example 5:
 *  Input:  "MCMXCIV"
 *  Output: 1994
 *  Explanation:
 *      M = 1000, CM = 900, XC = 90 and IV = 4.
 ************************************************************************************************
 */
public class RomanToInteger {
    public int romanToInt(String s) {
        int[] nums = new int[s.length()];
        for(int i = 0; i < s.length(); i++) {
            switch(s.charAt(i)) {
                case 'M' :
                    nums[i] = 1000;
                    break;
                case 'D' :
                    nums[i] = 500;
                    break;
                case 'C' :
                    nums[i] = 100;
                    break;
                case 'L' :
                    nums[i] = 50;
                    break;
                case 'X' :
                    nums[i] = 10;
                    break;
                case 'V' :
                    nums[i] = 5;
                    break;
                case 'I' :
                    nums[i] = 1;
                    break;
            }
        }

        int sum = 0;
        for(int i = 0; i < s.length()-1; i++) {
            if(nums[i] < nums[i+1]) {
                sum -= nums[i];
            }
            else {
                sum += nums[i];
            }
        }
        return sum + nums[s.length()-1];
    }

    // =================================================================================

    private void runTests(String s, int expectedAnswer) {
        int result = romanToInt(s);
        boolean testFailed = expectedAnswer != result;
        if (testFailed) {
            System.out.println("[FAILED]Test case: [s=" + s +  "], expected answer: " + expectedAnswer
                    + ", the program returns: " + result);
        } else {
            System.out.println("Test case: [s=" + s +  "], pass, answer is: " + result);
        }
    }

    public static void main(String[] args) {
        RomanToInteger romanToInteger = new RomanToInteger();

        romanToInteger.runTests("III", 3);
        romanToInteger.runTests("IV", 4);
        romanToInteger.runTests("IX", 9);
        romanToInteger.runTests("LVIII", 58);
        romanToInteger.runTests("MCMXCIV", 1994);
    }
}

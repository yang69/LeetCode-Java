package number.convert;

/**
 * @author Yang
 * @version 0.1 2018/07/16.
 * Created by Yang on 2017/10/4.
 ************************************************************************************************
 * 12. Integer to Roman
 * https://leetcode.com/problems/integer-to-roman/
 * 数字转换
 *  13. Roman to Integer（罗马数字转阿拉伯数字）
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
 * Given an integer, convert it to a roman numeral.
 * Input is guaranteed to be within the range from 1 to 3999.
 *
 * Example 1:
 *  Input:  3
 *  Output: "III"
 *
 * Example 2:
 *  Input:  4
 *  Output: "IV"
 *
 * Example 3:
 *  Input:  9
 *  Output: "IX"
 *
 * Example 4:
 *  Input:  58
 *  Output: "LVIII"
 *  Explanation:
 *      C = 100, L = 50, XXX = 30 and III = 3.
 *
 * Example 5:
 *  Input:  1994
 *  Output: "MCMXCIV"
 *  Explanation:
 *      M = 1000, CM = 900, XC = 90 and IV = 4.
 ************************************************************************************************
 */
public class IntegerToRoman {
    public String intToRoman(int num) {
        int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] strs = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                sb.append(strs[i]);
                num -= values[i];
            }
        }
        return sb.toString();
    }

    // =================================================================================

    private void runTests(int num, String expectedAnswer) {
        String result = intToRoman(num);
        boolean testFailed = !expectedAnswer.equals(result);
        if (testFailed) {
            System.out.println("[FAILED]Test case: [num=" + num +  "], expected answer: " + expectedAnswer
                    + ", the program returns: " + result);
        } else {
            System.out.println("Test case: [num=" + num +  "], pass, answer is: " + result);
        }
    }

    public static void main(String[] args) {
        IntegerToRoman integerToRoman = new IntegerToRoman();

        integerToRoman.runTests(3, "III");
        integerToRoman.runTests(4, "IV");
        integerToRoman.runTests(9, "IX");
        integerToRoman.runTests(58, "LVIII");
        integerToRoman.runTests(1994, "MCMXCIV");
    }
}

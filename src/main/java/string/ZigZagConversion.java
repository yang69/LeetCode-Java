package string;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Yang
 * @version 0.1 2018/07/02.
 ************************************************************************************************
 * 6. ZigZag Conversion
 * https://leetcode.com/problems/zigzag-conversion/
 * https://leetcode.com/articles/zigzag-conversion/
 ************************************************************************************************
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
 * (you may want to display this pattern in a fixed font for better legibility)
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 *
 * And then read line by line: "PAHNAPLSIIGYIR"
 * Write the code that will take a string and make this conversion given a number of rows:
 * string convert(string s, int numRows);
 * Example 1:
 *  Input:  s = "PAYPALISHIRING", numRows = 3
 *  Output: "PAHNAPLSIIGYIR"
 *
 * Example 2:
 *  Input:  s = "PAYPALISHIRING", numRows = 4
 *  Output: "PINALSIGYAHRPI"
 *  Explanation:
 *          P     I    N
 *          A   L S  I G
 *          Y A   H R
 *          P     I
 ************************************************************************************************
 */
public class ZigZagConversion {
    /**
     * 直接按顺序置入恰当位置处的字符
     */
    public String convert(String s, int numRows) {
        if (1 == numRows) {
            return s;
        }
        StringBuilder sb = new StringBuilder();

        // 添加第一行的字符
        for (int i = 0; i < s.length(); i += 2*numRows-2) {
            sb.append(s.charAt(i));
        }
        // 依次添加每一行的字符
        for (int row = 1; row < numRows - 1; row++) {
            for (int i = row, j = 2*numRows-2-row; i < s.length(); i += 2*numRows-2, j += 2*numRows-2) {
                sb.append(s.charAt(i));
                if (j < s.length()) {
                    sb.append(s.charAt(j));
                }
            }
        }
        // 添加最后一行的字符
        for (int i = numRows-1; i < s.length(); i += 2*numRows-2) {
            sb.append(s.charAt(i));
        }

        return sb.toString();
    }

    /**
     * 先按ZigZag的模式排列，然后按行拼接
     */
    public String convertZigZagPattern(String s, int numRows) {
        if (1 == numRows) {
            return s;
        }
        List<StringBuilder> rows = new LinkedList<>();
        for (int i = 0; i < Math.min(s.length(), numRows); i++) {
            rows.add(new StringBuilder());
        }
        int rowIndex = 0;
        int step = -1;
        for (char c : s.toCharArray()) {
            rows.get(rowIndex).append(c);
            if (0 == rowIndex || rows.size() - 1 == rowIndex) {
                step = -step;
            }
            rowIndex += step;
        }
        StringBuilder res = new StringBuilder();
        for (StringBuilder row : rows) {
            res.append(row);
        }

        return res.toString();
    }

    // =================================================================================

    private void runTests(String s, int numRows, String expectedAnswer) {
        String result = convert(s, numRows);
        boolean testFailed = !expectedAnswer.equals(result);
        if (testFailed) {
            System.out.println("Test failed, expected answer: " + expectedAnswer
                    + ", the program returns: " + result);
        } else {
            System.out.println("Test pass, answer is: " + result);
        }
    }
    
    public static void main(String[] args) {
        ZigZagConversion zigZagConversion = new ZigZagConversion();
        zigZagConversion.runTests("PAYPALISHIRING", 3, "PAHNAPLSIIGYIR");
        zigZagConversion.runTests("PAYPALISHIRING", 4, "PINALSIGYAHRPI");
    }
}

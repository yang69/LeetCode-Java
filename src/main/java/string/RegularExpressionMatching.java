package string;

/**
 * @author Yang
 * @version 0.1 2018/07/05.
 ************************************************************************************************
 * 10. Regular Expression Matching
 * https://leetcode.com/problems/palindrome-number/
 ************************************************************************************************
 * Given an input string (s) and a pattern (p), implement regular expression matching with
 * support for '.' and '*'.
 *  '.' Matches any single character.
 *  '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 *
 * Note:
 *      s could be empty and contains only lowercase letters a-z.
 *      p could be empty and contains only lowercase letters a-z, and characters like . or *.
 *
 * Example 1:
 *  Input:  s = "aa", p = "a"
 *  Output: false
 *  Explanation:
 *      "a" does not match the entire string "aa".
 *
 * Example 2:
 *  Input:  s = "aa", p = "a*"
 *  Output: true
 *  Explanation:
 *      '*' means zero or more of the precedeng element, 'a'. Therefore, by repeating 'a' once,
 *      it becomes "aa".
 *
 * Example 3:
 *  Input:  s = "aa", p = ".*"
 *  Output: true
 *  Explanation:
 *      ".*" means "zero or more (*) of any character (.)".
 *
 * Example 4:
 *  Input:  s = "aab", p = "c*a*b"
 *  Output: true
 *  Explanation:
 *      c can be repeated 0 times, a can be repeated 1 time. Therefore it matches "aab".
 *
 * Example 5:
 *  Input:  s = "mississippi", p = "mis*is*p*."
 *  Output: false
 ************************************************************************************************
 */
public class RegularExpressionMatching {

    public boolean isMatchRecursive(String s, String p) {
        if (null == p) {
            return false;
        }
        if (p.isEmpty()) {
            return s.isEmpty();
        }
        boolean firstMatch = s.length() > 0 && (p.charAt(0) == '.' || p.charAt(0) == s.charAt(0));
        if (p.length() > 1 && p.charAt(1) == '*') {
            // "firstMatch == true" means "s.length() > 0"
            return isMatchRecursive(s, p.substring(2)) || firstMatch && (isMatchRecursive(s.substring(1), p));
        } else {
            // "firstMatch == true" means "s.length() > 0"
            return firstMatch && isMatchRecursive(s.substring(1), p.substring(1));
        }
    }

    public boolean isMatchDpTopDown(String s, String p) {
        return isMatchDpTopDown(new boolean[s.length()+1][p.length()+1], new boolean[s.length()+1][p.length()+1], s, p, 0, 0);
    }

    private boolean isMatchDpTopDown(boolean memo[][], boolean visited[][], String s, String p, int i, int j) {
        if (visited[i][j]) {
            return memo[i][j];
        }
        if (j == p.length()) {
            memo[i][j] = i == s.length();
            visited[i][j] = true;
            return memo[i][j];
        }
        boolean firstMatch = i < s.length() && (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i));
        if (j < p.length() - 1 && p.charAt(j+1) == '*') {
            memo[i][j] = isMatchDpTopDown(memo, visited, s, p, i, j+2) || (firstMatch && isMatchDpTopDown(memo, visited, s, p, i+1, j));
            visited[i][j] = true;
            return memo[i][j];
        } else {
            memo[i][j] = firstMatch && isMatchDpTopDown(memo, visited, s, p, i+1, j+1);
            visited[i][j] = true;
            return memo[i][j];
        }
    }

    // =================================================================================

    private enum Algorithm {
        RECURSIVE, DP_TOP_DOWN
    }

    private static Algorithm algorithm = Algorithm.DP_TOP_DOWN;

    public boolean isMatch(String s, String p) {
        switch (algorithm) {
            case RECURSIVE:
                return isMatchRecursive(s, p);
            case DP_TOP_DOWN:
                return isMatchDpTopDown(s, p);
            default:
                throw new IllegalStateException("Algorithm not defined.");
        }
    }

    private void runTests(String s, String p, boolean expectedAnswer) {
        boolean result = isMatch(s, p);
        boolean testFailed = result != expectedAnswer;
        if (testFailed) {
            System.out.println("[FAILED]Test case: [s=" + s + ", p=" + p +  "], expected answer: " + expectedAnswer
                    + ", the program returns: " + result);
        } else {
            System.out.println("Test case: [s=" + s + ", p=" + p +  "], pass, answer is: " + result);
        }
    }

    public static void main(String[] args) {
        RegularExpressionMatching rem = new RegularExpressionMatching();

        algorithm = Algorithm.RECURSIVE;
        System.out.println("Begin testing for Recursive algorithm:");
        rem.runTests("aa", "a", false);
        rem.runTests("aa", "a*", true);
        rem.runTests("aa", ".*", true);
        rem.runTests("aab", "c*a*b", true);
        rem.runTests("mississippi", "mis*is*p*.", false);
        System.out.println();

        algorithm = Algorithm.DP_TOP_DOWN;
        System.out.println("Begin testing for DP top-down algorithm:");
        rem.runTests("aa", "a", false);
        rem.runTests("aa", "a*", true);
        rem.runTests("aa", ".*", true);
        rem.runTests("aab", "c*a*b", true);
        rem.runTests("mississippi", "mis*is*p*.", false);
    }
}

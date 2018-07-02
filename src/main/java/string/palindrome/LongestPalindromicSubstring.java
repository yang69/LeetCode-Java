package string.palindrome;

/**
 * @author Yang
 * @version 0.1 2018/06/28.
 ************************************************************************************************
 * 5. Longest Palindromic Substring
 * https://leetcode.com/problems/longest-palindromic-substring/
 * https://leetcode.com/articles/longest-palindromic-substring/
 ************************************************************************************************
 * Given a string s, find the longest palindromic substring in s. You may assume that the
 * maximum length of s is 1000.
 *
 * Example 1:
 *  Input:  "babad"
 *  Output: "bab"
 *  Note:   "aba" is also a valid answer.
 *
 * Example 2:
 *  Input:  "cbbd"
 *  Output: "bb"
 ************************************************************************************************
 */

public class LongestPalindromicSubstring {
    /**
     * 中心扩展法
     */
    public String longestPalindrome(String s) {
        if (null == s) {
            return null;
        }
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i+1);
            int len = Math.max(len1, len2);
            System.out.println(len);
            if (len > end - start) {
                start = i - (len-1)/2;
                end = i + len/2 + 1;
            }
        }

        return s.substring(start, end);
    }

    /**
     * 返回字符串以left和right为中心的最长回文子字符串的长度
     * right == left 或者 right == left + 1
     */
    private int expandAroundCenter(String s, int left, int right) {
        assert(left == right || left + 1 == right);
        int l = left;
        int r = right;
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }

        return r - l - 1;
    }

    // =================================================================================

    public static void main(String[] args) {
        LongestPalindromicSubstring longestPalindromicSubstring = new LongestPalindromicSubstring();
        // bab或者aba
        System.out.println(longestPalindromicSubstring.longestPalindrome("babad") + " <---> bab");
    }
}

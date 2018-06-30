package string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Yang
 * @version 0.1 2017/10/1.
 ************************************************************************************************
 * 3. Longest Substring Without Repeating Characters
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * https://discuss.leetcode.com/topic/68976/sliding-window-algorithm-template-to-solve-all-the-leetcode-substring-search-problem
 * 滑动窗口：
 *  30. Substring with Concatenation of All Words
 *  76. Minimum Window Substring
 *  438. Find All Anagrams in a String
 ************************************************************************************************
 * Given a string, find the length of the longest substring without repeating characters.
 *
 * Example 1:
 *  Input:  s = "abcabcbb"
 *  Output: "abc", which the length is 3.
 *
 * Example 2:
 *  Input:  s = "bbbbb"
 *  Output: "b", which the length is 1.
 *
 * Example 3:
 *  Input:  s = "pwwkew"
 *  Output: "wke", which the length is 3.
 *
 * Note:
 *      The answer must be a substring, for Example 3, "pwke" is a subsequence and not a substring.
 ************************************************************************************************
 */
public class LongestSubstringWithoutRepeatingCharacters {
    /**
     * 优化的滑动窗口
     */
    public int lengthOfLongestSubstringSlidingWindowOpt(String s) {
        int len = s.length();
        int ans = 0;
        Map<Character,Integer> map = new HashMap<>(); // 字符最后出现的位置索引
        // start指向当前滑动窗口的左边界（包括），end指向当前滑动窗口的右边界（包括）
        for(int start = 0, end = 0; end < len; end++) {
            if(map.containsKey(s.charAt(end))) { // 当前字符出现过，找出它最后出现的位置
                // 当前位置的字符上次的出现位置肯定不能在滑窗内，否则窗口内存在重复字符
                start = Math.max(map.get(s.charAt(end)) + 1, start);
            }
            ans = Math.max(ans, end-start+1);
            map.put(s.charAt(end), end);
        }
        return ans;
    }

    /**
     * 滑动窗口
     */
    public int lengthOfLongestSubstringSlidingWindow(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Set<Character> set = new HashSet<>();
        int n = s.length();
        int res = 0;
        int start = 0; // start是滑动窗口的左边界（包括）
        int end = 0; // end是滑动窗口的右边界（不包括）
        while (start < n && end < n) {
            if (!set.contains(s.charAt(end))) {
                set.add(s.charAt(end++));
                res = Math.max(res, end - start);
            } else {
                set.remove(s.charAt(start++));
            }
        }

        return res;
    }

    /**
     * 穷举法
     */
    public int lengthOfLongestSubstringBruteForce(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int res = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j <= n; j++) {
                if (allUnique(s, i, j)) {
                    res = Math.max(res, j - i);
                }
            }
        }

        return res;
    }

    private boolean allUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i < end; i++) {
            if (set.contains(s.charAt(i))) {
                return false;
            }
            set.add(s.charAt(i));
        }

        return true;
    }

    // =================================================================================

    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters lswrc = new LongestSubstringWithoutRepeatingCharacters();

        System.out.println(lswrc.lengthOfLongestSubstringBruteForce("abcabcbb")
                + " <---> " + lswrc.lengthOfLongestSubstringSlidingWindow("abcabcbb")
                + " <---> " + lswrc.lengthOfLongestSubstringSlidingWindowOpt("abcabcbb"));
        System.out.println(lswrc.lengthOfLongestSubstringBruteForce("bbbbb")
                + " <---> " + lswrc.lengthOfLongestSubstringSlidingWindow("bbbbb")
                + " <---> " + lswrc.lengthOfLongestSubstringSlidingWindowOpt("bbbbb"));
        System.out.println(lswrc.lengthOfLongestSubstringBruteForce("pwwkew")
                + " <---> " + lswrc.lengthOfLongestSubstringSlidingWindow("pwwkew")
                + " <---> " + lswrc.lengthOfLongestSubstringSlidingWindowOpt("pwwkew"));
    }
}

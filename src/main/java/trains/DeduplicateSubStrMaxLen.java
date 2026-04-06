package trains;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DeduplicateSubStrMaxLen {


    public static void main(String[] args) {
        System.out.println(computeMaxLen("abcabcbb"));
        System.out.println(computeMaxLen("abcde"));
        System.out.println(computeMaxLen("abcdefg"));
        System.out.println(computeMaxLen("abacabac"));
    }

    static int computeMaxLen(String s) {
        int maxLen = 0;
        char[] chars = s.toCharArray();
        int size = chars.length;
        Set<Character> existsSet = new HashSet<>();
        int l = 0, r = 0;
        while (l <= r && r < size) {
            while (existsSet.contains(chars[r])){
                existsSet.remove(chars[l++]);
            }
            existsSet.add(chars[r++]);
            maxLen = Math.max(maxLen, existsSet.size());
        }
        return maxLen;
    }
}

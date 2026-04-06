package luogu.class101;

import java.io.*;

public class RadioTransmission {

    public static void main(String[] args) throws IOException {
        try (PrintWriter writer = new PrintWriter(System.out);
             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));) {
            StreamTokenizer tokenizer = new StreamTokenizer(reader);
            while (tokenizer.nextToken() != StreamTokenizer.TT_EOF) {
                int l = (int) tokenizer.nval;
                tokenizer.nextToken();
                String target = tokenizer.sval;
                int minLen = computeMinSubStrLen(target, l);
                writer.println(minLen);
                writer.flush();
            }
        }
    }

    // 错误做法，没通过落谷
    public static int computeMinSubStrLen(String target, int len) {
        // ababfbabc
        // abab
        char[] chars = target.toCharArray();
        for (int i = 1; i <= len;) {
            int j = i;
            while (j < len && chars[j] == chars[j % i]) {
                j++;
            }
            if (j == len) {
                return i;
            }
            i = j + 1;
        }
        return 1;
    }
}

package com.study.algo.practice;


/**
 * @author ldb
 * @date 2020/8/25
 * @description 正则表达式
 */
public class Pattern {
    private boolean matched = false;
    private char[] pattern; // 正则表达式
    private int plen; // 正则表达式长度

    public Pattern(char[] pattern, int plen) {
        this.pattern = pattern;
        this.plen = plen;
    }

    public boolean match(char[] text, int tlen) { // 文本串及长度
        matched = false;
        rmatch(0, 0, text, tlen);
        return matched;
    }

  /**
   *
   * @param ti 文本下标
   * @param pj 正则表达式下标
   * @param text 文本
   * @param tlen 文本长度
   */
    private void rmatch(int ti, int pj, char[] text, int tlen) {
        // 如果已经匹配了，就不要继续递归了
        if (matched) {
          return;
        }
        // 正则表达式到结尾了
        if (pj == plen) {
            if (ti == tlen) {
              // 文本串也到结尾了
              matched = true;
            }
            return;
        }
        // * 匹配任意个字符
        if (pattern[pj] == '*') {
            for (int k = 0; k <= tlen - ti; ++k) {
                rmatch(ti + k, pj + 1, text, tlen);
            }
        } else if (pattern[pj] == '?') {
            // ? 匹配 0 个或者 1 个字符
            rmatch(ti, pj + 1, text, tlen);
            rmatch(ti + 1, pj + 1, text, tlen);
        } else if (ti < tlen && pattern[pj] == text[ti]) {
            // 纯字符匹配才行
            rmatch(ti + 1, pj + 1, text, tlen);
        }
    }

    public static void main(String[] args) {
        Pattern pattern = new Pattern("abc?aa*a".toCharArray(), 8);
        System.out.println(pattern.match("abcdaaba".toCharArray(), 8));

      // 去除单词与 , 和 . 之间的空格
      String Str = "Hello , World .";
      String pattern2 = "(\\w)(\\s+)([.,])";
      // $0 匹配 `(\w)(\s+)([.,])` 结果为 `o空格,` 和 `d空格.`
      // $1 匹配 `(\w)` 结果为 `o` 和 `d`
      // $2 匹配 `(\s+)` 结果为 `空格` 和 `空格`
      // $3 匹配 `([.,])` 结果为 `,` 和 `.`
      System.out.println(Str.replaceAll(pattern2, "$1$3")); // Hello, World.
    }
}
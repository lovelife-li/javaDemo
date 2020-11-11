package com.study.other.extend;

import java.util.*;

/**
 * 思路：因为是敏感词过滤，多字符串匹配，想到AC自动机算法来解决
 * @author 黎徳波
 */
public class EvilCorp {
    private ACNode root;

    public EvilCorp() {
        this.root = new ACNode("/");
    }

    /**
     * 插入字符串
     *
     * @param pattern 字符串
     */
    private void insert(String pattern) {
        ACNode node = this.root;
        int len = pattern.length();
        for (int i = 0; i < len; i++) {
            String c = pattern.charAt(i) + "";
            if (Objects.isNull(node.children.get(c))) {
                node.children.put(c, new ACNode(c));
            }
            node = node.children.get(c);
        }

        node.isEndingChar = true;
        node.length = pattern.length();
    }

    /**
     * 构建失败指针
     */
    private void buildFailurePointer() {
        ACNode root = this.root;
        LinkedList<ACNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            ACNode p = queue.pop();
            for (ACNode pc : p.children.values()) {
                if (Objects.isNull(pc)) {
                    continue;
                }
                if (p == root) {
                    pc.fail = root;
                } else {
                    ACNode q = p.fail;
                    while (Objects.nonNull(q)) {
                        ACNode qc = q.children.get(pc.data);
                        if (Objects.nonNull(qc)) {
                            pc.fail = qc;
                            break;
                        }
                        q = q.fail;
                    }
                    if (Objects.isNull(q)) {
                        pc.fail = root;
                    }
                }
                queue.add(pc);
            }
        }
    }

    /**
     * 匹配字符串，返回过滤词在字符串中的位置，长度
     *
     * @param text 字符串
     * @return 过滤词在字符串中的位置，长度  Map<Integer, Integer>
     */
    private Map<Integer, Integer> match(String text) {
        ACNode root = this.root;
        ACNode p = root;
        HashMap<Integer, Integer> res = new HashMap<>(16);
        int n = text.length();
        for (int i = 0; i < n; i++) {
            String c = text.charAt(i) + "";
            while (Objects.isNull(p.children.get(c)) && p != root) {
                p = p.fail;
            }
            p = p.children.get(c);
            if (Objects.isNull(p)) {
                p = root;
            }
            ACNode tmp = p;
            while (tmp != root) {
                if (tmp.isEndingChar == true) {
                    int key = i - p.length + 1;
                    // System.out.println("index: " + key + " , length:" + p.length);
                    res.put(key, p.length);
                }
                tmp = tmp.fail;
            }
        }

        return res;
    }

    /**
     * 过滤敏感词
     * strMap 和  defaultChar 只能生效一个。
     * @param text        字符串
     * @param patterns    敏感词数组
     * @param strMap      替换字符串map
     * @param defaultChar 默认替换字符
     * @return
     */
    public static String filterStr(String text, String[] patterns, HashMap<String, String> strMap,
                                   char defaultChar) {
        EvilCorp automata = new EvilCorp();
        for (String pattern : patterns) {
            automata.insert(pattern);
        }
        // 构建失败指针
        automata.buildFailurePointer();
        // a-z A-Z 放入Set
        HashSet<Integer> sets = new HashSet<>(128);
        for (int i = 'a'; i <= 'z'; i++) {
            sets.add(Integer.valueOf(i));
        }
        for (int i = 'A'; i <= 'Z'; i++) {
            sets.add(Integer.valueOf(i));
        }
        // 得到敏感词位置信息
        Map<Integer, Integer> res = automata.match(text);
        if (strMap == null) {
            return handleMethod1(text, res, sets, defaultChar);
        } else {
            return handleMethod2(text, res, sets, strMap);
        }
    }

    /***
     * 根据替换词进行替换
     * @param text  字符串
     * @param res   敏感词在字符串位置，长度
     * @param sets  记录英文字母
     * @param strMap    替换词map
     * @return  替换后字符串
     */
    private static String handleMethod2(String text, Map<Integer, Integer> res, HashSet<Integer> sets, HashMap<String, String> strMap) {
        // 记录位置变动
        int tmp = 0;
        for (Map.Entry<Integer, Integer> entry : res.entrySet()) {
            int key = entry.getKey() - tmp;
            int val = entry.getValue();
            int p = key + val;
            int q = key;
            while (p < text.length() && sets.contains((int) text.charAt(p))) {
                p = p + 1;
            }
            while (q > 0 && sets.contains((int) text.charAt(q - 1))) {
                q = q - 1;
            }
            String oldStr = text.substring(q, p);
            String newStr = strMap.get(oldStr);
            tmp += oldStr.length() - newStr.length();
            text = text.replace(oldStr, newStr);

        }
        return text;

    }

    /**
     * 根据默认字符进行替换
     * @param text  字符串
     * @param res   敏感词在字符串位置，长度
     * @param sets  记录英文字母
     * @param defaultChar   替换字符
     * @return  替换后字符串
     */
    private static String handleMethod1(String text, Map<Integer, Integer> res, HashSet<Integer> sets,
                                        char defaultChar) {
        for (Map.Entry<Integer, Integer> entry : res.entrySet()) {
            int key = entry.getKey();
            int val = entry.getValue();
            StringBuilder star = new StringBuilder();
            for (int i = 0; i < val; i++) {
                star.append(defaultChar);
            }
            int p = key + val;
            int q = key;
            while (p < text.length() && sets.contains((int) text.charAt(p))) {
                star.append(defaultChar);
                p = p + 1;
            }
            while (q > 0 && sets.contains((int) text.charAt(q - 1))) {
                star.append(defaultChar);
                q = q - 1;
            }
            text = text.replace(text.substring(q, p), star.toString());

        }
        return text;
    }

    public class ACNode {
        private String data;
        private Map<String, ACNode> children;
        private Boolean isEndingChar;
        private Integer length;
        private ACNode fail;

        public ACNode(String data) {
            this.data = data;
            this.children = new HashMap<>();
            this.isEndingChar = false;
            this.length = 0;
            this.fail = null;
        }
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test5();
    }

    public static void test1() {
        String[] patterns = {"nice"};
        String text = "you are a nice person";
        System.out.println("原文：" + text);
        System.out.println("过滤后：" + filterStr(text, patterns, null, 'X'));
        System.out.println();
    }

    public static void test2() {
        String[] patterns = {"nice", "sun", "happy"};
        String text = "Such a nice day with a bright sun, makes me happy";
        System.out.println("原文：" + text);
        System.out.println("过滤后：" + filterStr(text, patterns, null, 'X'));
        System.out.println();
    }

    public static void test3() {
        String[] patterns = {"friend"};
        String text = "you are so friendly!";
        System.out.println("原文：" + text);
        System.out.println("过滤后：" + filterStr(text, patterns, null, 'X'));
        System.out.println();
    }

    public static void test4() {
        String[] patterns = {"bad", "better", "agree", "Objection"};
        String text = "Objection is bad, a better thing to do is to agree";
        HashMap<String, String> strMap = new HashMap<>(16);
        strMap.put("bad", "ungood");
        strMap.put("better", "gooder");
        strMap.put("agree", "crimestop");
        strMap.put("Objection", "Thoughtcrime");
        System.out.println("原文：" + text);
        System.out.println("过滤后：" + filterStr(text, patterns, strMap, 'X'));
        System.out.println();
    }

    public static void test5() {
        String[] patterns = {"happy"};
        String text = "Are you unhappy today?";
        System.out.println("原文：" + text);
        System.out.println("过滤后：" + filterStr(text, patterns, null, 'X'));
        System.out.println();
    }
}

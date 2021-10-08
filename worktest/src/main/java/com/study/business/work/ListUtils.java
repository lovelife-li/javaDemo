package com.study.business.work;

import java.util.ArrayList;
import java.util.List;

/**
 * todo
 *
 * @author ldb
 * @date 2021/8/28
 */
public class ListUtils {

    /**
     * 给集合分页
     */
    public void pageList(){
        List<String> bizIds = new ArrayList<>();
        // 如果bizIds 大于3000就分批
        List<List<String>> bizIdList = new ArrayList<>();
        int i1 = 0;
        int j1 = 0;
        int k1 = 0;
        int n1 = 3000;
        while (bizIds.size() / n1 > k1) {
            ++k1;
            j1 = k1 * n1;
            // 拆list
            bizIdList.add(bizIds.subList(i1, j1));
            i1 = j1;
        }
        if (bizIds.size() % n1 > 0) {
            bizIdList.add(bizIds.subList(i1, bizIds.size()));
        }
        // 分批插入
        for (List<String> list : bizIdList) {

        }
    }
}

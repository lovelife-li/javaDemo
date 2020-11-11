package com.study.algo.exam.demo02;

/**
 *
 * 封装一行数据
 * @author ldb
 * @date 2020/09/24
 */
public class Data {
    private String id;
    private String groupId;
    private float quota;

    public Data(String id, String groupId, float quota) {
        this.id = id;
        this.groupId = groupId;
        this.quota = quota;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public float getQuota() {
        return quota;
    }

    public void setQuota(float quota) {
        this.quota = quota;
    }
}
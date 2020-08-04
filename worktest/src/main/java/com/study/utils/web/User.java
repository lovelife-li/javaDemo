package com.study.utils.web;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * todo
 *
 * @author ldb
 * @date 2020/07/20
 */
@Data
@AllArgsConstructor
public class User {

    private String nickname;

    private String wechat;

    private String qq;
}

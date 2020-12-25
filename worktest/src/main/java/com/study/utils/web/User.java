package com.study.utils.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * todo
 *
 * @author ldb
 * @date 2020/07/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String nickname;

    private String wechat;

    private String qq;

    private Long id;
}

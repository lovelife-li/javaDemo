package com.study.other.extend;

import java.lang.annotation.Annotation;

/**
 * todo
 *
 * @author ldb
 * @date 2020/08/25
 */
public class Children implements Hello {

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}

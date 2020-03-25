package com.study.enums;

import java.util.EnumMap;
import java.util.EnumSet;

/**
 * @author ldb
 * @date 2020-03-19 8:49
 * @description ??
 */
public enum UserRole {
    ROLE_ROOT_ADMIN,    // 系统管理员
    ROLE_ORDER_ADMIN, // 订单管理员
    ROLE_NORMAL; // 普通用户

    public static void main(String[] args) {
        UserRole role1 = UserRole.ROLE_ROOT_ADMIN;
        UserRole role2 = UserRole.ROLE_ORDER_ADMIN;
        UserRole role3 = UserRole.ROLE_NORMAL;
        //values()方法：返回所有故举常逻的数细集合
        for (UserRole role : UserRole.values()) {
            System.out.println(role);
        }
        //打印：
        //ROLE_ROOT_ADNIN
        //ROLE_ORDER_ADNEN
        //ROLE_NORMWAL

        //ordinal()方法：返回枚举常量的序散，注意从0开始
        System.out.println(role1.ordinal());//7印n0
        System.out.println(role2.ordinal());//打1
        System.out.println(role3.ordinal());//打印2

        //compareTo()方法：校举激量间的比较
        System.out.println(role1.compareTo(role2));//打印-1
        System.out.println(role2.compareTo(role3));//打第-1
        System.out.println(role1.compareTo(role3));//打印-2

        //name()方法：获得纹举常据的名粉
        System.out.println(role1.name());//打ROLE_ROOT_ADNIN
        System.out.println(role2.name());//打印ROLE_ORDER ADNTN
        System.out.println(role3.name());//打印ROLE NORMAL

        //valueOf()方法：返回指定名称的收举常量
        UserRole role_root_admin = UserRole.valueOf("ROLE_ROOT_ADMIN");
        System.out.println(role_root_admin);
        System.out.println(UserRole.valueOf("ROLE_ORDER_ADMIN"));
        System.out.println(UserRole.valueOf("ROLE_NORMAL"));


        UserRole userRole=UserRole.ROLE_ORDER_ADMIN;
        switch(userRole) {
            case ROLE_ROOT_ADMIN:
                System.out.println("这是系统管理员角色");
                break;
            case ROLE_ORDER_ADMIN:
                System.out.println("这是订单管理员角色");
                break;
            case ROLE_NORMAL:
                System.out.println("这是普通用户角色");
                break;
        }

        EnumSet<UserRole> userRoles = EnumSet.of(UserRole.ROLE_ORDER_ADMIN, UserRole.ROLE_NORMAL);

        if (userRoles.contains("role")){
            return ;
        }

        EnumMap<UserRole, Object> map = new EnumMap<>(UserRole.class);

    }

}

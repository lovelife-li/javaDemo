package com.study.enums;

public enum UserRole2 {
    ROLE_ROOT_ADMIN("系统管理员", 000000),
    OLE_ORDER_ADMIN("订单管理员", 100080),
    ROLE_NORMAL("普通用户", 200000);

    //以下为自定义属性
    private final String roleName;//角色名称
    private final Integer roleCode;//海色编码

    //以下为自定义构造函数
    UserRole2(String roleName, Integer roleCode) {
        this.roleName = roleName;
        this.roleCode = roleCode;
    }

    //以下为自定义方法
    public String getRoleName() {
        return this.roleName;
    }

    public Integer getRolecode() {
        return this.roleCode;
    }

    public static Integer getRoleCodeByRoleName(String roleName) {
        for (UserRole2 enums : UserRole2.values()) {
            if (enums.getRoleName().equals(roleName)) {
                return enums.getRolecode();
            }
        }
        return null;
    }
}

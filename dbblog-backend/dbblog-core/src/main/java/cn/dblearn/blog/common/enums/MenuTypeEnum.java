package cn.dblearn.blog.common.enums;

import lombok.Getter;

/**
 * MenuTypeEnum
 *
 * @author bobbi
 * @date 2019/01/08 20:24
 * @email 571002217@qq.com
 * @description 菜单类型
 */
@Getter
public enum MenuTypeEnum {
    /**
     * 目录
     */
    CATALOG(0),
    /**
     * 菜单
     */
    MENU(1),
    /**
     * 按钮
     */
    BUTTON(2);

    private int value;

    MenuTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

package cn.dblearn.blog.common.enums;

import lombok.Getter;

/**
 * TypeEnum
 *
 * @author bobbi
 * @date 2019/01/08 20:21
 * @email 571002217@qq.com
 * @description 模块枚举
 */
@Getter
public enum ModuleEnum {
    /**
     * 文章模块
     */
    ARTICLE(0),
    /**
     * 阅读模块
     */
    READING(1);

    private Integer value;

    ModuleEnum(Integer value) {
        this.value = value;
    }
}

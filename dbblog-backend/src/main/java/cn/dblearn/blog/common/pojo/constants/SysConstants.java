package cn.dblearn.blog.common.pojo.constants;

/**
 * Constants
 *
 * @author bobbi
 * @date 2018/10/19 13:46
 * @email 571002217@qq.com
 * @description
 */
public class SysConstants {
    public static Integer SUPER_ADMIN=1;

    /**
     * 菜单类型
     */
    public enum MenuType {
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

        MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 分类级别
     */
    public enum CategoryRank {
        /**
         * 一级
         */
        ROOT(-1),
        /**
         * 一级
         */
        FIRST(0),
        /**
         * 二级
         */
        SECOND(1),
        /**
         * 三级
         */
        THIRD(2);

        private int value;

        CategoryRank(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}

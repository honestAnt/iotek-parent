package com.iotekclass.common.constants.enums;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 *
 * @ClassName: GradeEnum
 * @Description： 优才年级
 * @Author：袁亚明
 * @Date：2015年8月24日17:49:28
 * @version
 */
public enum GradeEnum {
    /**
     1.大一
     2.大二
     3.大三
     4.大四
     */
    GRADEONE(1, "大一"),
    GRADETWO(2, "大二"),
    GRADETHREE(3,"大三"),
    GRADEFOUR(4,"大四");

    private static final Map<Integer, GradeEnum> lookup = Maps.newHashMap();

    static {
        for (GradeEnum c : GradeEnum.values()) {
            lookup.put(c.getValue(), c);
        }
    }

    private int value;
    private String label;

    private GradeEnum(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public int getId() {
        return this.ordinal();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getName() {
        return this.name();
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static GradeEnum fromOrdinal(int ordinal) {
        return lookup.get(ordinal);
    }
}

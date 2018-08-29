package com.system.enums;

/**
 * @description:学历类别枚举
 * @author: lei.zhang2@100credit.com
 * @time: 2018年8月29日 下午3:48:17
 */
public enum EducationTypeEnum {
	DOCTOR(1, "博士"),
	MASTER(2, "硕士"),
	BACHELOR(3, "本科"),
	HIGH_SCHOOL(4, "高中"),
    ;

    private int code;
    private String name;

    EducationTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}

package com.system.enums;

/**
 * @description:课程类别枚举
 * @author: lei.zhang2@100credit.com
 * @time: 2018年8月29日 下午3:48:17
 */
public enum CourseTypeEnum {
	CHINESE_PAINTING(1, "国画"),
	CARTOON(2, "漫画"),
	CREATIVE_PAINTING(3, "创意画"),
	SKETCH(4, "素描"),
	CALLIGRAPHY(5, "书法"),
	ART_INITIATION(6, "艺术启蒙课"),
    ;

    private int code;
    private String name;

    CourseTypeEnum(int code, String name) {
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

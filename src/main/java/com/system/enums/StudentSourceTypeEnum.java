package com.system.enums;

/**
 * @description:学员来源枚举
 * @author: lei.zhang2@100credit.com
 * @time: 2018年8月29日 下午3:48:17
 */
public enum StudentSourceTypeEnum {
	INTRODUCE(1, "推荐"),
	LEAFLET(2, "传单"),
	WECHAT(3, "微信推广"),
    ;

    private int code;
    private String name;

    StudentSourceTypeEnum(int code, String name) {
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

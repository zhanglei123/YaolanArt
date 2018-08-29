package com.system.enums;

/**
 * @description:教师状态枚举
 * @author: lei.zhang2@100credit.com
 * @time: 2018年8月29日 下午3:48:17
 */
public enum TeacherStatusEnum {
	FULL_TIME_JOB(1, "全职"),
	PART_TIME_JOB(2, "兼职"),
	QUIT_JOB(3, "离职"),
    ;

    private int code;
    private String name;

    TeacherStatusEnum(int code, String name) {
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

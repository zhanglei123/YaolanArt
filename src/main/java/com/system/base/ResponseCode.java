package com.system.base;

public enum ResponseCode {
    SUCCESS("00", "操作成功"),
    FAIL("01", "操作失败"),
    ERROR("02", "程序错误"),
    
    //登录相关
    LOGIN_FAIL("101000", "登录失败"),
    LOGIN_ACCOUNT_ERROR("101001", "用户不存在"),
    LOGIN_PWD_ERROR("101002", "密码错误"),
    LOGIN_ACCOUNT_LOCKED("101003", "用户被锁定"),
    LOGIN_EXCESSIVE_ATTEMPTS("101004", "失败次数过多，请稍后重试"),
    LOGIN_AUTHENTICATION_FAIL("101005", "认证失败"),
    
    
    
    
    NO_MATCH("100002", "匹配结果为空"),
    NEED_KEY("100003", "缺少必填的key值"),
    COMPANY_NOT_EXIST("100004", "用户不存在"),
    WRONG_PASSWORD("100005", "登陆密码不正确"),
    PARAM_ERROR("100006", "请求参数格式错误"),
    TOKENID_EXPIRE("100007", "tokenid过期"),
    NEED_APICODE("100008", "客户端api调用码不能为空"),
    IP_ERROR("100009", "IP地址错误"),
    OVER_LIMIT_TIMES("100010", "超出时间区间内访问次数"),
    DISABLED("100011", "用户已停用"),
    NO_RIGHT("100012", "无访问权限"),
    ON_MATCHING("100013", "正在匹配结果"),
    PARAM_OVER_LIMIT("100014", "请求批次数量超出限制"), 
    MATCH_FAIL("100015", "匹配失败"),
    NOT_UNIQUE("100016", "no不唯一"),
    LINKED_PARAM_ERROR("100017", "联系人参数错误"),
    YEAR_OVER_LIMITS("100018","当年超出数量限额"),
    MONTH_OVER_LIMITS("100019","当月超出数量限额"),
    OVER_LIMITS("100020","请求次数超过购买次数"),
    PHONE_NOTEXITS("100021","电话号码不存在"),
    PHONE_NORIGHT("100022","没有开通电话业务"),
    MAIL_NORIGHT("100023","没有开通邮件业务"),
    CALLSID_NOTEXITS("100024","电话案件不存在"),
    RECORD_NOTEXIT("100025","此案件没有录音或电话没有接通"),
    DOWNLOAD_FAIL("100026","录音下载失败"),
    UNRECORD("100027","此案件没有开通下载录音功能"),
    DOWNLING_CALLLOG("100028","正在下载录音"),
    DOWNLOAD_SUCC("100028","录音下载成功"),
    MAIL_NOTEXITS("100029","邮箱不存在"),
    MAIL_MISS("100030","邮箱必要信息缺失"),
    MAIL_FAIL("100031","邮件发送失败，请核对信息后重新发送"),
    MAIL_SUCC("100032","邮件发送成功"),
    CALLER_WRONG("100033","主叫号码错误"),
    MAILER_WRONG("100034","发送者邮件格式错误"),
    TYPE_WRONG("100035","案件类型格式错误，请核对后重新上传"),
    
    //ivr信息
    FILE_EXIST("10001","IVR文件已存在"),
    FILE_SAVE_FAIL("10002","IVR文件保存到服务器失败"),
    THIRD_ERROR("10003","IVR文件保存到服务器失败"),
    NOT_FIND_FAIL("10004","未找到对应的IVR文件"),
    IVR_DOWNLOAD_FAIL("10005","未找到对应的IVR文件"),
    IVR_FILE_REFUSE("10006","IVR文件未通过审核"),
    IVR_BATCH_CLOSE("10007","IVR批次已关闭"),
    IVR_BATCH_DEAL_DOING("10008","该批次正在拨号, 请勿重复启动!"),
    IVR_BATCH_EXIST("10009","该批次已存在!"),
    IVR_NO_FILE("10010","未获取到文件!"),
    IVR_THIRD_FILESTATE_ERROR("10011","第三方文件状态异常"),
    IVR_THIRD_FILESTATE_DOING("10012","第三方审核中"),
    IVR_THIRD_FILESTATE_REFUSE("10013","第三方审核拒绝"),
    IVR_BAIRONG_FILESTATE_DOING("10014","百融审核中"),
    IVR_BAIRONG_FILESTATE_REFUSE("10015","百融审核拒绝"),
    IVR_NO_BATCH("10016","未获取到该批次信息"),
    IVR_BATCH_STATE_NOFOUND("10017","未获取到该批次信息"),
    IVR_BATCH_STATE_UPLOADEND("10018","该批次信息上传完成"),
    IVR_BATCH_STATE_UPLOADFILE("10019","该批次信息上传失败"),
    IVR_BATCH_STATE_DOING("10020","该批次信息正在拨号"),
    IVR_BATCH_STATE_STOP("10021","该批次信息拨号暂停"),
    IVR_BATCH_STATE_ERROR("10022","该批次状态异常"),
    IVR_FILE_NO_NAME("10023","IVR文件名为空"),
    IVR_FILE_SIZE_ZERO("10024","IVR文件大小为0"),
    IVR_DISABLE("10025","IVR功能未开通"),
    
    
    
    ;
    
    private final String code;
    private final String msg;

    private ResponseCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
    
    @Override
    public String toString(){
    	return this.code;
    }
}

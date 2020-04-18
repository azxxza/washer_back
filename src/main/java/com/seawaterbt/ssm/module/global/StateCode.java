package com.seawaterbt.ssm.module.global;

/**
 * 描述：
 *
 * @author azx
 * @version 1.0 2017/10/27 16:14
 */
public enum StateCode {
    SUCCESS(200, "成功"),
    /**
     * 系统状态码
     */
    ERROR(100, "失败"),
    ACCOUNT_FORBIDERN(205, "用户名被锁定"),
    ACCOUNT_LOGING_ID_DIFFERENCE(201, "用户登录ip更改，请联系管理员"),
    AUTHOR_SIGN_ERR(202, "签名错误,请重新登录"),
    AUTHOR_SIGN_BALAK(203, "签名为空,请重新登录"),
    BLACK_MENU_IP(204, "您的IP已被加入黑名单,请联系管理员"),
    ACCOUNT_REPEAT_LOGIN(206, "您的账号在其它地点登录,已失效"),
    NO_LOGIN(207, "未登录，或登录失效，请登录"),
    IP_LOGIN_FREQUENCY(209, "您请求过于频繁"),

    REMOTE_HTTP_ERR(310, "游戏服务端接口出问题啦"),
    MISS_PARAM(311, "缺少请求参数"),
    NOTREADABLE_PARAM(312, "参数解析失败"),
    NotValid_param(313, "参数验证失败"),
    BindError_param(314, "参数绑定失败"),
    ViolationERROR_Param(315, "参数验证失败"),
    PARAM_ERROR(316, "参数错误"),
    SIGN_ERRO(317, "签名错误"),
    NOT_FOUND(318, "找不到资源"),
    TIME_OUT(319, "请求超时"),
    REMOTE_HTTP_LONG_HU_ERR(320, ""),
    DTFROBOTBET_ADD_WARN(321, ""),

    /**
     * 登录状态码
     */
    ACCESS_DENIED(403, "没有权限访问"),
    ACCOUNT_LOCK(402, "账号被锁定"),
    ACCOUNT_PASS_ERROR(403, "用户名或密码错误"),
    OLD_PWD_ERROR(403, "原始密码不正确"),
    ACCOUNT_NOT(404, "无此账号"),
    ACCOUNT_PASSWORD_ERROR(405, "密码错误"),

    SERVER_ERRO(500, "系统错误"),
    USER_NAME_EXIT(501, "用户名已存在"),
    ROLE_NAME_EXIT(502, "角色名称已存在"),

    /**
     * 业务状态码
     */
    REQUEST_NULL(401, "请求有错误，必要参数为空！"),
    QUERY_RESULT_IS_NULL(401, "查询不到该玩家信息，操作失败！"),
    QUERY_CLUB_RESULT_IS_NULL(401, "查询不到该俱乐部信息，操作失败！"),
    PLAYER_ALREADY_EXIST(401, "该玩家已存在！"),
    TEXT_CONTENT_EMPTY(401, "文本内容为空！"),
    IPBLACKMENU_ALREADY_HAVE(401, "黑名单中已存在该用户！"),
    IPWHITEMENU_ALREADY_HAVE(401, "白名单中已存在该用户！"),
    USER_NOT_EXIST(401, "该用户不存在！"),
    IMPORT_FAIL(401, "导入失败！"),
    DEVICE_SN_EXIT(401, "设备SN已经存在"),
    DEVICE_SN__NO_EXIT(401, "设备SN不存在"),
    UPLOAD_ERROR(500, "上传图片出错");


    private Integer code;
    private String msg;

    StateCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    StateCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

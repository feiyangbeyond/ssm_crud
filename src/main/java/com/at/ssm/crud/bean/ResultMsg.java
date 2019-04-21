package com.at.ssm.crud.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 服务器返回消息处理类
 * @author Beyond
 * @date 2019/04/14
 */
public class ResultMsg {
    /**
     * 状态码
     */
    private int code;
    /**
     * 处理信息
     */
    private String msg;
    /**
     * 数据
     */
    private Map<String, Object> extend = new HashMap<>();

    /**
     * 处理成功
     * @return ResultMsg
     */
    public static ResultMsg success(){
        return new ResultMsg(200, "处理成功");
    }
    /**
     * 处理失败
     * @return ResultMsg
     */
    public static ResultMsg fail(){
        return new ResultMsg(500, "处理失败");
    }

    /**
     * 添加数据
     * @param key key
     * @param value value
     * @return ResultMsg
     */
    public ResultMsg addData(String key, Object value){
        this.extend.put(key, value);
        return this;
    }

    private ResultMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }
}

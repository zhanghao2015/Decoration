package com.example.decoration.module.myfrag.myslef.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/17 0017.
 */
public class SendCodeInfo {

    /**
     * status : 200
     * msg : ["短信发送成功"]
     * data : {"code":"5037","guoducode":"10690228540701","codenum":"4","upsms_time":"15"}
     * items : []
     * page : {}
     */

    private int status;
    /**
     * code : 5037
     * guoducode : 10690228540701
     * codenum : 4
     * upsms_time : 15
     */

    private DataBean data;
    private List<String> msg;
    private List<?> items;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public List<String> getMsg() {
        return msg;
    }

    public void setMsg(List<String> msg) {
        this.msg = msg;
    }

    public List<?> getItems() {
        return items;
    }

    public void setItems(List<?> items) {
        this.items = items;
    }

    public static class DataBean {
        private String code;
        private String guoducode;
        private String codenum;
        private String upsms_time;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getGuoducode() {
            return guoducode;
        }

        public void setGuoducode(String guoducode) {
            this.guoducode = guoducode;
        }

        public String getCodenum() {
            return codenum;
        }

        public void setCodenum(String codenum) {
            this.codenum = codenum;
        }

        public String getUpsms_time() {
            return upsms_time;
        }

        public void setUpsms_time(String upsms_time) {
            this.upsms_time = upsms_time;
        }
    }
}

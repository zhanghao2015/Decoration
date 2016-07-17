package com.example.decoration.module.myfrag.ui.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/17 0017.
 */
public class LoginInfo {

    /**
     * status : 200
     * msg : ["登录成功"]
     * data : {"user_id":"276295","alipay_user_id":null,"alipay_user_name":null,"area_id":null,"site_id":null,"mobile":"15096064450","gender":"0","avatar":"http://hzyzq-10006163.image.myqcloud.com/15d6c909-498d-4a94-974a-1b225b5ce4f0","name":null,"nick_name":"","version":"","os":"","type":"0","is_new_coupon":"0","status":"1","reg_time":"1468582403","reg_ip":"219.134.212.45","last_login":"1468582403","amount":"0","channel":"","mac":"864394101844274","share_code":"","used_share_code":"","msg_type_id":"1","cc_uid":"0","cc_uname":null,"cc_assign_time":"0","cc_state":"0","has_deal":"0","cc_thrown_reason":null,"coupon":null,"token":"1025c5a33f6f9705cbbca554d64ce7f07b9940ff"}
     * items : []
     * page : {}
     */

    private int status;
    /**
     * user_id : 276295
     * alipay_user_id : null
     * alipay_user_name : null
     * area_id : null
     * site_id : null
     * mobile : 15096064450
     * gender : 0
     * avatar : http://hzyzq-10006163.image.myqcloud.com/15d6c909-498d-4a94-974a-1b225b5ce4f0
     * name : null
     * nick_name :
     * version :
     * os :
     * type : 0
     * is_new_coupon : 0
     * status : 1
     * reg_time : 1468582403
     * reg_ip : 219.134.212.45
     * last_login : 1468582403
     * amount : 0
     * channel :
     * mac : 864394101844274
     * share_code :
     * used_share_code :
     * msg_type_id : 1
     * cc_uid : 0
     * cc_uname : null
     * cc_assign_time : 0
     * cc_state : 0
     * has_deal : 0
     * cc_thrown_reason : null
     * coupon : null
     * token : 1025c5a33f6f9705cbbca554d64ce7f07b9940ff
     */

    private DataBean data;
    private PageBean page;
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

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
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
        private String user_id;
        private Object alipay_user_id;
        private Object alipay_user_name;
        private Object area_id;
        private Object site_id;
        private String mobile;
        private String gender;
        private String avatar;
        private Object name;
        private String nick_name;
        private String version;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public Object getAlipay_user_id() {
            return alipay_user_id;
        }

        public void setAlipay_user_id(Object alipay_user_id) {
            this.alipay_user_id = alipay_user_id;
        }

        public Object getAlipay_user_name() {
            return alipay_user_name;
        }

        public void setAlipay_user_name(Object alipay_user_name) {
            this.alipay_user_name = alipay_user_name;
        }

        public Object getArea_id() {
            return area_id;
        }

        public void setArea_id(Object area_id) {
            this.area_id = area_id;
        }

        public Object getSite_id() {
            return site_id;
        }

        public void setSite_id(Object site_id) {
            this.site_id = site_id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public Object getName() {
            return name;
        }

        public void setName(Object name) {
            this.name = name;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }

    public static class PageBean {
    }
}

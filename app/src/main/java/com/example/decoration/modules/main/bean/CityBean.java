package com.example.decoration.modules.main.bean;

import java.util.List;

/**
 * Created by Administrator on 16-7-25.
 */
public class CityBean {

    /**
     * status : 200
     * msg : ["access successful"]
     * data : {}
     * items : [{"site_id":"1","site_name":"成都","spelling":"chengdu","name_code":"www","area_id":"2291","is_hot":"1","is_new_process":"0","lat":"30.659462","lng":"104.065735","area_name":"成都市","province":"四川省","time":1469409662},{"site_id":"4","site_name":"西安","spelling":"xian","name_code":"xa","area_id":"2819","is_hot":"0","is_new_process":"0","lat":"34.263161","lng":"108.948024","area_name":"西安市","province":"陕西省","time":1469409662},{"site_id":"11","site_name":"郑州","spelling":"zhengzhou","name_code":"zz","area_id":"1568","is_hot":"1","is_new_process":"0","lat":"34.783761","lng":"113.727777","area_name":"郑州市","province":"河南省","time":1469409662},{"site_id":"14","site_name":"长沙","spelling":"changsha","name_code":"cs","area_id":"1861","is_hot":"0","is_new_process":"0","lat":"28.219926","lng":"112.933369","area_name":"长沙市","province":"湖南省","time":1469409662},{"site_id":"16","site_name":"北京","spelling":"beijing","name_code":"bj","area_id":"1","is_hot":"0","is_new_process":"0","lat":"39.904989","lng":"116.405285","area_name":"北京市","province":"北京","time":1469409662},{"site_id":"18","site_name":"天津","spelling":"tianjin","name_code":"tj","area_id":"40","is_hot":"0","is_new_process":"0","lat":"39.125596","lng":"117.190182","area_name":"天津市","province":"天津","time":1469409662},{"site_id":"30","site_name":"上海","spelling":"shanghai","name_code":"sh","area_id":"20","is_hot":"0","is_new_process":"0","lat":"31.231706","lng":"121.472644","area_name":"上海市","province":"上海","time":1469409662},{"site_id":"36","site_name":"杭州","spelling":"hangzhou","name_code":"hz","area_id":"979","is_hot":"1","is_new_process":"0","lat":"30.320404","lng":"120.178989","area_name":"杭州市","province":"浙江省","time":1469409662},{"site_id":"42","site_name":"无锡","spelling":"wuxi","name_code":"wx","area_id":"873","is_hot":"1","is_new_process":"0","lat":"31.582352","lng":"120.311097","area_name":"无锡市","province":"江苏省","time":1469409662},{"site_id":"52","site_name":"广州","spelling":"guangzhou","name_code":"gz","area_id":"1998","is_hot":"0","is_new_process":"0","lat":"23.125178","lng":"113.280637","area_name":"广州市","province":"广东省","time":1469409662},{"site_id":"78","site_name":"乌鲁木齐","spelling":"wulumuqi","name_code":"wlmq","area_id":"3117","is_hot":"0","is_new_process":"0","lat":"43.792818","lng":"87.617733","area_name":"乌鲁木齐市","province":"新疆维吾尔自治区","time":1469409662},{"site_id":"86","site_name":"保定","spelling":"baoding","name_code":"bd","area_id":"188","is_hot":"1","is_new_process":"0","lat":"38.878205","lng":"115.475588","area_name":"保定市","province":"河北省","time":1469409662},{"site_id":"88","site_name":"咸阳","spelling":"xianyang","name_code":"xy","area_id":"2851","is_hot":"1","is_new_process":"0","lat":"34.318849","lng":"108.713767","area_name":"咸阳市","province":"陕西省","time":1469409662},{"site_id":"90","site_name":"大庆","spelling":"daqing","name_code":"dq","area_id":"780","is_hot":"1","is_new_process":"0","lat":"46.612194","lng":"124.916082","area_name":"大庆市","province":"黑龙江省","time":1469409662},{"site_id":"94","site_name":"金华","spelling":"jinhua","name_code":"jh","area_id":"1038","is_hot":"1","is_new_process":"0","lat":"29.089524","lng":"119.649506","area_name":"金华市","province":"浙江省","time":1469409662},{"site_id":"96","site_name":"扬州","spelling":"yangzhou","name_code":"yz","area_id":"950","is_hot":"1","is_new_process":"0","lat":"32.393159","lng":"119.421003","area_name":"扬州市","province":"江苏省","time":1469409662},{"site_id":"98","site_name":"潍坊","spelling":"weifang","name_code":"wf","area_id":"1469","is_hot":"1","is_new_process":"0","lat":"36.702581","lng":"119.110347","area_name":"潍坊市","province":"山东省","time":1469409662},{"site_id":"100","site_name":"深圳","spelling":"shen","name_code":"sz","area_id":"2022","is_hot":"1","is_new_process":"0","lat":"22.649711","lng":"114.190273","area_name":"深圳市","province":"广东省","time":1469409662},{"site_id":"102","site_name":"蚌埠","spelling":"bangbu","name_code":"bb","area_id":"1097","is_hot":"1","is_new_process":"0","lat":"32.924613","lng":"117.366723","area_name":"蚌埠市","province":"安徽省","time":1469409662},{"site_id":"104","site_name":"德阳","spelling":"deyang","name_code":"dy","area_id":"2332","is_hot":"1","is_new_process":"0","lat":"31.125581","lng":"104.401097","area_name":"德阳市","province":"四川省","time":1469409662}]
     * page : {"num":10,"minId":10}
     */

    private int status;
    private DataBean data;
    /**
     * num : 10
     * minId : 10
     */

    private PageBean page;
    private List<String> msg;
    /**
     * site_id : 1
     * site_name : 成都
     * spelling : chengdu
     * name_code : www
     * area_id : 2291
     * is_hot : 1
     * is_new_process : 0
     * lat : 30.659462
     * lng : 104.065735
     * area_name : 成都市
     * province : 四川省
     * time : 1469409662
     */

    private List<ItemsBean> items;

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

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class DataBean {
    }

    public static class PageBean {
        private int num;
        private int minId;

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getMinId() {
            return minId;
        }

        public void setMinId(int minId) {
            this.minId = minId;
        }
    }

    public static class ItemsBean {
        private String site_id;
        private String site_name;
        private String spelling;
        private String name_code;
        private String area_id;
        private String is_hot;
        private String is_new_process;
        private String lat;
        private String lng;
        private String area_name;
        private String province;
        private int time;

        public String getSite_id() {
            return site_id;
        }

        public void setSite_id(String site_id) {
            this.site_id = site_id;
        }

        public String getSite_name() {
            return site_name;
        }

        public void setSite_name(String site_name) {
            this.site_name = site_name;
        }

        public String getSpelling() {
            return spelling;
        }

        public void setSpelling(String spelling) {
            this.spelling = spelling;
        }

        public String getName_code() {
            return name_code;
        }

        public void setName_code(String name_code) {
            this.name_code = name_code;
        }

        public String getArea_id() {
            return area_id;
        }

        public void setArea_id(String area_id) {
            this.area_id = area_id;
        }

        public String getIs_hot() {
            return is_hot;
        }

        public void setIs_hot(String is_hot) {
            this.is_hot = is_hot;
        }

        public String getIs_new_process() {
            return is_new_process;
        }

        public void setIs_new_process(String is_new_process) {
            this.is_new_process = is_new_process;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getArea_name() {
            return area_name;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }
    }
}

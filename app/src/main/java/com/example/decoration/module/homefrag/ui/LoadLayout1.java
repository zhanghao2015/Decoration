package com.example.decoration.module.homefrag.ui;

import com.example.decoration.module.homefrag.bean.IndexBean;

/**
 * Created by Administrator on 16-7-19.
 */
public class LoadLayout1 {

    public IndexBean indexBean;
    public String site_name;

    public String getSite_id() {
        return site_id;
    }

    public void setSite_id(String site_id) {
        this.site_id = site_id;
    }

    public String site_id;

    public IndexBean getIndexBean() {
        return indexBean;
    }

    public void setIndexBean(IndexBean indexBean) {
        this.indexBean = indexBean;
    }

    public String getSite_name() {
        return site_name;
    }

    public void setSite_name(String site_name) {
        this.site_name = site_name;
    }

    public LoadLayout1(IndexBean indexBean, String site_name,String site_id) {
        this.indexBean = indexBean;
        this.site_name = site_name;
        this.site_id = site_id;
    }
}

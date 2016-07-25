package com.example.decoration.module.homefrag.ui;

import com.example.decoration.module.homefrag.bean.IndexBean;

/**
 * Created by Administrator on 16-7-20.
 */
public class LoadLatestFrag {

    private IndexBean indexBean;
    private String site_name;
    private String site_id;

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

    public LoadLatestFrag(IndexBean indexBean, String layoutID) {
        this.indexBean = indexBean;
        this.site_name = layoutID;
    }

    public IndexBean getIndexBean() {
        return indexBean;
    }

    public void setIndexBean(IndexBean indexBean) {
        this.indexBean = indexBean;
    }


}

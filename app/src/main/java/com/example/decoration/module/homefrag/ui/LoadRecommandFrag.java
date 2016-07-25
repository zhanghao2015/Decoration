package com.example.decoration.module.homefrag.ui;

import com.example.decoration.module.homefrag.bean.IndexBean;

/**
 * Created by Administrator on 16-7-19.
 */
public class LoadRecommandFrag {

    public IndexBean indexBean;
    public String layoutID;

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

    public String getLayoutID() {
        return layoutID;
    }

    public void setLayoutID(String layoutID) {
        this.layoutID = layoutID;
    }

    public LoadRecommandFrag(IndexBean indexBean, String layoutID) {
        this.indexBean = indexBean;
        this.layoutID = layoutID;
    }
}

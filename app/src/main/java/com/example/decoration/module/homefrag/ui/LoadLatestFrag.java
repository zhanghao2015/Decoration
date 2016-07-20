package com.example.decoration.module.homefrag.ui;

import com.example.decoration.module.homefrag.bean.IndexBean;

/**
 * Created by Administrator on 16-7-20.
 */
public class LoadLatestFrag {

    private IndexBean indexBean;
    private String layoutID;

    public LoadLatestFrag(IndexBean indexBean, String layoutID) {
        this.indexBean = indexBean;
        this.layoutID = layoutID;
    }

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
}

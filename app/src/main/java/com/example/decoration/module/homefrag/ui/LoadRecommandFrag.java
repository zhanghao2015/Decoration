package com.example.decoration.module.homefrag.ui;

import com.example.decoration.module.homefrag.bean.IndexBean;

/**
 * Created by Administrator on 16-7-19.
 */
public class LoadRecommandFrag {

    public IndexBean indexBean;
    public String layoutID;

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

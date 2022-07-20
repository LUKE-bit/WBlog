package com.wang.myblog.pojo;



import java.io.Serializable;
import java.util.Date;

public class HistoricalViews implements Serializable {
    private Integer views;
    private Date create_by;

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Date getCreateBy() {
        return create_by;
    }

    public void setCreateBy(Date create_by) {
        this.create_by = create_by;
    }

    @Override
    public String toString() {
        return "HistoricalViews{" +
                "views=" + views +
                ", create_by=" + create_by +
                '}';
    }
}

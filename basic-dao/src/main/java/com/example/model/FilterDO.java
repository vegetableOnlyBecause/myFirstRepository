package com.example.model;

import java.util.Date;

public class FilterDO {
    private Integer id;

    private String filterId;

    private String filterName;

    private String content;

    private Integer enable;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilterId() {
        return filterId;
    }

    public void setFilterId(String filterId) {
        this.filterId = filterId == null ? null : filterId.trim();
    }

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName == null ? null : filterName.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
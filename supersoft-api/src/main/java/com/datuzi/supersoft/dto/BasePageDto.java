package com.datuzi.supersoft.dto;


import java.io.Serializable;

/**
 * Created by 27度 on 2017/12/16 0016.
 */
public class BasePageDto implements Serializable{
    private int page=0;
    private int limit=20;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}

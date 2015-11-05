package com.iotekclass.common.cache.obj;

import java.util.Date;

/*********************************************************************************
//* Copyright (C) 2014 mo-win. All Rights Reserved.
//*
//* Filename:      CacheLast.java
//* Revision:      1.0
//* Author:        lxu
//* Created On:    2014-8-13
//* Modified by:   
//* Modified On:   
//*
//* Description:   <description>
/********************************************************************************/

public class CacheLast<T> {

    /**
     * 上次缓存的数据
     */
    private T    data;

    /**
     * 最后刷新时间
     */
    private long refreshtime;

    public CacheLast(T data, long refreshtime) {
        this.data = data;
        this.refreshtime = refreshtime;
    }

    public CacheLast(T data) {
        this(data, new Date().getTime());
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getRefreshtime() {
        return refreshtime;
    }

    public void setRefreshtime(long refreshtime) {
        this.refreshtime = refreshtime;
    }

}

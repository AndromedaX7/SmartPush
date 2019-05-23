package com.zhhl.smartpush.common;


import com.google.gson.Gson;

public abstract class BaseModel implements IModel {
    private final OriginApp application;
    private final Gson gson;

    protected BaseModel(OriginApp application, Gson gson) {
        this.application = application;
        this.gson = gson;
    }
}
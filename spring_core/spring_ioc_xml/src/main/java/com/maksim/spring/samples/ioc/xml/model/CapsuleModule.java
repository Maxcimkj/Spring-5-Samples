package com.maksim.spring.samples.ioc.xml.model;

import java.io.Serializable;

public class CapsuleModule implements Serializable {
    private Object capsule;

    public CapsuleModule() {
    }

    public Object  getCapsule() {
        return "capsule";
    }

    public void setCapsule(Object capsule) {
        this.capsule = capsule;
    }
}

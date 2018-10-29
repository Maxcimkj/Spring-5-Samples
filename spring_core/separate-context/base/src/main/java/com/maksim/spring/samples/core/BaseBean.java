package com.maksim.spring.samples.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("baseBean")
public class BaseBean {
    private ChildBean childBean;

    @Autowired
    public BaseBean(ChildBean childBean) {
        this.childBean = childBean;
    }

    public ChildBean getChildBean() {
        return childBean;
    }

    public void setChildBean(ChildBean childBean) {
        this.childBean = childBean;
    }
}

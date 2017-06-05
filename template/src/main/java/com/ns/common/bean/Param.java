package com.ns.common.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by xuezhucao on 15/8/19.
 */
@Entity
public class Param {
    @Id
    private String name;
    private String value;
    private Date createTime;
    private Date updateTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}

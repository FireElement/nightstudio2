package com.ns.template.bean;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by xuezhucao on 15/8/19.
 */
@Entity
public class Test {
    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

package com.ns.common.bean;

import org.mongodb.morphia.annotations.Entity;
import org.springframework.data.annotation.Id;

/**
 * Created by xuezhucao on 16/8/13.
 */
@Entity("mongoTest")
public class MongoTest {
    @Id
    private String id;

    private String firstName;
    private String lastName;

    public MongoTest(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

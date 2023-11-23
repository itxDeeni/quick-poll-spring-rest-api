package com.appress.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


@Entity
public class Option {
    @Id
    @GeneratedValue
    @Column(name = "OPTION_ID")
    private long id;

    @Column(name = "Optional Value")
    private String value;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}

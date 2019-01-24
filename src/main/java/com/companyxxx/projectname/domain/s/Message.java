package com.companyxxx.projectname.domain.s;

import javax.persistence.*;

/**
 * @author: luzj
 * @date: 2019-01-24
 * @description:
 */
@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String content;

    public Message(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

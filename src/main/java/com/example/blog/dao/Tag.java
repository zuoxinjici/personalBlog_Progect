package com.example.blog.dao;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @author QianMo
 * @date 2021/7/20 6:29
 */

@Entity
@Table(name = "Tag_t")
public class Tag {

    @Id
    @GeneratedValue
    private long id;
    private String name;

    @ManyToMany(mappedBy = "tags")
    private List<Blog> blogs = new ArrayList<>();




    public Tag() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

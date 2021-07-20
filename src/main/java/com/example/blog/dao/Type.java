package com.example.blog.dao;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @author QianMo
 * @date 2021/7/20 6:25
 */

@Entity
@Table(name = "Type_t")
public class Type {

    @Id
    @GeneratedValue
    private long id;
    private String name;

    @OneToMany(mappedBy = "type")
    private List<Blog> blogs = new ArrayList<>();

    public Type() {
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

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }



    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

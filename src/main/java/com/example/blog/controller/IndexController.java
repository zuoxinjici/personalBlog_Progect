package com.example.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author QianMo
 * @date  2021/7/17
 */

@Controller
public class IndexController {
    @GetMapping("")
    public String index() {

//        String Blog = null;
//        if(Blog == null){
//            throw new NotFoundException("此博客不存在，请联系作者进行添加!");
//        }
        System.out.println("------------index--------------");
        return "index";
    }
    @GetMapping("/blog")
    public String blog() {
        return "blog";
    }

    @GetMapping("/archives")
    public String archives() {
        return "archives";
    }

    @GetMapping("/type")
    public String type() {
        return "type";
    }

    @GetMapping("/admin/newBlog")
    public String newBlog() {
        return "admin/newBlog";
    }
    @GetMapping("/admin/index")
    public String adminIndex() {
        return "admin/index";
    }
}

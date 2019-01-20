package com.zhou.bot.zhoubottest.controller;

import com.zhou.bot.zhoubottest.mapper.ProductMapper;
import com.zhou.bot.zhoubottest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/index/{id}")
    public String master(@PathVariable("id") Long id ) {
        try {
            System.out.println(productService.masterProductInfo(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Hello World";
    }

    @RequestMapping("/slave/{id}")
    public String slave(@PathVariable("id") Long id ) {
        System.out.println(productService.slaveProductInfo(id));
        return "Hello World";
    }
}

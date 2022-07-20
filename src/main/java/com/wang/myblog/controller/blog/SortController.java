package com.wang.myblog.controller.blog;

import com.wang.myblog.pojo.BlogInfo;
import com.wang.myblog.pojo.Sort;
import com.wang.myblog.service.BlogService;
import com.wang.myblog.service.SortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class SortController {


    @Autowired
    private SortService sortService;

    @PostMapping("/allSorts")
    @ResponseBody
    public List<Sort> allSorts(){
        return sortService.getAllSorts();
    }


    @PostMapping("/sortToBlog")
    @ResponseBody
    public List<BlogInfo> sortToBlog(String sortName){
        return sortService.sortToBlog(sortName);
    }
}

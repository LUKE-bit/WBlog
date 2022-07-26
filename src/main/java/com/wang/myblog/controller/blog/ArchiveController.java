package com.wang.myblog.controller.blog;

import com.wang.myblog.pojo.BlogInfo;
import com.wang.myblog.service.ArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class ArchiveController {

    @Autowired
    private ArchiveService archiveService;

    @PostMapping("/archive")
    @ResponseBody
    public Map<String,Integer> archiveDetail(){
        return archiveService.findAchiveByYear();
    }

    @PostMapping("/findByYear")
    @ResponseBody
    public List<BlogInfo> findByYear (@RequestParam("year") String year){
        return archiveService.findByYear(year);
    }
}

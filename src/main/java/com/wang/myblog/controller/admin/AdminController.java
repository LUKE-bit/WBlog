package com.wang.myblog.controller.admin;

import com.wang.myblog.dto.Result;
import com.wang.myblog.dto.TableList;
import com.wang.myblog.pojo.BlogInfo;
import com.wang.myblog.service.AdminService;
import com.wang.myblog.service.BlogService;
import com.wang.myblog.service.SortService;
import com.wang.myblog.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private static final String AUTHORITIE = "ROLE_ADMIN";

    @Autowired
    private BlogService blogService;

    @Autowired
    private SortService sortService;

    @Autowired
    private AdminService adminService;

    @RequestMapping("/index")
    public String index(HttpServletRequest request){
        GrantedAuthority[] authorities = UserUtil.getGrantedAuthorities();
        if (AUTHORITIE.equals(authorities[0].getAuthority())){
            request.setAttribute("TotalViews",blogService.getTotalView() );
            request.setAttribute("TotalBlogs",blogService.getTotalBlogs() );
            request.setAttribute("TotalComments",blogService.getTotalComments() );
        }else {
            request.setAttribute("TotalViews",blogService.getUserTotalViews() );
            request.setAttribute("TotalBlogs",blogService.getUserTotalBlogs() );
            request.setAttribute("TotalComments",blogService.getUserTotalComments() );
        }

        return "admin/index";
    }
    @RequestMapping("/editors")
    public String editors(HttpServletRequest request){
        request.setAttribute("sorts",sortService.editorSorts() );
        return "admin/editors";
    }
    @RequestMapping("/historicalViews")
    public String historicalViews(HttpServletRequest request){
        return "admin/HistoricalViews";
    }
    @RequestMapping("/manager")
    public String manager(){
        return "admin/BlogManager";
    }
//    @RequestMapping("/profile")
//    public String profile(){
//        return "admin/profile";
//    }
    @RequestMapping("/commentManager")
    public String commentManager(){
        return "admin/commentManager";
    }
    @RequestMapping("/sortManager")
    public String sortManager(){
        return "admin/sortManager";
    }

    @PostMapping("/save")
    @ResponseBody
    public Result save(@RequestParam("articleTitle") String articleTitle,
                       @RequestParam("blogContent") String blogContent,
                       @RequestParam("imageUrl") String imageUrl,
                       @RequestParam("sortIds") String sortIds){
        System.out.println(sortIds);
        System.out.println(imageUrl);
        BlogInfo blogInfo = new BlogInfo();
        blogInfo.setArticleTitle(articleTitle);
        blogInfo.setArticleContent(blogContent);
        blogInfo.setSorts(sortIds);
        blogInfo.setImageUrl(imageUrl);
        Result result = blogService.saveBlog(blogInfo);
        return result;
    }

    @PostMapping("/blogList")
    @ResponseBody
    public TableList blogList(@RequestParam("limit") Integer limit, @RequestParam("offset") Integer offset){
        return blogService.blogList(limit,offset);
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, HttpServletRequest request){
        BlogInfo blogInfo = blogService.editBlog(id);
        request.setAttribute("blog",blogInfo );
        request.setAttribute("sorts",sortService.getAllSorts());
        return "admin/editors";
    }

    @PostMapping("/update")
    @ResponseBody
    public Result update(@RequestParam("articleTitle") String articleTitle,
                         @RequestParam("blogContent") String blogContent,
                         @RequestParam("imageUrl") String imageUrl,
                         @RequestParam("blogId") Integer blogId){
        BlogInfo blogInfo = blogService.findBlog(blogId);
        blogInfo.setArticleTitle(articleTitle);
        blogInfo.setArticleContent(blogContent);
        blogInfo.setImageUrl(imageUrl);
        Result result = blogService.update(blogInfo);
        return result;
    }

    @PostMapping("/comments")
    @ResponseBody
    public TableList comments(@RequestParam("limit") Integer limit, @RequestParam("offset") Integer offset){
        return blogService.commentList(limit,offset);
    }

    @PostMapping("/saveReply")
    @ResponseBody
    public Result saveReply(@RequestParam("id") Integer id,@RequestParam("replyComment") String replyComment){
        return blogService.saveReply(id,replyComment );
    }

    @PostMapping("/delBlog")
    @ResponseBody
    public Result delBlog(@RequestParam("id") Integer id){
        return blogService.delBlog(id);
    }

    @PostMapping("/delComment")
    @ResponseBody
    public Result delComment(@RequestParam("id") Integer id){
        return blogService.delComment(id);
    }

    @PostMapping("/addSort")
    @ResponseBody
    public Result addSort(@RequestParam("sortName") String sortName){
        return sortService.addSort(sortName);
    }

    @GetMapping("/sorts")
    @ResponseBody
    public TableList sorts(@RequestParam("limit") Integer limit, @RequestParam("offset") Integer offset){
        return sortService.sorts(limit, offset);
    }

    @PostMapping("/delSort")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Result delSort(@RequestParam("id") Integer id){
        return sortService.delSort(id);
    }

    @PostMapping("/getHistoricalViews")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Map<String,Integer> getHistoricalViews(String key){
        return blogService.getHistoricalViews(key);
    }

    @PostMapping("/isRegistered")
    @ResponseBody
    public Integer isRegistered(@RequestParam("username") String username){
       return adminService.isRegistered(username);
    }
}

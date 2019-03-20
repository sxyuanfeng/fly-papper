package com.example.hellodemo.controller;

import java.util.List;

import com.example.hellodemo.entity.Article;
import com.example.hellodemo.entity.User;
import com.example.hellodemo.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/CRUD", method = { RequestMethod.GET, RequestMethod.POST })
public class CRUD {

    @Autowired
    private UserService userservice;

    @RequestMapping("/ListUser")
    @ResponseBody
    public List<User> ListUser(){
        return userservice.ListUser();
    }

    @RequestMapping("/ListArticle")
    @ResponseBody
    public List<Article> ListArticle() { return userservice.ListArticle(); }

    @RequestMapping("/ListArticleByid")
    @ResponseBody
    public List<Article> ListArticleByid(int author_id) { return userservice.ListArticleByid(author_id); }

    @RequestMapping("/ListCollectByid")
    @ResponseBody
    public List<Article> ListCollectByid(int collecter_id) { return userservice.ListCollectByid(collecter_id); }

    @RequestMapping("/ListFollowByid")
    @ResponseBody
    public List<User> ListFollowByid(int follower_id) { return userservice.ListFollowByid(follower_id); }

    @RequestMapping("/ListMimi")
    @ResponseBody
    public List<String>ListMimi() { return userservice.ListMimi(); }

    @RequestMapping("/ListUserByname")
    @ResponseBody
    public List<User> ListUserByname(int name){
        return userservice.findByName(name);
    }


    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(int id) {
        int result = userservice.delete(id);
        if (result >= 1) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(int id_1, String password_1) {
        int result = userservice.Update(id_1, password_1);
        if (result >= 1) {
            return "修改成功";
        } else {
            return "修改失败";
        }

    }

    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public String insert(int id_2, String password_2) {
        int result = userservice.insertUser(id_2,password_2);
        if (result >= 1) {
            return "添加成功";
        } else {
            return "添加失败";
        }

    }

    @RequestMapping(value = "/fbmm", method = RequestMethod.GET)
    public String fbmm(String fbmm_content){
        int result = userservice.fbmm(fbmm_content);
        if (result >= 1) {
            return "发布成功";
        } else {
            return "发布失败";
        }
    }

    @RequestMapping(value = "/fbwz", method = RequestMethod.GET)
    public String fbwz(int author_id, String fbwz_content){
        int result = userservice.fbwz(author_id, fbwz_content);
        if (result >= 1) {
            return "发布成功";
        } else {
            return "发布失败";
        }
    }

}

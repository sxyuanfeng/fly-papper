package com.example.hellodemo.service;

import java.util.List;

import com.example.hellodemo.entity.Article;
import com.example.hellodemo.entity.User;
import com.example.hellodemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserService {
    @Autowired
    public UserMapper userMapper;

    public List<User> findByName(int name) {
        return userMapper.findUserByName(name);
    }

    public int insertUser(int id_2, String password_2) {
        return userMapper.insertUser(id_2, password_2);
    }

    public List<User> ListUser(){
        return userMapper.ListUser();
    }

    public List<Article> ListArticle() { return userMapper.ListArticle(); }

    public List<Article> ListArticleByid(int author_id) { return userMapper.ListArticleByid(author_id); }

    public List<Article> ListCollectByid(int collecter_id) { return userMapper.ListCollectByid(collecter_id); }

    public List<User> ListFollowByid(int follower_id) { return userMapper.ListFollowByid(follower_id); }

    public List<String>ListMimi() { return userMapper.ListMimi(); }

    public int Update(int id_1, String password_1){
        return userMapper.Update(id_1, password_1);
    }

    public int delete(int id){
        return userMapper.delete(id);
    }

    public int fbmm(String fbmm_content){
        return userMapper.fbmm(fbmm_content);
    }

    public int fbwz(int author_id, String fbwz_content){
        return userMapper.fbwz(author_id, fbwz_content);
    }
}

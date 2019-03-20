package com.example.hellodemo.mapper;

import java.util.List;

import com.example.hellodemo.entity.Article;
import com.example.hellodemo.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {


    @Select("select * from user where id=#{name}")
    List<User> findUserByName(int name);

    @Select("select * from user")
    public List<User> ListUser();

    @Select("select * from article")
    public List<Article> ListArticle();

    @Select("select * from article where authorid=#{author_id}")
    public List<Article> ListArticleByid(int authoe_id);

    @Select("select * from article where articleid in(select collectdarticleid from collect where collecterid=#{collecter_id})")
    public List<Article> ListCollectByid(int collecter_id);

    @Select("select * from user where id in(select followee from follow where follower=#{follower_id})")
    public List<User> ListFollowByid(int follower_id);

    @Select("select content from mimi")
    public List<String>ListMimi();

    @Insert("insert into user(id, password) values (#{id_2}, #{password_2})")
    public int insertUser(@Param("id_2") int id_2, @Param("password_2") String password_2);

    public int delete(int id);

    @Update("update user set password=#{password_1} where id=#{id_1}")
    public int Update(@Param("id_1") int id_1, @Param("password_1") String password_1);

    @Insert("insert into mimi(content) values (#{fbmm_content})")
    public int fbmm(@Param("fbmm_content") String fbmm_content);

    @Insert("insert into article(authorid, content) values (#{author_id}, #{fbwz_content})")
    public int fbwz(@Param("author_id") int author_id, @Param("fbwz_content") String fbwz_content);

}

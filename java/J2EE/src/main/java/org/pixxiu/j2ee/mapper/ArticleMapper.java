package org.pixxiu.j2ee.mapper;


import org.apache.ibatis.annotations.*;
import org.pixxiu.j2ee.pojo.Article;

import java.util.List;

@Mapper
public interface ArticleMapper {
    //新增
    @Insert("insert into article(title,content,cover_img,state,category_id,create_user,create_time,update_time) " +
            "values(#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},#{createTime},#{updateTime})")
    void add(Article article);

    //分页查询
    List<Article> list(Integer userId, Integer categoryId, String state);

    //根据id查询文章详情
    @Select("select * from article where id=#{id}")
    Article findById(Integer id);

    //该接口用于更新文章信息
    @Update("update article set category_id=#{categoryId},title=#{title},update_time=now(),content=#{content},cover_img=#{coverImg},state=#{state} where id=#{id}")
    void update(Article article);

    @Delete("delete from article where id=#{id}")
    void delById(Integer id);
}

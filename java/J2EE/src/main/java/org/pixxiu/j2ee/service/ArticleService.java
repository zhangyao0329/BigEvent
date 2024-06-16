package org.pixxiu.j2ee.service;


import org.pixxiu.j2ee.pojo.Article;
import org.pixxiu.j2ee.pojo.Category;
import org.pixxiu.j2ee.pojo.PageBean;

public interface ArticleService {
    //新增文章
    void add(Article article);

    //条件分页列表查询
    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);

    //该接口用于根据ID获取文章详细信息
    Article findById(Integer id);

    //该接口用于更新文章信息
    void update(Article article);

    void deleteById(Integer id);
}

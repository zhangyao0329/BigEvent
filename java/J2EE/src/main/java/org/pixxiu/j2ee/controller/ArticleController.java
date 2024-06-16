package org.pixxiu.j2ee.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.pixxiu.j2ee.pojo.Article;
import org.pixxiu.j2ee.pojo.Category;
import org.pixxiu.j2ee.pojo.PageBean;
import org.pixxiu.j2ee.pojo.Result;
import org.pixxiu.j2ee.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "文章接口")
@RestController
@RequestMapping("/article")
@CrossOrigin
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Operation(summary = "新增文章")
    @PostMapping
    public Result add(@RequestBody @Validated Article article) {
        articleService.add(article);
        return Result.success();
    }

    @Operation(summary = "分页查询--获取文章列表")
    @GetMapping
    public Result<PageBean<Article>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String state
    ) {
        PageBean<Article> pb = articleService.list(pageNum, pageSize, categoryId, state);
        return Result.success(pb);
    }

    @Operation(summary = "获取分类详情",description = "该接口用于根据ID获取文章详细信息")
    @GetMapping("/detail")
    public Result<Article> detail(Integer id) {
        Article article = articleService.findById(id);
        return Result.success(article);
    }

    @Operation(summary = "更新文章",description = "该接口用于更新文章信息")
    @PutMapping
    public Result update(@RequestBody Article article) {
        articleService.update(article);
        return Result.success();
    }

    @Operation(summary = "删除文章",description = "该接口用于根据ID删除文章")
    @DeleteMapping
    public Result delete(Integer id) {
        articleService.deleteById(id);
        return Result.success();
    }


}

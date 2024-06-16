package org.pixxiu.j2ee.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.pixxiu.j2ee.pojo.Category;
import org.pixxiu.j2ee.pojo.Result;
import org.pixxiu.j2ee.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@Tag(name = "文章分类接口")
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Operation(summary = "添加文章分类")
    @PostMapping
    public Result add(@RequestBody @Validated(Category.Add.class) Category category) {
        categoryService.add(category);
        return Result.success();
    }

    @Operation(summary = "获取文章分类列表")
    @GetMapping
    public Result<List<Category>> list() {
        List<Category> cs = categoryService.list();
        return Result.success(cs);
    }

    @Operation(summary = "文章分类详情")
    @GetMapping("/detail")
    public Result<Category> detail(Integer id) {
        Category c = categoryService.findById(id);
        return Result.success(c);
    }

    @Operation(summary = "更新文章分类")
    @PutMapping
    public Result update(@RequestBody @Validated(Category.Update.class) Category category) {
        categoryService.update(category);
        return Result.success();
    }

    @Operation(summary = "删除文章分类")
    @DeleteMapping
    public Result delete(Integer id) {
        categoryService.deleteById(id);
        return Result.success();
    }
}

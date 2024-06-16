import request from "@/utils/request";
/* 文章分类表的操作 */
// 获取文章分类
export const articleCategoryListService = () => {
  return request.get('/category')
}
// 添加文章分类
export const articleCategoryAddService = (categoryData) => {
  return request.post('/category', categoryData)
}
// 更新文章分类
export const articleCategoryUpdateService = (categoryData) => {
  return request.put('/category', categoryData)
}
// 删除文章分类
export const articleCategoryDeleteService = (id) => {
  return request.delete('/category?id=' + id)
}

// 文章分类详情---
export const articleCategoryDetailService = (id) => {
  return request.get('/category/detail?id=' + id)
}



/* 文章表的操作 */
// 分页查询
export const articleListService = (params) => {
  return request.get('/article', { params })
}
// 新增文章
export const articleAddService = (articleData) => {
  return request.post('/article', articleData)
}
// 更新文章
export const articleUpdateService = (articleData) => {
  return request.put('/article', articleData)
}
// 删除文章
export const articleDeleteService = (id) => {
  return request.delete('/article?id=' + id)
}

// 获取文章分类详情---
export const articleDetailService = (id) => {
  return request.get('/article/detail?id=' + id)
}


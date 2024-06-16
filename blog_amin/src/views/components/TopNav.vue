<template>
  <nav>
    <ul>
      <li><router-link to="/blog/all">首页</router-link></li>
      <li><router-link to="/blog/categories">分类</router-link></li>
      <li> <router-link to="/blog/archives">归档</router-link></li>
      <li><router-link to="/blog/friends">友链</router-link></li>
      <li><router-link to="/">后台</router-link></li>
    </ul>
  </nav>
</template>

<script>
export default {
  name: 'Navigation'
}
</script>

<style>
nav {
  display: flex;
  justify-content: center;
  height: 18px;
}

ul {
  list-style-type: none;
  padding: 0;
  margin: 0;
}

li {
  display: inline;
  margin-right: 20px;
  /* 设置宽高 */
  width: 50px;
  height: 20px;
  transition: background-color 0.5s;
}

/* 控制导航栏字体样式 */
a {
  color: #ffffff;
  text-decoration: none;
  margin-right: 10px;
  font-size: 18px;
}

a:hover {
  color: #c1b0b0;
}
</style>
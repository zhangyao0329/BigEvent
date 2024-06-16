<script setup>
import { RouterView, RouterLink } from 'vue-router';
import TopNav from '@/views/components/TopNav.vue';


</script>

<template>
    <!-- 导航区 -->
    <div class="navigation">
        <TopNav />
    </div>

    <!-- 展示区 -->
    <div class="main-content">
        <RouterView />
    </div>


<div>
    ICP 备案号：
</div>
</template>

<style scoped>
.navigation {
    display: flex;
    justify-content: flex-end;
    padding: 20px;
    border-bottom: 1px solid #e6eae5;
    border-radius: 5px;
    margin-bottom: 0px;
    /* 背景透明效果 */
    background: rgba(190, 134, 196, 0.8);

}

.main-content {
    border: 1px solid #dd1b1b;
    padding: 20px;
    border-radius: 5px;
    height: 790px;
    overflow: auto;
    background-color: #eaeaea;
}

/* 控制底部备案样式 */
div {
    text-align: center;
    padding: 10px;
    background-color: #eaeaea;
    border-radius: 5px;
    margin-top: 10px;
}
</style>
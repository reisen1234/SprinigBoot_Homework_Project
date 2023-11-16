<template>
    <div class="home">
        <el-container>
            <el-header>Header</el-header>
            <el-container>
                <el-aside width="200px">Aside</el-aside>
                <el-container>
                    <el-main padding-right="10px">
                        <bookListVue  />
                    </el-main>
                    <el-footer>Footer</el-footer>
                </el-container>
            </el-container>
        </el-container>
    </div>
</template>

<script setup>
import bookListVue from "@/components/bookList.vue";
</script>
<style>
.home {
    height: 100%;
    width: 100%;
}

.el-container {
    height: 100%;
}
</style>
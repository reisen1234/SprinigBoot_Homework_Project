<template>
    <div>
        <el-table :data="booklist" style="width: 100%">
            <el-table-column label="ID">
                <template #default="scope">
                    <div style="display: flex; align-items: center">
                        <el-icon>
                            <timer />
                        </el-icon>
                        <span style="margin-left: 10px">{{ scope.row.id }}</span>
                    </div>
                </template>
            </el-table-column>
            <el-table-column label="Name">
                <template #default="scope">
                    <div style="display: flex; align-items: center">
                        <el-tag>{{ scope.row.name }}</el-tag>
                    </div>
                </template>
            </el-table-column>
            <el-table-column label="Price">
                <template #default="scope">
                    <div style="display: flex; align-items: center">
                        <span>{{ scope.row.price }}</span>
                    </div>
                </template>
            </el-table-column>
            <el-table-column label="CreateDate">
                <template #default="scope">
                    <div style="display: flex; align-items: center">
                        <span>{{ scope.row.createTime }}</span>
                    </div>
                </template>
            </el-table-column>
            <el-table-column label="UserName">
                <template #default="scope">
                    <div style="display: flex; align-items: center">
                        <span>{{ scope.row.userDto.name }}</span>
                    </div>
                </template>
            </el-table-column>
            <el-table-column label="Operations">
                <template #default="scope">
                    <el-button size="small" @click="handleEdit(scope.$index, scope.row)">Edit</el-button>
                    <el-button size="small" type="danger" @click="handleDelete(scope.$index, scope.row)">Delete</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script setup>
import { Timer } from '@element-plus/icons-vue'
import { useStore } from "vuex";
import { onBeforeMount, ref } from "vue";
const store = useStore();
const booklist = ref([]); // 创建一个 ref 来保存书籍列表
onBeforeMount(async () => {
    try {
        // 使用 mapActions 方便调用 Vuex action
        await store.dispatch('book/getBookByName', store.state.user.username);
        // 获取书籍列表并设置到 booklist 变量
        store.getters['book/FormatDateList'];
        booklist.value = store.state.book.bookList
        // 执行其他操作...
    } catch (error) {
        console.error("获取书籍列表失败: ", error);
    }
});
const handleEdit = (index, row) => {
    store.dispatch('book/deleteBookById', row.id).then(() => {
        console.log(2)
    })
}
const handleDelete = (index, row) => {
    store.dispatch('book/deleteBookById', row.id).then(() => {
        booklist.value = store.state.book.bookList

    })
}
</script>


<style lang="scss" scoped></style>
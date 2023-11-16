<template>
    <div class="my-component">
        <div class="login">
            <div style="padding: 20px">
                <h1>register</h1>
            </div>
            <el-form ref="ruleFormRef" :model="ruleForm" status-icon :rules="rules" label-width="120px"
                class="demo-ruleForm">
                <el-form-item label="Username" prop="name">
                    <el-input v-model="ruleForm.name" autocomplete="off" />
                </el-form-item>
                <el-form-item label="Password" prop="password">
                    <el-input v-model="ruleForm.password" type="password" autocomplete="off" />
                </el-form-item>
                <el-form-item label="Confirm" prop="checkPass">
                    <el-input v-model="ruleForm.checkPass" type="password" autocomplete="off" />
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm(ruleFormRef)">Submit</el-button>
                    <el-button @click="resetForm(ruleFormRef)">Reset</el-button>
                </el-form-item>
                <router-link to="/login">login</router-link>
            </el-form>
        </div>
    </div>
</template>

<script setup>
import { reactive, ref } from 'vue';
import { useStore } from "vuex";
import { useRouter } from "vue-router"; // 导入useRouter
const router = useRouter(); // 初始化router
const store = useStore();
const ruleFormRef = ref();
const checkUsername = (rule, value, callback) => {
    if (value === '') {
        callback(new Error('Please input the username'));
    } else {
        callback();
    }
};

const validatePass = (rule, value, callback) => {
    if (value === '') {
        callback(new Error('Please input the password'));
    } else {
        if (ruleForm.checkPass !== '') {
            if (!ruleFormRef.value) return;
            ruleFormRef.value.validateField('checkPass', () => null);
        }
        callback();
    }
};
const validatePass2 = (rule, value, callback) => {
    if (value === '') {
        callback(new Error('Please input the password again'));
    } else if (value !== ruleForm.password) {
        callback(new Error("Two inputs don't match!"));
    } else {
        callback();
    }
};

const ruleForm = reactive({
    password: '',
    checkPass: '',
    name: '', // 修复拼写错误
});

const rules = reactive({
    password: [{ validator: validatePass, trigger: 'blur' }],
    checkPass: [{ validator: validatePass2, trigger: 'blur' }],
    name: [{ validator: checkUsername, trigger: 'blur' }],
});

const submitForm = (formEl) => {
    if (!formEl) return;
    formEl.validate((valid) => {
        if (valid) {
            store.dispatch('user/register', ruleForm).then(res => {
                if (res == false) alert("username is exist")
                else {
                    alert("success, link to the login page")
                    router.push('/login')
                }
            })
        } else {
            return false;
        }
    });
};

const resetForm = (formEl) => {
    if (!formEl) return;
    formEl.resetFields();
};
</script>

<style >
.my-component {
    background: url('/src/assets/109862500_p0.jpg') center center no-repeat;
    background-size: cover;
    width: 100vw;
    height: 100vh;
    opacity: 0.8;
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 100vh;
}

.login {
    background-color: #fff;
    border: 1px solid #ccc;
    border-radius: 5px;
    padding: 20px;
    text-align: center;
    width: 500px;
}
</style>

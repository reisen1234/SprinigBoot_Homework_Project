<template>
    <div class="my-component">
        <div class="login">
            <div style="padding:20px">
                <h1>login</h1>
            </div>
            <el-form ref="ruleFormRef" :model="ruleForm" status-icon :rules="rules" label-width="120px"
                class="demo-ruleForm">
                <el-form-item label="Username" prop="name">
                    <el-input v-model="ruleForm.name" autocomplete="off" />
                </el-form-item>
                <el-form-item label="Password" prop="password">
                    <el-input v-model="ruleForm.password" type="password" autocomplete="off" />
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm(ruleFormRef)">Submit</el-button>
                    <el-button @click="resetForm(ruleFormRef)">Reset</el-button>
                </el-form-item>
                <router-link to="/register">register</router-link>
            </el-form>
        </div>
    </div>
</template>

<script  setup>
import { reactive, ref } from 'vue';
import { useStore } from "vuex";
import { useRouter } from "vue-router"; // 导
const ruleFormRef = ref();
// eslint-disable-next-line no-unused-vars
const router = useRouter();
const store = useStore();
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
        callback();
    }
};

const ruleForm = reactive({
    password: '',
    name: '',
});

const rules = reactive({
    password: [{ validator: validatePass, trigger: 'blur' }],
    name: [{ validator: checkUsername, trigger: 'blur' }],
});

const submitForm = (formEl) => {
    if (!formEl) return;
    formEl.validate((valid) => {
        if (valid) {
            store.dispatch('user/login',ruleForm).then(res => {
                if (res == false) alert("login fail")
                else {
                    alert("success, link to the home page")
                    router.push('/')
                }
            })
            console.log('submit!');
        } else {
            console.log('error submit!');
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
import { createRouter, createWebHistory } from "vue-router";
import { useStore } from "vuex";
import Home from "../views/Home/homePage.vue";
import login from "../views/Login/loginPage.vue";
import register from "../views/Register/registerPage.vue";
const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: "/",
      name: "home",
      component: Home,
    },
    {
      path: "/login",
      name: "login",
      component: login,
    },
    {
      path: "/register",
      name: "register",
      component: register,
    },
  ],
});
router.beforeEach(async (to, from, next) => {
  const store = useStore();
  const res = await store.dispatch("user/checkLogin");
  //未进行登入检查
  if (!store.state.user.checkedLogin) {
    //未登入
    store.commit("user/updateCheckedLogin", true);
    if (res !== "success") {
      if (to.name === "login" || to.name === "register") {
        next();
      } else next({ name: "login" });
    } else {
      if (to.name === "login" || to.name === "register") {
        next({ name: "home" });
      } else {
        next();
      }
    }
  }else {
    next()
    store.commit("user/updateCheckedLogin", false);
  }

});
export default router;

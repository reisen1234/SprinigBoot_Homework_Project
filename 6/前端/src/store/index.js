import user from "./user";
import book from "./book"
import { createStore } from 'vuex';
const store = createStore();

// 注册 userModule 和 otherModule 模块
store.registerModule('user', user);
store.registerModule('book',book)

export default store;

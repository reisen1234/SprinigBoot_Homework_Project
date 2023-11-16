import api from "../api/userApi/index";
const user = {
  namespaced: true,
  state() {
    return {
      username: "",
      userStatus: "idle",
      checkedLogin: false
    };
  },
  mutations: {
    updateStatus(state, payload) {
      state.userStatus = payload;
    },
    updateName(state, payload) {
      state.username = payload;
    },
    updateCheckedLogin(state,payload){
        state.checkedLogin = payload
    }
  },
  actions: {
    async login({ commit }, data) {
      commit("updateStatus", "idle");
      try {
        const response = (await api.login(data)).data;
        if (response.statusCode === "200") {
          commit("updateStatus", "success");
          commit("updateName", response.data.user.name);
          localStorage.setItem("token",response.data.token)
          return true
        } else return false;
      } catch (error) {
        console.log(error);
      }
    },
    async checkLogin({ commit, state }) {
      const token = localStorage.getItem("token");
      if (token) {
        commit("updateStatus", "idle");
        try {
          await api.checkLogin({"token":token}).then(res => {
            if(res.data.statusCode === "200"){
                commit("updateStatus", "success");
                commit("updateName",res.data.data.name)
            }
            else commit("updateStatus", "idle");
          });
        } catch (error) {
          console.log(error);
        }
      }
      return state.userStatus
    },
    // eslint-disable-next-line no-unused-vars
    async register({ commit }, data) {
      try {
        const response = await api.register(data);
        if (response.data.statusCode === "200") return true;
        else return false;
      } catch (error) {
        console.log(error);
      }
    },
  },
  getters: {
    // eslint-disable-next-line no-unused-vars
    getUserName(){
        console.log(1)
        return this.$store.state.username
    }
  },
};
export default user;

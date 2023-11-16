import api from "../api/bookApi/index";
const book = {
  namespaced: true,
  state() {
    return {
      bookList: [],
      bookStatus: "idle",
    };
  },
  mutations: {
    updateStatus(state, payload) {
      state.bookStatus = payload;
    },
    updateBook(state, payload) {
      state.bookList = payload;
    },
  },
  actions: {
    async getBookByName({ commit }, name) {
      try {
        const BookList = await api.getBookByName(name);
        return new Promise((rs) => {
          commit("updateStatus", "succees");
          commit("updateBook", BookList.data.data);
          rs();
        });
      } catch (error) {
        console.log(error);
      }
    },
    async deleteBookById({ commit, state }, id) {
      console.log(1)
      try {
        const a = await api.deleteBookById(id);
        console.log(a)
        return new Promise((rs) => {
          commit("updateStatus", "succees");
          commit(
            "updateBook",
            state.bookList.filter((item) => item.id !== id)
          );
          console.log(state)
          rs();
        });
      } catch (error) {
        console.log(error);
      }
    },
  },
  getters: {
    // eslint-disable-next-line no-unused-vars
    FormatDateList(state) {
      return state.bookList.map((item) => {
        var n = item.createTime.split("T");
        item.createTime = n[0] + " " + n[1].split(".")[0];
        return item;
      });
    },
  },
};
export default book;

<template>
  <div class="component">
    <RouterView />
  </div>
</template>

<script lang="js">
import { useStore, mapState } from 'vuex';
import { computed } from 'vue'
const debounce = (fn, delay) => {
  let timer = null;
  return function () {
    let context = this
    let args = arguments;
    clearTimeout(timer);
    timer = setTimeout(function () {
      fn.apply(context, args);
    }, delay);
  };
};
// 解决ERROR ResizeObserver loop completed with undelivered notifications.
const _ResizeObserver = window.ResizeObserver;
window.ResizeObserver = class ResizeObserver extends _ResizeObserver {
  constructor(callback) {
    callback = debounce(callback, 16);

    super(callback);
  }
};
export default {
  setup() {
    const store = useStore();
    store.dispatch('user/checkLogin');
    // eslint-disable-next-line no-unused-vars
    const userStatus = computed(() => store.state.userStatus);
    // eslint-disable-next-line no-unused-vars
    const userName = computed(() => store.state.userName);
  },
  computed: {
    ...mapState('userApi', {
      userStatus: state => state.userStatus
    }),
    ...mapState('userApi', {
      userName: state => state.userName
    }),
  }
}
</script>

<style>
.component {
  width: 100vw;
  height: 100vh;
}
</style>
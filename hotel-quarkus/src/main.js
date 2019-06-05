import axios from 'axios'
import Vue from 'vue';
import VueRouter from 'vue-router'
import App from './App.vue';
import HotelList from './components/HotelList.vue';
import Hotel from './components/Hotel.vue';

window.axios = axios;
window.axios.defaults.baseURL = (process.env.NODE_ENV !== 'production') ? 'http://localhost:8081' : 'http://localhost:8080';

Vue.use(VueRouter);

Vue.config.debug = true;
Vue.config.devtools = true;
Vue.config.productionTip = process.env.NODE_ENV === 'production';

const routes = [
  {
    path: '/',
    redirect: '/hotels'
  },
  {
    path: '/hotels',
    component: HotelList
  },
  {
    path: '/hotels/:id',
    component: Hotel
  }
];


const router = new VueRouter({
  routes
});

new Vue({
  router,
  render: h => h(App),
}).$mount('#app');

import Vue from 'vue';
import App from "../pages/app";
import Login from "../pages/Login";
import VueRouter from 'vue-router';

Vue.use(VueRouter)

const routes = [
  { path: '/', name: 'dayspan', component: App },
  { path: '/login', name: 'login', component: Login }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
})

export default router
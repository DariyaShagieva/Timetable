import Vue from 'vue'
import Vuetify from 'vuetify'
import VueRouter from 'vue-router'
import DaySpanVuetify from './plugin'
import App from './app'

import 'vuetify/dist/vuetify.min.css'
import 'material-design-icons-iconfont/dist/material-design-icons.css'
import './styles/app.scss'

import en from './locales/en'
import ru from './locales/ru'

import * as moment from 'moment'
moment.lang('ru')

Vue.config.productionTip = false

Vue.use(Vuetify);

Vue.use(VueRouter);

Vue.use(DaySpanVuetify,
  {
    data:
    {
      locales: { en, ru }
    },
    methods:
    {
      getDefaultEventColor() {
        return '#1976d2';
      }
    }
  });

const routes = [
  { path: '/', component: App }
]

// 3. Create the router instance and pass the `routes` option
// You can pass in additional options here, but let's
// keep it simple for now.
const router = new VueRouter({
  routes // short for `routes: routes`
})
/* eslint-disable no-new */
new Vue({
  router,
  el: '#app',
  render: h => h(App)
})

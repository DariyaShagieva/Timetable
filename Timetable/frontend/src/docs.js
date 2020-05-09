import Vue from 'vue'
import Vuetify from 'vuetify'
import DaySpanVuetify from './plugin'
import App from './pages/app'

import 'vuetify/dist/vuetify.min.css'
import 'material-design-icons-iconfont/dist/material-design-icons.css'
import './styles/app.scss'

import ru from './locales/ru'
import en from './locales/en'

import 'moment/lang/fr'
import 'moment/lang/ca'
import 'moment/lang/nl'
import * as moment from 'moment'
moment.lang('en')
import router from './router/index'

Vue.config.productionTip = false

Vue.use(Vuetify);

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

new Vue({
  el: '#app',
  router,
  render: h => h(App)
})

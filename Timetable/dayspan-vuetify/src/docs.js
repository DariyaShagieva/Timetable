import Vue from 'vue'
import Vuetify from 'vuetify'
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

Vue.use(DaySpanVuetify,
{
  data:
  {
    locales: { en, ru }
  },
  methods:
  {
    getDefaultEventColor()
    {
      return '#1976d2';
    }
  }
});

/* eslint-disable no-new */
new Vue({
  el: '#app',
  render: h => h(App)
})

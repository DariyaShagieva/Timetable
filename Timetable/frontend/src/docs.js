import Vue from 'vue'
import Vuetify from 'vuetify'
import DaySpanVuetify from './plugin'
import App from './pages/TimeTable'

import 'vuetify/dist/vuetify.min.css'
import 'material-design-icons-iconfont/dist/material-design-icons.css'
import './styles/app.scss'

import ru from './locales/ru'
import en from './locales/en'

import 'moment/locale/ru'

import moment from "moment"
moment.locale('ru')

Vue.config.productionTip = false
// Vuetify.locale('ru');
Vue.use(Vuetify);

Vue.use(DaySpanVuetify,
  {
    data:
    {
      defaults: {
        dsWeeksView: {
          weekdays: moment.weekdaysShort(true)
        },
      },
      locales: { ru, en }
    },
    methods:
    {
      getDefaultEventColor() {
        return '#015666';
      }
    }
  });
Vue.$dayspan.addLocale('ru', ru);
Vue.$dayspan.setLocale('ru', true);
new Vue({
  el: '#app',
  render: h => h(App)
})

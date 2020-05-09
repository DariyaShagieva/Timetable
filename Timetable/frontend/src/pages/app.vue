<template>
  <v-app id="dayspan" v-cloak>
    <ds-calendar-app ref="app" :calendar="calendar" :read-only="readOnly" @change="saveState">
      <template id="title" slot="title">TimeTable ITIS</template>
      <template slot="eventPopover" slot-scope="slotData">
        <ds-calendar-event-popover v-bind="slotData" :read-only="readOnly" @finish="saveState"></ds-calendar-event-popover>
      </template>

      <template slot="eventCreatePopover" slot-scope="{placeholder, calendar}">
        <ds-calendar-event-create-popover
          :calendar-event="placeholder"
          :calendar="calendar"
          :close="$refs.app.$refs.calendar.clearPlaceholder"
          @create-edit="$refs.app.editPlaceholder"
          @create-popover-closed="saveState"
        ></ds-calendar-event-create-popover>
      </template>

      <template slot="drawerBottom">
        <v-container fluid>
          <v-layout wrap align-center>
            <v-flex xs12>
              <v-select
                label="Language"
                :items="locales"
                v-model="currentLocale"
                @input="setLocale"
              ></v-select>
            </v-flex>
            <v-flex xs12>
              <v-btn
                round
                block
                id="joinChannel"
                color="primary"
                @click.stop="dialog = true"
              >Join to channel</v-btn>
            </v-flex>
            <v-dialog v-model="dialog" max-width="290">
              <v-card>
                <v-card-title class="headline">Join to channel</v-card-title>
                <v-flex>
                  <v-card-text>Введите код для идентификации</v-card-text>
                  <v-card-text>53563890567</v-card-text>
                </v-flex>
                <v-card-actions>
                  <v-spacer></v-spacer>

                  <v-btn color="blue darken-1" round text @click="dialog = false">Отмена</v-btn>

                  <v-btn color="blue darken-1" round text>
                    <a
                      style="text-decoration: none;color:black"
                      href="tg://join?invite=https://t.me/1356101762"
                    >Перейти на канал</a>
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
            <v-flex xs12>
              <v-btn round block id="downloadShedule" color="primary">Upload timetable</v-btn>
            </v-flex>
          </v-layout>
        </v-container>
      </template>
    </ds-calendar-app>
  </v-app>
</template>

<script>
import { dsMerge } from "../functions";
import { Calendar, Weekday, Month, Sorts } from "dayspan";
import * as moment from "moment";

export default {
  name: "dayspan",
  data: vm => ({
    storeKey: "dayspanState",
    calendar: Calendar.months(),
    readOnly: false,
    dialog: false,
    currentLocale: vm.$dayspan.currentLocale,
    locales: [
      { value: "ru", text: "Russia" },
      { value: "en", text: "English" }
    ],
    defaultEvents: [
      {
        data: {
          title: "Философия",
          type: "Лекция"
        },
        schedule: {
          dayOfWeek: [Weekday.MONDAY],
          times: [9],
          duration: 90,
          durationUnit: "minutes"
        }
      },{
        data: {
          title: "Философия",
          type: "Лекция"
        },
        schedule: {
          dayOfWeek: [Weekday.TUESDAY],
          times: [9],
          duration: 90,
          durationUnit: "minutes"
        }
      },{
        data: {
          title: "Философия",
          type: "Лекция"
        },
        schedule: {
          dayOfWeek: [Weekday.WEDNESDAY],
          times: [9],
          duration: 90,
          durationUnit: "minutes"
        }
      },{
        data: {
          title: "Философия",
          type: "Лекция"
        },
        schedule: {
          dayOfWeek: [Weekday.THURSDAY],
          times: [9],
          duration: 90,
          durationUnit: "minutes"
        }
      },{
        data: {
          title: "Философия",
          type: "Лекция"
        },
        schedule: {
          dayOfWeek: [Weekday.FRIDAY],
          times: [9],
          duration: 90,
          durationUnit: "minutes"
        }
      },{
        data: {
          title: "Философия",
          type: "Лекция"
        },
        schedule: {
          dayOfWeek: [Weekday.SATURDAY],
          times: [9],
          duration: 90,
          durationUnit: "minutes"
        }
      },
    ]
  }),
  mounted() {
    window.app = this.$refs.app;
    this.loadState();
  },
  methods: {
    getCalendarTime(calendarEvent) {
      let sa = calendarEvent.start.format("a");
      let ea = calendarEvent.end.format("a");
      let sh = calendarEvent.start.format("h");
      let eh = calendarEvent.end.format("h");
      if (calendarEvent.start.minute !== 0) {
        sh += calendarEvent.start.format(":mm");
      }
      if (calendarEvent.end.minute !== 0) {
        eh += calendarEvent.end.format(":mm");
      }
      return sa === ea ? sh + " - " + eh + ea : sh + sa + " - " + eh + ea;
    },
    setLocale(code) {
      moment.lang(code);
      this.$dayspan.setLocale(code);
      this.$dayspan.refreshTimes();
      this.$refs.app.$forceUpdate();
    },
    saveState() {
      let state = this.calendar.toInput(true);
      let json = JSON.stringify(state);
      localStorage.setItem(this.storeKey, json);
    },
    loadState() {
      let state = {};
      try {
        let savedState = JSON.parse(localStorage.getItem(this.storeKey));
        if (savedState) {
          state = savedState;
          state.preferToday = false;
        }
      } catch (e) {
        console.log(e);
      }
      if (!state.events || !state.events.length) {
        state.events = this.defaultEvents;
      }
      let defaults = this.$dayspan.getDefaultEventDetails();
      state.events.forEach(ev => {
        ev.data = dsMerge(ev.data, defaults);
      });
      this.$refs.app.setState(state);
    }
  }
};
</script>

<style>
body,
html,
#app {
  width: 100%;
  height: 100%;
}
#downloadShedule {
  background-color: bisque;
}
.v-btn round .v-btn__content .v-icon {
  color: white;
}
/* .v-btn__content {
  color: wheat;
} */
.ds-day-picker .ds-week-header .subtitle[data-v-941fe142] {
  color: black;
}
</style>
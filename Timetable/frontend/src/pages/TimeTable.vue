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
                style="color:white"
                round
                block
                id="login"
                color="#04859D"
                @click.stop="account = true"
              >Login</v-btn>
            </v-flex>
            <v-row justify="center">
              <v-dialog v-model="account" persistent max-width="600px">
                <v-card class="account">
                  <v-tabs vertical style="padding:10px 10px 10px">
                    <v-tab>Login</v-tab>
                    <v-tab>Register</v-tab>
                    <v-tab-item>
                      <v-card-text>
                        <v-container>
                          <v-row>
                            <v-col cols="12">
                              <v-text-field
                                prepend-icon="person"
                                label="Email*"
                                required
                                v-model="input.email"
                              ></v-text-field>
                            </v-col>
                            <v-col cols="12">
                              <v-text-field
                                prepend-icon="lock"
                                name="Password"
                                label="Password*"
                                v-model="input.password"
                                type="password"
                                required
                              ></v-text-field>
                            </v-col>
                          </v-row>
                        </v-container>
                      </v-card-text>
                      <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn
                          style="color:white"
                          color="#04859D"
                          text
                          round
                          v-on:click="closeLogin()"
                        >Close</v-btn>
                        <v-btn
                          round
                          style="color:white"
                          color="#04859D"
                          v-model="readOnly"
                          text
                          v-on:click="login()"
                        >Login</v-btn>
                      </v-card-actions>
                    </v-tab-item>
                    <v-tab-item>
                      <v-card-text>
                        <v-container>
                          <v-row>
                            <v-col cols="12">
                              <v-text-field
                                prepend-icon="person"
                                label="Email*"
                                required
                                v-model="input.email"
                              ></v-text-field>
                            </v-col>
                            <v-col cols="12">
                              <v-text-field
                                prepend-icon="person"
                                label="Имя*"
                                required
                                v-model="input.lastName"
                              ></v-text-field>
                            </v-col>
                            <v-col cols="12">
                              <v-text-field
                                prepend-icon="person"
                                label="Фамилия*"
                                required
                                v-model="input.firstName"
                              ></v-text-field>
                            </v-col>
                            <v-col cols="12">
                              <v-text-field
                                prepend-icon="person"
                                label="Отчество*"
                                required
                                v-model="input.patronymic"
                              ></v-text-field>
                            </v-col>
                            <v-col cols="12">
                              <v-text-field
                                prepend-icon="person"
                                label="Группа*"
                                required
                                v-model="input.group"
                              ></v-text-field>
                            </v-col>
                            <v-col cols="12">
                              <v-text-field
                                prepend-icon="lock"
                                name="Password"
                                label="Password*"
                                v-model="input.password"
                                type="password"
                                required
                              ></v-text-field>
                            </v-col>
                          </v-row>
                        </v-container>
                      </v-card-text>
                      <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn
                          style="color:white"
                          color="#04859D"
                          text
                          round
                          v-on:click="closeLogin()"
                        >Close</v-btn>
                        <v-btn
                          style="color:white"
                          color="#04859D"
                          v-model="readOnly"
                          text
                          round
                          v-on:click="register()"
                        >Register</v-btn>
                      </v-card-actions>
                    </v-tab-item>
                  </v-tabs>
                </v-card>
              </v-dialog>
            </v-row>
            <v-flex xs12></v-flex>

            <v-flex xs12>
              <v-btn
                round
                block
                id="joinChannel"
                color="#04859D"
                style="color:white"
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

                  <v-btn
                    style="color:white"
                    color="#04859D"
                    round
                    text
                    @click="dialog = false"
                  >Отмена</v-btn>

                  <v-btn style="color:white" color="#04859D" round text>
                    <a
                      style="text-decoration: none;color:black"
                      href="tg://join?invite=https://t.me/1356101762"
                    >Перейти на канал</a>
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
            <v-flex xs12>
              <v-btn
                style="color:white"
                round
                block
                id="downloadShedule"
                color="#04859D"
              >Upload timetable</v-btn>
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
import { HTTP } from "../infrastructure/http-common";
import axios from "axios";

export default {
  name: "dayspan",
  data: vm => ({
    storeKey: "dayspanState",
    calendar: Calendar.months(),
    readOnly: true,
    dialog: false,
    input: {
      email: "",
      password: "",
      firstName: "",
      lastName: "",
      patronymic: "",
      group: ""
    },
    tab: null,
    items: [
      { tab: "Login", content: "1" },
      { tab: "Register", content: "2" }
    ],
    account: false,
    currentLocale: vm.$dayspan.currentLocale,
    locales: [
      { value: "ru", text: "Русский" },
      { value: "en", text: "English" }
    ],
    defaultEvents: [
      {
        data: {
          title: "Философия"
        },
        schedule: {
          dayOfWeek: [Weekday.MONDAY],
          times: [15],
          duration: 90,
          durationUnit: "minutes"
        }
      },
      {
        data: {
          title: "UX",
          type: "Лекция",
          color: "#2196F3"
        },
        schedule: {
          dayOfWeek: [Weekday.MONDAY],
          times: [16],
          duration: 90,
          durationUnit: "minutes"
        }
      },
      {
        data: {
          title: "UX",
          type: "Лекция",
          color: "#2196F3"
        },
        schedule: {
          dayOfWeek: [Month.NOVEMBER.WEDNESDAY],
          times: [16],
          duration: 90,
          durationUnit: "minutes"
        }
      },
      {
        data: {
          title: "Inauguration Day",
          color: "#2196F3",
          calendar: "US Holidays"
        },
        schedule: {
          month: [Month.JANUARY],
          dayOfMonth: [20]
        }
      },
      {
        data: {
          title: "Martin Luther King, Jr. Day",
          color: "#2196F3",
          calendar: "US Holidays"
        },
        schedule: {
          month: [Month.JANUARY],
          dayOfWeek: [Weekday.MONDAY],
          weekspanOfMonth: [2]
        }
      },
      {
        data: {
          title: "George Washington's Birthday",
          color: "#2196F3",
          calendar: "US Holidays"
        },
        schedule: {
          month: [Month.FEBRUARY],
          dayOfWeek: [Weekday.MONDAY],
          weekspanOfMonth: [2]
        }
      },
      {
        data: {
          title: "Memorial Day",
          color: "#2196F3",
          calendar: "US Holidays"
        },
        schedule: {
          month: [Month.MAY],
          dayOfWeek: [Weekday.MONDAY],
          lastWeekspanOfMonth: [0]
        }
      },
      {
        data: {
          title: "Independence Day",
          color: "#2196F3",
          calendar: "US Holidays"
        },
        schedule: {
          month: [Month.JULY],
          dayOfMonth: [4]
        }
      },
      {
        data: {
          title: "Labor Day",
          color: "#2196F3",
          calendar: "US Holidays"
        },
        schedule: {
          month: [Month.SEPTEMBER],
          dayOfWeek: [Weekday.MONDAY],
          lastWeekspanOfMonth: [0]
        }
      },
      {
        data: {
          title: "Columbus Day",
          color: "#2196F3",
          calendar: "US Holidays"
        },
        schedule: {
          month: [Month.OCTOBER],
          dayOfWeek: [Weekday.MONDAY],
          weekspanOfMonth: [1]
        }
      },
      {
        data: {
          title: "Veterans Day",
          color: "#2196F3",
          calendar: "US Holidays"
        },
        schedule: {
          month: [Month.NOVEMBER],
          dayOfMonth: [11]
        }
      },
      {
        data: {
          title: "Thanksgiving Day",
          color: "#2196F3",
          calendar: "US Holidays"
        },
        schedule: {
          month: [Month.NOVEMBER],
          dayOfWeek: [Weekday.THURSDAY],
          weekspanOfMonth: [3]
        }
      },
      {
        data: {
          title: "Christmas Day",
          color: "#2196F3",
          calendar: "US Holidays"
        },
        schedule: {
          month: [Month.DECEMBER],
          dayOfMonth: [25]
        }
      },
      {
        data: {
          title: "Коррупция",
          type: "Лекция"
        },
        schedule: {
          dayOfWeek: [Weekday.TUESDAY],
          times: [9],
          duration: 90,
          durationUnit: "minutes"
        }
      },
      {
        data: {
          title: "Основы предпринимательства",
          color: "#21F6F5",
          type: "Лекция"
        },
        schedule: {
          dayOfWeek: [Weekday.TUESDAY],
          times: [11],
          duration: 90,
          durationUnit: "minutes"
        }
      },
      {
        data: {
          title: "Анализ данных",
          type: "Лекция",
          color: "#B1009B"
        },
        schedule: {
          dayOfWeek: [Weekday.WEDNESDAY],
          times: [14],
          duration: 90,
          durationUnit: "minutes"
        }
      },
      {
        data: {
          title: "Анализ данных",
          type: "Лекция",
          color: "#B1239B"
        },
        schedule: {
          dayOfWeek: [Weekday.WEDNESDAY],
          times: [16],
          duration: 90,
          durationUnit: "minutes"
        }
      },
      {
        data: {
          title: "Архитектура систем",
          type: "Лекция",
          color: "#FF9200"
        },
        schedule: {
          dayOfWeek: [Weekday.THURSDAY],
          times: [11.3],
          duration: 90,
          durationUnit: "minutes"
        }
      },
      {
        data: {
          title: "Информационная безопасность",
          type: "Лекция",
          color: "#FFA500"
        },
        schedule: {
          dayOfWeek: [Weekday.FRIDAY],
          duration: 90,
          durationUnit: "minutes"
        }
      },
      {
        data: {
          title: "Информационная безопасность",
          type: "Лекция",
          color: "#FFA500"
        },
        schedule: {
          dayOfWeek: [Weekday.FRIDAY],
          duration: 90,
          durationUnit: "minutes"
        }
      },
      {
        data: {
          title: "Физическая культура",
          color: "#1DD300"
        },
        schedule: {
          dayOfWeek: [Weekday.SATURDAY],
          times: [8],
          year: [2020],
          duration: 90,
          durationUnit: "minutes"
        }
      }
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
      const token = localStorage.getItem("token");
      if (token) {
        this.readOnly = false;
      }
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

      state.events = this.defaultEvents;
      let defaults = this.$dayspan.getDefaultEventDetails();
      state.events.forEach(ev => {
        ev.data = dsMerge(ev.data, defaults);
      });
      this.$refs.app.setState(state);
    },
    login() {
      if (this.input.email != "" && this.input.password != "") {
        const result = {
          email: this.input.email,
          password: this.input.password
        };
        HTTP.post(`/login`, {
          body: result
        })
          .then(response => {
            this.readOnly = false;
            this.account = false;
            const token = localStorage.setItem("token", response.data);
            console.log(token);
            console.log(response);
          })
          .catch(e => {
            console.log(e);
          });
      } else {
        console.log("A email and password must be present");
      }
    },
    register() {
      if (
        this.input.email != "" &&
        this.input.password != "" &&
        this.input.firstName != "" &&
        this.input.lastName != "" &&
        this.input.patronymic != "" &&
        this.input.group != ""
      ) {
        const result = {
          email: this.input.email,
          password: this.input.password,
          firstName: this.input.firstName,
          lastName: this.input.lastName,
          patronymic: this.input.patronymic,
          group: this.input.group
        };
        HTTP.post(`/register`, {
          body: result
        })
          .then(response => {
            this.readOnly = false;
            this.account = false;
            const token = localStorage.setItem("token", response.data);
            console.log(response);
          })
          .catch(e => {
            console.log(e);
          });
      } else {
        console.log("A email and password must be present");
      }
    },
    closeLogin() {
      this.readOnly = true;
      this.account = false;
      this.email = "";
      this.password = "";
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
.account {
  border-radius: 10px;
}
.v-btn round .v-btn__content .v-icon {
  color: white;
}
.v-toolbar__content {
  color: #015666;
}
.title .ds-light-forecolor {
  color: #015666;
}
.ds-day-picker .ds-week-header .subtitle[data-v-941fe142] {
  color: black;
}
</style>
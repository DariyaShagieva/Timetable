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
                v-if="token"
                style="color:white"
                round
                block
                id="logout"
                color="#04859D"
                v-on:click="logout()"
              >LogOut</v-btn>
              <v-btn
                style="color:white"
                round
                v-if="!token"
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
                v-if="token"
                id="joinChannel"
                color="#04859D"
                style="color:white"
                @click.stop="joinToChannel()"
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
              <input accept=".xlsx" id="fileUpload" type="file" hidden @change="previewFiles" />
              <v-btn
                style="color:white"
                round
                block
                id="downloadShedule"
                color="#04859D"
                @click="chooseFiles()"
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
import readXlsxFile from "read-excel-file";

export default {
  name: "dayspan",
  data: vm => ({
    storeKey: "dayspanState",
    calendar: Calendar.months(),
    readOnly: true,
    dialog: false,
    token: false,
    USER_TOKEN: "jkadfhkj3hadf",
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
        this.token = true;
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

      HTTP.get(`/`)
        .then(response => {
          state.events = Object.values(response.data);
          let defaults = this.$dayspan.getDefaultEventDetails();
          state.events.forEach(ev => {
            ev.data = dsMerge(ev.data, defaults);
          });
          this.$refs.app.setState(state);
        })
        .catch(e => alert(e));
    },
    joinToChannel() {
      const AuthStr = 'Bearer '.concat(this.USER_TOKEN);
      console.log(AuthStr);
      HTTP.get(`/telegram`)
        .then(response => {
          this.dialog = true;
          console.log(response);
        })
        .catch(e => alert(e));
    },
    chooseFiles(event) {
      document.getElementById("fileUpload").click();
    },
    previewFiles(event) {
      console.log(event.target.files[0]);
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
            this.USER_TOKEN = response.data.data.token;
            localStorage.setItem("token", this.USER_TOKEN);
            this.token = true;
            this.input.email = "";
            this.input.password = "";
          })
          .catch(e => {
            console.log(e);
          });
      } else {
        console.log("A email and password must be present");
      }
    },
    logout() {
      this.token = false;
      this.readOnly = true;
      localStorage.clear();
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
            this.USER_TOKEN = response.data.data.token;
            localStorage.setItem("token", this.USER_TOKEN);
            this.token = true;
            this.input.email = "";
            this.input.password = "";
            this.input.firstName = "";
            this.input.lastName = "";
            this.input.patronymic = "";
            this.input.group = "";
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
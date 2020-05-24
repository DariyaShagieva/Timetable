import axios from 'axios';

export const HTTP = axios.create({
  baseURL: 'https://private-cbd41-timetable7.apiary-mock.com',
})
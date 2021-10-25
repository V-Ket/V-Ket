import axios from 'axios';
import store from './store/index';
let baseURL = '';
if (store.getters.DEVELOPMODE) baseURL = 'http://localhost:8080/';
else baseURL = 'https://j5a105.p.ssafy.io:8080/';

export default axios.create({
  baseURL: baseURL,
  headers: {
    'Content-type': 'application/json',
    'Access-Control-Allow-Origin': '*',
  },
});

import Vue from 'vue';
import Vuex from 'vuex';
import {localStorageVx} from '@/store/local-storage';

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    localStorage: localStorageVx
  }
});

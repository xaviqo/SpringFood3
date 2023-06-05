import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

export const localStorageVx = {
    namespaced: true,
    state: {},
    getters: {
        getAccessToken: () => {
            return localStorage.getItem('access-token')
        },
        getRefreshToken: () => {
            return localStorage.getItem('refresh-token')
        },
        isConnected: () => {
            return localStorage.getItem('access-token') != null;
        }
    },
    actions: {
        setLocalStorage: (context, payload) => {
            localStorage.setItem('access-token',payload.accessToken);
            localStorage.setItem('refresh-token',payload.refreshToken);
            localStorage.setItem('name',payload.name);
            localStorage.setItem('email',payload.email);
            localStorage.setItem('roles',payload.roles);
        }
    }
}
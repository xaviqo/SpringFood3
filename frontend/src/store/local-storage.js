import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

export const localStorageVx = {
    namespaced: true,
    state: {
        connected: false
    },
    getters: {
        getAccessToken: () => {
            return localStorage.getItem('access-token')
        },
        getRefreshToken: () => {
            return localStorage.getItem('refresh-token')
        },
        isConnected: (state) => {
            return state.connected;
        }
    },
    actions: {
        setLocalStorage: (context, payload) => {
            localStorage.setItem('access-token',payload.accessToken);
            localStorage.setItem('refresh-token',payload.refreshToken);
            localStorage.setItem('name',payload.name);
            localStorage.setItem('email',payload.email);
            localStorage.setItem('roles',payload.roles);
            context.state.connected = true;
        },
        checkLocalStorage: (context) => {
            context.state.connected = localStorage.getItem('access-token') != null;
        }
    }
}
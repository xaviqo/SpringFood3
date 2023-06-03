import Vue from 'vue';
import Vuetify from 'vuetify/lib/framework';

Vue.use(Vuetify);
export default new Vuetify({
    theme: {
        themes: {
            light: {
                primary: '#74c3ac',
                secondary: '#f49499',
                success: '#51cc9c',
                info: '#68c2d7',
                danger: '#68c2d7',
                warning: '#fece52',
                light: '#efefee'
            },
        },
    },
});


<template>
  <v-app>
    <v-alert
        prominent
        border="bottom"
        dismissible
        colored-border
        :type="alert.type"
        :color="alert.color"
        elevation="3"
        min-width="350px"
        max-width="400px"
        v-model="alert.show"
        transition="slide-y-transition"
        style="position: absolute; right: 5%; top: 5%; z-index:20001;"
    >
      {{ alert.message }}
    </v-alert>
    <TopBar />
    <v-main class="main-bg">
      <router-view/>
    </v-main>
    <FooterBar />
  </v-app>
</template>

<script>
const ALERT_DEFAULT = {
  show: false,
  color: 'white',
  type: null,
  message: ''
};
import TopBar from "@/components/shared/TopBar.vue";
import FooterBar from "@/components/shared/FooterBar.vue";
import {EventBus} from "@/main";

export default {
  name: 'App',
  components: {FooterBar, TopBar},
  mounted() {
    EventBus.$on('showAlert', model => {
      this.showAlert(model);
    });
  },
  methods: {
    showAlert(model) {
      this.alert = {
        show: true,
        color: model.color,
        type: model.type,
        message: model.message
      }
      setTimeout(() => this.alert = ALERT_DEFAULT, 2200);
    }
  },
  data: () => ({
    alert: ALERT_DEFAULT
  }),
};
</script>
<style>
.main-bg {
  background-image: url("assets/sfbg.png");
  background-position: top;
  background-size: cover;
}
::-webkit-scrollbar {
  width: 6px;
  height: 6px;
}
::-webkit-scrollbar-track {
  border-radius: 10px;
  background: rgba(0,0,0,0.1);
}
::-webkit-scrollbar-thumb{
  border-radius: 10px;
  background: rgba(0,0,0,0.2);
}
::-webkit-scrollbar-thumb:hover{
  background: rgba(0,0,0,0.4);
}
::-webkit-scrollbar-thumb:active{
  background: rgba(0,0,0,.9);
}
</style>

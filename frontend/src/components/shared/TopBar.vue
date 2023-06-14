<template>
  <v-app-bar
      app
      color="primary"
      dark
      elevation="0"
  >
    <v-col cols="2">
      <v-toolbar-title class="overline text-h6" width="100px">
        <v-icon right class="mb-1 mr-4">mdi-leaf</v-icon>
        <span class="font-weight-light">S p r i n g </span>
        <span>F o o d</span>
      </v-toolbar-title>
    </v-col>
    <v-col cols="8">
      <v-tabs
          centered
          dark
          background-color="primary"
          icons-and-text
          class="mb-2"
      >
        <v-tabs-slider></v-tabs-slider>

        <v-tab href="#tab-1">
          Recents
          <v-icon>mdi-phone</v-icon>
        </v-tab>

        <v-tab href="#tab-2">
          Favorites
          <v-icon>mdi-heart</v-icon>
        </v-tab>

        <v-tab href="#tab-3">
          Nearby
          <v-icon>mdi-account-box</v-icon>
        </v-tab>
      </v-tabs>
    </v-col>
    <v-col cols="2">

    </v-col>
  </v-app-bar>
</template>

<script>
import { mapGetters } from 'vuex';

export default {
  name: 'TopBar',
  components: {},
  data: () => ({

  }),
  created() {
    this.$store.dispatch(
        'localStorage/checkLocalStorage'
    );
    if (this.isConnected){
      console.log("si")
    } else {
      console.log("no")
    }
  },
  computed: {
    ...mapGetters('localStorage', ['isConnected'])
  }
}
</script>

<style scoped>

</style>
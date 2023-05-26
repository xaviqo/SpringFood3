<template>
  <v-app>
    <TopBar />
    <v-main>
      <router-view/>
    </v-main>
    <FooterBar />
  </v-app>
</template>

<script>

import TopBar from "@/components/shared/TopBar.vue";
import FooterBar from "@/components/shared/FooterBar.vue";

export default {
  name: 'App',
  components: {FooterBar, TopBar},

  data: () => ({
    //
  }),
};
</script>
<style>
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

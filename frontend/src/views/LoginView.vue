<template>
  <v-container class="fill-height">
    <v-row class="d-flex justify-center align-center">
      <sign-card />
    </v-row>
  </v-container>
</template>
<style>
</style>
<script>
import SignCard from "@/components/login/SignCard.vue";

export default {
  name: 'LoginCard',
  components: {SignCard},
  data: () => ({})
}
</script>
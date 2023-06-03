<template>
  <v-card
      :min-width="cardWidth"
      min-height="550px"
      tile
      class="mb-16"
      elevation="1"
  >
    <v-row no-gutters>
      <v-col cols="8">
        <v-card v-if="signIn" height="550px" flat tile color="light">
          <v-col class="d-flex justify-center">
            <v-card-title class="display-2 font-weight-thin px-6 mt-2">
              <span v-if="getWindowWidth() > 800">
                Sign in with your personal account
              </span>
              <span v-else>
                Sign in
              </span>
            </v-card-title>
          </v-col>
          <v-card-text align="center" class="mt-10">
            <div style="max-width: 480px">
              <v-form ref="loginRef">
                <v-text-field
                    label="Email"
                    prepend-icon="mdi-at"
                    type="text"
                    color="primary"
                    v-model="login.email"
                    :rules="rules.email"
                    :counter="40"
                />

                <v-text-field
                    id="password"
                    label="Password"
                    prepend-icon="mdi-lock-outline"
                    color="primary"
                    v-model="login.password"
                    :rules="rules.password"
                    :counter="40"
                    :type="showInputPassword ? 'text' : 'password'"
                    append-icon="mdi-eye"
                    @click:append="showInputPassword = !showInputPassword"
                />
              </v-form>
            </div>
          </v-card-text>
          <v-card-actions class="d-flex justify-space-around mt-10">
            <v-btn
                large
                elevation="0"
                color="error"
                rounded
                class="ml-16"
            >
              Reset
            </v-btn>
            <v-btn
                large
                elevation="0"
                color="primary"
                rounded
                class="mr-16"
            >
              Sign In
            </v-btn>
          </v-card-actions>
        </v-card>
        <v-card v-else height="550px" flat tile color="light">
          <v-col class="d-flex justify-center">
            <v-card-title class="display-2 font-weight-thin px-6 mt-2">
              <span v-if="getWindowWidth() > 800">
                Create a new SpringFood account
              </span>
            </v-card-title>
          </v-col>
          <v-card-text align="center">
            <v-row :style="{ width: signFormWidth }">
              <v-col cols="6">
                <v-text-field
                    label="Name"
                    prepend-icon="mdi-account"
                    type="text"
                    color="primary"
                    v-model="signup.name"
                    :rules="rules.name"
                    :counter="40"
                />
              </v-col>
              <v-col cols="6">
                <v-text-field
                    label="Phone"
                    prepend-icon="mdi-phone"
                    type="text"
                    color="green"
                    v-model="signup.phone"
                    :rules="rules.phone"
                    :counter="12"
                />
              </v-col>
              <v-col cols="12">
                <v-text-field
                    label="Email"
                    name="Email"
                    prepend-icon="mdi-at"
                    type="text"
                    color="green"
                    v-model="signup.email"
                    :rules="rules.email"
                    :counter="40"
                />
              </v-col>
              <v-col cols="6">
                <v-text-field
                    label="Password"
                    prepend-icon="mdi-lock"
                    :type="showInputPassword ? 'text' : 'password'"
                    color="primary"
                    v-model="signup.password"
                    :rules="rules.password"
                    :counter="40"
                    append-icon="mdi-eye"
                    @click:append="showInputPassword = !showInputPassword"
                />
              </v-col>
              <v-col cols="6">
                <v-text-field
                    label="Confirm Password"
                    prepend-icon="mdi-lock"
                    :type="showInputPassword ? 'text' : 'password'"
                    color="green"
                    v-model="signup.passwordCheck"
                    :rules="rules.password"
                    :counter="40"
                    append-icon="mdi-eye"
                    @click:append="showInputPassword = !showInputPassword"
                />
              </v-col>
            </v-row>
          </v-card-text>
          <v-card-actions class="d-flex justify-space-around mt-10">
            <v-btn
                large
                elevation="0"
                color="error"
                rounded
                class="ml-16"
            >
              Reset
            </v-btn>
            <v-btn
                large
                elevation="0"
                color="primary"
                rounded
                class="mr-16"
            >
              Sign Up
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
      <v-col cols="4">
        <v-card color="primary" height="550px" flat tile>
          <div class="pt-10">
            <v-tabs
                centered
                icons-and-text
                background-color="primary"
            >
              <v-btn
                  x-large
                  dark
                  rounded
                  outlined
                  color="white"
                  @click="signIn = !signIn"
              >
                SIGN {{ signIn ? 'UP' : 'IN' }}
              </v-btn>
            </v-tabs>
          </div>
          <div>
            <v-card-text class="white--text pa-10">
              <h1 class="text-center display-1 mt-5">Welcome!</h1>
              <h4
                  class="text-center">
                Enter your personal details to start your journey with us.
              </h4>
            </v-card-text>
          </div>
        </v-card>
      </v-col>
    </v-row>
  </v-card>
</template>
<script>
import {mixinResponsive} from "@/mixins/responsive";

export default {
  name: 'SignCard',
  mixins: [mixinResponsive],
  data: () => ({
    cardWidth: '1000px',
    signFormWidth: '650px',
    signIn: true,
    showInputPassword: false,
    login: {
      email: '',
      password: ''
    },
    signup: {
      email: '',
      password: '',
      passwordCheck: '',
      name: '',
      phone: ''
    },
    rules: {
      email: [
        v => !!v || 'Email is required',
        v => (v && v.length <= 40) || 'Email must be less than 40 characters',
        v => (v && v.length >= 6) || 'Email must be at least 6 characters',
      ],
      password: [
        v => !!v || 'Password is required',
        v => (v && v.length <= 40) || 'Password must be less than 40 characters',
        v => (v && v.length >= 4) || 'Password must be at least 4 characters',
      ],
      phone: [
        v => !!v || 'Phone is required',
        v => (v && v.length <= 40) || 'Phone must be less than 12 characters',
        v => (v && v.length >= 6) || 'Phone must be at least 6 characters',
      ],
      name: [
        v => !!v || 'Name is required',
        v => (v && v.length <= 40) || 'Name must be less than 40 characters',
        v => (v && v.length >= 3) || 'Name must be at least 3 characters',
      ]
    }
  }),
  mounted() {
    window.addEventListener('resize', this.modifyCardSize);
  },
  methods: {
    modifyCardSize(){
      if (this.getWindowWidth() < 1000) {
        this.cardWidth = `${this.getWindowWidth()-150}px`;
        this.signFormWidth = `${
          ((this.getWindowWidth()-150) / 1.4).toFixed(0)
        }px`;
      } else {
        this.cardWidth = '1000px';
        this.signFormWidth = '650px';
      }
    },
  }
}
</script>

<style>

</style>
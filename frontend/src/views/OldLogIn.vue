<template>
  <v-content>
    <v-container class="fill-height" fluid>
      <v-row align="center" justify="center">
        <v-col cols="12" sm="8" md="8">
          <v-card height="7vh" color="transparent" class="elevation-0">
            <v-alert
                dark
                v-model="alertModel.show"
                :color="alertModel.color"
                :icon="alertModel.icon"
                dismissible
            >{{alertModel.msg}}</v-alert>
            <br/>
          </v-card>
          <v-card class="elevation-12 ">
            <v-window v-model="step" class="bgSpringWhite">
              <v-window-item :value="1">
                <v-row>
                  <v-col cols="12" md="8">
                    <v-card-text class="mt-12 bgSpringWhite pa-5">
                      <h1
                          class="text-center display-2 mb-15"
                          color="green"
                      >Sign in to SpringFood</h1>
                      <h4 class="text-center mt-4">Ensure your email for registration</h4>
                      <v-form ref="loginRef" v-model="loginValidator" @submit.prevent="sendLogin()">
                        <v-text-field
                            label="Email"
                            name="Email"
                            prepend-icon="mdi-at"
                            type="text"
                            color="green"
                            v-model="dataModel.email"
                            :rules="rules.email"
                            :counter="40"
                        />

                        <v-text-field
                            id="password"
                            label="Password"
                            name="password"
                            prepend-icon="mdi-lock-outline"
                            type="password"
                            color="green"
                            v-model="dataModel.password"
                            :rules="rules.password"
                            :counter="40"
                        />
                      </v-form>
                    </v-card-text>
                    <div class="text-center mt-3">
                      <v-btn
                          dark
                          rounded
                          color="green"
                          class="ma-5"
                          :disabled="!loginValidator"
                          @click="sendLogin()"
                      >LOG IN</v-btn>
                    </div>
                  </v-col>
                  <v-col cols="12" md="4" class="bgSpringfood">
                    <div class="text-center" align-self="end">
                      <v-btn rounded outlined dark x-large @click="step++" class="mt-15">SIGN UP</v-btn>
                    </div>
                    <v-card-actions>
                      <v-card-text class="white--text mt-16">
                        <h1 class="text-center display-1 mt-5">Welcome!</h1>
                        <h5
                            class="text-center">
                          Enter your personal details to start your journey with us.
                        </h5>
                      </v-card-text>
                    </v-card-actions>
                  </v-col>
                </v-row>
              </v-window-item>
              <v-window-item :value="2">
                <v-row class="fill-height">
                  <v-col cols="12" md="4" class="bgSpringfood">
                    <div class="text-center" align-self="end">
                      <v-btn rounded outlined dark x-large @click="step++" class="mt-15">LOG IN</v-btn>
                    </div>
                    <v-card-actions>
                      <v-card-text class="white--text mt-16">
                        <h1 class="text-center display-1 mt-5">Already registered?</h1>
                        <h5
                            class="text-center">
                          To keep connected, please login with your personal info.
                        </h5>
                      </v-card-text>
                    </v-card-actions>
                  </v-col>
                  <v-col cols="12" md="8">
                    <v-card-text class="mt-12 bgSpringWhite pa-5">
                      <h1
                          class="text-center display-2 mb-15"
                          color="green"
                      >Create an account</h1>
                      <v-form ref="signupRef" v-model="signupValidator" @submit.prevent="sendSignup()">
                        <v-row>
                          <v-col
                              cols="12"
                              md="6"
                          >
                            <v-text-field
                                v-model="dataModel.name"
                                :rules="rules.name"
                                :counter="40"
                                label="First name"
                                required
                            ></v-text-field>
                          </v-col>

                          <v-col
                              cols="12"
                              md="6"
                          >
                            <v-text-field
                                v-model="dataModel.phone"
                                :rules="rules.phone"
                                :counter="12"
                                label="Phone number"
                                required
                            ></v-text-field>
                          </v-col>
                          <v-col
                              cols="12"
                              md="6"
                          >
                            <v-text-field
                                v-model="dataModel.email"
                                :rules="rules.email"
                                :counter="40"
                                label="Email"
                                required
                            ></v-text-field>
                          </v-col>

                          <v-col
                              cols="12"
                              md="6"
                          >
                            <v-text-field
                                v-model="dataModel.password"
                                :rules="rules.password"
                                :counter="40"
                                label="Password"
                                required
                            ></v-text-field>
                          </v-col>
                        </v-row>
                      </v-form>
                    </v-card-text>
                    <div class="text-center mt-3">
                      <v-btn
                          dark
                          rounded color="green"
                          class="ma-5"
                          :disabled="!signupValidator"
                          @click="sendSignup()"
                      >SIGN UP</v-btn>
                    </div>
                  </v-col>
                </v-row>
              </v-window-item>
            </v-window>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </v-content>
</template>
<style>
.bgSpringfood {
  background-color: #78c2ad;
}
.bgSpringWhite {
  background-color: #fafafa;
}
</style>
<script>
import { EventBus } from '@/main.js';
export default {
  data: () => ({
    step: 1,
    loginValidator: true,
    signupValidator: true,
    rules : {
      name: [
        v => !!v || 'Name is required',
        v => (v && v.length <= 40) || 'Name must be less than 40 characters',
        v => (v && v.length >= 3) || 'Name must be at least 3 characters',
      ],
      email: [
        v => !!v || 'Email is required',
        v => (v && v.length <= 40) || 'Email must be less than 40 characters',
        v => (v && v.length >= 6) || 'Email must be at least 6 characters',
      ],
      phone: [
        v => !!v || 'Phone is required',
        v => (v && v.length <= 40) || 'Phone must be less than 12 characters',
        v => (v && v.length >= 6) || 'Phone must be at least 6 characters',
      ],
      password: [
        v => !!v || 'Password is required',
        v => (v && v.length <= 40) || 'Password must be less than 40 characters',
        v => (v && v.length >= 4) || 'Phone must be at least 4 characters',
        () => this.signup.passwordCheck === this.signup.password || 'Passwords do not match'
      ]
    },
    dataModel: {
      password: "",
      email: "",
      phone: "",
      name: ""
    },
    alertModel: {
      show: false,
      msg: '',
      color: '',
      icon: 'mdi-looks'
    }
  }),
  props: {
    source: String
  },
  methods: {
    sendSignup() {
      if (this.$refs.signupRef.validate()) {
        this.axios
            .post(`/api/account/createClient`,this.dataModel)
            .then((res) => {
              console.log(res.status);
              this.showAlert('ok','Hi '+this.dataModel.name+'! Welcome to SpringFood.')
            })
            .catch((e) => {
              this.showAlert(e.response.status,'Error creating user. Maybe this email is already registered')
              console.log(e);
            });
      }
    },
    sendLogin() {
      if (this.$refs.loginRef.validate()) {
        this.axios
            .post(`/api/account/login`,this.dataModel)
            .then((res) => {
              localStorage.setItem("sf_session",JSON.stringify(res.data));
              EventBus.$emit('refresAfterLogin');
              this.showAlert('ok','Hi '+res.data.name+'! Now, you are connected.')
              setTimeout(() => this.$router.push('/'), 550);

            })
            .catch((e) => {
              this.showAlert(e.response.status,'Email not found or incorrect password')
            });
      }
    },
    showAlert(status, msg){
      this.alertModel.show = true;
      switch (status) {
        case 'ok':
          this.alertModel.color = 'success'
          this.alertModel.icon = 'mdi-looks'
          break;
        default:
          this.alertModel.color = '#ff997b'
          this.alertModel.icon = 'mdi-alert-circle'
          if (msg == null) {
            this.alertModel.msg = 'Unable to connect'
          }
          break;
      }
      this.alertModel.msg = msg
    }
  },
}
</script>
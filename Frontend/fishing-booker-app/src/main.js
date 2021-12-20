import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import WaveUI from 'wave-ui'
import 'wave-ui/dist/wave-ui.css'
import storage from './services/firebase'
import axios from "axios"
import config from "./configuration/config"
const app =createApp(App)
app.use(router)
axios.defaults.headers['Authorization']=config.requestHeader.headers.Authorization
app.config.globalProperties.$axios=axios
app.config.globalProperties.$firebaseStorage=storage
new WaveUI(app, {})

app.mount('#app')

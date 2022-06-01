import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import WaveUI from 'wave-ui'
import 'wave-ui/dist/wave-ui.css'
import storage from './services/firebase'
import axios from "axios"
import config from "./configuration/config"
import OpenLayersMap from 'vue3-openlayers'
import 'vue3-openlayers/dist/vue3-openlayers.css'

const app =createApp(App)
app.use(OpenLayersMap)
app.use(router)
axios.defaults.headers['Authorization']=config.requestHeader.headers.Authorization

app.config.globalProperties.$axios=axios
app.config.globalProperties.$firebaseStorage=storage
new WaveUI(app, {})



app.mount('#app')

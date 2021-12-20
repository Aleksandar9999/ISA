import firebase from 'firebase/app'
import 'firebase/storage'
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyCZwm6K04av--mqeQXfLDgWvY8_Ra9wR20",
  authDomain: "fishingbooker-c95ea.firebaseapp.com",
  projectId: "fishingbooker-c95ea",
  storageBucket: "fishingbooker-c95ea.appspot.com",
  messagingSenderId: "122315298522",
  appId: "1:122315298522:web:2b5142cebea8264f41b7e3",
  measurementId: "G-35D3HRP3SJ"
};

// Initialize Firebase
const app = firebase.initializeApp(firebaseConfig);
//const storage = app.storage
export default app.storage()
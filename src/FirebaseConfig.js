// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAuth, GoogleAuthProvider } from "firebase/auth";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
const firebaseConfig = {
  apiKey: "AIzaSyAmxqhoN5X9YGS692QUUNEO3AbB8iTWid4",
  authDomain: "ecase-tracker.firebaseapp.com",
  projectId: "ecase-tracker",
  storageBucket: "ecase-tracker.appspot.com",
  messagingSenderId: "535113880534",
  appId: "1:535113880534:web:75b6ca551a785bd8194a77"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);

const auth = getAuth(app);

const provider = new GoogleAuthProvider(app);

export { auth, provider };
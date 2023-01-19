const ClientId = "123905091561-t9neu7t8jf4bv9vot4cs7jr4sls0fuke.apps.googleusercontent.com";
const CliendPw = "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";
const REDIRECT_URI = "http://localhost:3000/oauth";
// const GOOGLE_AUTH_URL = `https://accounts.google.com/o/oauth2/auth?client_id=${ClientId}&redirect_uri=${REDIRECT_URI}&response_type=token&scope=https://www.googleapis.com/auth/userinfo.email`;
const GOOGLE_AUTH_URL = `https://accounts.google.com/o/oauth2/v2/auth?response_type=code&client_id=${ClientId}&scope=openid%20profile%20email&redirect_uri=${REDIRECT_URI}`;
// https://www.googleapis.com/auth/userinfo.profile

export { GOOGLE_AUTH_URL };
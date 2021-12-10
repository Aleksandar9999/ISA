export default {
  apiStart: "http://" + location.hostname + ":8080",
  requestHeader: {
    headers: {
      Authorization: localStorage.jwtToken ? "Bearer " + localStorage.jwtToken:'',
    },
  },
};



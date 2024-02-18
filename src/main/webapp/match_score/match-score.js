const urlParams = new URLSearchParams(window.location.search);
const myParam = urlParams.get('uuid');
console.log(myParam)
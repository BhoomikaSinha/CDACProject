const sign_in_btn = document.querySelector("#sign-in-btn");
const sign_up_btn = document.querySelector("#sign-up-btn");
const container = document.querySelector(".container");

sign_up_btn.addEventListener("click", () => {
  container.classList.add("sign-up-mode");
});

sign_in_btn.addEventListener("click", () => {
  container.classList.remove("sign-up-mode");
});

function validateForm() {
let x = document.forms["regForm"]["password"].value;
let y = document.forms["regForm"]["passwordConfirm"].value;
	if (x!=y) {
alert("Passwords doesn't match!!!");
return false; }


};
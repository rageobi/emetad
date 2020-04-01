function validatePassword() {
  debugger;
  var enteredPass = document.getElementById('pwdInput');
  var reEnteredPass = document.getElementById('pwdRptInput');
  var right = "#66cc66";
  var wrong = "#ff6666";
  if (reEnteredPass.value) {
    if (enteredPass.value == reEnteredPass.value) {
      reEnteredPass.style.borderColor = right;
      reEnteredPass.style.borderWidth = "3px";

    } else {
      reEnteredPass.style.borderColor = wrong;
    }
  }
}

function validateEmail() {
  debugger;
  var re = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
  var email = document.getElementById("emailInput");
  if (re.test(email.value)) {
    return true;
  }
  return false;
}

function validateDOB() {
  debugger;
  var enteredDate = document.getElementById("dobInput").value;
  var actualDate = new Date();

  if (new Date(enteredDate).getTime() >= actualDate.getTime()) {
    return false;
  }
  return true;
}

function Resetform() {
  debugger;
  document.getElementById('pwdRptInput').style.removeProperty('border');
  //document.getElementById('emailInput').style.removeProperty('border');
  document.getElementById('dobInput').type = "text";
  document.getElementById('dobInput').placeholder = "Date of Birth";
  document.getElementById("dpInput").value = "";
  $('#signupAlert').hide();
}

function validateFileType() {
  debugger
  var fileName = document.getElementById("dpInput").value;
  var idxDot = fileName.lastIndexOf(".") + 1;
  var extFile = fileName.substr(idxDot, fileName.length).toLowerCase();
  if (extFile == "jpg" || extFile == "jpeg" || extFile == "png") {
    //TO DO
  } else {
    alert("Only jpg/jpeg and png files are allowed!");
    document.getElementById("dpInput").value = "";
  }
}

function validate() {
  document.getElementById("genderInput").value = document.querySelector('input[name = "genderRadio"]:checked').value;
  document.getElementById("iGenderInput").value = document.getElementById("intSelect").value
  var alarm = true;
  var msg = "";
  debugger;
  if (document.loginForm.fnameInput.value == "") {
    document.loginForm.fnameInput.focus();
    alarm = false;
  }
  if (document.loginForm.lnameInput.value == "") {
    document.loginForm.lnameInput.focus();
    alarm = false;
  }
  if (document.loginForm.emailInput.value == "" && !validateEmail()) {
    event.preventDefault();
    document.loginForm.emailInput.focus();
    return false;
  }
  if (!validateEmail()) {
    event.preventDefault();
    $('#signupAlert').fadeIn();
    document.getElementById("emailInput").style.borderColor = "#ff6666";
    document.getElementById("emailInput").style.borderWidth = "3px";
    document.loginForm.emailInput.focus()
    msg += "<li>Please enter a valid email</li>";
    alarm = false;
  }
  if (!validateDOB()) {
    event.preventDefault();
    $('#signupAlert').fadeIn();
    document.getElementById("dobInput").style.borderColor = "#ff6666";
    document.getElementById("dobInput").style.borderWidth = "3px";
    document.loginForm.dobInput.focus()
    msg += "<li>Date of Birth should be lesser than todays date</li>";
    alarm = false;
  }
  if (document.loginForm.pwdInput.value && document.loginForm.pwdInput.value != document.loginForm.pwdRptInput.value) {
    event.preventDefault();
    $('#signupAlert').fadeIn();
    document.loginForm.pwdRptInput.focus();
    msg += "<li>Passwords don't match</li>";
    alarm = false;
  }
  if (!alarm) {
    document.getElementById("signupAlert").innerHTML = msg;
    return false;
  }
  return true;
}

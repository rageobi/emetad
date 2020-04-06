
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
function validate() {
	  debugger;
  document.getElementById("fgenderInput").value = document.getElementById('genderInput').value;
  document.getElementById("iGenderInput").value = document.getElementById("intSelect").value
  var alarm = true;
  var msg = "";

  if (!validateEmail()) {
    event.preventDefault();
    $('#signupAlert').fadeIn();
    document.getElementById("emailInput").style.borderColor = "#ff6666";
    document.getElementById("emailInput").style.borderWidth = "3px";
    document.profileForm.emailInput.focus()
    msg += "<li>Please enter a valid email</li>";
    alarm = false;
  }
  if (!validateDOB()) {
    event.preventDefault();
    $('#signupAlert').fadeIn();
    document.getElementById("dobInput").style.borderColor = "#ff6666";
    document.getElementById("dobInput").style.borderWidth = "3px";
    document.profileForm.dobInput.focus()
    msg += "<li>Date of Birth should be lesser than todays date</li>";
    alarm = false;
  }
  if (document.profileForm.pwdInput.value && document.profileForm.pwdInput.value != document.profileForm.pwdRptInput.value) {
	    event.preventDefault();
	    $('#signupAlert').fadeIn();
	    document.profileForm.pwdRptInput.focus();
	    msg += "<li>Passwords don't match</li>";
	    alarm = false;
	  }
  if (!alarm) {
    document.getElementById("signupAlert").innerHTML = msg;
    return false;
  }
  return true;
}

document.addEventListener("DOMContentLoaded", function () {
    const findPwName = document.getElementById("findPwName");
    const findPwId = document.getElementById("findPwId");
    const findPwEmail = document.getElementById("findPwEmail");

    const authenticationCodeBtn = document.getElementById("authenticationCodeBtn");
    const findPwBtn = document.getElementById("findPwBtn");


    const checkNameBlank = document.getElementById("checkNameBlank");
    const checkIdBlank = document.getElementById("checkIdBlank");
    const checkEmailBlank = document.getElementById("checkEmailBlank");
    const emailError = document.getElementById("emailError");

    checkNameBlank.style.display = "none";
    checkIdBlank.style.display = "none";
    checkEmailBlank.style.display = "none";
    emailError.style.display = "none";

    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

    findPwEmail.addEventListener("input", function() {
        checkEmailBlank.style.display = "none";
        if(!emailRegex.test(findPwEmail.value)) {
            emailError.style.display = "block";
        } else {
            emailError.style.display = "none";
        }
    });

    authenticationCodeBtn.onclick = function(e) {
        let isValid = true;

        checkNameBlank.style.display = "none";
        checkIdBlank.style.display = "none";
        checkEmailBlank.style.display = "none";
        emailError.style.display = "none";

        if(findPwName.value === "") {
            checkNameBlank.style.display = "block";
            isValid = false;
        } else if(findPwId.value === "") {
            checkIdBlank.style.display = "block";
            isValid = false;
        } else if(findPwEmail.value === "") {
            checkEmailBlank.style.display = "block";
            isValid = false;
        } else if(!emailRegex.test(findPwEmail.value)) {
            emailError.style.display = "block";
            isValid = false;
        }

        if(!isValid) e.preventDefault();
    }

});
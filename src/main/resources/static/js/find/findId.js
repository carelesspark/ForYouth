document.addEventListener("DOMContentLoaded", function () {
    const errorInput = document.getElementById("errorMessage");
    if(errorInput && errorInput.value) {
        alert(errorInput.value);
    }

    const findIdName = document.getElementById("findIdName");
    const findIdEmail = document.getElementById("findIdEmail");
    const findIdBtn = document.getElementById("findIdBtn");

    const checkNameBlank = document.getElementById("checkNameBlank");
    const checkEmailBlank = document.getElementById("checkEmailBlank");
    const emailError = document.getElementById("emailError");

    checkNameBlank.style.display = "none";
    checkEmailBlank.style.display = "none";
    emailError.style.display = "none";

    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

    findIdEmail.addEventListener("input", function() {
        checkEmailBlank.style.display = "none";
        if(!emailRegex.test(findIdEmail.value)) {
            emailError.style.display = "block";
        } else {
            emailError.style.display = "none";
        }
    });

    findIdBtn.onclick = function(e) {
        let isValid = true;

        checkNameBlank.style.display = "none";
        checkEmailBlank.style.display = "none";
        emailError.style.display = "none";

        if (findIdName.value === "") {
            checkNameBlank.style.display = "block";
            isValid = false;
        } else if (findIdEmail.value === "") {
            checkEmailBlank.style.display = "block";
            isValid = false;
        } else if (!emailRegex.test(findIdEmail.value)) {
            emailError.style.display = "block";
            isValid = false;
        }

        if (!isValid) e.preventDefault();
    }

});
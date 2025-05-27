document.addEventListener("DOMContentLoaded", function () {
    const joinId = document.getElementById("joinId");
    const joinPw = document.getElementById("joinPw");
    const joinEmail = document.getElementById("joinEmail");
    const joinBtn = document.getElementById("joinBtn");

    const idError = document.getElementById("idError");
    const pwError = document.getElementById("pwError");
    const emailError = document.getElementById("emailError");
    const checkIdBlank = document.getElementById("checkIdBlank");
    const checkPwBlank = document.getElementById("checkPwBlank");
    const checkEmailBlank = document.getElementById("checkEmailBlank");
    const checkClickBtn = document.getElementById("checkClickBtn");

    idError.style.display = "none";
    pwError.style.display = "none";
    emailError.style.display = "none";
    checkIdBlank.style.display = "none";
    checkPwBlank.style.display = "none";
    checkEmailBlank.style.display = "none";

    const idRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,18}$/;
    const pwRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*()\-_=+[\]{};:'",.<>/?\\|])[A-Za-z\d!@#$%^&*()\-_=+[\]{};:'",.<>/?\\|]{8,24}$/;
    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

    joinId.addEventListener("input", function() {
        checkIdBlank.style.display = "none";
        if(!idRegex.test(joinId.value)) {
            idError.style.display = "block";
        } else {
            idError.style.display = "none";
        }
    });

    joinPw.addEventListener("input", function() {
        checkPwBlank.style.display = "none";
        if(!pwRegex.test(joinPw.value)) {
            pwError.style.display = "block";
        } else {
            pwError.style.display = "none";
        }
    });

    joinEmail.addEventListener("input", function() {
        checkEmailBlank.style.display = "none";
        if(!emailRegex.test(joinEmail.value)) {
            emailError.style.display = "block";
        } else {
            emailError.style.display = "none";
        }
    });

    joinBtn.onclick = function(e) {
        let isValid = true;

        idError.style.display = "none";
        pwError.style.display = "none";
        emailError.style.display = "none";
        checkIdBlank.style.display = "none";
        checkPwBlank.style.display = "none";
        checkEmailBlank.style.display = "none";

        if (joinId.value === "") {
            checkIdBlank.style.display = "block";
            isValid = false;
        } else if(!idRegex.test(joinId.value)) {
            idError.style.display = "block";
            isValid = false;
        } else if(joinPw.value === "") {
            checkPwBlank.style.display = "block";
            isValid = false;
        } else if(!pwRegex.test(joinPw.value)) {
            pwError.style.display = "block";
            isValid = false;
        } else if(joinEmail.value === "") {
            checkEmailBlank.style.display = "block";
            isValid = false;
        } else if(!emailRegex.test(joinEmail.value)) {
            emailError.style.display = "block";
            isValid = false;
        } else if(checkClickBtn.value === "false") {
            alert("아이디가 중복되는지 확인해주세요!");
            isValid = false;
        }

        if (!isValid) e.preventDefault();
    }

});
document.addEventListener("DOMContentLoaded", function () {
    const resetPw = document.getElementById("resetPw");
    const verifyPw = document.getElementById("verifyPw");
    const resetPwBtn = document.getElementById("resetPwBtn");

    const checkPwBlank = document.getElementById("checkPwBlank");
    const checkPwVerifyBlank = document.getElementById("checkPwVerifyBlank");
    const pwError = document.getElementById("pwError");
    const comparePw = document.getElementById("comparePw");

    pwError.style.display = "none";
    checkPwBlank.style.display = "none";
    checkPwVerifyBlank.style.display = "none";
    comparePw.style.display = "none";



    const pwRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*()\-_=+[\]{};:'",.<>/?\\|])[A-Za-z\d!@#$%^&*()\-_=+[\]{};:'",.<>/?\\|]{8,24}$/;

    resetPw.addEventListener("input", function() {
        checkPwBlank.style.display = "none";
        if(!pwRegex.test(resetPw.value)) {
            pwError.style.display = "block";
        } else {
            pwError.style.display = "none";
        }
    });

    resetPwBtn.onclick = function(e) {
        let isValid = true;

        pwError.style.display = "none";
        checkPwBlank.style.display = "none";
        checkPwVerifyBlank.style.display = "none";
        comparePw.style.display = "none";

        if(resetPw.value === "") {
            checkPwBlank.style.display = "block";
            isValid = false;
        } else if(verifyPw.value === "") {
            checkPwVerifyBlank.style.display = "block";
            isValid = false;
        } else if(!pwRegex.test(resetPw.value)) {
            pwError.style.display = "block";
            isValid = false;
        } else if(resetPw.value != verifyPw.value) {
            comparePw.style.display = "block";
            isValid = false;
        }

        if (!isValid) e.preventDefault();
    }

});
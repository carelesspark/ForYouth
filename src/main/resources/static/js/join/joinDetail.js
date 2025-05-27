document.addEventListener("DOMContentLoaded", function () {
    const joinId = document.getElementById("joinId");
    const joinPw = document.getElementById("joinPw");
    const joinPhone = document.getElementById("joinPhone");
    const joinName = document.getElementById("joinName");
    const joinBirth = document.getElementById("joinBirth");
    const joinEmail = document.getElementById("joinEmail");
    const joinBtn = document.getElementById("joinBtn");

    const idError = document.getElementById("idError");
    const pwError = document.getElementById("pwError");
    const phoneError = document.getElementById("phoneError");
    const nameError = document.getElementById("nameError");
    const birthError = document.getElementById("birthError");
    const emailError = document.getElementById("emailError");

    const checkIdBlank = document.getElementById("checkIdBlank");
    const checkPwBlank = document.getElementById("checkPwBlank");
    const checkPhoneBlank = document.getElementById("checkPhoneBlank");
    const checkNameBlank = document.getElementById("checkNameBlank");
    const checkBirthBlank = document.getElementById("checkBirthBlank");
    const checkEmailBlank = document.getElementById("checkEmailBlank");


    const checkClickBtn = document.getElementById("checkClickBtn");

    idError.style.display = "none";
    pwError.style.display = "none";
    phoneError.style.display = "none";
    nameError.style.display = "none";
    birthError.style.display = "none";
    emailError.style.display = "none";


    checkIdBlank.style.display = "none";
    checkPwBlank.style.display = "none";
    checkPhoneBlank.style.display = "none";
    checkNameBlank.style.display = "none";
    checkBirthBlank.style.display = "none";
    checkEmailBlank.style.display = "none";

    const idRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,18}$/;
    const pwRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*()\-_=+[\]{};:'",.<>/?\\|])[A-Za-z\d!@#$%^&*()\-_=+[\]{};:'",.<>/?\\|]{8,24}$/;
    const phoneRegex = /^010\d{8}$/;
    const nameRegex = /^[가-힣]{1,20}$/;
    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;



    joinId.addEventListener("input", function() {
        checkIdBlank.style.display = "none";
        checkClickBtn.value = "false";

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

    joinPhone.addEventListener("input", function() {
        checkPhoneBlank.style.display = "none";
        const digitsOnly = joinPhone.value.replace(/[^0-9]/g, '');
        if(!phoneRegex.test(digitsOnly)) {
            phoneError.style.display = "block";
        } else {
            phoneError.style.display = "none";
        }
    });

    joinName.addEventListener("input", function() {
        checkNameBlank.style.display = "none";
        if(!nameRegex.test(joinName.value)) {
            nameError.style.display = "block";
        } else {
            nameError.style.display = "none";
        }
    });

    joinBirth.addEventListener("input", function() {
        checkBirthBlank.style.display = "none";

        if(joinBirth.value.length !== 10) {
            birthError.style.display = "block";
            return;
        } else {
            const [yearStr, monthStr, dayStr] = joinBirth.value.split("-");
            const year = parseInt(yearStr, 10);
            const month = parseInt(monthStr, 10);
            const day = parseInt(dayStr, 10);

            const isValidYear = year >= 1900 && year <= 2025;
            const isValidMonth = month >= 1 && month <= 12;
            const isValidDay = day >= 1 && day <= new Date(year, month, 0).getDate();

            if(isValidYear && isValidMonth && isValidDay) {
                birthError.style.display = "none";
            } else {
                birthError.style.display = "block";
            }
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
        phoneError.style.display = "none";
        nameError.style.display = "none";
        birthError.style.display = "none";
        emailError.style.display = "none";

        checkIdBlank.style.display = "none";
        checkPwBlank.style.display = "none";
        checkPhoneBlank.style.display = "none";
        checkNameBlank.style.display = "none";
        checkBirthBlank.style.display = "none";
        checkEmailBlank.style.display = "none";

        const digitsOnly = joinPhone.value.replace(/[^0-9]/g, '');

        const [yearStr, monthStr, dayStr] = joinBirth.value.split("-");
        const year = parseInt(yearStr, 10);
        const month = parseInt(monthStr, 10);
        const day = parseInt(dayStr, 10);

        const isValidYear = year >= 1900 && year <= 2025;
        const isValidMonth = month >= 1 && month <= 12;
        const isValidDay = day >= 1 && day <= new Date(year, month, 0).getDate();

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
        } else if(joinPhone.value === "") {
            checkPhoneBlank.style.display = "block";
            isValid = false;
        } else if(!phoneRegex.test(digitsOnly)) {
            phoneError.style.display = "block";
            isValid = false;
        } else if(joinName.value === "") {
            checkNameBlank.style.display = "block";
            isValid = false;
        } else if(!nameRegex.test(joinName.value)) {
            nameError.style.display = "block";
            isValid = false;
        } else if(joinBirth.value === "") {
            checkBirthBlank.style.display = "block";
            isValid = false;
        } else if(joinBirth.value.length !== 10) {
            birthError.style.display = "block";
            isValid = false;
        } else if(!(isValidYear && isValidMonth && isValidDay)) {
            birthError.style.display = "block";
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
document.addEventListener("DOMContentLoaded", function () {
    const joinId = document.getElementById("joinId");
    const joinPw = document.getElementById("joinPw");
    const joinEmail = document.getElementById("joinEmail");
    const joinBtn = document.getElementById("joinBtn");

    const idError = document.getElementById("idError");
    const pwError = document.getElementById("pwError");
    const emailError = document.getElementById("emailError");

    idError.style.display = "none";
    pwError.style.display = "none";
    emailError.style.display = "none";

    const idRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,18}$/;
    const pwRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*()\-_=+[\]{};:'",.<>/?\\|])[A-Za-z\d!@#$%^&*()\-_=+[\]{};:'",.<>/?\\|]{8,24}$/;
    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

    joinId.addEventListener("input", function() {
        if(!idRegex.test(joinId.value)) {
            idError.style.display = "block";
        } else {
            idError.style.display = "none";
        }
    });

    joinPw.addEventListener("input", function() {
        if(!pwRegex.test(joinPw.value)) {
            pwError.style.display = "block";
        } else {
            pwError.style.display = "none";
        }
    });

    joinEmail.addEventListener("input", function() {
        if(!emailRegex.test(joinEmail.value)) {
            emailError.style.display = "block";
        } else {
            emailError.style.display = "none";
        }
    });

    joinBtn.onclick = function(e) {
        let isValid = true;

        if (joinId.value === "") {
            alert("아이디를 입력해주세요.");
            isValid = false;
        } else if (!idRegex.test(joinId.value)) {
            alert("아이디 형식이 올바른지 확인해주세요.");
            isValid = false;
        }

        else if (joinPw.value === "") {
            alert("비밀번호를 입력해주세요.");
            isValid = false;
        } else if (!pwRegex.test(joinPw.value)) {
            alert("비밀번호 형식이 올바른지 확인해주세요.");
            isValid = false;
        }

        else if (joinEmail.value === "") {
            alert("이메일을 입력해주세요.");
            isValid = false;
        } else if (!emailRegex.test(joinEmail.value)) {
            alert("이메일 형식이 올바른지 확인해주세요.");
            isValid = false;
        }

        if (!isValid) e.preventDefault();
    }

});
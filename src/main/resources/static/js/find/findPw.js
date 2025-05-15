document.addEventListener("DOMContentLoaded", function () {
    const findPwName = document.getElementById("findPwName");
    const findPwId = document.getElementById("findPwId");
    const findPwEmail = document.getElementById("findPwEmail");
    const findPwBtn = document.getElementById("findPwBtn");

    const emailError = document.getElementById("emailError");

    emailError.style.display = "none";
    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

    findPwEmail.addEventListener("input", function() {
        if(!emailRegex.test(findPwEmail.value)) {
            emailError.style.display = "block";
        } else {
            emailError.style.display = "none";
        }
    });

    findPwBtn.onclick = function(e) {
        let isValid = true;

        if (findPwName.value === "") {
            alert("이름을 입력해주세요.");
            isValid = false;
        } else if (findPwId.value === "") {
            alert("아이디를 입력해주세요.");
            isValid = false;
        } else if (findPwEmail.value === "") {
            alert("이메일을 입력해주세요.");
            isValid = false;
        } else if (!emailRegex.test(findPwEmail.value)) {
            alert("이메일 형식이 올바른지 확인해주세요.");
            isValid = false;
        }

        if (!isValid) e.preventDefault();
    }

});
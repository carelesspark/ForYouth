document.addEventListener("DOMContentLoaded", function () {
    const findIdName = document.getElementById("findIdName");
    const findIdEmail = document.getElementById("findIdEmail");
    const findIdBtn = document.getElementById("findIdBtn");

    const emailError = document.getElementById("emailError");

    emailError.style.display = "none";
    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

    findIdEmail.addEventListener("input", function() {
        if(!emailRegex.test(findIdEmail.value)) {
            emailError.style.display = "block";
        } else {
            emailError.style.display = "none";
        }
    });

    findIdBtn.onclick = function(e) {
        let isValid = true;

        if (findIdName.value === "") {
            alert("이름을 입력해주세요.");
            isValid = false;
        } else if (findIdEmail.value === "") {
            alert("이메일을 입력해주세요.");
            isValid = false;
        } else if (!emailRegex.test(findIdEmail.value)) {
            alert("이메일 형식이 올바른지 확인해주세요.");
            isValid = false;
        }

        if (!isValid) e.preventDefault();
    }

});
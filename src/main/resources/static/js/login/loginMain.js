document.addEventListener("DOMContentLoaded", function () {
    const hasError = document.body.getAttribute('data-error') === "true";
    if(hasError) {
        alert("아이디 또는 비밀번호가 잘못되었습니다.");
        window.location.href = "/login/loginMain";
    }
});
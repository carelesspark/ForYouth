document.addEventListener("DOMContentLoaded", function () {
    const body = document.body;
    const hasError = body.getAttribute('data-error') === "true";
    const errorMessage = body.getAttribute('data-error-message');

    if(hasError && errorMessage) {
        alert(errorMessage);
        window.location.href = "/login/loginMain";
    }
});
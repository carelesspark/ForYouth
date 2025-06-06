document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("authenticationCodeCheckBtn").addEventListener("click", function () {
        const userId = document.getElementById('findPwId').value;
        const authenticationCode = document.getElementById('authenticationCode').value;

        const params = new URLSearchParams({userId, authenticationCode});

        const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
        const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

        const headers = {
            'Content-Type': 'application/x-www-form-urlencoded'
        };
        headers[csrfHeader] = csrfToken;

        fetch('/auth/check-code', {
            method: 'POST',
            headers: headers,
            body: params.toString()
        })
        .then(response => response.text().then(message => {
            if (response.ok) {
                alert(message);
                window.location.href = `/find/resetPw`;
            } else {
                alert(message);
            }
        }))
        .catch(error => {
            alert("요청 중 오류가 발생했습니다: " + error);
            console.error(error);
        });
    });
});
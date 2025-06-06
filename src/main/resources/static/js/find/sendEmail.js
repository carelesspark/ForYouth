document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("authenticationCodeBtn").addEventListener("click", function () {
        const userName = document.getElementById('findPwName').value;
        const userId = document.getElementById('findPwId').value;
        const userEmail = document.getElementById('findPwEmail').value;

        const params = new URLSearchParams({ userName, userId, userEmail });

        const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
        const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

        const headers = {
            'Content-Type': 'application/x-www-form-urlencoded'
        };
        headers[csrfHeader] = csrfToken;

        fetch('/auth/send-code', {
            method: 'POST',
            headers: headers,
            body: params.toString()
        })
        .then(response => response.text().then(message => {
            if (response.ok) {
                alert(message);
                document.getElementById('hiddenInfoOne').style.display = 'block';
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
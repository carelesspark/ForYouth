function marketingBtn() {
    const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

    console.log("js 동작");
    fetch("/api/user/marketing-alarm", {
        method: "POST",
        headers: {
            'Content-Type' : 'application/json',
            [csrfHeader] : csrfToken
        },
        body: JSON.stringify({})
    })
    .then(res => {
        if(res.ok) return res.json();
        throw new Error("요청 실패");
    })
    .then(data => {
        if(data.success) {
            alert("알림 상태가 변경되었습니다!");
            location.reload();
        } else {
            alert("변경 실패 : " + data.message);
        }
    })
    .catch(err => alert("오류 발생 : " + err.message));
}
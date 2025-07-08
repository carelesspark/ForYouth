function saveInterests() {
    const checked = document.querySelectorAll('.check_item:checked');

    const interests = Array.from(checked)
        .map(element => element.closest('.check').querySelector('.checkbox_text strong').textContent)
        .join(', ');

    const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

    console.log(interests);

    fetch('/api/user/interests', {
        method: 'POST',
        headers: {
            'Content-Type' : 'application/json',
            [csrfHeader] : csrfToken
        },
        body: JSON.stringify({interests : interests})
    })
    .then(res => {
            if(res.ok) return res.json();
            throw new Error("요청 실패");
    })
    .then(data => {
        if(data.success) {
            alert("관심 분야가 저장되었습니다!");
            location.reload();
        } else {
            alert("저장 실패 : " + data.message);
        }
    })
    .catch(err => alert("오류 발생 : " + err.message));
}
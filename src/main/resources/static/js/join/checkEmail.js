function checkUserEmail() {
    const userEmail = document.getElementById("joinEmail").value;
    const checkClickBtn = document.getElementById("checkClickBtn2");
    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

    if(!emailRegex.test(userEmail)) {
        alert("올바른 이메일 형식을 입력해주세요.")
        checkClickBtn.value = "false";
        return;
    }

    fetch(`/check-userEmail?userEmail=${encodeURIComponent(userEmail)}`)
        .then(response => response.json())
        .then(data => {
            if(data.exists) {
                alert("이미 존재하는 이메일입니다.");
                checkClickBtn.value = "false";
            } else {
                alert("사용 가능한 이메일입니다.");
                checkClickBtn.value = "true";
            }
        });
}
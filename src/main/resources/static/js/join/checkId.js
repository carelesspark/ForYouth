function checkUserId() {
    const userId = document.getElementById("joinId").value;
    const checkClickBtn = document.getElementById("checkClickBtn");

    fetch(`/check-userid?userId=${encodeURIComponent(userId)}`)
        .then(response => response.json())
        .then(data => {
            if(data.exists) {
                alert("이미 존재하는 아이디입니다.");
                checkClickBtn.value = "false";
            } else {
                alert("사용 가능한 아이디입니다.");
                checkClickBtn.value = "true";
            }
        });
}
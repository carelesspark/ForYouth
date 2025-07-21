function checkUserId() {
    const userId = document.getElementById("joinId").value;
    const checkClickBtn = document.getElementById("checkClickBtn");
    const idRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,18}$/;

    if(!idRegex.test(userId)) {
        alert("6~18자의 영문과 숫자를 조합하여 아이디를 입력해주세요.")
        checkClickBtn.value = "false";
        return;
    }

    fetch(`/check-userId?userId=${encodeURIComponent(userId)}`)
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
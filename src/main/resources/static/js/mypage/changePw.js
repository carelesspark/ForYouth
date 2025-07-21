document.addEventListener("DOMContentLoaded", function () {

    const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

    const openModal2 = document.getElementById("openModal2");
    const closeModal2 = document.getElementById("closeModal2");
    const changePwModal = document.getElementById("changePwModal");

    openModal2.onclick = function(e) {
        e.preventDefault();
        changePwModal.style.display = "block";
    };

    closeModal2.onclick = function() {
        changePwModal.style.display = "none";
    };

    const originalPw = document.getElementById("originalPw");
    const originalPwBlank = document.getElementById("originalPwBlank");

    const newPw = document.getElementById("newPw");
    const newPwError = document.getElementById("newPwError");
    const newPwBlank = document.getElementById("newPwBlank");

    const checkNewPw = document.getElementById("checkNewPw");
    const checkNewPwBlank = document.getElementById("checkNewPwBlank");
    const compareNewPw = document.getElementById("compareNewPw");

    originalPwBlank.style.display = "none";

    newPwError.style.display = "none";
    newPwBlank.style.display = "none";

    checkNewPwBlank.style.display = "none";
    compareNewPw.style.display = "none";


    const pwRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*()\-_=+[\]{};:'",.<>/?\\|])[A-Za-z\d!@#$%^&*()\-_=+[\]{};:'",.<>/?\\|]{8,24}$/;


    originalPw.addEventListener("input", function() {
        originalPwBlank.style.display = "none";
    });

    newPw.addEventListener("input", function() {
        newPwBlank.style.display = "none";
        if(!pwRegex.test(newPw.value)) {
            newPwError.style.display = "block";
        } else {
            newPwError.style.display = "none";
        }
    });

    checkNewPw.addEventListener("input", function() {
        checkNewPwBlank.style.display = "none";
        if(checkNewPw.value == newPw.value) {
            compareNewPw.style.display = "none";
        } else {
            compareNewPw.style.display = "block";
        }
    });

    document.getElementById("changeModal").addEventListener("click", function() {
       const original = originalPw.value.trim();
       const newPassword = newPw.value.trim();
       const confirmPassword = checkNewPw.value.trim();

       let hasError = false;

       if(!original) {
           originalPwBlank.style.display = "block";
           hasError = true;
       }

       if(!newPassword) {
           newPwBlank.style.display = "block";
           hasError = true;
       }

       if(!confirmPassword) {
           checkNewPwBlank.style.display = "block";
           hasError = true;
       }

       if(hasError) return;

       fetch("/api/user/check-password", {
           method: "POST",
           headers: {
               "Content-Type": "application/json",
               [csrfHeader]: csrfToken
           },
           body: JSON.stringify({originalPassword: original})
       })
       .then(res => {
           if(!res.ok) throw new Error("비밀번호 검증 실패");
           return res.json();
       })
       .then(data => {
           if(!data.success) {
               originalPwBlank.textContent = "기존 비밀번호가 일치하지 않습니다.";
               originalPwBlank.style.display = "block";
               return;
           }

           if(!pwRegex.test(newPassword)) {
               newPwError.style.display = "block";
               return;
           }

           if(newPassword !== confirmPassword) {
               compareNewPw.style.display = "block";
               return;
           }

           fetch("/api/user/change-password", {
               method: "POST",
               headers: {
                   "Content-Type": "application/json",
                   [csrfHeader]: csrfToken
               },
               body: JSON.stringify({
                   newPassword: newPassword
               })
           })
           .then(res => {
               if(!res.ok) throw new Error("비밀번호 변경 실패");
               return res.json();
           })
           .then(data => {
               if(data.success) {
                   alert("비밀번호가 성공적으로 변경되었습니다.");
                   location.reload();
               } else {
                   alert("비밀번호 변경 실패: " + data.message);
               }
           })
           .catch(err => {
               alert("오류 발생: " + err.message);
           });
       })
       .catch(err => {
           alert("오류 발생: " + err.message);
       });
    });

});
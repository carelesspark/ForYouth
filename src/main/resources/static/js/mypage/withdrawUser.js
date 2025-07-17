document.addEventListener("DOMContentLoaded", function() {
    const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

    const openModal3 = document.getElementById("openModal3");
    const closeModal3 = document.getElementById("closeModal3");
    const withdrawModal = document.getElementById("withdrawModal");

    openModal3.onclick = function(e) {
        e.preventDefault();
        withdrawModal.style.display = "block";
    };

    closeModal3.onclick = function() {
        withdrawModal.style.display = "none";
    };

    const checkboxes = document.querySelectorAll(".check_item2");

    checkboxes.forEach(cb => {
        cb.addEventListener("change", function() {
            if(this.checked) {
                checkboxes.forEach(other => {
                    if(other !== this) other.checked = false;
                });
            }
        });
    });

    document.getElementById("withdrawUserModal").addEventListener("click", function () {
        const checked = document.querySelector(".check_item2:checked");

        if(!checked) {
            alert("탈퇴 사유를 선택해주세요.");
            return;
        }

        let selectedReason = "";

        document.querySelectorAll(".check_item2").forEach(item => {
            if(item.checked) {
                const labelText = item.closest(".check2").querySelector(".checkbox_text2 strong").textContent.trim();
                selectedReason = labelText;
            }
        });

        fetch("/api/user/withdraw", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                [csrfHeader]: csrfToken
            },
            body: JSON.stringify({reason: selectedReason})
        })
        .then(res => {
            if(res.ok) return res.json();
            throw new Error("요청 실패");
        })
        .then(data => {
            if(data.success) {
                alert("탈퇴가 완료되었습니다. 그동안 ForYouth를 이용해주셔서 감사합니다:)");
                window.location.replace("/");
            } else {
                alert("탈퇴 요청이 이뤄지지 않았습니다. : " + data.message);
            }
        })
        .catch(err => alert("오류 발생 : " + err.message));
    });
});
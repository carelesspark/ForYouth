document.addEventListener("DOMContentLoaded", function () {
    const openBtn = document.getElementById("openModal");
    const closeBtn = document.getElementById("closeModal");
    const modal = document.getElementById("termsModal");

    const openBtn2 = document.getElementById("openModal2");
    const closeBtn2 = document.getElementById("closeModal2");
    const modal2 = document.getElementById("termsModal2");

    openBtn.onclick = function() {
        modal.style.display = "block";
    };

    closeBtn.onclick = function() {
        modal.style.display = "none";
    };

    window.onclick = function(event) {
        if(event.target == modal) {
            modal.style.display = "none";
        } else if(event.target == modal2) {
            modal2.style.display = "none";
        }
    };

    openBtn2.onclick = function() {
        modal2.style.display = "block";
    };

    closeBtn2.onclick = function() {
        modal2.style.display = "none";
    };

    const checkAll = document.getElementById("checkAll");
    const checkItems = document.querySelectorAll(".check_item");

    checkAll.addEventListener("change", function() {
        checkItems.forEach(function(item) {
            item.checked = checkAll.checked;
        });
    });

    checkItems.forEach(function(item) {
        item.addEventListener("change", function() {
            const allChecked = [...checkItems].every((i) => i.checked);
            checkAll.checked = allChecked;
        });
    });

    const nextBtn = document.getElementById("nextBtn");
    const checkItem1 = document.getElementById("checkItem1");
    const checkItem2 = document.getElementById("checkItem2");
    const acceptModal = document.getElementById("acceptModal");
    const acceptModal2 = document.getElementById("acceptModal2");

    acceptModal.onclick = function() {
        checkItem1.checked = true;
        modal.style.display = "none";
    }

    acceptModal2.onclick = function() {
        checkItem2.checked = true;
        modal2.style.display = "none";
    }

    nextBtn.onclick = function() {
        if(!checkItem1.checked) {
            alert("ForYouth 이용에 대한 약관 확인 후 동의해주세요.");
            return;
        }

        if(!checkItem2.checked) {
            alert("개인정보 수집 및 이용에 대한 약관 확인 후 동의해주세요.");
            return;
        }
    }

});
document.addEventListener("DOMContentLoaded", function() {
    const savedInterests = document.querySelector('.userInterests').textContent.trim();

    if(savedInterests){
        const interestList = savedInterests.split(",").map(item => item.trim());

        allChecks = document.querySelectorAll(".check");

        allChecks.forEach(check => {
            const checkbox = check.querySelector(".check_item");
            const labelText = check.querySelector(".checkbox_text strong").textContent.trim();

            if(interestList.includes(labelText)) {
                checkbox.checked = true;
            }
        })
    }
});
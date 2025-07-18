document.addEventListener("DOMContentLoaded", function() {

    const startInput = document.getElementById("startDate");
    const endInput = document.getElementById("endDate");

    if(startInput) {
        startInput.value = getToday();
    }

    if(endInput) {
        endInput.value = getToday();
    }

    const buttons = document.querySelectorAll('.date-buttons button');

    buttons.forEach((btn) => {
        btn.addEventListener("click", () => {
            const months = parseInt(btn.getAttribute("data-months"));
            const today = getToday();
            const past = getPastDate(months);

            buttons.forEach((b) => b.classList.remove("active"));

            btn.classList.add("active");

            if(startInput) {
                startInput.value = past;
            }

            if(endInput) {
                endInput.value = today;
            }
        });
    });

    function getToday() {
        const today = new Date();
        return today.toISOString().split("T")[0];
    }

    function getPastDate(months) {
        const date = new Date();
        date.setMonth(date.getMonth() - months);

        if(date.getDate() !== new Date().getDate()) {
            date.setDate(0);
        }

        return date.toISOString().split("T")[0];
    }
});
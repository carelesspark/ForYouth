document.addEventListener("DOMContentLoaded", function () {
    const monthYear = document.getElementById("month-year");
    const calendarBody = document.getElementById("calendar-body");
    let today = new Date();
    let currentYear = today.getFullYear();
    let currentMonth = today.getMonth();

    function renderCalendar(year, month) {
        calendarBody.innerHTML = "";
        const firstDay = new Date(year, month, 1).getDay();
        const lastDate = new Date(year, month + 1, 0).getDate();

        monthYear.textContent = `${year}년 ${month + 1}월`;

        let row = document.createElement("tr");
        for(let i = 0; i < firstDay; i++) {
            row.appendChild(document.createElement("td"));
        }

        for(let day = 1; day <= lastDate; day++) {
            const dateCell = document.createElement("td");
            dateCell.textContent = day;

            if(day === today.getDate() && year === today.getFullYear() && month === today.getMonth()) {
                dateCell.classList.add("today");
            }

            row.appendChild(dateCell);

            if((firstDay + day) % 7 === 0 || day === lastDate) {
                calendarBody.appendChild(row);
                row = document.createElement("tr");
            }
        }
    }

    renderCalendar(currentYear, currentMonth);

    document.getElementById("prev-month").addEventListener("click", () => {
        currentMonth--;
        if(currentMonth < 0) {
            currentMonth = 11;
            currentYear --;
        }
        renderCalendar(currentYear, currentMonth);
    });

    document.getElementById("next-month").addEventListener("click", () => {
        currentMonth++;
        if(currentMonth > 11) {
            currentMonth = 0;
            currentYear++;
        }
        renderCalendar(currentYear, currentMonth);
    });

    const track = document.querySelector('.logo-track');
    const logos = Array.from(track.children);

    logos.forEach(logo => {
        const clone = logo.cloneNode(true);
        track.appendChild(clone);
    });
});
document.addEventListener("DOMContentLoaded", function () {
    let prevScrollPos = window.pageYOffset;
    const header = document.getElementById("header");

    window.addEventListener("scroll", function () {
        let currentScrollPos = window.pageYOffset;

        if (prevScrollPos > currentScrollPos) {
            header.style.top = "0";
        } else {
            header.style.top = "-80px";
        }
        prevScrollPos = currentScrollPos;
    });
});
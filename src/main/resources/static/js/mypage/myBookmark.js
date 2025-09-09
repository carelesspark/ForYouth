document.addEventListener("DOMContentLoaded", function () {
    const categoryButtons = document.querySelectorAll('.category-btn');
    const fragmentWrapper = document.getElementById('bookmarkFragment');
    let currentCategory = '';

    categoryButtons.forEach(button => {
        button.addEventListener('click', function() {
            categoryButtons.forEach(btn => btn.classList.remove('active'));
            this.classList.add('active');

            currentCategory = this.dataset.category;
            fetchBookmarks(currentCategory, 1);
        });
    });

    fragmentWrapper.addEventListener('click', function(event) {
        if(event.target.classList.contains('page-link')) {
            event.preventDefault();
            const page = event.target.dataset.page;
            fetchBookmarks(currentCategory, page);
        }
    });

    function fetchBookmarks(category, page) {
        const categoryParam = category ? `&category=${category}` : '';

        fetch(`/mypage/bookmark/fragments?page=${page}${categoryParam}`)
        .then(response => response.text())
        .then(html => {
            fragmentWrapper.innerHTML = html;
        })
        .catch(error => console.error('비동기 처리 에러 : ', error));
    }
});
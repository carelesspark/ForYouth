document.addEventListener("DOMContentLoaded", function() {

    const accordionHeaders = document.querySelectorAll('.accordion-header');

    accordionHeaders.forEach(header => {
        header.addEventListener('click', function() {
            const content = this.nextElementSibling;
            const icon = this.querySelector('.icon');

            if(content.style.maxHeight) {
                content.style.maxHeight = null;
                icon.textContent = '+';
            } else {
                content.style.maxHeight = content.scrollHeight + 'px';
                icon.textContent = '-';
            }
        });
    });

    const modal = document.getElementById('qnaModal');
    const openBtn = document.getElementById('qnaBtn');
    const closeBtn = document.querySelector('.close-btn');

    openBtn.addEventListener('click', () => {
        modal.style.display = 'block';
    });

    closeBtn.addEventListener('click', () => {
        modal.style.display = 'none';
    });

    const replyModal = document.getElementById('replyModal');
    const openReplyBtn = document.getElementById('replyCheckBtn');
    const closeReplyBtn = document.querySelector('.close-reply-btn');

    openReplyBtn.addEventListener('click', () => {
        replyModal.style.display = 'block';
    });

    closeReplyBtn.addEventListener('click', () => {
        replyModal.style.display = 'none';
    });

    const replyModalContent = document.getElementById('qnaTableFragment');

    replyModalContent.addEventListener('click', function(event) {
        if(event.target.classList.contains('page-link')) {
            event.preventDefault();
            const page = event.target.dataset.page;
            fetchQnaFragment(page);
        }
    });

    function fetchQnaFragment(page) {
        fetch(`/mypage/qna/fragments?page=${page}`)
            .then(response => response.text())
            .then(html => {
                replyModalContent.innerHTML = html;
            })
            .catch(error => console.error('비동기 처리 에러 : ', error));
    }
});
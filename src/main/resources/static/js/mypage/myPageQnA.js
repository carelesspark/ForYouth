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

    const qnaModal = document.getElementById('qnaModal');
    const replyModal = document.getElementById('replyModal');
    const openQnaBtn = document.getElementById('qnaBtn');
    const openReplyBtn = document.getElementById('replyCheckBtn');
    const closeBtns = document.querySelectorAll('.close-btn');

    openQnaBtn.addEventListener('click', () => qnaModal.style.display = 'block');
    openReplyBtn.addEventListener('click', () => {
        fetchQnaFragment(0);
        replyModal.style.display = 'block';
    });

    closeBtns.forEach(btn => {
        btn.addEventListener('click', () => {
            qnaModal.style.display = 'none';
            replyModal.style.display = 'none';
        });
    });

    const replyModalContent = document.getElementById('qnaTableFragment');

    replyModalContent.addEventListener('click', function(event) {
        const target = event.target;

        if(target.classList.contains('page-link')) {
            event.preventDefault();
            const page = event.target.dataset.page;
            fetchQnaFragment(page);
        }

        if(target.classList.contains('details-btn')) {
            const targetSelector = target.dataset.target;
            const detailRow = document.querySelector(targetSelector);
            if(detailRow) {
                const isActive = detailRow.classList.toggle('active');
                target.textContent = isActive ? '숨기기' : '상세보기';
            }
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
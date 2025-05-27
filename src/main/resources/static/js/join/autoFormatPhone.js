function autoFormatPhone(target) {
    let value = target.value;
    let numbers = value.replace(/[^0-9]/g, '');
    let formatted = '';

    if(numbers.length <= 3) {
        formatted = numbers;
    } else if(numbers.length <= 7) {
        formatted = numbers.slice(0, 3) + '-' + numbers.slice(3);
    } else if(numbers.length <= 11) {
        formatted = numbers.slice(0, 3) + '-' + numbers.slice(3, 7) + '-' + numbers.slice(7);
    } else {
        formatted = numbers.slice(0, 3) + '-' + numbers.slice(3, 7) + '-' + numbers.slice(7, 11);
    }

    let cursor = target.selectionStart;
    let beforeLength = target.value.length;

    target.value = formatted;

    let afterLength = formatted.length;
    cursor += afterLength - beforeLength;

    target.setSelectionRange(cursor, cursor);
}
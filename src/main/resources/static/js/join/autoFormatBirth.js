function autoFormatBirth(target) {
    let value = target.value;
    let numbers = value.replace(/[^0-9]/g, '');
    let formatted = '';

    if(numbers.length <= 4) {
        formatted = numbers;
    } else if(numbers.length <= 6) {
        formatted = numbers.slice(0, 4) + '-' + numbers.slice(4);
    } else {
        formatted = numbers.slice(0, 4) + '-' + numbers.slice(4, 6) + '-' + numbers.slice(6, 8);
    }

    let cursor = target.selectionStart;
    let beforeLength = target.value.length;

    target.value = formatted;

    let afterLength = formatted.length;
    cursor += afterLength - beforeLength;

    target.setSelectionRange(cursor, cursor);
}
window.DOMUtils = function() {
    return {
        getTextValueById(id) {
            return document.getTextValueById(id).textContent;
        }
    };
}
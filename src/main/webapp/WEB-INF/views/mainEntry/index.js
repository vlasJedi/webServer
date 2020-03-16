;(function() {
    const XHR = window.xhr();
    const DOMUtils = window.DOMUtils();
    if (document.readyState === "loading") {
        document.body.addEventListener("DOMContentLoaded", init);
        return;
    }

    (function init() {
        document.getElementById("submitTaskForm").addEventListener("submit", (e) => {
            e.preventDefault();
            const submitTaskFormData = new FormData(document.getElementById("submitTaskForm"));
            XHR.post("/tasks", null, null, submitTaskFormData, () => console.log("request success"), () => console.log("request fail"));
        });
        const registerBtnMenuDropdown = document.getElementById("registerBtnMenuDropdown");
        const submitSignUpForm = document.getElementById("submitSignUpForm");
        const submitLoginForm = document.getElementById("submitLoginForm");
        const signBtnMenuDropdown = document.getElementById("signBtnMenuDropdown");
        const submitSignUpBtn = document.getElementById("submitSignUpBtn");
        const submitLoginBtn = document.getElementById("submitLoginBtn");

        registerBtnMenuDropdown.addEventListener("click", e => {
            setElementVisibility(submitLoginForm, false);
            toggleElementVisibility(submitSignUpForm)
            });
        submitSignUpForm.addEventListener("submit", e => {
            e.preventDefault();
            sendFormData(submitSignUpForm, "/register");
        });
        signBtnMenuDropdown.addEventListener("click", e => {
            setElementVisibility(submitSignUpForm, false);
            toggleElementVisibility(submitLoginForm)
        });
        submitLoginForm.addEventListener("submit", e => {
            e.preventDefault();
            sendFormData(submitSignUpForm, "/login");
        });
        })();
    function setElementVisibility(element, isVisible) {
          element.style.display = isVisible ? "inline-block" : "none";
    }
    function toggleElementVisibility(element) {
          element.style.display = element.style.display === "none" ? "inline-block" : "none";
    }
    function sendFormData(form, urlPath) {
          const submitTaskFormData = new FormData(form);
          XHR.post(urlPath, null, null, submitTaskFormData, () => console.log("request success"), () => console.log("request fail"));
    }
})();
;(function() {
    const XHR = window.xhr();
    const DOMUtils = window.DOMUtils();
    if (document.readyState === "loading") {
        document.body.addEventListener("DOMContentLoaded", init);
        return;
    }

    (function init() {
        /*document.getElementById("submitTaskForm").addEventListener("submit", (e) => {
            e.preventDefault();
            const submitTaskFormData = new FormData(document.getElementById("submitTaskForm"));
            XHR.post("/tasks", null, null, submitTaskFormData, () => console.log("request success"), () => console.log("request fail"));
        });*/
        const registerBtnMenuDropdown = document.getElementById("registerBtnMenuDropdown");
        const submitSignUpForm = document.getElementById("submitSignUpForm");
        const submitLoginForm = document.getElementById("submitLoginForm");
        const signBtnMenuDropdown = document.getElementById("signBtnMenuDropdown");
        const submitSignUpBtn = document.getElementById("submitSignUpBtn");
        const submitLoginBtn = document.getElementById("submitLoginBtn");
        const userLoginPanel = document.getElementById("userLoginPanel");

        registerBtnMenuDropdown.addEventListener("click", e => {
            setElementVisibility(submitLoginForm, false);
            setElementVisibility(submitSignUpForm, true);
            });
        submitSignUpForm.addEventListener("submit", e => {
            e.preventDefault();
            sendFormData(submitSignUpForm, "/users");
        });
        signBtnMenuDropdown.addEventListener("click", e => {
            setElementVisibility(submitSignUpForm, false);
            setElementVisibility(submitLoginForm, true);
        });
        submitLoginForm.addEventListener("submit", e => {
            e.preventDefault();
            sendFormData(submitLoginForm, "/login", applyUserIsLogged);
        });

        function applyUserIsLogged(userInfo) {
            setElementVisibility(userLoginPanel, false);
        }
        function applyUserIsNotLogged(userName) {
            setElementVisibility(userLoginPanel, true);
        }

        })();
    function setElementVisibility(element, isVisible) {
          element.style.display = isVisible ? "inline-block" : "none";
    }
    function toggleElementVisibility(element) {
          element.style.display = element.style.display === "none" ? "inline-block" : "none";
    }
    function sendFormData(form, urlPath, successCall, failCall) {
          const submitTaskFormData = new FormData(form);
          XHR.post(urlPath, null, null, submitTaskFormData, successCall, failCall);
    }
})();
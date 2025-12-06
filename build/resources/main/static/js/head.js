// html 태그에 data-theme가 이미 설정되어 있으면 변경하지 않음
if (!document.documentElement.hasAttribute("data-theme")) {
    const prefersDark = window.matchMedia("(prefers-color-scheme: dark)").matches;
    document.documentElement.setAttribute("data-theme", prefersDark ? "dark" : "light");
}
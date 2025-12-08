document.addEventListener("DOMContentLoaded", () => {
    const menuBtn = document.querySelector(".mobile-menu-btn");
    const panel = document.getElementById("mobileMenu");

    // 메뉴가 없는 페이지(PC 페이지 등)면 실행 안 함
    if (!menuBtn || !panel) return;

    // 오버레이 생성
    const overlay = document.createElement("div");
    overlay.classList.add("mobile-overlay");
    document.body.appendChild(overlay);

    // 메뉴 버튼 클릭 → 슬라이드 열기/닫기
    menuBtn.addEventListener("click", () => {
        panel.classList.toggle("open");
        overlay.classList.toggle("show");
    });

    // 오버레이 클릭 → 닫기
    overlay.addEventListener("click", () => {
        panel.classList.remove("open");
        overlay.classList.remove("show");
    });
});
document.addEventListener("DOMContentLoaded", () => {
    if (!document.documentElement.hasAttribute("data-theme")) {
        const prefersDark = window.matchMedia("(prefers-color-scheme: dark)").matches;
        document.documentElement.setAttribute("data-theme", prefersDark ? "dark" : "light");
    }
});
if (!document.documentElement.hasAttribute("data-theme")) {
    const prefersDark = window.matchMedia("(prefers-color-scheme: dark)").matches;
    document.documentElement.setAttribute("data-theme", prefersDark ? "dark" : "light");
}
package org.example.util;

import jakarta.servlet.http.HttpServletRequest;

public class MobileUtils {

    // 모바일 판단: 운영용은 Host, 개발/테스트는 User-Agent
    public static boolean isMobile(HttpServletRequest req) {
        String host = req.getHeader("Host");
        if (host != null && host.startsWith("m.")) {
            return true; // 운영용 모바일 서브도메인
        }

        // 테스트 환경용 User-Agent 체크
        String ua = req.getHeader("User-Agent");
        if (ua == null) return false;
        return ua.contains("Mobile") || ua.contains("Android") || ua.contains("iPhone");
    }

    // 템플릿 선택
    public static String view(String page, HttpServletRequest req) {
        return isMobile(req) ? "user/m/" + page : "user/" + page;
    }
}
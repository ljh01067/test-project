package org.example.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MobileRedirectFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String userAgent = req.getHeader("User-Agent");
        String host = req.getHeader("Host");
        String uri = req.getRequestURI();

        boolean isMobile = userAgent != null && userAgent.matches(".*(Mobile|Android|iPhone).*");

        // 1️⃣ localhost에서 작동 금지 (개발환경)
        if (host != null && host.contains("localhost")) {
            chain.doFilter(request, response);
            return;
        }

        // 2️⃣ 정적 리소스는 redirect하지 않음
        if (uri.startsWith("/css") || uri.startsWith("/js") || uri.startsWith("/images") || uri.startsWith("/static")) {
            chain.doFilter(request, response);
            return;
        }

        // 3️⃣ 모바일이고, m.으로 시작하지 않으면 m. 도메인으로 이동
        if (isMobile && host != null && !host.startsWith("m.")) {
            String target = "https://m." + host + uri;
            res.sendRedirect(target);
            return;
        }

        chain.doFilter(request, response);
    }
}
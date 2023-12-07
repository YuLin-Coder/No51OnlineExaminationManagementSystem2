package com.wil.interceptor;

import com.wil.entity.Admin;
import com.wil.entity.Student;
import com.wil.entity.Teacher;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by wil on 2018/5/22.
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        String uri = request.getRequestURI();
        if(uri.startsWith("/static/") || "".equals(uri) || "/".equals(uri)
                || "/admin/login".equals(uri) || "/teacher/login".equals(uri)) {
            return true;
        }

        HttpSession session = request.getSession();

        Admin admin = (Admin) session.getAttribute("admin");
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        Student student = (Student) session.getAttribute("student");

        if(teacher != null || admin != null || student != null) {
            return true;
        }

        response.sendRedirect("/");
        return false;
    }
}

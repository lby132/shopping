package com.example.controller;
// Servlet의 기본 골격

import com.example.entity.Customer;
import com.example.repository.ShopMyBatisDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// http://localhost:8081/shopping/main -----> /WEB-INF/views/template.jsp
@WebServlet("/login")
public class LoginController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final String customer_id = req.getParameter("customer_id");
        final String password = req.getParameter("password");
        final Customer customer = new Customer();
        customer.setCustomer_id(customer_id);
        customer.setPassword(password);

        final ShopMyBatisDAO dao = new ShopMyBatisDAO();
        final Customer cusDto = dao.customer_login(customer);
        if (cusDto != null) {
            HttpSession session = req.getSession();
            session.setAttribute("cusDto", cusDto);
        }

        resp.sendRedirect("/shopping/list");

    }
}

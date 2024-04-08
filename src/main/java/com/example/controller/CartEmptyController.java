package com.example.controller;

import com.example.repository.ShopMyBatisDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// http://localhost:8081/shopping/main -----> /WEB-INF/views/template.jsp
@WebServlet("/empty")
public class CartEmptyController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String customer_id = req.getParameter("customer_id");
        final ShopMyBatisDAO dao = new ShopMyBatisDAO();
        final int cnt = dao.cartEmpty(customer_id);
        PrintWriter out = resp.getWriter();
        out.println(cnt);
    }
}

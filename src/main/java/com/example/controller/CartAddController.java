package com.example.controller;
// Servlet의 기본 골격
import com.example.entity.CusPro;
import com.example.repository.ShopMyBatisDAO;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
// http://localhost:8081/shopping/main -----> /WEB-INF/views/template.jsp
@WebServlet("/cartAdd")
public class CartAddController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final String customer_id = req.getParameter("customer_id");
        final int product_number = Integer.parseInt(req.getParameter("product_number"));
        final CusPro dto = new CusPro();
        dto.setCustomer_id(customer_id);
        dto.setProduct_number(product_number);

        final ShopMyBatisDAO dao = new ShopMyBatisDAO();
        final int cnt = dao.cartAdd(dto);

        final PrintWriter out = resp.getWriter();
        out.println(cnt);
    }
}

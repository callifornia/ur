package com.prokopiv.servlet.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestingServlet extends HttpServlet{

	private static final Logger logger = LogManager.getLogger(TestingServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("piupiu");
		req.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(req, resp);
	}
}

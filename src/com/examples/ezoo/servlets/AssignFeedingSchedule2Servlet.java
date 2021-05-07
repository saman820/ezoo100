package com.examples.ezoo.servlets;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.examples.ezoo.model.*;
import com.examples.ezoo.dao.*;

@WebServlet("/assignFeedingSchedule2")
public class AssignFeedingSchedule2Servlet extends HttpServlet{
	private static final long serialVersionUID=1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("assignAFeedingSchedule.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		
		FeedingScheduleDAO daoFeeding = DAOUtilities.getFeedingScheduleDao();

		
		Long feedingScheduleID = Long.parseLong(request.getParameter("feedingScheduleID"));
		Long animalID = Long.parseLong(request.getParameter("animalID"));
		try {
			daoFeeding.assignFeedingSchedule(feedingScheduleID,animalID);
			request.getSession().setAttribute("message", "Feeding Schedule successfully assigned to animal");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("animalCare");
		}catch (Exception e) {
			e.printStackTrace();
			//change the message
			request.getSession().setAttribute("message", "There was a problem assigning the feeding schedule at this time");
			request.getSession().setAttribute("messageClass", "alert-danger");
			
			request.getRequestDispatcher("assignAFeedingSchedule.jsp").forward(request, response);
		}
				
	}
}

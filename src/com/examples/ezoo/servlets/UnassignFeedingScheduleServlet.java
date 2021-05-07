package com.examples.ezoo.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.examples.ezoo.model.*;
import com.examples.ezoo.dao.*;

@WebServlet("/unassignFeedingSchedule")
public class UnassignFeedingScheduleServlet extends HttpServlet {
	private static final Long serialVersionUID = 1L;
	
//	@Override
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//		request.getRequestDispatcher("deleteFeedingSchedule.jsp").forward(request, response);
//	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
		Long animalID = Long.parseLong(request.getParameter("animalID"));
		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDao();
		
		try {
			dao.removeFeedingSchedule(animalID);
			request.getSession().setAttribute("message", "Feeding Schedule successfully removed from the animal");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("animalCare");

		}catch(Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("message", "There was a problem removing feeding schedule from the animal at this time");
			request.getSession().setAttribute("messageClass", "alert-danger");
		}
		
	}
}

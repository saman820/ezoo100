package com.examples.ezoo.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.examples.ezoo.model.*;
import com.examples.ezoo.dao.*;

@WebServlet("/deleteFeedingSchedule")
public class DeleteFeedingScheduleServlet extends HttpServlet {
	private static final Long serialVersionUID = 1L;
	
//	@Override
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//		request.getRequestDispatcher("updateAFeedingSchedule.jsp").forward(request, response);
//	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
		Long feedingScheduleID = Long.parseLong(request.getParameter("feedingScheduleID"));
		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDao();
		
		try {
			dao.deleteFeedingSchedule(feedingScheduleID);
			request.getSession().setAttribute("message", "Feeding Schedule successfully deleted");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("viewFeedingSchedules");

		}
		catch(SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			request.getSession().setAttribute("message", "Feeding schedule cannot be deleted due to violation of foreign key constraint in animals table");
			request.getSession().setAttribute("messageClass", "alert-danger");
			request.getRequestDispatcher("updateAFeedingSchedule.jsp").forward(request, response);
		
		}
//		catch(SQLException e) {
//			e.printStackTrace();
//			request.getSession().setAttribute("message", "Feeding schedule cannot be deleted due to violation of foreign key constraint in animals table");
//			request.getSession().setAttribute("messageClass", "alert-danger");
//			request.getRequestDispatcher("updateAFeedingSchedule.jsp").forward(request, response);
//		
//		}catch(DataIntegrityViolationException  e) {
//			e.printStackTrace();
//			
//			request.getSession().setAttribute("message", "Feeding schedule cannot be deleted due to violation of foreign key constraint in animals table");
//			request.getSession().setAttribute("messageClass", "alert-danger");
//			request.getRequestDispatcher("updateAFeedingSchedule.jsp").forward(request, response);
//		
//		}
		catch(Exception e) {
			e.printStackTrace();
			if(e.getMessage().contains("foreign"))
				request.getSession().setAttribute("message", "Feeding schedule cannot be deleted due to violation of foreign key constraint in animals table");
			else
				request.getSession().setAttribute("message", "There was a problem deleting the feeding schedule at this time");
			request.getSession().setAttribute("messageClass", "alert-danger");
			request.getRequestDispatcher("updateAFeedingSchedule.jsp").forward(request, response);
		}
		
	}
}

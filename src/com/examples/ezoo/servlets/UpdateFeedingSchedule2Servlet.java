package com.examples.ezoo.servlets;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examples.ezoo.dao.DAOUtilities;
import com.examples.ezoo.dao.FeedingScheduleDAO;
import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.FeedingSchedule;

/**
 * Servlet implementation class UpdateFeedingSchedule
 */
@WebServlet("/updateFeedingSchedule2")
public class UpdateFeedingSchedule2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("updateAFeedingSchedule.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//Get Parameters
//		Long id = Long.parseLong(request.getParameter("id"));
		Long feedingScheduleID=  Long.parseLong(request.getParameter("feedingScheduleID"));
		String feedingTime= request.getParameter("feedingTime");
		String recurrence=request.getParameter("recurrence");
		String food=request.getParameter("food");
		String notes= request.getParameter("notes");
		
		FeedingSchedule feedingSchedule = new FeedingSchedule(feedingScheduleID, feedingTime, recurrence, food, notes);
		
		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDao();
		
		
		try {
			dao.updateFeedingSchedule(feedingSchedule);
			request.getSession().setAttribute("message", "Feeding Schedule successfully updated");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("viewFeedingSchedules");
			
//		}
//		catch(SQLIntegrityConstraintViolationException e) {
//			e.printStackTrace();
//			
//			//change the message
//			request.getSession().setAttribute("message", "Id of " + feedingScheduleToSave.getScheduleID() + " is already in use");
//			request.getSession().setAttribute("messageClass", "alert-danger");
//			
//			request.getRequestDispatcher("createFeedingSchedule.jsp").forward(request, response);
//			
			
		}catch (Exception e) {
			e.printStackTrace();
			//change the message
			request.getSession().setAttribute("message", "There was a problem updating the feeding schedule at this time");
			request.getSession().setAttribute("messageClass", "alert-danger");
			
			request.getRequestDispatcher("updateAFeedingSchedule.jsp").forward(request, response);
		}	
	}
}

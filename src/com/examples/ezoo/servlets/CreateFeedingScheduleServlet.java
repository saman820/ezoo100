package com.examples.ezoo.servlets;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.examples.ezoo.model.FeedingSchedule;
import com.examples.ezoo.dao.*;

@WebServlet("/createFeedingSchedule")
public class CreateFeedingScheduleServlet extends HttpServlet{
//	??what is this???
	private static final long serialVersionUID=1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("createAFeedingSchedule.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//Get Parameters
		Long id = Long.parseLong(request.getParameter("id"));
		String feedingTime= request.getParameter("feedingTime");
		String recurrence=request.getParameter("recurrence");
		String food=request.getParameter("food");
		String notes= request.getParameter("notes");
		
		FeedingSchedule feedingScheduleToSave = new FeedingSchedule (id,feedingTime, recurrence, food, notes);
				
		//call DAO method
		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDao();
		
		try {
			dao.addFeedingSchedule(feedingScheduleToSave);
			request.getSession().setAttribute("message", "Feeding Schedule successfully created");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("animalCare");
			
		}catch(SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			
			//change the message
			request.getSession().setAttribute("message", "Id of " + feedingScheduleToSave.getScheduleID() + " is already in use");
			request.getSession().setAttribute("messageClass", "alert-danger");
			
			request.getRequestDispatcher("createFeedingSchedule.jsp").forward(request, response);
			
			
		}catch (Exception e) {
			e.printStackTrace();
			//change the message
			request.getSession().setAttribute("message", "There was a problem creating the feeding schedule at this time");
			request.getSession().setAttribute("messageClass", "alert-danger");
			
			request.getRequestDispatcher("createFeedingSchedule.jsp").forward(request, response);
		}
		
		
		
	}
}

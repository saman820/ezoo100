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

@WebServlet("/assignFeedingSchedule")
public class AssignFeedingScheduleServlet extends HttpServlet{
	private static final long serialVersionUID=1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		FeedingScheduleDAO daoFeeding = DAOUtilities.getFeedingScheduleDao();
		AnimalDAO daoAnimal = DAOUtilities.getAnimalDao();
		List<FeedingSchedule> feedingSchedules = daoFeeding.getAllFeedingSchedules();
		List<Animal> animals = daoAnimal.getAllAnimals();
		request.getSession().setAttribute("feedingSchedules", feedingSchedules);
		request.getSession().setAttribute("animals", animals);
			
		request.getRequestDispatcher("assignAFeedingSchedule.jsp").forward(request, response);	
	}
	

}

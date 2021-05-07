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
@WebServlet("/updateFeedingSchedule")
public class UpdateFeedingScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDao();
		List<FeedingSchedule> feedingSchedules = dao.getAllFeedingSchedules();
		request.getSession().setAttribute("feedingSchedules", feedingSchedules);
		request.getRequestDispatcher("updateAFeedingSchedule.jsp").forward(request, response);

	}
	

}

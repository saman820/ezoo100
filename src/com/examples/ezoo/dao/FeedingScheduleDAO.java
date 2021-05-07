package com.examples.ezoo.dao;
import java.util.List;
import com.examples.ezoo.model.FeedingSchedule;
import com.examples.ezoo.model.Animal;


public interface FeedingScheduleDAO {
	void addFeedingSchedule(FeedingSchedule feedingSchedule) throws Exception;
	void deleteFeedingSchedule(Long feedingScheduleID) throws Exception;
	List<FeedingSchedule> getAllFeedingSchedules();
	FeedingSchedule getFeedingSchedule(Animal animal);
//	void assignFeedingSchedule(FeedingSchedule feedingSchedule, Animal animal) throws Exception;
	void assignFeedingSchedule(Long feedingScheduleID, Long animalID) throws Exception;
	void removeFeedingSchedule(Long animalID) throws Exception;
	void updateFeedingSchedule(FeedingSchedule feedingSchedule)throws Exception;

}

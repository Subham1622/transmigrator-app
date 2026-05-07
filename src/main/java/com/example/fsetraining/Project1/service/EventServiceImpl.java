package com.example.fsetraining.Project1.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.fsetraining.Project1.domain.event.EventId;
import com.example.fsetraining.Project1.domain.event.MeaningFulEvent;

@Service
public class EventServiceImpl implements EventService {

private static List<MeaningFulEvent> events = new ArrayList<MeaningFulEvent>();
	
	static {
		events.add(new MeaningFulEvent(
				new EventId(1L),
				"This is a demo event",
				"Happy new year",
				LocalDateTime.of(2024,4,1,10,0)
				));
        events.add(new MeaningFulEvent(
        		new EventId(2L),
                "Spring Boot Started",
                "Learning Spring fundamentals",
                LocalDateTime.of(2024, 4, 2, 10, 0)
        ));

        events.add(new MeaningFulEvent(
        		new EventId(3L),
                "REST API Understood",
                "Controller and RequestParam",
                LocalDateTime.of(2024, 4, 5, 15, 30)
        ));

        events.add(new MeaningFulEvent(
        		new EventId(3L),
                "Service Layer Added",
                "Business logic separated",
                LocalDateTime.of(2024, 4, 9, 9, 0)
        ));
	}
	
	@Override
	public List<MeaningFulEvent> getEventBetweenDate(LocalDateTime start , LocalDateTime end) {
		
//		List<MeaningFulEvent> eventBetweenDates = new ArrayList<MeaningFulEvent>();
//		for(MeaningFulEvent event: events) {
//			if(!event.getEventDateTime().isBefore(start) && !event.getEventDateTime().isAfter(end) ) {
//				eventBetweenDates.add(event);
//			}
//		}
//		return eventBetweenDates;
		return events.stream().
				filter(e -> !e.getEventDateTime().isBefore(start)).
				filter(e -> !e.getEventDateTime().isAfter(end)).toList();
		
	}
	
	@Override
	public MeaningFulEvent getEventById(EventId eventId) {
		for(MeaningFulEvent event : events) {
			if(event.getId().equals(eventId)) {
				return event;
			}
		}
		throw new RuntimeException("Event not found for id :"+ eventId.getValue());
	}
}

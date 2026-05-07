package com.example.fsetraining.Project1.service;

import java.time.LocalDateTime;
import java.util.List;

import com.example.fsetraining.Project1.domain.event.EventId;
import com.example.fsetraining.Project1.domain.event.MeaningFulEvent;

public interface EventService {
	
	public List<MeaningFulEvent> getEventBetweenDate(LocalDateTime start , LocalDateTime end);
	
	MeaningFulEvent getEventById(EventId eventId);
}

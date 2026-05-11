package com.example.fsetraining.Project1.service;

import java.time.LocalDateTime;
import java.util.List;

import com.example.fsetraining.Project1.domain.event.CategoryType;
import com.example.fsetraining.Project1.domain.event.MeaningFulEvent;
import com.example.fsetraining.Project1.domain.event.SuggestionType;

public interface EventService {
	
	public List<MeaningFulEvent> getEventBetweenDate(LocalDateTime start , LocalDateTime end);
	
	List<MeaningFulEvent> saveEvent(List<MeaningFulEvent> event);
	
	MeaningFulEvent getEventById(Long eventId);
	
	void deleteById(Long eventId);
	
	List<MeaningFulEvent> findEventsBySuggestionType(SuggestionType suggestion);
	
	List<MeaningFulEvent> findEventsByCategoryType(CategoryType category);
	
	List<MeaningFulEvent> findEventsbyFilter(SuggestionType suggestionType , CategoryType category);
}

package com.example.fsetraining.Project1.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.fsetraining.Project1.domain.event.CategoryType;
import com.example.fsetraining.Project1.domain.event.MeaningFulEvent;
import com.example.fsetraining.Project1.domain.event.SuggestionType;
import com.example.fsetraining.Project1.repository.EventRepository;

@Service
public class EventServiceImpl implements EventService {
	
	EventRepository eventRepository;
	
	Sort sort = Sort.by(Sort.Direction.DESC, "eventDateTime");
	
	public EventServiceImpl(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}

	@Override
	public List<MeaningFulEvent> getEventBetweenDate(LocalDateTime start , LocalDateTime end) {

		return eventRepository.findByEventDateTimeBetween(start , end, sort);		
	}
	
	@Override
	public MeaningFulEvent getEventById(Long eventId) {
		return eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found for id: "+eventId));
	}
	
	@Override
	public void deleteById(Long eventId) {
		eventRepository.deleteById(eventId);
	}

	@Override
	public List<MeaningFulEvent> saveEvent(List<MeaningFulEvent> event) {
		return eventRepository.saveAll(event);
	}

	@Override
	public List<MeaningFulEvent> findEventsBySuggestionType(SuggestionType suggestion) {
		return eventRepository.findBySuggestion(suggestion, sort);
	}

	@Override
	public List<MeaningFulEvent> findEventsByCategoryType(CategoryType category) {
		return eventRepository.findByCategory(category, sort);
	}

	@Override
	public List<MeaningFulEvent> findEventsbyFilter(SuggestionType suggestion, CategoryType category) {
		return eventRepository.findBySuggestionAndCategory(suggestion, category, sort);
	}
}

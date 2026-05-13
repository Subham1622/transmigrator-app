package com.example.fsetraining.Project1.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.fsetraining.Project1.domain.event.CategoryType;
import com.example.fsetraining.Project1.domain.event.MeaningFulEvent;
import com.example.fsetraining.Project1.domain.event.SuggestionType;
import com.example.fsetraining.Project1.repository.EventRepository;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@Service
public class EventServiceImpl implements EventService {
	
	private EventRepository eventRepository;
	private Counter eventFetchCounter;
	
	Sort sort = Sort.by(Sort.Direction.DESC, "eventDateTime");
	
	public EventServiceImpl(EventRepository eventRepository, MeterRegistry meterRegistry) {
		this.eventRepository = eventRepository;
		this.eventFetchCounter = meterRegistry.counter("events.fetch.count");
	}

	@Override
	public List<MeaningFulEvent> getEventBetweenDate(LocalDateTime start , LocalDateTime end) {
		
		eventFetchCounter.increment();
		return eventRepository.findByEventDateTimeBetween(start , end, sort);		
	}
	
	@Override
	public MeaningFulEvent getEventById(Long eventId) {
		eventFetchCounter.increment();
		return eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found for id: "+eventId));
	}
	
	@Override
	public void deleteById(Long eventId) {
		eventFetchCounter.increment();
		eventRepository.deleteById(eventId);
	}

	@Override
	public List<MeaningFulEvent> saveEvent(List<MeaningFulEvent> event) {
		eventFetchCounter.increment();
		return eventRepository.saveAll(event);
	}

	@Override
	public List<MeaningFulEvent> findEventsBySuggestionType(SuggestionType suggestion) {
		eventFetchCounter.increment();
		return eventRepository.findBySuggestion(suggestion, sort);
	}

	@Override
	public List<MeaningFulEvent> findEventsByCategoryType(CategoryType category) {
		eventFetchCounter.increment();
		return eventRepository.findByCategory(category, sort);
	}

	@Override
	public List<MeaningFulEvent> findEventsbyFilter(SuggestionType suggestion, CategoryType category) {
		eventFetchCounter.increment();
		return eventRepository.findBySuggestionAndCategory(suggestion, category, sort);
	}
}

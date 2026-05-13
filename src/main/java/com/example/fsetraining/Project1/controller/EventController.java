package com.example.fsetraining.Project1.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.fsetraining.Project1.domain.event.CategoryType;
import com.example.fsetraining.Project1.domain.event.MeaningFulEvent;
import com.example.fsetraining.Project1.domain.event.SuggestionType;
import com.example.fsetraining.Project1.service.EventService;

@RestController
@RequestMapping("/events")
public class EventController {
	
	
	private final EventService eventService;
	
	public EventController(EventService  eventService) {
		// TODO Auto-generated constructor stub
		this.eventService = eventService;
	}
	
	@GetMapping
	public ResponseEntity<List<MeaningFulEvent>> getEventBetweenDate(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
			LocalDate fromDate , 
			@RequestParam(required = false)
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
			LocalDate toDate) {
		
		Logger.getLogger("getEventBetweenDate");
		
		if(toDate == null) {
			toDate = LocalDate.now();
		}
		
		
		LocalDateTime startDateTime = fromDate.atStartOfDay();
		LocalDateTime endDateTime = toDate.atTime(LocalTime.MAX);
		
		if(startDateTime.isAfter(endDateTime)) {
			throw new RuntimeException("FromDate must be before ToDate.");
		}
        
		List<MeaningFulEvent> events =  eventService.getEventBetweenDate(startDateTime, endDateTime);

		return ResponseEntity.ok(events);
	}
	
	@PostMapping("/saveEvent")
	public ResponseEntity<List<MeaningFulEvent>> saveEvent(@RequestBody List<MeaningFulEvent> requestEvent) {
		List<MeaningFulEvent> saveEvent = eventService.saveEvent(requestEvent);
		return ResponseEntity.ok(saveEvent);
	}
	
	@GetMapping("/{eventId}")
	public ResponseEntity<MeaningFulEvent> getEventById(@PathVariable Long eventId) {		
		MeaningFulEvent event = eventService.getEventById(eventId);
		return ResponseEntity.ok(event);
	}
	
	@DeleteMapping("/delete/{eventId}")
	public ResponseEntity<Void> deleteEventById(@PathVariable Long eventId) {
		eventService.deleteById(eventId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/by-suggestion/{suggestion}")
	public ResponseEntity<List<MeaningFulEvent>> findEventBySuggestionType(@PathVariable SuggestionType suggestion) {
		List<MeaningFulEvent> events = eventService.findEventsBySuggestionType(suggestion);
		return ResponseEntity.ok(events);
	}
	
	@GetMapping("/by-category/{category}")
	public ResponseEntity<List<MeaningFulEvent>> findEventBySuggestionType(@PathVariable CategoryType category) {
		List<MeaningFulEvent> events = eventService.findEventsByCategoryType(category);
		return ResponseEntity.ok(events);
	}
	
	@GetMapping("/filter/{suggestion}/{category}")
	public ResponseEntity<List<MeaningFulEvent>> findEventsbyFilter(@PathVariable SuggestionType suggestion,
			@PathVariable CategoryType category) {
		List<MeaningFulEvent> events = eventService.findEventsbyFilter(suggestion, category);
		return ResponseEntity.ok(events);
	}
}


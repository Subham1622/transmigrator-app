package com.example.fsetraining.Project1.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.fsetraining.Project1.domain.event.EventId;
import com.example.fsetraining.Project1.domain.event.MeaningFulEvent;
import com.example.fsetraining.Project1.service.EventService;

@RestController
public class EventController {
	
	
	private final EventService eventService;
	
	public EventController(EventService  eventService) {
		// TODO Auto-generated constructor stub
		this.eventService = eventService;
	}
	
	@GetMapping("/events")
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
			return ResponseEntity.badRequest().build();
		}
        
		List<MeaningFulEvent> events =  eventService.getEventBetweenDate(startDateTime, endDateTime);

		return ResponseEntity.ok(events);
	}
	
	@GetMapping("/event")
	public ResponseEntity<MeaningFulEvent> getEventById(@RequestParam Long id) {
		EventId eventId = new EventId(id);
		MeaningFulEvent event = eventService.getEventById(eventId);
		if(event == null ) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(event);
	}
}


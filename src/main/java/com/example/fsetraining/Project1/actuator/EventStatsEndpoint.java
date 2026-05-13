package com.example.fsetraining.Project1.actuator;

import java.util.Map;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import com.example.fsetraining.Project1.repository.EventRepository;

@Component
@Endpoint(id="event-stats")
public class EventStatsEndpoint {

	private final EventRepository eventRepository;
	
	public EventStatsEndpoint(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}
	
	@ReadOperation
	public Map<String, Object> stats() {
		return Map.of("Total events", eventRepository.count());
	}
}

package com.example.fsetraining.Project1.actuator;

import java.time.LocalDateTime;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import com.example.fsetraining.Project1.service.EventService;

@Component
public class EventServiceHealthIndicator implements HealthIndicator {
	
	private final EventService eventService;
	public EventServiceHealthIndicator(EventService eventService) {
		this.eventService = eventService;
	}
	@Override
	public Health health() {
		try {
			eventService.getEventBetweenDate(
					LocalDateTime.now().minusDays(1),
					LocalDateTime.now()
					);
			return Health.up().withDetail("EventService", "Working fine").build();
		} catch (Exception e) {
			return Health.down().withDetail("Error", e.getMessage()).build();
		}
	}
	

}

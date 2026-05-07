package com.example.fsetraining.Project1.domain.event;

import java.time.LocalDateTime;
import java.util.Objects;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MeaningFulEvent {

	private EventId id;
	private String title;
	private String description;
	private LocalDateTime eventDateTime;
	

	public MeaningFulEvent(EventId id,
	                           String title,
	                           String description,
	                           LocalDateTime eventDateTime) {
	
	        if (title == null || title.isBlank()) {
	            throw new IllegalArgumentException("Event title cannot be empty");
	        }
	
	        this.id = id;
	        this.title = title;
	        this.description = description;
	        this.eventDateTime = eventDateTime;
	    }
	
	public EventId getId() {
		return id;
	}
		
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(!(o instanceof MeaningFulEvent)) return false;
		MeaningFulEvent that = (MeaningFulEvent) o;
		return Objects.equals(id, that.id);		
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
}

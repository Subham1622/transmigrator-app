package com.example.fsetraining.Project1.domain.event;

import java.util.Objects;

public class EventId {

	private Long value;
	
	public EventId(Long value) {
		if (value == null || value <=0 ) {
			throw new IllegalArgumentException("Event Id must be positive"); 
		}
		this.value = value;
		
	}
	
	public Long getValue() {
		return value;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(!(o instanceof EventId)) return false;
		EventId that = (EventId) o;
		return Objects.equals(value, that.value);
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(value);
	}
}

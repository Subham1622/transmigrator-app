package com.example.fsetraining.Project1.domain.event;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="events")
public class MeaningFulEvent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	private String description;
	private LocalDateTime eventDateTime;
	
	@Enumerated(EnumType.STRING)
	private SuggestionType suggestion;
	
	@Enumerated(EnumType.STRING)
	private CategoryType category;
	
	public MeaningFulEvent() {}

	public MeaningFulEvent(Long id,
	                           String title,
	                           String description,
	                           LocalDateTime eventDateTime,
	                           SuggestionType suggestion,
	                           CategoryType category) {
	
	        if (title == null || title.isBlank()) {
	            throw new IllegalArgumentException("Event title cannot be empty");
	        }
	
	        this.id = id;
	        this.title = title;
	        this.description = description;
	        this.eventDateTime = eventDateTime;
	        this.suggestion = suggestion;
	        this.category = category;
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

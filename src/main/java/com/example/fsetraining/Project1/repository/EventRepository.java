package com.example.fsetraining.Project1.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fsetraining.Project1.domain.event.CategoryType;
import com.example.fsetraining.Project1.domain.event.MeaningFulEvent;
import com.example.fsetraining.Project1.domain.event.SuggestionType;

public interface EventRepository extends JpaRepository<MeaningFulEvent, Long> {

	List<MeaningFulEvent> findByEventDateTimeBetween(LocalDateTime start, LocalDateTime end, Sort sort);
	
	List<MeaningFulEvent> findBySuggestion(SuggestionType suggestion, Sort sort);
	
	List<MeaningFulEvent> findByCategory(CategoryType category, Sort sort);
	
	List<MeaningFulEvent> findBySuggestionAndCategory(SuggestionType suggestion, CategoryType category, Sort sort);
}

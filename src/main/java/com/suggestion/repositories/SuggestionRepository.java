package com.suggestion.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.suggestion.models.Suggestion;

@Repository
public interface SuggestionRepository extends PagingAndSortingRepository<Suggestion, Long> {

}

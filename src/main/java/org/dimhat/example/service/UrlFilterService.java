package org.dimhat.example.service;

import java.util.List;

import org.dimhat.example.entity.UrlFilter;

public interface UrlFilterService {

	UrlFilter createUrlFilter(UrlFilter urlFilter);
	
	void updateUrlFilter(UrlFilter urlFilter);
	
	void deleteUrlFilter(Long urlFilterId);
	
	UrlFilter findOne(Long urlFilterId);
	
	List<UrlFilter> findAll();
}

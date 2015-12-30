package org.dimhat.example.dao;

import java.util.List;

import org.dimhat.example.entity.UrlFilter;

public interface UrlFilterDao {

	UrlFilter save(UrlFilter urlFilter);
	
	void update(UrlFilter urlFilter);
	
	void delete(Long urlFilterId);
	
	UrlFilter findOne(Long urlFilterId);
	
	List<UrlFilter> findAll();
}

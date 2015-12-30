package org.dimhat.example.service.impl;

import java.util.List;

import org.dimhat.example.dao.UrlFilterDao;
import org.dimhat.example.entity.UrlFilter;
import org.dimhat.example.service.UrlFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UrlFilterServiceImpl implements UrlFilterService {

	@Autowired
	private UrlFilterDao urlFilterDao;
	
	@Override
	public UrlFilter createUrlFilter(UrlFilter urlFilter) {
		return urlFilterDao.save(urlFilter);
	}

	@Override
	public void updateUrlFilter(UrlFilter urlFilter) {
		urlFilterDao.update(urlFilter);
	}

	@Override
	public void deleteUrlFilter(Long urlFilterId) {
		urlFilterDao.delete(urlFilterId);
	}

	@Override
	public UrlFilter findOne(Long urlFilterId) {
		return urlFilterDao.findOne(urlFilterId);
	}

	@Override
	public List<UrlFilter> findAll() {
		return urlFilterDao.findAll();
	}

}

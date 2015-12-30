package org.dimhat.example.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.dimhat.example.dao.UrlFilterDao;
import org.dimhat.example.entity.UrlFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UrlFilterDaoImpl implements UrlFilterDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public UrlFilter save(final UrlFilter urlFilter) {
		final String sql="insert into sys_url_filter(name,url,roles,permissions) values(?,?,?,?)";
		
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement psst = con.prepareStatement(sql,new String[]{"id"});
				int idx=1;
				psst.setString(idx++, urlFilter.getName());
				psst.setString(idx++, urlFilter.getUrl());
				psst.setString(idx++, urlFilter.getRoles());
				psst.setString(idx++, urlFilter.getPermissions());
				return psst;
			}
		},keyHolder);
		urlFilter.setId(keyHolder.getKey().longValue());
		return urlFilter;
	}

	@Override
	public void update(UrlFilter urlFilter) {
		String sql="update sys_url_filter set name=?,url=?,roles=?,permission=? where id=?";
		jdbcTemplate.update(sql,urlFilter.getName(),urlFilter.getUrl(),urlFilter.getRoles(),urlFilter.getPermissions(),urlFilter.getId());
	}

	@Override
	public void delete(Long urlFilterId) {
		String sql="delete from sys_url_filter where id=?";
		jdbcTemplate.update(sql,urlFilterId);
	}

	@Override
	public UrlFilter findOne(Long urlFilterId) {
		String sql="select * from sys_url_filter where id=?";
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<UrlFilter>(),urlFilterId);
	}

	@Override
	public List<UrlFilter> findAll() {
		String sql="select * from sys_url_filter";
		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<UrlFilter>());
	}

}

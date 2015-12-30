package org.dimhat.example.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.dimhat.example.dao.ResourceDao;
import org.dimhat.example.entity.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 权限DAO实现类
 * @author dimhat
 * @date 2015年12月29日 下午11:15:29
 * @version 1.0
 */
@Repository
@Transactional
public class ResourceDaoImpl implements ResourceDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /** 
     * @see org.dimhat.example.dao.ResourceDao#save(org.dimhat.example.entity.Resource)
     */
    @Override
    public Resource save(final Resource resource) {
        final String sql = "insert into sys_resource(resource, description, available, type, parent_id, parent_ids, url) values(?,?,?,?,?,?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement psst = con.prepareStatement(sql, new String[] { "id" });
                int idx = 1;
                psst.setString(idx++, resource.getPermission());
                psst.setString(idx++, resource.getDescription());
                psst.setBoolean(idx++, resource.getAvailable());
                psst.setString(idx++, resource.getType().name());
                psst.setLong(idx++, resource.getParentId());
                psst.setString(idx++, resource.getParentIds());
                psst.setString(idx++, resource.getUrl());
                return psst;
            }
        }, keyHolder);
        resource.setId(keyHolder.getKey().longValue());
        return resource;
    }

    /** 
     * @see org.dimhat.example.dao.ResourceDao#update(org.dimhat.example.entity.Resource)
     */
    @Override
    public void update(Resource resource) {
        String sql = "update sys_resource set resource=?, description=?, available=?, type=?, parent_id=?, parent_ids=?, url=? where id=?";
        jdbcTemplate.update(sql, resource.getPermission(), resource.getDescription(), resource.getAvailable(),
            resource.getId(), resource.getType().name(), resource.getParentId(), resource.getParentIds(),
            resource.getUrl());
    }

    /** 
     * @see org.dimhat.example.dao.ResourceDao#delete(java.lang.Long)
     */
    @Override
    public void delete(Long resourceId) {
        Resource resource = findOne(resourceId);
        String deleteSelfSql = "delete from sys_resource where id=?";
        jdbcTemplate.update(deleteSelfSql, resourceId);
        //删除所有子路径
        String deleteDescendantsSql = "delete from sys_resource where parent_ids like ?";
        jdbcTemplate.update(deleteDescendantsSql, resource.makeSelfAsParentIds() + "%");
    }

    @Override
    public Resource findOne(Long resourceId) {
        String sql = "select * from sys_resource where id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Resource>(), resourceId);
    }

    /** 
     * @see org.dimhat.example.dao.ResourceDao#findAll()
     */
    @Override
    public List<Resource> findAll() {
        String sql = "select * from sys_resource order by CONCAT(parent_ids,id) asc";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(Resource.class));
    }

}

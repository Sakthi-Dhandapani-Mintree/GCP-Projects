package com.resoursetechmapping.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.resoursetechmapping.bean.ResourceBean;
import com.resoursetechmapping.dao.ResourceBeanDao;

class ResourceMapper implements RowMapper<ResourceBean> {

	public ResourceBean mapRow(ResultSet rs, int arg1) throws SQLException {
		ResourceBean resource = new ResourceBean();
		resource.setId(rs.getLong("id"));
		resource.setFirstName(rs.getString("firstName"));
		resource.setMiddleName(rs.getString("middleName"));
		resource.setLastName(rs.getString("lastName"));
		resource.setListOfTechWorkedOn(rs.getString("listOfTechWorkedOn"));
		resource.setCertifications(rs.getString("certifications"));
		resource.setProjects(rs.getString("projects"));
		resource.setApplicationWorkLoadTypes(rs.getString("applicationWorkLoadTypes"));
		return resource;
	}
}

@Repository
public class ResourceBeanDaoImpl implements ResourceBeanDao {
	private final Logger logger = Logger.getLogger(ResourceBeanDaoImpl.class);
	private JdbcTemplate jdbcTemplate;

	public ResourceBeanDaoImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public boolean addResourece(ResourceBean resource) {
		boolean result = true;
		logger.info("Resource Bean details are :" + resource.toString());
		String addResourece = "insert into resource values(?,?,?,?,?,?,?,?)";
		logger.info("sql get query is =>" +addResourece);
		try {
			jdbcTemplate.update(addResourece,
					new Object[] { resource.getId(), resource.getFirstName(), resource.getMiddleName(),
							resource.getLastName(), resource.getListOfTechWorkedOn(), resource.getCertifications(),
							resource.getProjects(), resource.getApplicationWorkLoadTypes() });
			logger.info("Resource Bean object saved into the database successfully !!!");
			return result;
			 
		} catch (Exception ex) {
			logger.info("Exception has occured :" + ex.getMessage());
			logger.info("Data not stored");
			 return false;
		}

	}

	public ResourceBean getResource(int id) {
		logger.info("Entered Resource Mid =>" + id);
		ResourceBean resourceResult = null;
		String sql = "SELECT * FROM resource WHERE id= ?";
		logger.info("sql get query is =>" +sql);
		try {
			resourceResult = jdbcTemplate.queryForObject(sql, new ResourceMapper(), id);
			logger.info("Resource details retrived from DB =>" + resourceResult.toString());
		} catch (Exception ex) {
			logger.info("While Retriving the resource details error occured :" + ex.getMessage());
		}
		return resourceResult;
	}

	public int updateResource(ResourceBean resource) {
		String sql = "update resource set firstName='" + resource.getFirstName() + "',middleName='"
				+ resource.getMiddleName() + "',lastName=" + resource.getLastName() + "',listOfTechWorkedOn='"
				+ resource.getListOfTechWorkedOn() + "',certifications='" + resource.getCertifications()
				+ "',projects='" + resource.getProjects() + "',applicationWorkLoadTypes='"
				+ resource.getApplicationWorkLoadTypes() + ", where id=" + resource.getId() + ",";
		return jdbcTemplate.update(sql);

	}

	public int removeResource(int id) {
		String sql="delete from resource where id="+id+"";  
	    return jdbcTemplate.update(sql);  
		
	}
}

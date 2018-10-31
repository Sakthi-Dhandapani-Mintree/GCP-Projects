package com.resoursetechmapping.service;

import com.resoursetechmapping.bean.ResourceBean;

public interface ResourceBeanService {
	
	public boolean addResourece(ResourceBean resource);
	public int updateResource(ResourceBean resource);
	public ResourceBean getResource(int id);
	public int removeResource(int id);
}

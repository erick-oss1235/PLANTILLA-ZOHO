package com.example.plantilla.app.models.dao;

import java.util.List;
import java.util.Map;

import com.zoho.crm.library.exception.ZCRMException;

public interface IDemoDao {

	public List<Map<String,Object>> listRecords(String nameModule);

	public Map<String,Object> getRecordById(String nameModule,Long id) throws ZCRMException ;
	
	public Map<String, Object> addRecordInModule(String nameModule, Map<String, Object> data) throws ZCRMException;
	
	public Map<String,Object> updateRecordById(String nameModule, Long id, Map<String,Object> data) throws ZCRMException;
	
	public Map<String, Object> deleteRecordById(String nameModule, Long id) throws ZCRMException;
	
	public List<Map<String,Object>> getRelatedListRecords(String nameModule, Long id) throws ZCRMException;
}

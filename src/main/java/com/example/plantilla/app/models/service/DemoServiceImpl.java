package com.example.plantilla.app.models.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.plantilla.app.models.dao.IDemoDao;
import com.google.gson.Gson;
import com.zoho.crm.library.api.response.APIResponse;
import com.zoho.crm.library.common.CommonUtil;
import com.zoho.crm.library.common.CommonUtil.SortOrder;
import com.zoho.crm.library.crud.ZCRMModule;
import com.zoho.crm.library.crud.ZCRMRecord;
import com.zoho.crm.library.exception.ZCRMException;
import com.zoho.crm.library.setup.users.ZCRMUser;

@Service
public class DemoServiceImpl implements IDemoService {

	@Autowired
	private IDemoDao demoDao;
	
	private Logger log = LoggerFactory.getLogger(DemoServiceImpl.class);

	private Gson formatJSON = new Gson();

	@Override
	public List<Map<String, Object>> listRecords(String nameModule) {
		return demoDao.listRecords(nameModule);
	}

	@Override
	public Map<String, Object> getRecordById(String nameModule, Long id) throws ZCRMException {
		return demoDao.getRecordById(nameModule, id);
	}

	@Override
	public Map<String, Object> addRecordInModule(String nameModule, Map<String, Object> data) throws ZCRMException {

		return demoDao.addRecordInModule(nameModule, data);
	}

	@Override
	public Map<String, Object> updateRecordById(String nameModule, Long id, Map<String, Object> data)
			throws ZCRMException {
		return demoDao.updateRecordById(nameModule, id, data);
	}

	@Override
	public Map<String, Object> deleteRecordById(String nameModule, Long id) throws ZCRMException {
		return demoDao.deleteRecordById(nameModule, id);

	}

	@Override
	public List<Map<String, Object>> getRelatedListRecords(String nameModule, Long id) throws ZCRMException {
		return demoDao.getRelatedListRecords(nameModule, id);
	}

}

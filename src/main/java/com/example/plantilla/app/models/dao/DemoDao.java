package com.example.plantilla.app.models.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.zoho.crm.library.api.response.APIResponse;
import com.zoho.crm.library.common.CommonUtil;
import com.zoho.crm.library.common.CommonUtil.SortOrder;
import com.zoho.crm.library.crud.ZCRMModule;
import com.zoho.crm.library.crud.ZCRMRecord;
import com.zoho.crm.library.exception.ZCRMException;
import com.zoho.crm.library.setup.users.ZCRMUser;

@Repository
public class DemoDao implements IDemoDao{

	private Logger log = LoggerFactory.getLogger(DemoDao.class);

	private Gson formatJSON = new Gson();

	@Override
	public List<Map<String, Object>> listRecords(String nameModule) {
		return null;
	}

	@Override
	public Map<String, Object> getRecordById(String nameModule, Long id) throws ZCRMException {
		Map<String, Object> registro = new HashMap<>();
		ZCRMModule module = ZCRMModule.getInstance(nameModule);
		APIResponse response = null;
		response = module.getRecord(id);
//		ZCRMRecord record = (ZCRMRecord) response.getData();
		String dataJson = response.getResponseJSON().getJSONArray("data").get(0).toString();
		registro = formatJSON.fromJson(dataJson, registro.getClass());
		return registro;
	}

	@Override
	public Map<String, Object> addRecordInModule(String nameModule, Map<String, Object> data) throws ZCRMException {

		Map<String, Object> registro = new HashMap<>();
		ZCRMRecord record = new ZCRMRecord(nameModule);
//		ZCRMUser owner = ZCRMUser.getInstance((Long) data.get("id_propietario"));// user id
//		record.setOwner(owner);
		record.setFieldValue("Last_Name", data.get("apellidos"));
//        record.setFieldValue("Account_Name","");
		List<CommonUtil.Trigger> triggers = Arrays.asList(CommonUtil.Trigger.workflow, CommonUtil.Trigger.approval,
				CommonUtil.Trigger.blueprint);
		APIResponse response = record.create(triggers);
		String dataJson = response.getResponseJSON().toString();
		registro = formatJSON.fromJson(dataJson, registro.getClass());
		return registro;
	}

	@Override
	public Map<String, Object> updateRecordById(String nameModule, Long id, Map<String, Object> data)
			throws ZCRMException {
		Map<String, Object> registro = new HashMap<>();
//		ZohoPersistenceHandler zph = new ZohoOAuthFilePersistence();
		List<CommonUtil.Trigger> triggers = Arrays.asList(CommonUtil.Trigger.workflow, CommonUtil.Trigger.approval,
				CommonUtil.Trigger.blueprint);
		ZCRMRecord record = ZCRMRecord.getInstance(nameModule, id);
		record.setFieldValue("Mobile", data.get("movil"));
//		record.setFieldValue("Estado_de_Factura_2",""); 
		APIResponse responseIn = null;
		responseIn = record.update(triggers);
		String dataJson = responseIn.getResponseJSON().toString();
		registro = formatJSON.fromJson(dataJson, registro.getClass());
		return registro;
	}

	@Override
	public Map<String, Object> deleteRecordById(String nameModule, Long id) throws ZCRMException {
		Map<String, Object> registro = new HashMap<>();
		ZCRMRecord record = ZCRMRecord.getInstance(nameModule, id);
		APIResponse responseIn = record.delete();
		String dataJson = responseIn.getResponseJSON().toString();
		registro = formatJSON.fromJson(dataJson, registro.getClass());
		return registro;

	}

	@Override
	public List<Map<String, Object>> getRelatedListRecords(String nameModule, Long id) throws ZCRMException {
		
		List<Map<String, Object>> registro = new ArrayList();
		ZCRMRecord record=ZCRMRecord.getInstance(nameModule,id); //To get ZCRMRecord instance
		String relatedListAPIName="Attachments";
		String sortByField="Last_Name";
		SortOrder sortOrder=CommonUtil.SortOrder.asc;
		int page=1;//page
		int per_page=200;//records per page
		String modifiedSince="2019-01-04T16:31:50";
		List<ZCRMRecord> relatedlistsrecords=(List<ZCRMRecord> )record.getRelatedListRecords(relatedListAPIName, sortByField, sortOrder, page, per_page, null).getData();
		for (ZCRMRecord  relatedlistrecord: relatedlistsrecords){
			System.out.println(relatedlistrecord.getEntityId());//to get the entity id 
			System.out.println(relatedlistrecord.getFieldValue("File_Name"));//to get the file name
			System.out.println(relatedlistrecord.getModuleAPIName());//to get the api name of the module
        }
		return null;
	}
}

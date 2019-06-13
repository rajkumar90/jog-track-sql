package com.jogtrack.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jogtrack.service.contract.JogInfo;
import com.jogtrack.service.contract.JogReport;

/**
 * This interface lists methods for CRUD on JogInfo
 * @author raj
 *
 */
public interface JogInfoService {
	// Create
	public JogInfo addJogInfo(JogInfo jogInfo, HttpServletRequest request) throws Exception;
	
	// Read
	public JogInfo getJogInfo(String jogId, HttpServletRequest request);
	public List<JogInfo> getJogInfoList(String userId, String jogId, String filterString, int offset, int limit, HttpServletRequest request) throws Exception;
	
	// Update
	public JogInfo update(JogInfo jogInfo, HttpServletRequest request) throws Exception;

	// Delete
	public void delete(String userId, String jogId, HttpServletRequest request) throws Exception;
	
	// Report
	public JogReport getReport(String userId, HttpServletRequest request);
	
}
package com.jogtrack.resource.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jogtrack.auth.Secured;
import com.jogtrack.resource.JogInfoResource;
import com.jogtrack.service.JogInfoService;
import com.jogtrack.service.contract.JogInfo;
import com.jogtrack.service.contract.JogReport;
import com.jogtrack.service.contract.PaginatedResponse;
import com.jogtrack.service.contract.PaginatedResponseMetadata;
import com.jogtrack.service.contract.PaginationLinks;
import com.jogtrack.service.contract.Role;
import com.jogtrack.util.JogTrackConstants;

@Component
@Secured({Role.REGULAR, Role.ADMIN})
public class JogInfoResourceImpl implements JogInfoResource {
	private static final Logger LOG = LoggerFactory
			.getLogger(JogInfoResourceImpl.class);
	
	@Autowired
	JogInfoService jogInfoService;
	
	@Override
	public PaginatedResponse<JogInfo> getJogInfoList(String userId, String jogId, String filterString, int offset, int limit, HttpServletRequest request) throws Exception {
		LOG.debug("Begin getJogInfoList()");
		
		limit = (limit == 0)? JogTrackConstants.DEFAULT_LIMIT_PAGINATION : limit;
		PaginatedResponse<JogInfo> response = new PaginatedResponse<JogInfo>();
		List<JogInfo> jogInfoList = jogInfoService.getJogInfoList(userId, jogId, filterString, offset, limit, request);
		response.setPayload(jogInfoList);
		
		PaginatedResponseMetadata metadata = new PaginatedResponseMetadata();
		metadata.setNumEntries(jogInfoList == null ? 0 : jogInfoList.size());
		PaginationLinks links = new PaginationLinks();
		links.setSelf("/jogInfo?limit=" + limit + "&offset=" + offset);
		int nextOffset = offset + limit;
		links.setNext("/jogInfo?limit=" + limit + "&offset=" + nextOffset);
		int prevOffset = offset - limit;
		if (prevOffset >= 0)
			links.setPrev("/jogInfo?limit=" + limit + "&offset=" + prevOffset);
		metadata.setLinks(links);
		response.setMetadata(metadata );
		return response;
	}

	@Override
	@Transactional
	public JogInfo addJogInfo(JogInfo jogInfo, HttpServletRequest request) throws Exception {
		LOG.debug("Begin addJogInfo()");
		return jogInfoService.addJogInfo(jogInfo, request);
	}

	@Override
	@Transactional
	public JogInfo updateJogInfo(JogInfo jogInfo, HttpServletRequest request) throws Exception {
		LOG.debug("Begin updateJogInfo()");
		return jogInfoService.update(jogInfo, request);
	}

	@Override
	@Transactional
	public void deleteJogInfo(String userId, String jogId, HttpServletRequest request) throws Exception {
		LOG.debug("Begin deleteJogInfo()");
		jogInfoService.delete(userId, jogId, request);
	}

	@Override
	public JogReport getReport(String userId, HttpServletRequest request) {
		LOG.debug("Begin getReport()");
		return jogInfoService.getReport(userId, request);
	}
}

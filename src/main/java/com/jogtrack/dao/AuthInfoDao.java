package com.jogtrack.dao;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

import com.jogtrack.domain.AuthInfoDO;

@Component
public class AuthInfoDao extends GenericDao<AuthInfoDO>{
	public AuthInfoDO findByAuthToken(String authToken) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<AuthInfoDO> cq = cb.createQuery(AuthInfoDO.class);
		Root<AuthInfoDO> root = cq.from(AuthInfoDO.class);
		CriteriaQuery<AuthInfoDO> select = cq.select(root);
		
		// Create a Predicate object from the filter string
		Predicate pr = cb.equal(root.get("authToken"), authToken);
		select.where(pr);
	
		TypedQuery<AuthInfoDO> tq = em.createQuery(select);
		AuthInfoDO authInfoDO = tq.getSingleResult();
		
		return authInfoDO;
	}
}

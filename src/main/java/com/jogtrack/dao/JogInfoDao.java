package com.jogtrack.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

import com.jogtrack.domain.JogInfoDO;

@Component
public class JogInfoDao extends GenericDao<JogInfoDO>{
	public List<JogInfoDO> getAll(String userId, String jogId, String filterString, int offset, int limit) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<JogInfoDO> cq = cb.createQuery(JogInfoDO.class);
		Root<JogInfoDO> root = cq.from(JogInfoDO.class);
		CriteriaQuery<JogInfoDO> select = cq.select(root);
		// Sort by createTs
		cq.orderBy(cb.desc(root.get("createTs")));
		
		// Create a Predicate object from the filter string
		Predicate pr = getPredicate(filterString, cb, root);
		select.where(pr);
	
		TypedQuery<JogInfoDO> tq = em.createQuery(select);
		tq.setFirstResult(offset);
		tq.setMaxResults(limit);
		List<JogInfoDO> jogInfoList = tq.getResultList();
		
		return jogInfoList;
	}
	
	public Tuple getReport(String userId, Date fromDate) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
		Root<JogInfoDO> root = cq.from(JogInfoDO.class);
		
		CriteriaQuery<Tuple> select = cq.multiselect(cb.avg(root.get("jogDistance")).alias("avgDistance"), cb.sum(root.get("jogDistance")).alias("sumDistance"), cb.sum(root.get("jogTime")).alias("sumJogTime"), cb.count(root.get("jogId")));
		
		Predicate dateCheck = cb.greaterThan(root.<Date>get("jogDate"), fromDate);
		
		Predicate userIdCheck = cb.equal(root.get("userId"), userId);
		select = select.where(dateCheck, userIdCheck);

		TypedQuery<Tuple> tq = em.createQuery(select);
		Tuple result = tq.getSingleResult();
		
		return result;		
	}
}

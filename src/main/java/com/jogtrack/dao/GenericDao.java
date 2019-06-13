package com.jogtrack.dao;

import java.util.Stack;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

import com.jogtrack.domain.BaseDO;
import com.jogtrack.service.contract.FilterCriteria;

@Component
public class GenericDao<T> {
	@PersistenceContext
	protected EntityManager em;
	
	public void add(T entity) {
		em.persist(entity);
	}
	
	public T get(Class<T> entityClass, String id) {
		T entity = em.find(entityClass , id);
		return entity;
	}
	
	public T update(T entity) {
		return em.merge(entity);
	}
	
	public void delete(Class<T> entityClass, String id) {
		T entity = em.find(entityClass, id);
		em.remove(entity);
	}
	
	/**
	 * This method evaluates the nested filter string, and returns a corresponding predicate
	 * @param filterString
	 * @param cb
	 * @param from
	 * @return
	 */
	protected Predicate getPredicate(String filterString, CriteriaBuilder cb, Root<T> from) {
		
		if (filterString == null || filterString.isEmpty())
			return cb.conjunction();
		
		Stack<String> operatorStack = new Stack<>();
		Stack<Predicate> operandStack = new Stack<>();
		
		int i = 0;
		while (i < filterString.length()) {
			// case (
			if (filterString.charAt(i) == '(') {
				String tempString = filterString.substring(i + 1, filterString.indexOf(')', i));
				if (tempString.contains("(")) {
					operatorStack.add("(");
					i++;
				}
				else {
					FilterCriteria fc = new FilterCriteria(tempString);
					Predicate tempPredicate = getPredicateFromFilterCriteria(fc, from);
					operandStack.add(tempPredicate);	
					i = filterString.indexOf(')', i) + 1;
				}
			} else if (filterString.charAt(i) == ')') {
			// case )
				String operatorString;
				while (!(operatorString = operatorStack.pop()).equals("(")) {
					Predicate p1 = operandStack.pop();
					Predicate p2 = operandStack.pop();
					if (operatorString.equals("AND")) {
						p2 = cb.and(p1, p2);
					} else if (operatorString.equals("OR")) {
						p2 = cb.or(p1, p2);
					}
					operandStack.push(p2);
				}
				i++;
			} else if (filterString.substring(i).startsWith("AND")) {			
			// case Operand - AND/OR
				operatorStack.push("AND");
				i += 3;
			} else if (filterString.substring(i).startsWith("OR")) {
				operatorStack.push("OR");
				i += 2;
			} else {
			// case whitespace
				i++;
			}
		}
		
		while (!operatorStack.isEmpty()) {
			String operatorString = operatorStack.pop();
			Predicate p1 = operandStack.pop();
			Predicate p2 = operandStack.pop();
			if (operatorString.equals("AND")) {
				p2 = cb.and(p1, p2);
			} else if (operatorString.equals("OR")) {
				p2 = cb.or(p1, p2);
			}
			operandStack.push(p2);
		}
		Predicate pr = operandStack.pop();
		return pr;
	}
	
	private Predicate getPredicateFromFilterCriteria(FilterCriteria fc, Root<T> r) {
		Predicate p = null;
		CriteriaBuilder cb = em.getCriteriaBuilder();
		if (fc.getComparison().equals("eq"))
			p = cb.equal(r.get(fc.getAttribute()), fc.getValue());
		if (fc.getComparison().equals("ne"))
			p = cb.notEqual(r.get(fc.getAttribute()), fc.getValue());
		if (fc.getComparison().equals("gt"))
			p = cb.greaterThan(r.get(fc.getAttribute()), fc.getValue());
		if (fc.getComparison().equals("lt"))
			p = cb.lessThan(r.get(fc.getAttribute()), fc.getValue());
		
		return p;
	}
}

package com.izhuixin.rsps.common.dba;

import com.google.common.base.Optional;
import com.izhuixin.rsps.common.page.Paginator;

import java.util.List;

/**
 * 
 * @author wangdong
 *
 * @param <T>
 */
public interface CrudService<T> {
	
	List<T> getList(FilterExample example, Paginator p);

	List<T> getList(Paginator p);

	List<T> getList(FilterExample example);

	List<T> getList();

	Optional<T> get(FilterExample example);

	Optional<T> get(Long id);

	long count(FilterExample example);
	
	long remove(Long id);

	long remove(FilterExample example);
	
	/**
	 * insert
	 * @param entity
	 */
	long save(T entity);
	
//	void saveOrUpdate(T entity);
//	
//	void saveOrUpdate(List<T> entity);

	/**
	 * update by primary key, entity.getId() must not be null.
	 * @param entity
	 */
	long update(T entity);

	/**
	 * update by example, may affect multi rows.
	 * @param entity
	 * @param example
	 * @return
	 */
	long update(T entity, FilterExample example);

	/**
	 * if not found by example row, then insert entity, otherwise insert entity.
	 * find by example must return single row, otherwise throw duplicate exception.
	 * @param entity
	 * @param example
	 */
	T saveOrUpdate(T entity, FilterExample example);
}

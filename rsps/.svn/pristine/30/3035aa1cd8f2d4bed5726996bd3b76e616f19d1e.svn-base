package com.izhuixin.rsps.common.dba;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.izhuixin.rsps.common.exception.ServiceException;
import com.izhuixin.rsps.common.page.Paginator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wd
 * 
 */
public abstract class AbstractCrudService<T> implements CrudService<T> {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	final static FilterExample ALL = new FilterExample();

	@Autowired
	private GenericRepository<T> genericRepository;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Optional<T> get(Long id) {
		Preconditions.checkNotNull(id);
		try {
			return Optional.fromNullable(genericRepository.selectByPrimaryKey(id));
		} catch (Exception e) {
			throw new ServiceException("DB Access Error!", e);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<T> getList() {
		try {
			return genericRepository.selectByExample(ALL);
		} catch (Exception e) {
			throw new ServiceException("DB Access Error!", e);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<T> getList(FilterExample example, Paginator p) {
		Preconditions.checkNotNull(example);
		Preconditions.checkNotNull(p);
		try {
			p.setItems((int)genericRepository.countByExample(example));
			return genericRepository.selectByExampleWithRowbounds(example, p.rowBounds());
		} catch (Exception e) {
			throw new ServiceException("DB Access Error!", e);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<T> getList(FilterExample example) {
		Preconditions.checkNotNull(example);
		try {
			return genericRepository.selectByExample(example);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("DB Access Error!", e);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<T> getList(Paginator p) {
		return getList(ALL, p);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Optional<T> get(FilterExample example) {
		List<T> result = getList(example);
		int s = result.size();
		if (s > 1) {
			throw new RuntimeException();
		}

		return s == 0 ? Optional.<T> absent() : Optional.of(result.get(0));
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public long count(FilterExample example) {
		Preconditions.checkNotNull(example);
		try {
			return this.genericRepository.countByExample(example);
		} catch (Exception e) {
			throw new ServiceException("DB Access Error!", e);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long remove(Long id) {
		Preconditions.checkNotNull(id);
		try {
			return genericRepository.deleteByPrimaryKey(id);
		} catch (Exception e) {
			throw new ServiceException("DB Access Error!", e);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long remove(FilterExample example) {
		Preconditions.checkNotNull(example);
		try {
			return this.genericRepository.deleteByExample(example);
		} catch (Exception e) {
			throw new ServiceException("DB Access Error!", e);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long save(T entity) {
		Preconditions.checkNotNull(entity);
		try {
			return genericRepository.insertSelective(entity);
		} catch (Exception e) {
			throw new ServiceException("DB Error.", e);
		}
	}
	
//	@Override
//	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
//	public void saveOrUpdate(T entity) {
//		Preconditions.checkNotNull(entity);
//		if (entity.getId() == null) {
//			save(entity);
//		} else {
//			update(entity);
//		}
//	}
//	
//	@Override
//	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
//	public void saveOrUpdate(List<T> entities) {
//		Preconditions.checkNotNull(entities);
//		for (T o : entities) {
//			saveOrUpdate(o);
//		}
//	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long update(T entity) {
		Preconditions.checkNotNull(entity);
		try {
			return genericRepository.updateByPrimaryKeySelective(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("DB Error", e);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long update(T entity, FilterExample example) {
		Preconditions.checkNotNull(entity);
		Preconditions.checkNotNull(example);
		try {
			return genericRepository.updateByExampleSelective(entity, example);
		} catch (Exception e) {
			throw new ServiceException("DB Error", e);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public T saveOrUpdate(T entity, FilterExample example) {
		Optional<T> r = get(example);

		if (r.isPresent()) {
			update(entity, example);
		} else {
			save(entity);
		}

		return get(example).get();
	}

}
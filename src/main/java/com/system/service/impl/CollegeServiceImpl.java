package com.system.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.system.po.College;
import com.system.service.CollegeService;
@Service
public class CollegeServiceImpl  implements CollegeService {


    public List<College> finAll() throws Exception {


        return null;
    }

	@Override
	public boolean insert(College entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertAllColumn(College entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertBatch(List<College> entityList) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertBatch(List<College> entityList, int batchSize) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertOrUpdateBatch(List<College> entityList) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertOrUpdateBatch(List<College> entityList, int batchSize) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Serializable id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteByMap(Map<String, Object> columnMap) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Wrapper<College> wrapper) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteBatchIds(List<? extends Serializable> idList) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateById(College entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateAllColumnById(College entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(College entity, Wrapper<College> wrapper) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateBatchById(List<College> entityList) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateBatchById(List<College> entityList, int batchSize) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertOrUpdate(College entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public College selectById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<College> selectBatchIds(List<? extends Serializable> idList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<College> selectByMap(Map<String, Object> columnMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public College selectOne(Wrapper<College> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> selectMap(Wrapper<College> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object selectObj(Wrapper<College> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectCount(Wrapper<College> wrapper) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<College> selectList(Wrapper<College> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<College> selectPage(Page<College> page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> selectMaps(Wrapper<College> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> selectObjs(Wrapper<College> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Map<String, Object>> selectMapsPage(Page page, Wrapper<College> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<College> selectPage(Page<College> page, Wrapper<College> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteBatchIds(Collection<? extends Serializable> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertOrUpdateAllColumn(College arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertOrUpdateAllColumnBatch(List<College> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertOrUpdateAllColumnBatch(List<College> arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<College> selectBatchIds(Collection<? extends Serializable> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateAllColumnBatchById(List<College> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateAllColumnBatchById(List<College> arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}
}

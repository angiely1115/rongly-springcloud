package com.rongly.zupu.service.system.impl;

import com.rongly.zupu.dao.system.LogDao;
import com.rongly.zupu.domain.PageDO;
import com.rongly.zupu.entity.system.LogDO;
import com.rongly.zupu.service.system.LogService;
import com.rongly.zupu.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {
	@Autowired
	LogDao logMapper;

	@Override
	public PageDO<LogDO> queryList(Query query) {
		int total = logMapper.count(query);
//		int limit = query.getLimit();
//		
//		if(total<=query.getOffset()) {
//			System.out.println(total +"-----"+query.getOffset());
//			query.setOffset((total/limit-1)*limit);
//			System.out.println(query.getOffset());
//		}
		List<LogDO> logs = logMapper.list(query);
		PageDO<LogDO> page = new PageDO<>();
		page.setTotal(total);
		page.setRows(logs);
		return page;
	}

	@Override
	public int remove(Long id) {
		int count = logMapper.deleteById(id);
		return count;
	}

	@Override
	public int batchRemove(Long[] ids){
		return logMapper.deleteBatchIds(Arrays.asList(ids));
//		return logMapper.batchRemove(ids);
	}
}

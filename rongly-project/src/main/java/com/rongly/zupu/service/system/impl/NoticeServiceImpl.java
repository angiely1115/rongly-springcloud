package com.rongly.zupu.service.system.impl;

import com.rongly.zupu.dao.system.NoticeDao;
import com.rongly.zupu.entity.system.NoticeDO;
import com.rongly.zupu.service.system.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service
public class NoticeServiceImpl implements NoticeService {
	@Autowired
	private NoticeDao noticeDao;
	
	@Override
	public NoticeDO get(Integer id){
		return noticeDao.get(id);
	}
	
	@Override
	public List<NoticeDO> list(Map<String, Object> map){
		return noticeDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return noticeDao.count(map);
	}
	
	@Override
	public int save(NoticeDO notice){
		return noticeDao.save(notice);
	}
	
	@Override
	public int update(NoticeDO notice){
		return noticeDao.update(notice);
	}
	
	@Override
	public int remove(Integer id){
		return noticeDao.deleteById(id);
//		return noticeDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return noticeDao.deleteBatchIds(Arrays.asList(ids));
//		return noticeDao.batchRemove(ids);
	}
	
}

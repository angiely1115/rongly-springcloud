package com.rongly.zupu.dao.system;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.rongly.zupu.entity.system.NoticeDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 公告表
 * @date 2018-03-08 15:17:29
 */
@Mapper
@Repository
public interface NoticeDao extends BaseMapper<NoticeDO> {

	NoticeDO get(Integer id);
	
	List<NoticeDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);
	
	int save(NoticeDO notice);
	
	int update(NoticeDO notice);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}

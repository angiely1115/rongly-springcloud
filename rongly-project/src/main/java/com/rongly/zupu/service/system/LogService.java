package com.rongly.zupu.service.system;

import com.rongly.zupu.domain.PageDO;
import com.rongly.zupu.entity.system.LogDO;
import com.rongly.zupu.utils.Query;
import org.springframework.stereotype.Service;

@Service
public interface LogService {
	PageDO<LogDO> queryList(Query query);
	int remove(Long id);
	int batchRemove(Long[] ids);
}

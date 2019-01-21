package com.rongly.zupu.controller.system;

import com.rongly.zupu.entity.system.NoticeDO;
import com.rongly.zupu.entity.system.UserDO;
import com.rongly.zupu.service.system.NoticeService;
import com.rongly.zupu.utils.PageUtils;
import com.rongly.zupu.utils.Query;
import com.rongly.zupu.utils.R;
import com.rongly.zupu.utils.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 公告表
 * 
 * @date 2018-03-08 15:17:29
 */
 
@Controller
@RequestMapping("/sys/notice")
public class NoticeController {
	@Autowired
	private NoticeService noticeService;
	
	@GetMapping()
	@RequiresPermissions("sys:notice:notice")
	String Notice(){
	    return "system/notice/notice";
	}
	
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<NoticeDO> noticeList = noticeService.list(query);
		int total = noticeService.count(query);
		PageUtils pageUtils = new PageUtils(noticeList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("sys:notice:add")
	String add(){
	    return "system/notice/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("sys:notice:edit")
	String edit(@PathVariable("id") Integer id, Model model){
		NoticeDO notice = noticeService.get(id);
		model.addAttribute("notice", notice);
	    return "system/notice/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("sys:notice:add")
	public R save(NoticeDO notice){
		notice.setCreateTime(new Date());
		UserDO user = ShiroUtils.getUser();
		notice.setCreator(user.getUsername());
		if(noticeService.save(notice)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("sys:notice:edit")
	public R update( NoticeDO notice){
		notice.setCreateTime(new Date());
		UserDO user = ShiroUtils.getUser();
		notice.setCreator(user.getUsername());
		noticeService.update(notice);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("sys:notice:remove")
	public R remove( Integer id){
		if(noticeService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("sys:notice:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		noticeService.batchRemove(ids);
		return R.ok();
	}
	
}

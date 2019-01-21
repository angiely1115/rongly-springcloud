package com.rongly.zupu.service.system;

import com.rongly.zupu.domain.Tree;
import com.rongly.zupu.entity.system.DeptDO;
import com.rongly.zupu.entity.system.UserDO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public interface UserService {
	UserDO get(Integer id);

	List<UserDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(UserDO user);

	int update(UserDO user);

	int remove(Integer userId);

	int batchremove(Integer[] userIds);

	boolean exit(Map<String, Object> params);

	Set<String> listRoles(Integer userId);

	int resetPwd(UserDO user);

	int resetFundPwd(UserDO user);

	Tree<DeptDO> getTree();

	/**
	 * @Description 更新密码
	 * @param dataUserDo
	 */
	void updatePassword(UserDO dataUserDo);

	/**
	 * @Description 更新资金密码
	 * @param dataUserDo
	 */
	void updateFundPassword(UserDO dataUserDo);

	/**
	 * @Description 通过用户信息 创建账户余额信息
	 * @param user
	 * @return

	public static PayAcctBal createAgentBalFromUser(UserDO user) {
		PayAcctBal payAcctBal = new PayAcctBal();
		payAcctBal.setUserId(user.getUserId());
		payAcctBal.setUsername(user.getUsername());
		payAcctBal.setUserType(user.getUserType());
		payAcctBal.setBalance(BigDecimal.ZERO);
		payAcctBal.setAvailBal(BigDecimal.ZERO);
		return payAcctBal;
	}
	 */
}

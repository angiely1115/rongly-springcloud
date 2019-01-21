package com.rongly.zupu.controller.system;


import com.rongly.zupu.common.annotation.Log;
import com.rongly.zupu.controller.BaseController;
import com.rongly.zupu.domain.Tree;
import com.rongly.zupu.entity.system.MenuDO;
import com.rongly.zupu.entity.system.UserDO;
import com.rongly.zupu.service.system.MenuService;
import com.rongly.zupu.service.system.SessionService;
import com.rongly.zupu.service.system.UserService;
import com.rongly.zupu.utils.CacheUtils;
import com.rongly.zupu.utils.ParamUtil;
import com.rongly.zupu.utils.R;
import com.rongly.zupu.utils.ShiroUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserService userService;

    @Autowired
    SessionService sessionService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
   /* @Autowired
    SMSServiceAliImpl smsServiceAli;
    @Autowired
    SMSServiceTXImpl smsServiceTx;*/

    @GetMapping({"/", ""})
    String welcome(Model model) {
        return "redirect:/index";
    }

    //    @Log("请求访问主页")
    @GetMapping({"/index"})
    String index(Model model) {
        UserDO user = ShiroUtils.getUser();
        List<Tree<MenuDO>> menus = menuService.listMenuTree(user.getUserId());
        model.addAttribute("menus", menus);
        model.addAttribute("name", user.getName());
        UserDO dataUserDo = userService.get(user.getUserId());
        String funPass = dataUserDo.getFundPassword();
        model.addAttribute("firstLogin", StringUtils.isBlank(funPass) ? "true" : "false");
        model.addAttribute("passNoUp", "1".equals(user.getPassword()) ? "true" : "false");
        model.addAttribute("helpDom", stringRedisTemplate.opsForValue().get("sys_config_upload_help_dom"));
        model.addAttribute("userType", user.getUserType());
        logger.info(user.getName());
        return "index_v1";
    }

    @GetMapping("/login")
    String login() {
        return "login";
    }

    /**
     * @param username 用户名
     * @return
     * @Description 用户登录 获取盐
     */
    @PostMapping("/salt")
    @ResponseBody
    R salt(@RequestParam("username") String username) {
        int salt = ParamUtil.generateCode6();
        CacheUtils.setLoginSalt(username, String.valueOf(salt));
        return R.okData(salt);
    }

    @Log("登录")
    @PostMapping("/login")
    @ResponseBody
    R ajaxLogin(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session) {

        //强制下线同一用户名的前一次登陆
        sessionService.forceLogoutByUserName(username);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            session.setAttribute("username", username);
            return R.ok();
        } catch (AuthenticationException e) {
            return R.error("用户或密码错误");
        }
    }

//    @Log("修改密码发送短信验证码")
  /*  @PostMapping("/chgSendCode")
    @ResponseBody
    R chgSendCode(@RequestParam("codeType") int codeType) {
        UserDO userDO = getUser();
        if (userDO == null || ParamUtil.isEmpty(userDO.getName())) {
            return R.error("请登录再修改密码！");
        }
        try {
        	UserDO dataUserDo = userService.get(userDO.getUserId());
        	String phone = dataUserDo.getMobile();
        	String validCode = String.valueOf(ParamUtil.generateCode6());
        	String key = CfgKeyConst.sms_code_phone_update_pass+phone+"_"+codeType;
        	Long expireTime = RedisUtil.getRedisTemplate().getExpire(key);
        	if(expireTime > 0) {
        		return R.error("请过"+expireTime+"秒后再试!");
        	}
        	
        	String type = RedisUtil.getSMSConfigValue(CfgKeyConst.sms_send_type);
        	boolean isSuccess = false;
        	if(CfgKeyConst.sms_send_type_tx.equals(type))
        		isSuccess = smsServiceTx.sendMessageReg(phone, validCode);
        	else if(CfgKeyConst.sms_send_type_aliy.equals(type))
        		isSuccess = smsServiceAli.sendMessageReg(phone, validCode);
        	if(isSuccess){
        		RedisUtil.setValue(key, validCode);
        		RedisUtil.getRedisTemplate().expire(key, 60, TimeUnit.SECONDS);
        		return R.ok("发送成功");
        	}else
        		return R.error("发送失败！");
        } catch (AuthenticationException e) {
            return R.error("发送失败！");
        }
    }*/

    @Log("修改密码")
    @PostMapping("/chgPass")
    @ResponseBody
    R chgPass(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword, @RequestParam("codeType") int codeType, @RequestParam("code") String code) {
        UserDO userDO = getUser();
        if (userDO == null || ParamUtil.isEmpty(userDO.getName())) {
            return R.error("请登录再修改密码！");
        }
        try {
            UserDO dataUserDo = userService.get(userDO.getUserId());
            /*String phone = dataUserDo.getMobile();
            if(StringUtils.isBlank(code)) {
            	return R.error("请输入验证码！");
            }
            String key = CfgKeyConst.sms_code_phone_update_pass+phone+"_"+codeType;
            String orgCode = (String)RedisUtil.getValue(key);
            if(StringUtils.isBlank(orgCode)) {
            	return R.error("请发送验证码！");
            }
        	Long expireTime = RedisUtil.getRedisTemplate().getExpire(key);
            if(expireTime <= 0) {
            	return R.error("验证码已过期！");
            }
            if(!orgCode.equals(code)) {
            	return R.error("验证码不正确！");
            }*/

            if (dataUserDo != null && dataUserDo.getPassword().equals(oldPassword)) {
                dataUserDo.setPassword(newPassword);
                userService.updatePassword(dataUserDo);
                userDO.setPassword("1");
            } else {
                return R.error("原密码错误！");
            }
//            RedisUtil.getRedisTemplate().delete(key);
            return R.ok("登陆密码修改成功");
        } catch (AuthenticationException e) {
            return R.error("原密码错误！");
        }
    }

    @Log("修改资金密码")
    @PostMapping("/chgFundPass")
    @ResponseBody
    R chgFundPass(@RequestParam("oldFundPassword") String oldFundPassword, @RequestParam("newFundPassword") String newFundPassword, @RequestParam("codeType") int codeType, @RequestParam("code") String code) {
        UserDO userDO = getUser();
        if (userDO == null || ParamUtil.isEmpty(userDO.getName())) {
            return R.error("请登录再修改资金密码！");
        }
        try {
            UserDO dataUserDo = userService.get(userDO.getUserId());
            
            /*String phone = dataUserDo.getMobile();
            if(StringUtils.isBlank(code)) {
            	return R.error("请输入验证码！");
            }
            String key = CfgKeyConst.sms_code_phone_update_pass+phone+"_"+codeType;
            String orgCode = (String)RedisUtil.getValue(key);
            if(StringUtils.isBlank(orgCode)) {
            	return R.error("请发送验证码！");
            }
        	Long expireTime = RedisUtil.getRedisTemplate().getExpire(key);
            if(expireTime <= 0) {
            	return R.error("验证码已过期！");
            }
            if(!orgCode.equals(code)) {
            	return R.error("验证码不正确！");
            }*/

            if (dataUserDo != null) {
                if (ParamUtil.isEmpty(oldFundPassword)) {
                    dataUserDo.setFundPassword(newFundPassword);
                    userService.updateFundPassword(dataUserDo);
                } else if (oldFundPassword.equals(dataUserDo.getFundPassword())) {
                    dataUserDo.setFundPassword(newFundPassword);
                    userService.updateFundPassword(dataUserDo);
                } else {
                    throw new AuthenticationException("资金密码修改失败！");
                }
            } else {
                return R.error("原资金密码错误！");
            }
//            RedisUtil.getRedisTemplate().delete(key);
            return R.ok("资金密码修改成功！");
        } catch (AuthenticationException e) {
            return R.error("原资金密码错误！");
        }
    }

    /**
     * @Description 获取是否设置资金密码
     * @Author chensi
     * @Time 2017/12/21 11:31
     */
    @PostMapping("/hasFundPassword")
    @ResponseBody
    R hasFundPassword() {
        UserDO userDO = getUser();
        if (userDO == null || ParamUtil.isEmpty(userDO.getName())) {
            return R.error("请登录再修改资金密码！");
        }

        UserDO dataUserDo = userService.get(userDO.getUserId());
        if (dataUserDo != null && ParamUtil.isNotEmpty(dataUserDo.getFundPassword())) {
            return R.ok();
        }
        return R.error();
    }


    @GetMapping("/logout")
    String logout() {
        ShiroUtils.logout();
        return "redirect:/login";
    }

    @GetMapping("/main")
    String main() {
        return "main";
    }

    @GetMapping("/403")
    String error403() {
        return "403";
    }

}

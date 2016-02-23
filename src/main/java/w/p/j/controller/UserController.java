package w.p.j.controller;

import com.github.pagehelper.Page;

import w.p.j.config.dataSourceSwitch.DataSourceInstances;
import w.p.j.config.dataSourceSwitch.DataSourceSwitch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 *  用户信息
 *
 */
@Controller
@RequestMapping("/User")
public class UserController extends BaseController {

    private final static Logger log = LoggerFactory.getLogger(UserController.class);

    /**
     *
     * 获取所有用户信息
     * @return 用户信息JSON格式
     */
    @RequestMapping("/getUser")
    @ResponseBody
    public Map<String,Object> getUser() {

        EiInfo.put("DATA",super.selectList("GetUserInfo.accountInfo")) ;
        //DataSourceSwitch.setDataSourceType(DataSourceInstances.MYSQL);
        //Page<Object> userList = (Page<Object>) super.selectList("GetUserInfo.queryUser");
        //EiInfo.put("DATA",super.selectList("GetUserInfo.queryAll1")) ;
        //EiInfo.put("rows",userList);
        //EiInfo.put("total",userList.getTotal());
        return EiInfo;
    }
}

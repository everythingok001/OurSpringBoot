package w.p.j.controller;


import w.p.j.config.dataSourceSwitch.DataSourceInstances;
import w.p.j.config.dataSourceSwitch.DataSourceSwitch;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import w.p.j.vendor.util.ParametersUtil;


import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 *  用户信息
 *
 */
@Controller
@RequestMapping("/User")
public class UserController extends BaseController {

    /**
     *
     * 获取所有用户信息
     * @return 用户信息JSON格式
     */
    @RequestMapping("/getUser")
    @ResponseBody
    public List getUser(HttpServletRequest request) {
        this.setEiInfo(ParametersUtil.parse(request));
        DataSourceSwitch.setDataSourceType(DataSourceInstances.ORACLE);
        return super.selectList("GetUserInfo.queryAll");
    }
}

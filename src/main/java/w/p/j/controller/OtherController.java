package w.p.j.controller;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;

import w.p.j.config.dataSourceSwitch.DataSourceInstances;
import w.p.j.config.dataSourceSwitch.DataSourceSwitch;


@Controller
@RequestMapping("/other")
@Scope("prototype")
public class OtherController extends BaseController {
	
	
	@RequestMapping("/show")
	public String show(){
		EiInfo.put("message", "王栋");
		EiInfo.put("time", "王栋");
		EiInfo.put("haha", "王栋111");
		return "show";
	}

	@RequestMapping("/index")
	public String index(){
		EiInfo.put("message", "王栋");
		EiInfo.put("time", "王栋");
		EiInfo.put("haha", "王栋111");
		return "index";
	}
	
	@RequestMapping("/user")
	public String findOne() {
		return "user";
	}
	
	@RequestMapping("/getUser")
	@ResponseBody
	public Map<String,Object> getUser() {
		//System.out.println(EiInfo);
		//EiInfo.put("DATA",super.selectList("GetUserInfo.queryAll")) ;
		DataSourceSwitch.setDataSourceType(DataSourceInstances.MYSQL);
		Page<Object> userList = (Page<Object>) super.selectList("GetUserInfo.queryUser");
		//EiInfo.put("DATA",super.selectList("GetUserInfo.queryAll1")) ;
		EiInfo.put("rows",userList);
		EiInfo.put("total",userList.getTotal());
		return EiInfo;
	}
	
	@RequestMapping("/getUser1")
	@ResponseBody
	public Map<String,Object> getUser1() {
		//System.out.println(EiInfo);
		DataSourceSwitch.setDataSourceType(DataSourceInstances.MYSQL);
		Page<Object> userList = (Page<Object>) super.selectList("GetUserInfo.queryUser");
		return EiInfo;
	}
	
	@RequestMapping("/index2")
	public String index2() {
		return "index_v2";
	}
}

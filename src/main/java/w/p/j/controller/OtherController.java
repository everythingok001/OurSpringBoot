package w.p.j.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
		EiInfo.put("DATA",super.selectList("GetUserInfo.queryAll")) ;
		EiInfo.put("name","wangddong");
		return "table";
	}
}

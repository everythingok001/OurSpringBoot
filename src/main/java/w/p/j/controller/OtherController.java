package w.p.j.controller;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.RowBounds;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@ResponseBody
	public List<Map<String,Object>> findOne() {
		return dao.selectList("GetUserInfo.queryAll", EiInfo, new RowBounds(1, 20));
	}
}

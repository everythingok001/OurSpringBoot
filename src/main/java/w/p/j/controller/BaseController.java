package w.p.j.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseController {
	
	@Resource
	SqlSessionTemplate dao;
	
	protected Logger logger= LoggerFactory.getLogger(this.getClass().getName());
	
	protected Map<String,Object> EiInfo;

	public Map<String, Object> getEiInfo() {
		return EiInfo;
	}

	public void setEiInfo(Map<String, Object> eiInfo) {
		EiInfo = eiInfo;
	}
	
	
}

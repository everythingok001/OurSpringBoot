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
	
	protected Map<String,Object> infoBlock;
	
	public Map<String, Object> getInfoBlock() {
		return infoBlock;
	}

	public void setInfoBlock(Map<String, Object> infoBlock) {
		this.infoBlock = infoBlock;
	}
}

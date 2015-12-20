package w.p.j.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
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

	/**
	 * @title 父类方法，默认取参数分页，参数为EiInfo
	 * @param 查询方法
	 * @return 结果集
     */
	public <E> List<E> selectList(String statement){
		RowBounds rowBounds = null;
		if (EiInfo.get("page")==null && EiInfo.get("rows")==null){
			rowBounds = new RowBounds(1,20);
		}else{
			rowBounds = new RowBounds(Integer.parseInt(EiInfo.get("page").toString()),Integer.parseInt(EiInfo.get("rows").toString()));
		}
		return dao.selectList(statement,EiInfo,rowBounds);
	}

}

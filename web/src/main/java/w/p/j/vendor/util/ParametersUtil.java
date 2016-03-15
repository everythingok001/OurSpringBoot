package w.p.j.vendor.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by everythingok on 2016/3/15.
 */
public class ParametersUtil {
    public static Map<String,Object> parse(HttpServletRequest request){
        Map<String,Object> queryMap = new HashMap<String,Object>();
        //封装request参数
        Enumeration<String> en=request.getParameterNames();
        while (en.hasMoreElements()) {
            String paramName = en.nextElement();
            String paramValue = request.getParameter(paramName);
            if(!paramValue.equals("")){
                //形成键值对应的map
                queryMap.put(paramName, paramValue);
            }
        }
        return queryMap;
    }
}

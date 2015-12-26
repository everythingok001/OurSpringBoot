package w.p.j.config.dataSourceSwitch;

public class DataSourceSwitch {
    private static final ThreadLocal contextHolder= new ThreadLocal();  
    
    public static void setDataSourceType(String dataSourceType){  
        contextHolder.set(dataSourceType);  
    }  
      
    public static String getDataSourceType(){  
        return (String) contextHolder.get();  
    }  
      
    public static void clearDataSourceType(){  
        contextHolder.remove();  
    }  
}

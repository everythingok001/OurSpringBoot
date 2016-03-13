package w.p.j.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wangdong on 2016/1/1.
 */
@Configuration
@ConfigurationProperties(locations = "classpath:config/project.properties")
public class ProjectEntity {
    private String TempUploadFileDirectory;
    private String RealUploadFileDirectory;

    public String getTempUploadFileDirectory() {
        return TempUploadFileDirectory;
    }

    public void setTempUploadFileDirectory(String tempUploadFileDirectory) {
        TempUploadFileDirectory = tempUploadFileDirectory;
    }

    public String getRealUploadFileDirectory() {
        return RealUploadFileDirectory;
    }

    public void setRealUploadFileDirectory(String realUploadFileDirectory) {
        RealUploadFileDirectory = realUploadFileDirectory;
    }
}

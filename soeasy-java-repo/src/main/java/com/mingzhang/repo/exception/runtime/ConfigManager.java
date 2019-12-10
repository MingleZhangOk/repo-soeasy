package com.mingzhang.repo.exception.runtime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.InputStream;

public class ConfigManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigManager.class);

    private static ConfigManager configManager;

    private Config config;

    private void setConfig(Config config) {
        this.config = config;
    }

    public Config getConfig() {
        return config;
    }

    private ConfigManager() {

    }

    /**
     * 根据配置文件的配置路径获取对一个的配置相关信息
     *
     * @return 返回对应的对象信息
     */
    public synchronized static ConfigManager getInstance() {

        if (configManager == null) {
            configManager = new ConfigManager();

            Yaml yaml = new Yaml();

            String frmsBasePath = System.getenv(CodeString.ENV_CEM_BASE_PATH);

            InputStream in;

            try {
                if (frmsBasePath == null) {
                    in = ConfigManager.class.getResourceAsStream("/" + CodeString.ENV_BASE_CONF_FILENAME);
                } else {
                    if (!frmsBasePath.endsWith("/")) {
                        frmsBasePath += "/";
                    }
                    in = new FileInputStream(frmsBasePath + CodeString.ENV_BASE_CONF_FILENAME);

                }
                Config config = yaml.loadAs(in, Config.class);

            /*    LicenseInfoConfig licenseInfoConfig = config.getLicense();
                if(licenseInfoConfig==null||licenseInfoConfig.getFile()==null
                        ||licenseInfoConfig.getModel() == null ){
                    LOGGER.error("license 配置信息读取错误！请检查配置文件信息");
                    throw new RuntimeException("license 配置信息读取错误！请检查配置文件信息");
                }else{



                    if(!new File(licenseInfoConfig.getFile()).exists()){
                        String file = licenseInfoConfig.getPath() + "/" + licenseInfoConfig.getFile();
                        if(!new File(file).exists()){
                            licenseInfoConfig.setFile( ConfigBuilder.class.getResource("/").getPath() + licenseInfoConfig.getFile() );
                        }else{
                            licenseInfoConfig.setFile(file);
                        }

                    }

                }*/


                LOGGER.info("环境变量路径cemBasePath:{}", frmsBasePath);
                LOGGER.info("配置信息如下：{}", config);
                configManager.setConfig(config);
            } catch (Exception e) {
                LOGGER.error("配置文件错误：" + e.getMessage(), e);
            }
        }
        return configManager;
    }

}

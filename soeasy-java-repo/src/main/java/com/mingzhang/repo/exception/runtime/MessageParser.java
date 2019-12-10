package com.mingzhang.repo.exception.runtime;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MessageParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageParser.class);


    private static PropertiesConfiguration configuration;

    static {
        try {

            Parameters params = new Parameters();

            FileBasedConfigurationBuilder<PropertiesConfiguration> builder =
                    new FileBasedConfigurationBuilder<PropertiesConfiguration>(PropertiesConfiguration.class)
                            .configure(params.properties()
                                    .setFileName("message.properties")
                                    .setEncoding(ConfigManager.getInstance().getConfig().getEncode()));
            configuration = builder.getConfiguration();
        } catch (ConfigurationException e) {
            LOGGER.error("读取消息信息信息错误", e);
        }
    }


    /**
     * get the mesageinformation
     *
     * @param messsageCode
     * @param params
     * @return
     */
    public static String getMessage(String messsageCode, Object... params) {

        String message = configuration.getString(messsageCode);
        if (!StringUtils.isEmpty(message)) {
            message = CommonUtil.getLoggerMessage(message, params);
        }

        return "{errorCode=" + messsageCode + ", message=" + message + "}";
    }

}

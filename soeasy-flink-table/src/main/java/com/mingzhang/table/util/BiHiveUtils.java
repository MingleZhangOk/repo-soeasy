package com.mingzhang.table.util;

import com.mingzhang.table.parameter.CommonParameter;
import org.apache.hadoop.conf.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BiHiveUtils {
	private static Logger logger = LoggerFactory.getLogger(BiHiveUtils.class);
	private static final String ZOOKEEPER_DEFAULT_LOGIN_CONTEXT_NAME = "Client";
	private static final String ZOOKEEPER_SERVER_PRINCIPAL_KEY = "zookeeper.server.principal";
	private static final String ZOOKEEPER_DEFAULT_SERVER_PRINCIPAL = "zookeeper/hadoop";

	
	private static String confFilePath = CommonParameter.confFilePath  + "hiveconf" + File.separator;

	// 初始化配置
	public static void init() throws IOException {
		Configuration CONF = new Configuration();
		Properties clientInfo = null;
		InputStream fileInputStream = null;
		try {
			clientInfo = new Properties();
			String hiveclientProp = confFilePath + "hiveclient.properties";
			File propertiesFile = new File(hiveclientProp);
			fileInputStream = new FileInputStream(propertiesFile);
			clientInfo.load(fileInputStream);
		} catch (Exception e) {
			throw new IOException(e);
		} finally {
			if (fileInputStream != null) {
				fileInputStream.close();
				fileInputStream = null;
			}
		}
		
		String auth = clientInfo.getProperty("auth");
		String USER_NAME = clientInfo.getProperty("userName");
		String USER_KEYTAB_FILE = confFilePath + "user.keytab";
		String KRB5_FILE = confFilePath + "krb5.conf";
		String jaas = confFilePath + "tmp_spark.jaas.conf";
		
		if ("KERBEROS".equalsIgnoreCase(auth)) {
			// 设置客户端的keytab和krb5文件路径
			
			System.setProperty("java.security.auth.login.config", jaas);
			LoginUtil.setJaasConf(ZOOKEEPER_DEFAULT_LOGIN_CONTEXT_NAME, USER_NAME, USER_KEYTAB_FILE);
			LoginUtil.setZookeeperServerPrincipal(ZOOKEEPER_SERVER_PRINCIPAL_KEY,
					ZOOKEEPER_DEFAULT_SERVER_PRINCIPAL);

			// 安全模式
			// Zookeeper登录认证
			LoginUtil.login(USER_NAME, USER_KEYTAB_FILE, KRB5_FILE, CONF);
			logger.info("KERBEROS认证成功");
		}
	}

	
	
}

package com.mingzhang.table.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.flink.table.api.TableEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class BdpUtil {

    private static final Logger LOG = LoggerFactory.getLogger(BdpUtil.class);

    public static boolean isNumberic(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void killApplication(String applicationId) throws Exception {
        if (StringUtils.isBlank(applicationId)) {
            return;
        }
        String shellStr = "sh " + "" + "kill_application.sh " + applicationId;
        LOG.info("执行脚本：" + shellStr);
        Process process = Runtime.getRuntime().exec(shellStr);
        int i = process.waitFor();
        if (i != 0) {
            throw new Exception("kill application failed...");
        }
    }

    public static Integer execShell(String operaType, String srcPath, String distPath) {
////		LOG.info("operaType：" + operaType);
//		System.out.println("operaType：" + operaType);
////		LOG.info("srcPath：" + srcPath);
//		System.out.println("srcPath：" + srcPath);
////		LOG.info("distPath：" + distPath);
//		System.out.println("distPath：" + distPath);
////		String shellStr = "java -jar F:\\bdp-etl-hdfs.jar "+operaType+" "+srcPath+" "+distPath;
//		String shellStr = "cd /zzYanfa_desCode/des-etl && java -jar bdp-etl-hdfs.jar "+operaType+" "+srcPath+" "+distPath;
//		LOG.info("执行脚本：" + shellStr);
//		System.out.println(shellStr);
//		Process	process = Runtime.getRuntime().exec(shellStr);
//		Integer i =process.waitFor();
////		LOG.info(shellStr + "返回值：" + i);
//		System.out.println(shellStr + "返回值：" + i);
//		process.destroy();
//		if( i == 1){
//			throw new Exception("HDFS操作出错");
//		}
//		return i;
//        String confFilePath = EtlProperties.confFilePath;
//
//        HdfsOperate hdfs = new HdfsOperate();
//        hdfs.initHdfs(confFilePath);
//
//        if ("delete".equals(operaType)) {
//            LOG.info("删除" + srcPath);
//            hdfs.deleteHdfsFile(srcPath);
//        } else if ("down".equals(operaType)) {
//            LOG.info("卸载" + srcPath);
//            hdfs.downloadHDFSFile2Local(srcPath, distPath, false);
//        } else if ("up".equals(operaType)) {
//            LOG.info("上传" + srcPath);
//            hdfs.uploadLocalFile2HDFS(srcPath, distPath);
//        } else if ("merge".equals(operaType)) {
//            LOG.info("合并" + srcPath);
//            //hdfs.mergeHdfsFile(srcPath, distPath);
//            hdfs.mergeHdfsFileByEmpty(srcPath, distPath);
//        } else if ("mergeempty".equals(operaType)) {
//            LOG.info("合并" + srcPath);
//            hdfs.mergeHdfsFileByEmpty(srcPath, distPath);
//        } else if ("length".equals(operaType)) {
//            Long length = hdfs.getHdfsLeng(srcPath);
//            LOG.info("文件大小：" + length);
//            System.exit(length.intValue());
//        } else if ("zip".equalsIgnoreCase(operaType)) {//新增压缩
//            hdfs.zipFile(srcPath, distPath);
//            LOG.info("压缩文件:" + srcPath + "到" + distPath);
//        }
        return 0;
    }

    /**
     * 获取指定日期的前一天
     *
     * @param dataDate
     * @return
     * @throws Exception
     */
    public static String getDayBefor(String dataDate) throws Exception {
        Calendar c = Calendar.getInstance();
        Date date = new SimpleDateFormat("yyyyMMdd").parse(dataDate);
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - 1);
        return new SimpleDateFormat("yyyyMMdd").format(c.getTime());
    }

    /**
     * 获取当天上一次成功的时点,针对时分秒的情况，返回该批次当天的最近成功的一次
     *
     * @throws Exception
     */
    public static String getLastSuccessDateTime(Connection conn, String dataDate, String dataTime, String tableId) {
        String result = "";
        Map<String, Object> dataMap = null;
        // ConnectManager.getMap(conn,                "select max(data_time) max_data_time from des_task_instance t where t.data_date='" + dataDate + "' and t.data_time is not null and t.table_id='" + tableId + "' and t.oper_type='1' and exec_sts='2' and t.data_time<'" + dataTime + "'");
        // 如果为最开始的一次，则它的前一次为一个不存在的值
        if (dataMap.get("MAX_DATA_TIME") == null || dataMap.get("MAX_DATA_TIME") == "") {
            result = dataDate + "999999";
        } else {
            result = dataDate + dataMap.get("MAX_DATA_TIME");
        }
        return result;
    }

    /**
     * 获取指定时间的前一小时
     *
     * @param dataDate
     * @param dataTime
     * @return
     * @throws Exception
     */
    public static String getHourBefor(String dataDate, String dataTime) throws Exception {
        Calendar c = Calendar.getInstance();
        Date date = new SimpleDateFormat("yyyyMMddHHmmss").parse(dataDate + dataTime);
        c.setTime(date);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        c.set(Calendar.HOUR_OF_DAY, hour - 1);
        return new SimpleDateFormat("yyyyMMddHHmmss").format(c.getTime());
    }

    /**
     * 实例化sparkSql
     *
     * @param appName
     * @return
     */
    public static TableEnvironment getSqlContext(String appName, String taskInstanceId) throws Exception {
//        SparkConf conf = new SparkConf().setAppName(appName).set("spark.port.maxRetries", "100");
//
////		SparkConf conf = new SparkConf().setAppName(appName);/*.set("spark.port.maxRetries", "100");*/
////		SparkConf conf = new SparkConf().setAppName(appName);
//
//
//        //       SparkConf conf = new SparkConf().setAppName(appName).setMaster("local[*]");
//        SparkContext sc = SparkContext.getOrCreate(conf);
//
//        //本地模式开始
//        String confdir = EtlProperties.confFilePath;
//        sc.hadoopConfiguration().addResource(new org.apache.hadoop.fs.Path(confdir + "core-site.xml"));
//        sc.hadoopConfiguration().addResource(new org.apache.hadoop.fs.Path(confdir + "hdfs-site.xml"));
//        //本地模式结束
//        // SparkContext sc = new SparkContext(conf);
//        SparkSession ss = SparkSession.builder().getOrCreate();
//        // SparkSession ss = new SparkSession(sc);
//        SQLContext sqlContext = ss.sqlContext();
//        //获取目标数据库连接
//        Connection conn = ConnectManager.getConnection(EtlProperties.url, EtlProperties.driverClassName, EtlProperties.username,
//                EtlProperties.password);
//        LOG.info("applicationID：" + sc.applicationId());
//        ConnectManager.excute(conn, "update des_task_instance set application_id = '" + sc.applicationId() + "' where task_instance_id = '" + taskInstanceId + "'");
//        conn.close();
        return null;
    }

    /**
     * 单独使用一个线程执行压缩
     *
     * @throws Exception
     */
    public static void execHdfsZip(String path) throws Exception {
        String[] paths = path.split(",");
        final String srcPath = paths[0];
        final String distPath = paths[1];
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    BdpUtil.execShell("zip", srcPath, distPath);
                } catch (Exception e) {
                    LOG.info(e.getMessage());
                }
            }
        }).start();
    }

    public static String recFieldName(String fieldName, String colOrder) {
        String newFieldName = fieldName;
        if (fieldName.indexOf("$") != -1) {
            newFieldName = "a" + colOrder;
        }
        if (fieldName.indexOf("#") != -1) {
            newFieldName = "a" + colOrder;
        }
        if (fieldName.indexOf("\"") != -1) {
            newFieldName = "a" + colOrder;
        }
        if (isContainChinese(fieldName)) {
            newFieldName = "a" + colOrder;
        }
        return newFieldName;
    }

    public static String fieldFormat(Map<String, Object> map) {
        String result = null;
        String fieldName = map.get("FIELD_NAME").toString();
        String ruleExp = map.get("RULE_EXP") == null ? "" : map.get("RULE_EXP").toString();
        String colOrder = map.get("COL_ORDER").toString();
        String ruleType = map.get("RULE_TYPE") == null ? "" : map.get("RULE_TYPE").toString();
        String colRuleNo = map.get("COL_RULE_NO") == null ? "" : map.get("COL_RULE_NO").toString();
        String fieldType = map.get("FIELD_TYPE").toString();
        String newFieldName = fieldName;
        //为名称中含特殊符号的字段设置别名
        newFieldName = BdpUtil.recFieldName(newFieldName, colOrder);
        //对配置了字段特殊处理的字段进行规则替换
        if ("0".equals(ruleType)) {
            if (ruleExp.length() > 0 && !colRuleNo.equals("4")) {
                result = ruleExp.replace("column", fieldName) + " as " + newFieldName + ",";
            } else if (colRuleNo.equals("4")) {//空字段
                result = "'' as " + newFieldName + ",";
            }
        } else {
            if (fieldType.equals("91") || fieldType.equals("93")) {
                result = "to_char(" + fieldName + ",'yyyy-MM-dd HH24:mi:ss') as " + newFieldName + ",";
            } else if (fieldType.equals("2") || fieldType.equals("3")) {
                result = "to_char(" + fieldName + ") as " + newFieldName + ",";
            } else {
                if (!newFieldName.equals(fieldName)) {
                    result = fieldName + " as " + newFieldName + ",";
                } else {
                    result = fieldName + ",";
                }
            }
        }
        return result;
    }

    /**
     * 字符串是否包含汉字
     *
     * @param str
     * @return
     */
    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

}

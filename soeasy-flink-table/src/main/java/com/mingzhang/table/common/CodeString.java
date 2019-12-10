package com.mingzhang.table.common;

public class CodeString {


    public static final String ENV_CEM_BASE_PATH = "CEM_BASE_PATH";
    public static final String ENV_BASE_CONF_FILENAME = "comp.yml";
    public static final String DATABASE_REDIS_TYPE_SINGLE = "single";
    public static final String DATABASE_REDIS_TYPE_CLUSTER = "cluster";
    public static final String DATABASE_REDIS_TYPE_SHARD = "shard";
    public static final String SPARK_CONF_MODE_LOCAL = "local";
    public static final String SPARK_CONF_MODE_CLUSTER = "cluster";


    public static final String LOG_PATTERN_VARABLE = "\\{\\}";

    public static final String NOSQL_TYPE_ELASTIC = "elastic";

    public static final String CACHE_TYPE_MEMORY = "memory";

    public static final char splite_COMMA = ',';


    /**
     * kafka接收的流水号字段名称
     */
    public static final String TRANS_HEADER_FIELD_TRNO = "PU_TRNO";
    /**
     * kafka里面接收的交易码字段
     */
    public static final String TRANS_HEADER_FIELD_TRCD = "PU_TRCD";
    /**
     * 交易日期
     */
    public static final String TRANS_HEADER_FIELD_TRDT = "PU_TRDT";
    /**
     * 交易时间
     */
    public static final String TRANS_HEADER_FIELD_TRTM = "PU_TRTM";
    /**
     * 交易柜员
     */
    public static final String TRANS_HEADER_FIELD_TRUS = "PU_TRUS";
    /**
     * 交易系统
     */
    public static final String TRANS_HEADER_FIELD_RSYS = "PU_RSYS";
    /**
     * 返回码
     */
    public static final String TRANS_HEADER_FIELD_RSCD = "PU_RSCD";
    /**
     * 交易机构
     */
    public static final String TRANS_HEADER_FIELD_TRBR = "PU_TRBR";
    /**
     * 分行号
     */
    public static final String TRANS_HEADER_FIELD_BRNO = "PU_BRNO";

    public static final String TRANS_HEADER_FIELD_NOSQLDATE = "TRANS_TIME";

    /**
     * 交易时间戳
     */
    public static final String TRANS_HEADER_FIELD_DTTM = "PU_DTTM";

    public static final String TRANS_HEADER_FIELD_NOSQLBUSIKEY = "BIZ_ID";
    public static final String TRASN_COMMON_CODE_AAAAAA = "AAAAAA";

    public static final String ES_TYPE_FIELD = "TABLE_NAME";

    public static final String DATA_TYPE_DATE = "DATE"; //日期类型G="";
    public static final String DATA_TYPE_DATESTAMP = "TIMESTAMP";
    public static final String DATA_TYPE_TIMESTAMP = "TIME";
    public static final String DATA_TYPE_TIME = "TIME";
    public static final String DATA_TYPE_INTEGER = "INTEGER"; //日期类型
    public static final String DATA_TYPE_BIGINT = "BIGINT"; //日期类型
    public static final String DATA_TYPE_NUMBER = "DECIMAL";//数据类型
    public static final String DATA_TYPE_STRING = "VARCHAR";  //VARCHAR
    public static final String DATE_FORMAT_YYYYMMDD = "yyyy-MM-dd";
    public static final String DATE_FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String DATE_FORMAT18_YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT10_YYYYMMDD = "yyyy-MM-dd";
    public static final String DATE_FORMAT16_YYYYMMDDHHMMSS = "yyyy-MM-ddHHmmss";
    public static final String DATE_FORMAT8_YYYYMMDD = "yyyyMMdd";
    public static final String DATETIME_HHMMSS = "HH:mm:ss";

    public static final String RULE_APP_CLASS_AL = "1"; //
    public static final String RULE_APP_CLASS_BR = "2"; //分行
    public static final String RULE_APP_CLASS_PR = "3"; //私有

    public static final String PATTERN_CONDITION_EXPRESSION = "@[A-Z|0-9|_|-|a-z]+";
    public static final String PATTERN_REF_EXPRESSION = "#[A-Z|0-9|_|a-z]+";

    public static final String REF_PARAM_DATASOURCE_TABLE = "B";
    public static final String REF_PARAM_DATASOURCE_TRANS = "A";

    public static final String ARITH_OPR_GT = ">>";
    public static final String ARITH_OPR_EGT = ">=";
    public static final String ARITH_OPR_LT = "<<";
    public static final String ARITH_OPR_ELT = "<=";
    public static final String ARITH_RELOPR_GT = ">";
    public static final char ARITH_RELOPR_CHAR_GT = '>';
    public static final String ARITH_RELOPR_LT = "<";
    public static final char ARITH_RELOPR_CHAR_LT = '<';
    public static final String ARITH_RELOPR_EQ = "==";
    public static final String ARITH_RELOPR_OR = "||";
    public static final String ARITH_RELOPR_RL_EQ = "=";

    public static final String RULE_MATCH_SIGN_CONFIG_OR = "‖";
    public static final String RULE_MATCH_SIGN_REPLCAE_OR = "\\|\\|";

    public static final String PATTERN_DATE_FORMAT_DATE = "~【[0-9]{4}-[0-9]{2}-[0-9]{2}】";
    public static final String PATTERN_DATE_FORMAT_DATETYPE = "1";
    public static final String PATTERN_DATE_FORMAT_DATESTAMP = "~【[0-9]{4}-[0-9]{2}-[0-9]{2}[0-9]{2}:[0-9]{2}:[0-9]{2}】~";
    public static final String PATTERN_DATE_FORMAT_DATESTAMPTYPE = "2";

    public static final String PATTERN_DATE_FORMAT_TIME = "【[0-9]{2}:[0-9]{2}:[0-9]{2}】";
    public static final String PATTERN_DATE_FORMAT_TIMETYPE = "3";

    //实时
    public static final String RULE_TYPE_DOING = "A";

    /*事后*/
    public static final String RULE_TYPE_DONE = "B";

    public static final int RULE_NO_FIX_LENGTH = 5;

    /*试用运行*/
    public static final String ALARM_STAT_TEST = "4";

    public static final String RULE_STATE_TEST = "2";

    public static final String ALARM_STAT_UNDO = "1";

    public static final int ES_MAX_RET_LINES = 100;

    /*警告类预警*/
    public static final String RULE_CLASS_WARNING = "1";
    /*提醒类预警**/
    public static final String RULE_CLASS_ALERT = "2";

    public static final String REDIS_CLUSTER_TYPE_SINGLE = "single";
    public static final String REDIS_CLUSTER_TYPE_CLUSTER = "cluster";


    public static final String EVENT_TRANS_HEADER_FIELD_TRNO = "SERI_NO";
    public static final String EVENT_TRANS_HEADER_FIELD_TRANSCODE = "TRANS_CODE";
    public static final String EVENT_TRANS_FIELD_CUST_NO = "CUST_NO";
    public static final String EVENT_TRANS_FIELD_CUST_ID = "CUST_ID";
    public static final String EVENT_TRANS_FIELD_CUST_NAME = "CUST_NAME";
    public static final String RAW_TRANS_MESSAGE = "rcvMsg";

}

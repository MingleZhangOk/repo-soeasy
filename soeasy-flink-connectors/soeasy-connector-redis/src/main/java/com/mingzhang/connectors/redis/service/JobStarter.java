package com.mingzhang.connectors.redis.service;

import org.apache.flink.api.java.utils.ParameterTool;

/**
 * File Discriptor:
 *
 * @author MingZhang
 * @DATE 2019-12-17  14:47
 **/
public class JobStarter {
    /**
     * 参数说明
     * --event_no    任务编号                                                     -----不区分大小写
     * --mode        任务类型: STREAM (流任务)  BATCH (批任务)  BYPASS(分流任务)  -----不区分大小写
     * --time        批任务指定精确时间(yyyymmddhhmmss): 时间格式为十二位数字
     * --interval    批任务制定范围时间(yyyymmddhhmmss--闭区间): 时间格式为十二位数字，开始时间和结束时间使用一个或多个空格分隔
     * 备注：系统配置文件设定，如果设置jar包外部配置文件的话，添加系统环境变量（ENGINE_HOME），将配置文件放入变量路径下即可。
     * <p>
     * 伪SQL 批量定义
     * WHERE SYSSECOND(yyyyMMddHHmm) BETWEEN NOWTIME AND NOWTIME-1H
     * 当前时间向前N小时的数据
     * WHERE SYSSECOND(yyyyMMddHHmm) = YESTERDAY
     * 昨天的一天的数据
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // TODO: 2019/11/30 数据流输出开启关闭
//        args = new String[]{"--event_no", "bypass"};
//        args = new String[]{"--event_no", "bypass", "--topic", "bypass02"};
//        args = new String[]{"--event_no", "SS_NEPS_SMFN"};
//        TableRegisterService.testFlag = true;
        //stream----------------------------------------------------------------------------------------------
        //SS_NEPS_JQRY  nams_cdc_jqry    SS_NPS_JCDT    nams_cdc_jcdt       SS_NPS_JWRN    nams_cdc_jwrn
        //SS_NEPS_DCRN  nams_cdc_dcrn    SS_NEPS_E142   nams_cdc_e142       SS_NEPS_E150   nams_cdc_e150
        //SS_NEPS_E372  nams_cdc_e372    SS_NEPS_E400   nams_cdc_e400       SS_NPS_JERR    nams_cdc_jerr
        //SS_NES_MADJ   nams_cdc_madj    SS_NEPS_RERR   nams_cdc_rerr       SS_CDC_JBK     nams_cdc_jbk
        //SS_NPS_JRQAB  nams_cdc_jrqab   SS_NEPS_JERRA  nams_cdc_jerra      SS_NEPS_JERRS  nams_cdc_jerrs
        //SS_NPS_MSYS   nams_cdc_msys    SS_NPS_RCHK    nams_cdc_rchk
        //SS_NEPS_SMFN  nams_cdc_smfn    SS_log142                          SS_log150
        //SS_cdc_pub                     SS_cdc_com
        //SS_NPS_JBAL   nams_cdc_jbal    SS_NPS_JBALP    nams_cdc_jbalp
        //SS_NPS_BACC   nams_cdc_bacc    SS_NPS_BBKI     nams_cdc_bbki
        //SS_BPS_SDZB                    SS_BPS_SSPB
        //SS_5Mzero142  nams_cdc_zero    SS_5Mzero150

        //(old stream)SS_log_tps   SS_cdc_bcls   SS_log372   SS_log142   SS_log150  SS_cdc_pay  SS_cdc_pub  SS_cdc_com
        //(test batch)SS_log_tsp   SS_log_test   BT_ES_TEST
        //batch--------------------------------------------------------------------------------------------------
        //PL_LOG_142    PL_LOG_150    PL_LOG_372
        //PL_LOG_E142   PL_LOG_E150   PL_LOG_E372
        //RPT_NEPSJQRYINFO1  RPT_NEPSJQRYINFO2   RPT_NEPSJQRYINFO3

        //redis_sink   SS_cdc_redis
        //{"MSGID":"20191209","INSTGBKID":"C9987183762134","MSGCD":"dsakf9238234","CREDTTM":"NPS.410.001.01","CREDSTAT":"yes"}
        //{"MSGID":"2012309","INSTGBKID":"C1287183762134","MSGCD":"dsakf9238234","CREDTTM":"NPS.410.001.01","CREDSTAT":"yes"}
        //redis_side   SS_side_redis
        //{"MSGID":"20191209","INSTGBKID":"C9987183762134","MSGCD":"dsakf9238234","CREDTTM":"NPS.410.001.01","CREDSTAT":"yes"}
        //insert into redis_side_sink select  MSGID,INSTGBKID,MSGCD,CREDTTM,CREDSTAT from side_redis_002
        //select * from  redis_side_source
        //select * from log_142_source, LATERAL TABLE(engine_orgtable_view(FinCode)) AS S(FinCode1 ,OrgId,OrgName)
        //select * from  redis_side_source ,LATERAL TABLE(redis_side(INSTGBKID,CREDTTM)) AS S(CREDSTAT)
        //FLINK:INSTGBKID:C9987183762134:CREDTTM:NPS.410.001.01:CREDSTAT
        //FLINK:INSTGBKID:C9987183762134:CREDTTM:NPS.410.001.01:CREDSTAT

        //有问题，带修正：SS_log_tps

//        ParameterTool parameterTool = ParameterTool.fromArgs(args);
//        String event = parameterTool.get("event_no");
//
//        if (null == event) {
//            throw new Exception("let me which job ? no ! please set --event_no config！- - ！");
//        }
//
//        if (ModeType.BYPASS.getCode().equalsIgnoreCase(event)) {
//            String topicFlag = parameterTool.get("topic");
//            System.err.println("mode is bypass !");
//            BypassJobStarter.startBypassJob(topicFlag == null ? "bypass01" : topicFlag);
//        } else {
//            String tableType = TableTypeReader.getInstance().getTableTypeByTableName(event);
//            String[] job = null;
//            if (JobType.STREAM.getName().equalsIgnoreCase(tableType)) {
//                job = new String[]{"--event_no", event, "--mode", ModeType.STREAM.getCode()};
//            } else if (JobType.BATCH.getName().equalsIgnoreCase(tableType)) {
//                job = new String[]{"--event_no", event, "--mode", ModeType.BATCH.getCode()};
//            } else {
//                System.err.println(event + "---job is not set config !!! please check this job config");
//                System.exit(0);
//            }
//
//            System.err.println("mode is table !");
//            TableJobStarter.startTableJob(job);
//        }
    }
}
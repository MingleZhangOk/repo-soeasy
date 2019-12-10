package com.mingzhang.table.tests.other_test;/*
package cn.com.frms.engine.work;

import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

import cn.com.frms.engine.base.ConnectionManager;
import cn.com.frms.engine.model.RuleInfo;
import cn.com.frms.engine.po.AlarmDetailEntity;
import cn.com.frms.engine.po.AlarmEntity;
import cn.com.frms.engine.util.CodeString;
import cn.com.frms.engine.util.CommonUtil;

*/
/**
 * 
 * @author Administrator
 *
 *//*

public class SinkFrmsDbservice  extends RichSinkFunction<AlarmEntity>{
	
	  private static Logger logger = Logger.getLogger(SinkFrmsDbservice.class);
 
	private static final long serialVersionUID = 6431315392186906676L;
	private static final String basicSQL =  "insert into FR_PD_ALARMMAIN"
            + "(ALARM_NO,ALARM_DTM,BRANCH_ID,TX_LOG_NO,ORG_ID,TELLER_ID,TX_DT,TX_TM,SUBRULE_ID,"
            + "RULE_ID,RULE_STAR,RULE_TYPE,RULE_NAME,BUS_TYPE,RULE_LEV,CHANNEL_TYPE,RULE_PROCESS,DYNAMIC_VALUE,ALARM_STAT,TASK_STAT"
            + ",ALARM_ID)"
            + "values(?,sysdate,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
	private static final String detailSQL = "insert into  FR_PD_ALARMDETAIL(ALARM_NO,ORIGIN_TAB,HEADER,content)"
            + "values(?,?,?,?) ";
	
	private Connection connection = null;
	private PreparedStatement prst = null;
	private PreparedStatement prstDetail = null;

	@Override
	public void open(Configuration parameters) throws Exception { 
		this.connection = ConnectionManager.getInstance().getConnection();
		this.connection.setAutoCommit(false);
		this.prst = connection.prepareStatement(basicSQL);
		this.prstDetail = connection.prepareStatement( detailSQL); 
		super.open(parameters);
	}

	@Override
	public void invoke(AlarmEntity alarmEntity) throws Exception { 
		
		try {
		
		this.prst.setString(1, alarmEntity.getAlarmNo());
		this.prst.setString(2, alarmEntity.getBranchId());
		this.prst.setString(3, alarmEntity.getTxlogNo()); //CodeString.TRANS_HEADER_FIELD_TRNO
		this.prst.setString(4, alarmEntity.getOrgId());
		this.prst.setString(5, alarmEntity.getTellerId());
		this.prst.setString(6, alarmEntity.getTxDt());
		this.prst.setString(7, alarmEntity.getTxTm());
		this.prst.setString(8, alarmEntity.getSubruleId());
		this.prst.setString(9, alarmEntity.getRuleId());
		this.prst.setString(10, alarmEntity.getRuleStar());
		this.prst.setString(11, alarmEntity.getRuleType());
		this.prst.setString(12, alarmEntity.getRuleName());
		this.prst.setString(13, alarmEntity.getBusType());
		this.prst.setString(14, alarmEntity.getRuleLev());
		this.prst.setString(15, alarmEntity.getChannelType());
		this.prst.setString(16, alarmEntity.getRuleProcess());
		this.prst.setString(17, alarmEntity.getDynamicValue());
		this.prst.setString(18, alarmEntity.getAlarmstat());
		this.prst.setString(19, alarmEntity.getTaskstat());
		this.prst.setLong(20, CommonUtil.getNextSeq()); 
		this.prst.executeUpdate();
		
		AlarmDetailEntity alarmDetailEntity=alarmEntity.getAlarmDetailEntity();
		
		this.prstDetail.setString(1, alarmDetailEntity.getAlarmNo());
		this.prstDetail.setString(2, alarmDetailEntity.getOriginTab());
        if(alarmDetailEntity.getHeaderList()!=null) {
        	this.prstDetail.setClob(3, new StringReader(JSONObject.toJSONString(alarmDetailEntity.getHeaderList())));
        }else {
        	List<Object> list = new ArrayList<Object>();
        	list.add(new Object());
        	this.prstDetail.setClob(3, new StringReader(JSONObject.toJSONString(list)));         	
        }
  
        this.prstDetail.setClob(4, new StringReader(JSONObject.toJSONString(alarmDetailEntity.getContentList())));
        this.prstDetail.execute();
        this.connection.commit();
		}catch(Exception e) {
			
			 try {
	                connection.rollback();
	            } catch (Exception e1) {
	                logger.error(e1.getMessage(), e1);
	            }
	            logger.error("更新交易明细记录失败：交易码:" +  alarmEntity.getTransCode()
	                    + " 交易流水号 :" + alarmEntity.getSeriNo());
	            logger.error(e.getMessage(), e);
			 
		}finally{
			if(this.prst != null) {
				this.prst.close();
			}
			if(this.prstDetail != null) {
				this.prst.close();
			}
			if(this.connection!=null) {
				this.connection.close();
			}
		}
		
		

	}

	@Override
	public void close() throws Exception { 
		
		
		 		
		super.close();
	}

}
*/

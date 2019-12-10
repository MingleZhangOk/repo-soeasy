package com.mingzhang.repo.enums;

public enum EtlErrorCode {

	NULL_POINT("1001","空指针异常"),
	CLASS_CAST("1002","类型强制转换异常"),
	NEGATIVE_ARRAY("1003","数组负下标异常异常"),
	ARRAY_INDEXOUT("1004","数据下标越界异常"),
	EOF_E("1005","文件已结束异常"),
	FILE_NOTFOUND("1006","文件未找到异常"),
	NUMBER_FORMAT("1007","字符串转换为数字异常"),
	SQL_E("1008","操作数据库异常"),
	IO_E("1009","输入输出异常"),
	NO_SUCH_METHOD("1010","方法未找到异常"),
	CLASS_FORMAT("1011","类格式错误"),
	ININTITIALIZER_E("1012","初始化程序错误"),
	INTERNAL_E("1013","实例化错误"),
	OUT_OF_MEMORY("1014","内存不足错误"),
	UNSUPPORTED_E("1015","不支持的类版本错误"),
	
	ERROR("",""),
	UNKNOWN_ERROR("9999","未知错误");
	
	public String value;
	private String desc;
	
	private EtlErrorCode(String value, String desc) {
		this.setValue(value);
		this.setDesc(desc);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	@Override
	public String toString() {
		return "[ERRORCODE-" + this.value + "：" + this.desc + "]";
	}
	
}

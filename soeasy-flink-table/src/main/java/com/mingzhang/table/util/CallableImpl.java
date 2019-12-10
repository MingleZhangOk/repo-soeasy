package com.mingzhang.table.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.Callable;

public class CallableImpl implements Callable<String> {

	private PreparedStatement ps;

	private ResultSet rs;

	public CallableImpl(PreparedStatement ps, ResultSet rs) {
		this.ps = ps;
		this.rs = rs;
	}

	@Override
	public String call() throws Exception {
		String data = "";
		rs = ps.executeQuery();
		if (rs.next()) {
			data = rs.getString(1);
		} else {
			data = "";
		}
		return data;
	}
}

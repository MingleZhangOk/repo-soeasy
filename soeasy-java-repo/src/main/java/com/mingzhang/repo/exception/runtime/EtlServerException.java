package com.mingzhang.repo.exception.runtime;

import com.mingzhang.repo.enums.EtlErrorCode;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class EtlServerException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    public EtlServerException(Exception e) throws EtlServerException {
        e.printStackTrace();
        if (e instanceof NullPointerException) {
            throw new EtlServerException(EtlErrorCode.NULL_POINT);
        } else if (e instanceof ClassCastException) {
            throw new EtlServerException(EtlErrorCode.CLASS_CAST);
        } else if (e instanceof NegativeArraySizeException) {
            throw new EtlServerException(EtlErrorCode.NEGATIVE_ARRAY);
        } else if (e instanceof IndexOutOfBoundsException) {
            throw new EtlServerException(EtlErrorCode.ARRAY_INDEXOUT);
        } else if (e instanceof EOFException) {
            throw new EtlServerException(EtlErrorCode.EOF_E);
        } else if (e instanceof FileNotFoundException) {
            throw new EtlServerException(EtlErrorCode.FILE_NOTFOUND);
        } else if (e instanceof NumberFormatException) {
            throw new EtlServerException(EtlErrorCode.NUMBER_FORMAT);
        } else if (e instanceof SQLException) {
            throw new EtlServerException(EtlErrorCode.SQL_E);
        } else if (e instanceof IOException) {
            throw new EtlServerException(EtlErrorCode.IO_E);
        } else if (e instanceof NoSuchMethodException) {
            throw new EtlServerException(EtlErrorCode.NO_SUCH_METHOD);
        } else {
            throw new EtlServerException(EtlErrorCode.UNKNOWN_ERROR);
        }
    }

    public EtlServerException(Object obj) {
        super(obj.toString());
    }

    public static void main(String[] args) throws Exception {
        try {
            Connection conn = null;
//            DbInfo dbinfo = new DbInfo(conn, "");
//            if (dbinfo.getDriver().equals("")) {
//                System.out.println(11);
//            }
        } catch (Exception e) {
            throw new EtlServerException(e);
        }
    }

}

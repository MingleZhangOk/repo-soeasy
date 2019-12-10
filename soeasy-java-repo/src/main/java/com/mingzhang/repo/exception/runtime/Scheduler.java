package com.mingzhang.repo.exception.runtime;

import java.io.Serializable;

public class Scheduler  implements Serializable {

    private static final long serialVersionUID = -850780902720614512L;

    private String eventNoFileName;


    public String getEventNoFileName() {
        return eventNoFileName;
    }

    public void setEventNoFileName(String eventNoFileName) {
        this.eventNoFileName = eventNoFileName;
    }
}

package com.mapr.springframework.data.maprdb.core;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MapROptions {
    private ObjectMapper objectMapper;
    private String tableRootPath;
    private OjaiConnectionFactory ojaiConnectionFactory;
    private DrillConnectionFactory drillConnectionFactory;
    private boolean autoCreateTables = true;

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String getTableRootPath() {
        return tableRootPath;
    }

    public void setTableRootPath(String tableRootPath) {
        this.tableRootPath = tableRootPath;
    }

    public OjaiConnectionFactory getOjaiConnectionFactory() {
        return ojaiConnectionFactory;
    }

    public void setOjaiConnectionFactory(OjaiConnectionFactory ojaiConnectionFactory) {
        this.ojaiConnectionFactory = ojaiConnectionFactory;
    }

    public DrillConnectionFactory getDrillConnectionFactory() {
        return drillConnectionFactory;
    }

    public void setDrillConnectionFactory(DrillConnectionFactory drillConnectionFactory) {
        this.drillConnectionFactory = drillConnectionFactory;
    }

    public boolean getAutoCreateTables() {
        return autoCreateTables;
    }

    public void setAutoCreateTables(boolean autoCreateTables) {
        this.autoCreateTables = autoCreateTables;
    }
}

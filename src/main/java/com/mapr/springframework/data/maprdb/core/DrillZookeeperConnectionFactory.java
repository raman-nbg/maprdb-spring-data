package com.mapr.springframework.data.maprdb.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class DrillZookeeperConnectionFactory implements DrillConnectionFactory {
    private List<String> serverList;
    private String clusterName;
    private Properties drillProperties;

    public DrillZookeeperConnectionFactory(List<String> serverList, String clusterName) {
        this(serverList, clusterName, new Properties());
    }

    public DrillZookeeperConnectionFactory(List<String> serverList, String clusterName, Properties drillProperties) {
        this.serverList = serverList;
        this.clusterName = clusterName;
        this.drillProperties = drillProperties;
    }

    @Override
    public Connection createConnection() throws SQLException, ClassNotFoundException {
        String connectionString = buildConnectionString();

        Class.forName("org.apache.drill.jdbc.Driver");
        return DriverManager.getConnection(connectionString, drillProperties);
    }

    private String buildConnectionString() {
        String servers = String.join(",", serverList);
        return String.format("jdbc:drill:zk=%s/drill/%s;auth=MAPRSASL", servers, clusterName);
    }
}

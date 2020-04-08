package com.mapr.springframework.data.maprdb.core;

import org.ojai.store.Connection;
import org.ojai.store.DriverManager;

public class DefaultOjaiConnectionFactory implements OjaiConnectionFactory {
    @Override
    public Connection createConnection() {
        return DriverManager.getConnection("ojai:mapr:");
    }
}

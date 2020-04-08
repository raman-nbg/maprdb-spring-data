package com.mapr.springframework.data.maprdb.core;


import org.ojai.store.Connection;

public interface OjaiConnectionFactory {
    Connection createConnection();
}

package com.mapr.springframework.data.maprdb.core;

import java.sql.Connection;
import java.sql.SQLException;

public interface DrillConnectionFactory {
    Connection createConnection() throws SQLException, ClassNotFoundException;
}


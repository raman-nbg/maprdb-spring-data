package com.mapr.springframework.data.maprdb.functional;

import com.mapr.springframework.data.maprdb.model.User;
import com.mapr.springframework.data.maprdb.model.UserWithCustomTable;
import com.mapr.springframework.data.maprdb.core.MapROperations;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ojai.store.Query;
import org.ojai.store.QueryCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MapRTestConfiguration.class })
public class MapROperationsFunctionalTests {

    public final static String TABLE_NAME = "/user";
    public final static String CUSTOM_TABLE_NAME = "/user2";

    @Autowired
    public MapROperations mapROperations;

    @Before
    @After
    public void deleteTables() {
        mapROperations.dropTable(TABLE_NAME);
        mapROperations.dropTable(CUSTOM_TABLE_NAME);
    }

    @Test
    public void tableNotExistsTest() {
        Assert.assertFalse(mapROperations.tableExists(TABLE_NAME));
    }

    @Test
    public void tableExistsTest() {
        mapROperations.createTable(TABLE_NAME);

        Assert.assertTrue(mapROperations.tableExists(TABLE_NAME));
    }

    @Test
    public void createTableByEntityTest() {
        mapROperations.createTable(User.class);

        Assert.assertTrue(mapROperations.tableExists(User.class));
    }

    @Test
    public void createCustomTableByEntityTest() {
        mapROperations.createTable(UserWithCustomTable.class);

        Assert.assertTrue(mapROperations.tableExists(CUSTOM_TABLE_NAME));
    }

    @Test
    public void getStoreTest() {
        mapROperations.createTable(TABLE_NAME);

        Assert.assertNotNull(mapROperations.getStore(TABLE_NAME));
    }

    @Test
    public void getStoreByEntity() {
        mapROperations.createTable(User.class);

        Assert.assertNotNull(mapROperations.getStore(User.class));
    }

    @Test
    public void executeQueryTest() {
        mapROperations.createTable(User.class);

        Query query = mapROperations.getConnection().newQuery().select("_id").orderBy("_id").limit(1).build();

        List<User> list = mapROperations.execute(query, User.class);

        Assert.assertNotNull(list);
    }

    @Test
    public void executeQueryConditionTest() {
        mapROperations.createTable(User.class);

        QueryCondition condition = mapROperations.getConnection().newCondition().build();

        List<User> list = mapROperations.execute(condition, User.class);

        Assert.assertNotNull(list);
    }

}

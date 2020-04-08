package com.mapr.springframework.data.maprdb.repository.query;

import com.mapr.springframework.data.maprdb.core.MapROperations;
import org.ojai.store.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.query.QueryMethod;
import org.springframework.data.repository.query.RepositoryQuery;
import java.util.List;

public abstract class AbstractMapRQuery implements RepositoryQuery {
    private final static Logger LOGGER = LoggerFactory.getLogger(AbstractMapRQuery.class);

    protected final MapROperations operations;
    protected final MapRQueryMethod method;
    protected final Class<?> domainClass;

    public AbstractMapRQuery(MapRQueryMethod method, Class<?> domainClass, MapROperations operations) {

        this.method = method;
        this.operations = operations;
        this.domainClass = domainClass;
    }

    @Override
    public Object execute(Object[] parameters) {

        if(isDeleteQuery()) {
            operations.execute(convertToQuery(parameters), domainClass).forEach(operations::remove);
            return null;
        }

        return convertToFormat(operations.execute(convertToQuery(parameters), domainClass));
    }

    @Override
    public QueryMethod getQueryMethod() {
        return method;
    }

    protected abstract Query convertToQuery(Object[] parameters);

    protected abstract boolean isCountQuery();

    protected abstract boolean isDeleteQuery();

    protected abstract boolean isExistsQuery();

    protected Object convertToFormat(List records) {

        if(method.getReturnedObjectType() == null)
            return null;

        if(method.isCollectionQuery())
            return records;

        if(method.isStreamQuery())
            return records.stream();

        // TODO Make normal count queries
        if(isCountQuery()) {
            LOGGER.warn("Count queries are working not in optimal way, please don't use them without necessity");
            return records.size();
        }

        // TODO Make normal exists queries
        if(isExistsQuery()) {
            LOGGER.warn("Exists queries are working not in optimal way, please don't use them without necessity");
            return records.size() > 0;
        }

        return records.size() > 0 ? records.get(0) : null;
    }

}

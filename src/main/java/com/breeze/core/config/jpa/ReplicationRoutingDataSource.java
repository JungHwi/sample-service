package com.breeze.core.config.jpa;

import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Log4j2
public class ReplicationRoutingDataSource extends AbstractRoutingDataSource {

    public final static String MASTER = "master";
    public final static String SLAVE = "slave";

    @Override
    protected Object determineCurrentLookupKey() {
        String dataSourceType = TransactionSynchronizationManager.isCurrentTransactionReadOnly() ? SLAVE : MASTER;
        log.debug("RDB 원천 데이터 유형: {}", dataSourceType);
        return dataSourceType;
    }
}

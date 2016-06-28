package com.ns.common.dao.spi.db;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 * Created by xuezhucao on 16/6/14.
 */
public abstract class AbsNSJdbcDao<T> {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public abstract String getTableName();
    public abstract String[] getReadFields();
    public abstract String[] getWriteFields();
    public abstract RowMapper<T> getRowMapper();

    private String getReadFieldStr() {
        return StringUtils.join(getReadFields(), ", ");
    }

    private String getQueryStr(String[] conds) {
        String cond = StringUtils.join(conds, " and ");
        return String.format("select %s from %s where %s",
                getReadFieldStr(), getTableName(), cond);
    }

    public T queryObject(String[] conds, Object... params) {
        return jdbcTemplate.queryForObject(getQueryStr(conds),
                params, getRowMapper());
    }
}

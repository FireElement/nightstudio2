package com.ns.common.dao.spi.db;

import com.ns.common.util.exception.sys.ParameterException;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

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
    public abstract Object[] getInsertParams(T t);
    public abstract void setId(T t, Number id);

    private String getReadFieldStr() {
        return StringUtils.join(getReadFields(), ", ");
    }

    private String getWriteFieldStr() {
        return StringUtils.join(getWriteFields(), ", ");
    }

    private String getWriteParamStr() {
        String[] writeFields = getWriteFields();
        if (ArrayUtils.isEmpty(writeFields)) {
            return "";
        }
        String[] params = new String[writeFields.length];
        for (int i = 0; i < params.length; i++) {
            params[i] = "?";
        }
        return StringUtils.join(params, ",");
    }


    private String getQueryStr(String[] conds) {
        if (ArrayUtils.isEmpty(conds)) {
            return String.format("select %s from %s",
                    getReadFieldStr(), getTableName());
        } else {
            String cond = StringUtils.join(conds, " and ");
            return String.format("select %s from %s where %s",
                    getReadFieldStr(), getTableName(), cond);
        }
    }

    public T queryObj(String[] conds, Object... params) {
        return jdbcTemplate.queryForObject(getQueryStr(conds),
                params, getRowMapper());
    }

    public List<T> queryList() {
        return jdbcTemplate.query(getQueryStr(null), getRowMapper());
    }

    public List<T> queryList(String[] conds, Object... params) {
        return jdbcTemplate.query(getQueryStr(conds),
                params, getRowMapper());
    }

    public T insert(T t) throws Throwable {
        if (t == null) {
            throw new ParameterException("object is null");
        }
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                (PreparedStatementCreator) con -> {
                    PreparedStatement ps = jdbcTemplate
                            .getDataSource()
                            .getConnection()
                            .prepareStatement(String.format("insert into %s (%s) values (%s)",
                                    getTableName(), getWriteFieldStr(), getWriteParamStr()),
                                    Statement.RETURN_GENERATED_KEYS);
                    int i = 0;
                    Object[] params = getInsertParams(t);
                    for (; i < params.length; i++) {
                        ps.setObject(i + 1, params[i]);
                    }
                    return ps;
                }, keyHolder);
        setId(t, keyHolder.getKey());
        return t;
    }

    public int update(String[] updates, String[] conds, Object... params) throws Throwable {
        if (ArrayUtils.isEmpty(updates)) {
            throw new ParameterException("updates is null");
        }
        for (int i = 0; i < updates.length; i++) {
            updates[i] += " = ?";
        }
        String sql;
        if (ArrayUtils.isEmpty(conds)) {
            sql = String.format("update %s set %s",
                    getTableName(),
                    StringUtils.join(updates, ","));
        } else {
            sql = String.format("update %s set %s where %s",
                    getTableName(),
                    StringUtils.join(updates, ","),
                    StringUtils.join(conds, " and "));
        }
        return jdbcTemplate.update(sql, params);
    }
}

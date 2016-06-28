package com.ns.common.dao;

import com.ns.common.bean.OpUser;
import com.ns.common.dao.spi.db.AbsNSJdbcDao;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

/**
 * Created by xuezhucao on 16/6/14.
 */
@Service
public class OpUserJdbcDao extends AbsNSJdbcDao<OpUser> {

    private RowMapper<OpUser> mapper = (rs, i) -> new OpUser(
            rs.getLong("id"),
            rs.getString("name"),
            rs.getString("passwd"),
            rs.getTimestamp("create_time"),
            rs.getTimestamp("update_time"));

    @Override
    public String getTableName() {
        return "op_user";
    }

    @Override
    public String[] getReadFields() {
        return new String[] {
            "id", "name", "passwd", "create_time", "update_time"
        };
    }

    @Override
    public String[] getWriteFields() {
        return new String[0];
    }

    @Override
    public RowMapper<OpUser> getRowMapper() {
        return mapper;
    }

    public OpUser getByName(String name) {
        return queryObject(new String[] {"name = ?"}, name);
    }
}

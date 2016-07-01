package com.ns.common.dao;

import com.ns.common.bean.OpUser;
import com.ns.common.dao.spi.db.AbsNSJdbcDao;
import com.ns.common.util.array.ArrayUtil;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public RowMapper<OpUser> getRowMapper() {
        return mapper;
    }

    @Override
    public String getTableName() {
        return "op_user";
    }

    @Override
    public String[] getReadFields() {
        return ArrayUtil.addAll(getWriteFields(), new String[] {
                "id", "update_time"
        });
    }

    @Override
    public String[] getWriteFields() {
        return new String[] {
                "name", "passwd", "create_time"
        };
    }

    @Override
    public Object[] getInsertParams(OpUser opUser) {
        return new Object[] {
                opUser.getName(),
                opUser.getPasswd(),
                opUser.getCreateTime(),
        };
    }

    @Override
    public void setId(OpUser opUser, Number id) {
        opUser.setId(id.longValue());
    }

    public OpUser getByName(String name) {
        return queryObj(new String[] {"name = ?"}, name);
    }

    public List<OpUser> getAll() {
        return queryList();
    }

    public OpUser update(OpUser opUser) throws Throwable {
        super.update(new String[] {
                "name", "passwd"
        },
        new String[]{
                "id = ?"
        },
        opUser.getName(), opUser.getPasswd(), opUser.getId());
        return opUser;
    }
}

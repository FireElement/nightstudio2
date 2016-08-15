package com.ns.common.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by xuezhucao on 16/8/15.
 */
@Service
public class TimerTaskJdbcDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public int modifyProcessor(String processor, Date processTime, int count) {
        String sql = "UPDATE timer_task SET processor = ? WHERE processor IS NULL AND process_time <= ? ORDER BY process_time ASC limit ?";
        return jdbcTemplate.update(sql, processor, processTime, count);
    }
}

package com.interswitch.valuevoucherz.api.dao;

import com.interswitch.valuevoucherz.api.exception.RequestException;
import com.interswitch.valuevoucherz.api.model.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
public abstract class AbstractBaseDao<T> implements BaseDao<T> {

    protected JdbcTemplate jdbcTemplate;
    protected SimpleJdbcCall createSingle, update, delete, find, findAll;

    protected final String SINGLE_RESULT = "object";
    protected final String MULTIPLE_RESULT = "list";
    protected static final String RESULT_COUNT = "count";

    public abstract void setDataSource(DataSource dataSource);

    public T update(T model) throws DataAccessException {
        SqlParameterSource in = new BeanPropertySqlParameterSource(model);
        return getSingleResult(in, update);
    }

    public T find(String code) {
        SqlParameterSource in = new MapSqlParameterSource().addValue("setCode", code);
        return getSingleResult(in, find);
    }

    public Page<T> findAll(int pageNumber, int pageSize) {
        SqlParameterSource in = new MapSqlParameterSource().addValue("pageNumber", pageNumber).addValue("pageSize", pageSize);
        Map<String, Object> m = findAll.execute(in);
        List<T> content = (List<T>) m.get(MULTIPLE_RESULT);
        List<Long> countList = (List<Long>) m.get(RESULT_COUNT);

        long count = 0;
        if (Objects.nonNull(countList) && !countList.isEmpty()) {
            count = countList.get(0);
        }
        Page<T> page = new Page<>(count, content);
        return page;
    }

    public T getSingleResult(SqlParameterSource in, SimpleJdbcCall jdbcCall){
        Map<String, Object> m = jdbcCall.execute(in);
        List<T> list = (List<T>) m.get(SINGLE_RESULT);
        if (list == null || list.isEmpty()) {
            throw new RequestException("Gift Voucher Not Found");
        }
        return list.get(0);
    }
}

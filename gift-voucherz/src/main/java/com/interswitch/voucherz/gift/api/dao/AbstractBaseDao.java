package com.interswitch.voucherz.gift.api.dao;

import com.interswitch.voucherz.gift.api.exception.RequestException;
import com.interswitch.voucherz.gift.api.model.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
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
    protected SimpleJdbcCall createSingle, update, delete, get, getAll;

    protected final String SINGLE_RESULT = "object";
    protected final String MULTIPLE_RESULT = "list";
    protected static final String RESULT_COUNT = "count";

    public abstract void setDataSource(DataSource dataSource);

    public boolean update(T model) throws DataAccessException {
        SqlParameterSource in = new BeanPropertySqlParameterSource(model);
        return withReturnValue(in, update);
    }

    public T get(String code) {
        SqlParameterSource in = new MapSqlParameterSource().addValue("setCode", code);
        return withSingleResultSet(in, get);
    }

    public Page<T> getAll(int pageNumber, int pageSize) {
        SqlParameterSource in = new MapSqlParameterSource().addValue("pageNumber", pageNumber).addValue("pageSize", pageSize);
        List<T> content = withMultipleResultSet(in, getAll);
        long count = content.size();
        Page<T> page = new Page<>(count, content);
        return page;
    }

    protected T withSingleResultSet(SqlParameterSource in, SimpleJdbcCall jdbcCall){
        Map<String, Object> m = jdbcCall.execute(in);
        List<T> list = getJdbcResult(m, SINGLE_RESULT);
        return list.get(0);
    }

    protected List<T> withMultipleResultSet(SqlParameterSource in, SimpleJdbcCall jdbcCall) {
        Map<String, Object> m = jdbcCall.execute(in);
        List<T> list = getJdbcResult(m, MULTIPLE_RESULT);
        return list;

    }

    protected Boolean withReturnValue(SqlParameterSource in, SimpleJdbcCall jdbcCall) {
        Map<String, Object> m = jdbcCall.execute(in);
        Integer returnValue = (Integer) m.get("RETURN_VALUE");
        return (returnValue >=0);
    }

    protected List<T> getJdbcResult(Map<String, Object> m, String resultSet) {
        List<T> list = (List<T>) m.get(resultSet);
        if (list == null || list.isEmpty()) {
            throw new RequestException(HttpStatus.NOT_FOUND, "Voucher(s) Not Found");
        }
        return list;
    }


}

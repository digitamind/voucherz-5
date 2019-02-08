package com.interswitch.voucherz.authservice.dao.impl;

import com.interswitch.voucherz.authservice.dao.AbstractBaseDao;
import com.interswitch.voucherz.authservice.dao.CampaignDao;
import com.interswitch.voucherz.authservice.dao.util.RowCountMapper;
import com.interswitch.voucherz.authservice.models.Campaign;
import com.interswitch.voucherz.authservice.models.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class CampaignDaoImpl extends AbstractBaseDao<Campaign> implements CampaignDao {

    protected SimpleJdbcCall findByName, delete;

    @Autowired
    @Override
    public void setDataSource(@Qualifier(value = "dataSource") DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        create = new SimpleJdbcCall(dataSource).withProcedureName("uspInsertCampaign").withReturnValue();
        findByName = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspGetCampaignByName")
                .returningResultSet(SINGLE_RESULT, new BeanPropertyRowMapper<>(Campaign.class));
        update = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspUpdateCampaign").withReturnValue();
        delete = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspDeleteCampaign").withReturnValue();
        findAll = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspGellAllCampaign")
                //.returningResultSet(RESULT_COUNT, new RowCountMapper())
                .returningResultSet(MULTIPLE_RESULT, new BeanPropertyRowMapper<>(Campaign.class));
    }

    @Override
    public List<Campaign> findAllCampaign(long merchantId) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("merchantId", merchantId);
        Map<String, Object> m = findAll.execute(in);
        List<Campaign> content = (List<Campaign>) m.get(MULTIPLE_RESULT);

        return content;
    }

    @Override
    public Campaign createCampaign(Campaign campaign) {
        SqlParameterSource in = new BeanPropertySqlParameterSource(campaign);
        Map<String, Object> m = create.execute(in);
        long id = (long) m.get("id");
        campaign.setId(id);
        return campaign;
    }

    @Override
    public Campaign getCampaignByName(String name, long merchantId) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("name", name)
                .addValue("merchantId", merchantId);
        Map<String, Object> m = findByName.execute(in);
        List<Campaign> list = (List<Campaign>) m.get(SINGLE_RESULT);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public void deleteCampaign(String name, long merchantId) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("name", name)
                .addValue("merchantId", merchantId);
        delete.execute(in);
    }


}

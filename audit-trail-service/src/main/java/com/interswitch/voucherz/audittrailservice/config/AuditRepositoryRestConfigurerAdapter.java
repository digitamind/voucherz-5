package com.interswitch.voucherz.audittrailservice.config;

import com.interswitch.voucherz.audittrailservice.model.AuditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class AuditRepositoryRestConfigurerAdapter extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config)
    {
        super.configureRepositoryRestConfiguration(config);
        config.exposeIdsFor(AuditEvent.class);
    }
}

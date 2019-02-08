package com.interswitch.voucherz.authservice.dao;

import com.interswitch.voucherz.authservice.models.Campaign;
import com.interswitch.voucherz.authservice.models.Page;

import java.util.List;

public interface CampaignDao extends BaseDao<Campaign> {
    Campaign getCampaignByName(String name, long merchantId);
    void deleteCampaign(String name, long merchantId);
    List<Campaign> findAllCampaign(long merchantId);
    Campaign createCampaign(Campaign campaign);
}

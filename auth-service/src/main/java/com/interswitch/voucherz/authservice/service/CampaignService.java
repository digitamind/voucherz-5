package com.interswitch.voucherz.authservice.service;


import com.interswitch.voucherz.authservice.models.Campaign;

import java.util.List;

public interface CampaignService {
    public Campaign createCampaign(Campaign campaign);
    public Campaign updateCampaign(Campaign campaign);
    public Campaign findCampaignByName(String name, long merchantId);
    public void deleteCampaign(String name, long merchantId);
    public List<Campaign> findAllCampaign(long merchantId);
}

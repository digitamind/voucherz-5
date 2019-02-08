package com.interswitch.voucherz.authservice.service.impl;

import com.interswitch.voucherz.authservice.Exception.CustomException;
import com.interswitch.voucherz.authservice.dao.CampaignDao;
import com.interswitch.voucherz.authservice.models.Campaign;
import com.interswitch.voucherz.authservice.models.Page;
import com.interswitch.voucherz.authservice.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignServiceImpl implements CampaignService {

    @Autowired
    CampaignDao campaignDao;

    @Override
    public Campaign createCampaign(Campaign campaign) {
        return campaignDao.createCampaign(campaign);
    }

    @Override
    public Campaign updateCampaign(Campaign campaign) {
        campaignDao.update(campaign);
        return campaign;
    }

    @Override
    public Campaign findCampaignByName(String name, long merchantId) {
        return campaignDao.getCampaignByName(name, merchantId);
    }

    @Override
    public void deleteCampaign(String name, long merchantId) {
        Campaign campaign = this.findCampaignByName(name, merchantId);
        if(campaign == null){
            throw new CustomException("Campaign does not exist", HttpStatus.NOT_FOUND);
        }
        campaignDao.deleteCampaign(name, merchantId);
    }

    @Override
    public List<Campaign> findAllCampaign(long merchantId) {
        return campaignDao.findAllCampaign(merchantId);
    }
}

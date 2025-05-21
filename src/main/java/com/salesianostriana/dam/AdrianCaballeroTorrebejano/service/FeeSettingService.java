package com.salesianostriana.dam.AdrianCaballeroTorrebejano.service;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.FeeSetting;

@Service
public class FeeSettingService {

    private FeeSetting currentSetting = new FeeSetting(45.0, 15.0, 10.0, 0, 0);

    public FeeSetting getCurrentSetting() {
        return currentSetting;
    }

    public void updateSetting(FeeSetting updated) {
        currentSetting.setBasePrice(updated.getBasePrice());
        currentSetting.setSiblingDiscount(updated.getSiblingDiscount());
        currentSetting.setEarlyRegistrationDiscount(updated.getEarlyRegistrationDiscount());
        currentSetting.setDaysBeforeCourseStartsSetByUser(updated.getDaysBeforeCourseStartsSetByUser());
    }
}
/*
 * HwCertDao.java
 *
 * This file is the exclusive property of RedHat.
 * Reproduction and Unauthorized use are forbidden.
 *
 * Copyright (C) 2014 RedHat. All rights reserved.
 */
package com.redhat.dao;

import java.util.List;

import com.redhat.model.HwCert;

/**
 *
 */
public interface HwCertDao extends GenericDao<HwCert, Long> {

    public List<HwCert> getCertsByModel(String modelName);
}

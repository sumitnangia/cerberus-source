/**
 * Cerberus Copyright (C) 2013 - 2017 cerberustesting
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This file is part of Cerberus.
 *
 * Cerberus is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Cerberus is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Cerberus.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.cerberus.crud.service.impl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.cerberus.crud.dao.IApplicationDAO;
import org.cerberus.crud.dao.ICountryEnvParamDAO;
import org.cerberus.crud.dao.ICountryEnvironmentParametersDAO;
import org.cerberus.crud.entity.TestCase;
import org.cerberus.exception.CerberusException;
import org.cerberus.crud.factory.IFactoryEnvironment;
import org.cerberus.crud.service.ICountryEnvironmentService;
import org.cerberus.crud.service.ITestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * {Insert class description here}
 *
 * @author Tiago Bernardes
 * @version 1.0, 31/12/2012
 * @since 2.0.0
 */
@Service
public class CountryEnvironmentService implements ICountryEnvironmentService {

    @Autowired
    private IApplicationDAO applicationDAO;
    @Autowired
    private ICountryEnvironmentParametersDAO countryEnvironmentParametersDAO;
    @Autowired
    private ICountryEnvParamDAO countryEnvParamDAO;
    @Autowired
    private ITestCaseService testCaseService;
    @Autowired
    private IFactoryEnvironment factoryEnvironment;

    @Override
    public List<String[]> getEnvironmentAvailable(String test, String testCase, String country) {
        try {
            List<String[]> list = null;

            TestCase tc = this.testCaseService.findTestCaseByKey(test, testCase);

            if (tc != null) {
                list = this.countryEnvironmentParametersDAO.getEnvironmentAvailable(country, tc.getApplication());
            }

            return list;
        } catch (CerberusException ex) {
            Logger.getLogger(CountryEnvironmentService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

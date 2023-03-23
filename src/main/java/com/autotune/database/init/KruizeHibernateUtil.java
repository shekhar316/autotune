/*******************************************************************************
 * Copyright (c) 2023 Red Hat, IBM Corporation and others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.autotune.database.init;


import com.autotune.utils.KruizeConstants;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class KruizeHibernateUtil {
    private static final SessionFactory sessionFactory;
    private static final Logger LOGGER = LoggerFactory.getLogger(KruizeHibernateUtil.class);

    static {
        SessionFactory sfTemp = null;
        try {
            String configFile = System.getenv(KruizeConstants.DBConstants.CONFIG_FILE);
            JSONObject databaseObj = null;
            try {
                InputStream is = new FileInputStream(configFile);
                String jsonTxt = new String(is.readAllBytes(), StandardCharsets.UTF_8);
                JSONObject jsonObj = new JSONObject(jsonTxt);
                databaseObj = jsonObj.getJSONObject(KruizeConstants.DBConstants.CONFIG_FILE_DB_KEY);
            } catch (FileNotFoundException fileNotFoundException) {
                LOGGER.error("DB init failed: {}", fileNotFoundException.getMessage());
                try {
                    databaseObj = new JSONObject();
                    for (String env : Arrays.asList(KruizeConstants.DBConstants.DB_DRIVER,
                            KruizeConstants.DBConstants.HOSTNAME,
                            KruizeConstants.DBConstants.PORT,
                            KruizeConstants.DBConstants.NAME,
                            KruizeConstants.DBConstants.USERNAME,
                            KruizeConstants.DBConstants.PASSWORD)) {
                        if (null == System.getenv(env)) {
                            throw new Exception("env: " + env + " not set");
                        } else {
                            databaseObj.put(env, System.getenv(env));
                        }
                    }
                } catch (Exception e) {
                    databaseObj = null;
                    LOGGER.error("DB connection failed: {}", e.getMessage());
                    LOGGER.error("Either {} parameter or following env should be set for db integration {},{},{},{},{}",
                            KruizeConstants.DBConstants.CONFIG_FILE,
                            KruizeConstants.DBConstants.HOSTNAME,
                            KruizeConstants.DBConstants.PORT,
                            KruizeConstants.DBConstants.NAME,
                            KruizeConstants.DBConstants.USERNAME,
                            KruizeConstants.DBConstants.PASSWORD);
                }
            } catch (Exception e) {
                LOGGER.error("DB connection failed: {}", e.getMessage());
                LOGGER.error("Either {} parameter or following env should be set to proceed {},{},{},{},{}",
                        KruizeConstants.DBConstants.CONFIG_FILE,
                        KruizeConstants.DBConstants.HOSTNAME,
                        KruizeConstants.DBConstants.PORT,
                        KruizeConstants.DBConstants.NAME,
                        KruizeConstants.DBConstants.USERNAME,
                        KruizeConstants.DBConstants.PASSWORD);
            }
            if (null != databaseObj) {
                Configuration configuration = new Configuration().configure();
                String connectionURL = System.getenv(KruizeConstants.DBConstants.DB_DRIVER) +
                        databaseObj.getString(KruizeConstants.DBConstants.HOSTNAME) +
                        ":" + databaseObj.getInt(KruizeConstants.DBConstants.PORT) +
                        "/" + databaseObj.getString(KruizeConstants.DBConstants.NAME);

                configuration.setProperty("hibernate.connection.url", connectionURL);
                configuration.setProperty("hibernate.connection.username", databaseObj.getString(KruizeConstants.DBConstants.USERNAME));
                configuration.setProperty("hibernate.connection.password", databaseObj.getString(KruizeConstants.DBConstants.PASSWORD));
                sfTemp = configuration.buildSessionFactory();
                LOGGER.info("DB build session is successful !");
            } else {
                LOGGER.info("DB build session failed !");
            }
        } catch (Exception e) {
            LOGGER.error("DB init failed: {}", e.getMessage());
            e.printStackTrace();
        } finally {
            sessionFactory = sfTemp;
        }
    }

    public static Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
/*******************************************************************************
 * Copyright (c) 2022 Red Hat, IBM Corporation and others.
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

package com.autotune.analyzer.services;

import com.autotune.analyzer.exceptions.AutotuneResponse;
import com.autotune.common.target.kubernetes.service.KubernetesServices;
import com.autotune.common.target.kubernetes.service.impl.KubernetesServicesImpl;
import com.autotune.utils.AnalyzerConstants;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ConcurrentHashMap;

import static com.autotune.utils.AnalyzerConstants.ServiceConstants.CHARACTER_ENCODING;
import static com.autotune.utils.AnalyzerConstants.ServiceConstants.JSON_CONTENT_TYPE;

/**
 * Rest API used to recommend right configuration.
 */
@WebServlet(asyncSupported = true)
public class Recommendation extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(Recommendation.class);
    ConcurrentHashMap<String, JsonObject> mainAutoTuneOperatorMap = new ConcurrentHashMap<>();
    KubernetesServices kubernetesServices = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.mainAutoTuneOperatorMap = (ConcurrentHashMap<String, JsonObject>) getServletContext().getAttribute(AnalyzerConstants.EXPERIMENT_MAP);
        this.kubernetesServices = new KubernetesServicesImpl();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(JSON_CONTENT_TYPE);
        response.setCharacterEncoding(CHARACTER_ENCODING);
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter out = response.getWriter();
        String temp = "{\n" +
                "  \"experimentName\": \"quarkus-resteasy-autotune-min-http-response-time-db4\",\n" +
                "  \"namespace\": \"default\",\n" +
                "  \"deploymentName\": \"tfb-qrh-sample\",\n" +
                "  \"containers\": {\n" +
                "    \"tfb-server-1\": {\n" +
                "      \"name\": \"tfb-server-1\",\n" +
                "      \"recommendation\": {\n" +
                "        \"2023-01-15 17:53:40.498\": {\n" +
                "          \"Short Term\": {\n" +
                "            \"monitoringStartTime\": \"jan 01, 2023, 5:53:40 PM\",\n" +
                "            \"monitoringEndTime\": \"Jan 02, 2023, 12:24:04 AM\",\n" +
                "            \"podsCount\": 0,\n" +
                "            \"confidence_level\": 0.0,\n" +
                "            \"config\": {\n" +
                "              \"max\": {\n" +
                "                \"memory\": {\n" +
                "                  \"amount\": 0.0\n" +
                "                },\n" +
                "                \"cpu\": {\n" +
                "                  \"amount\": 0.0\n" +
                "                }\n" +
                "              },\n" +
                "              \"capacity\": {\n" +
                "                \"memory\": {\n" +
                "                  \"amount\": 0.0\n" +
                "                },\n" +
                "                \"cpu\": {\n" +
                "                  \"amount\": 0.0\n" +
                "                }\n" +
                "              }\n" +
                "            }\n" +
                "          },\n" +
                "          \"Medium Term\": {\n" +
                "            \"monitoringStartTime\": \"jan 01, 2023, 5:53:40 PM\",\n" +
                "            \"monitoringEndTime\": \"jan 07, 2023, 12:24:04 AM\",\n" +
                "            \"podsCount\": 0,\n" +
                "            \"confidence_level\": 0.0,\n" +
                "            \"config\": {\n" +
                "              \"max\": {\n" +
                "                \"memory\": {\n" +
                "                  \"amount\": 0.0\n" +
                "                },\n" +
                "                \"cpu\": {\n" +
                "                  \"amount\": 0.0\n" +
                "                }\n" +
                "              },\n" +
                "              \"capacity\": {\n" +
                "                \"memory\": {\n" +
                "                  \"amount\": 0.0\n" +
                "                },\n" +
                "                \"cpu\": {\n" +
                "                  \"amount\": 0.0\n" +
                "                }\n" +
                "              }\n" +
                "            }\n" +
                "          },\n" +
                "          \"Long Term\": {\n" +
                "            \"monitoringStartTime\": \"jan 01, 2023, 5:53:40 PM\",\n" +
                "            \"monitoringEndTime\": \"jan 15, 2023, 12:24:04 AM\",\n" +
                "            \"podsCount\": 0,\n" +
                "            \"confidence_level\": 0.0,\n" +
                "            \"config\": {\n" +
                "              \"max\": {\n" +
                "                \"memory\": {\n" +
                "                  \"amount\": 0.0\n" +
                "                },\n" +
                "                \"cpu\": {\n" +
                "                  \"amount\": 0.0\n" +
                "                }\n" +
                "              },\n" +
                "              \"capacity\": {\n" +
                "                \"memory\": {\n" +
                "                  \"amount\": 0.0\n" +
                "                },\n" +
                "                \"cpu\": {\n" +
                "                  \"amount\": 0.0\n" +
                "                }\n" +
                "              }\n" +
                "            }\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "    },\n" +
                "    \"tfb-server-0\": {\n" +
                "      \"name\": \"tfb-server-0\",\n" +
                "      \"recommendation\": {\n" +
                "        \"2029-10-26 17:53:40.498\": {\n" +
                "          \"Short Term\": {\n" +
                "            \"monitoringStartTime\": \"jan 01, 2023, 5:53:40 PM\",\n" +
                "            \"monitoringEndTime\": \"jan 01, 2023, 12:24:04 AM\",\n" +
                "            \"podsCount\": 0,\n" +
                "            \"confidence_level\": 0.0,\n" +
                "            \"config\": {\n" +
                "              \"max\": {\n" +
                "                \"memory\": {\n" +
                "                  \"amount\": 0.0\n" +
                "                },\n" +
                "                \"cpu\": {\n" +
                "                  \"amount\": 0.0\n" +
                "                }\n" +
                "              },\n" +
                "              \"capacity\": {\n" +
                "                \"memory\": {\n" +
                "                  \"amount\": 0.0\n" +
                "                },\n" +
                "                \"cpu\": {\n" +
                "                  \"amount\": 0.0\n" +
                "                }\n" +
                "              }\n" +
                "            }\n" +
                "          },\n" +
                "          \"Mid Term\": {\n" +
                "            \"monitoringStartTime\": \"jan 01, 2023, 5:53:40 PM\",\n" +
                "            \"monitoringEndTime\": \"jan 07, 2023, 12:24:04 AM\",\n" +
                "            \"podsCount\": 0,\n" +
                "            \"confidence_level\": 0.0,\n" +
                "            \"config\": {\n" +
                "              \"max\": {\n" +
                "                \"memory\": {\n" +
                "                  \"amount\": 0.0\n" +
                "                },\n" +
                "                \"cpu\": {\n" +
                "                  \"amount\": 0.0\n" +
                "                }\n" +
                "              },\n" +
                "              \"capacity\": {\n" +
                "                \"memory\": {\n" +
                "                  \"amount\": 0.0\n" +
                "                },\n" +
                "                \"cpu\": {\n" +
                "                  \"amount\": 0.0\n" +
                "                }\n" +
                "              }\n" +
                "            }\n" +
                "          },\n" +
                "          \"Long Term\": {\n" +
                "            \"monitoringStartTime\": \"jan 01, 2023, 5:53:40 PM\",\n" +
                "            \"monitoringEndTime\": \"jan 30, 2023, 12:24:04 AM\",\n" +
                "            \"podsCount\": 0,\n" +
                "            \"confidence_level\": 0.0,\n" +
                "            \"config\": {\n" +
                "              \"max\": {\n" +
                "                \"memory\": {\n" +
                "                  \"amount\": 0.0\n" +
                "                },\n" +
                "                \"cpu\": {\n" +
                "                  \"amount\": 0.0\n" +
                "                }\n" +
                "              },\n" +
                "              \"capacity\": {\n" +
                "                \"memory\": {\n" +
                "                  \"amount\": 0.0\n" +
                "                },\n" +
                "                \"cpu\": {\n" +
                "                  \"amount\": 0.0\n" +
                "                }\n" +
                "              }\n" +
                "            }\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}\n";
        out.append("{\"quarkus-resteasy-autotune-min-http-response-time-db\":" + temp + "}");       //TODO add dummy results
        out.flush();
    }

    private void sendSuccessResponse(HttpServletResponse response) throws IOException {
        response.setContentType(JSON_CONTENT_TYPE);
        response.setCharacterEncoding(CHARACTER_ENCODING);
        response.setStatus(HttpServletResponse.SC_CREATED);
        PrintWriter out = response.getWriter();
        out.append(
                new Gson().toJson(
                        new AutotuneResponse("Updated metrics results successfully with Autotune. View update results at /listExperiments \"results\" section.", HttpServletResponse.SC_CREATED, "", "SUCCESS")
                )
        );
        out.flush();
    }

    public void sendErrorResponse(HttpServletResponse response, Exception e, int httpStatusCode, String errorMsg) throws
            IOException {
        if (null != e) {
            LOGGER.error(e.toString());
            e.printStackTrace();
            if (null == errorMsg) errorMsg = e.getMessage();
        }
        response.sendError(httpStatusCode, errorMsg);
    }

}

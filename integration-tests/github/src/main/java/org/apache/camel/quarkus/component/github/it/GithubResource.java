/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.quarkus.component.github.it;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.camel.ProducerTemplate;
import org.eclipse.egit.github.core.CommitFile;

@Path("/github")
public class GithubResource {

    private static final String GITHUB_AUTH_PARAMS = "username={{env:GITHUB_USERNAME:}}&password={{env:GITHUB_PASSWORD:}}";

    @Inject
    ProducerTemplate producerTemplate;

    @Path("/get")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getCamelQuarkusReadme() throws Exception {
        CommitFile commitFile = new CommitFile();
        commitFile.setSha("6195efafd0a8100795247e35942b5c61fea79267");

        return producerTemplate.requestBody(
                "github:GETCOMMITFILE?repoOwner=apache&repoName=camel-quarkus&" + GITHUB_AUTH_PARAMS,
                commitFile, String.class);
    }
}

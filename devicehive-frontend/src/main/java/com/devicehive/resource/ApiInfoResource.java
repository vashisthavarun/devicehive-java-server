package com.devicehive.resource;

/*
 * #%L
 * DeviceHive Java Server Common business logic
 * %%
 * Copyright (C) 2016 DataArt
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.devicehive.vo.ApiConfigVO;
import com.devicehive.vo.ApiInfoVO;
import com.devicehive.vo.ClusterConfigVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Api(tags = {"ApiInfoVO"}, description = "API information", consumes = "application/json")
@Path("/info")
@Produces({"application/json"})
public interface ApiInfoResource {

    @GET
    @PreAuthorize("permitAll")
    @ApiOperation(value = "Get API info", notes = "Returns version of API, server timestamp and WebSocket base uri"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Returns version of API, server timestamp and WebSocket base uri",
                    response = ApiInfoVO.class),
    })
    Response getApiInfo(@Context UriInfo uriInfo);

    @GET
    @Path("/config/auth")
    @PreAuthorize("permitAll")
    @ApiOperation(value = "Get oAuth configuration", notes = "Gets information about supported authentication providers.",
            response = ApiConfigVO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Returns configured identity providers",
                    response = ApiConfigVO.class)
    })
    @Deprecated
    Response getOauth2Config();

    @GET
    @Path("/config/cluster")
    @PreAuthorize("permitAll")
    @ApiOperation(value = "Get cluster configuration", notes = "Returns information about cluster (Kafka, Zookeeper etc.)",
            response = ClusterConfigVO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Returns information about cluster (Kafka, Zookeeper etc.)",
                    response = ClusterConfigVO.class)
    })
    Response getClusterConfig();
}

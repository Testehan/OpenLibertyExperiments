package com.testehan.openliberty.openapi.filter;

import org.eclipse.microprofile.openapi.OASFactory;
import org.eclipse.microprofile.openapi.OASFilter;
import org.eclipse.microprofile.openapi.models.OpenAPI;
import org.eclipse.microprofile.openapi.models.info.Info;
import org.eclipse.microprofile.openapi.models.info.License;
import org.eclipse.microprofile.openapi.models.responses.APIResponse;

import java.util.Collections;

// Filtering of certain elements and fields of the generated OpenAPI document can be done by using the OASFilter interface

public class InventoryOASFilter implements OASFilter {

    @Override
    public APIResponse filterAPIResponse(APIResponse apiResponse) {
        if ("Missing description".equals(apiResponse.getDescription())) {
            apiResponse.setDescription("Invalid hostname or the system service may not be running on the particular host.");
        }
        return apiResponse;
    }
/*
Your current OpenAPI document doesn’t provide much information on the application itself or on what server and port it
runs on. This information is usually provided in the info and servers elements, which are currently missing.
 */
    @Override
    public void filterOpenAPI(OpenAPI openAPI) {
        openAPI.setInfo(
                OASFactory.createObject(Info.class).title("Inventory App").version("1.0")
                        .description(
                                "App for storing JVM system properties of various hosts.")
                        .license(
                                OASFactory.createObject(License.class)
                                        .name("Eclipse Public License - v 1.0").url(
                                                "https://www.eclipse.org/legal/epl-v10.html")));

        openAPI.addServer(
                OASFactory.createServer()
                        .url("http://localhost:{port}")
                        .description("Simple Open Liberty.")
                        .variables(Collections.singletonMap("port",
                                OASFactory.createServerVariable()
                                        .defaultValue("9080")
                                        .description("Server HTTP port."))));
    }

}

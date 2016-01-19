/*
 * Copyright 2012-2014 the original author or authors.
 *
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
 */

package sample.jsp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sample.dataAccess.pojo.Service;
import sample.dataAccess.service.ServiceService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.stream.Collectors;

import static sample.ParamConsts.PARAM_SERVICE_NAME;

@Controller
public class ServiceController extends AbstractController {
    private final ServiceService serviceService;

    @Inject
    public ServiceController(ServiceService serviceService) {
	this.serviceService = serviceService;
    }

    @RequestMapping("/serviceTypes")
    public String serviceTypes(Map<String, Object> model) {
        model.put("services", serviceService.getAll());
	    return "serviceTypes";
    }

    @RequestMapping("/servicePrice")
    public String servicePrice(Map<String, Object> model, HttpServletRequest request) {
        printRequest("servicePrice", request);

        String requestedService = request.getParameter(PARAM_SERVICE_NAME);
        model.put("price", (int) serviceService.getByName(requestedService).getPrice());
        return "servicePrice";
    }

    @RequestMapping("/getServices")
    public String dictData(Map<String, Object> model) {
        model.put("dictData", serviceService.getAll().stream().map(Service::getName).collect(Collectors.<String> toList()));
        return "dictData";
    }
}

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

import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import sample.dataAccess.service.ClientService;

@Controller
public class ClientController extends AbstractController {
    private final ClientService clientService;

    @Inject
    public ClientController(ClientService clientService) {
	this.clientService = clientService;
    }

    @RequestMapping("/firstNames")
    public String firstNames(Map<String, Object> model) {
	putAllClientsToModel(model);

	return "grammarClientFirstNames";
    }

    @RequestMapping("/lastNames")
    public String surnames(Map<String, Object> model) {
	putAllClientsToModel(model);

	return "grammarClientLastNames";
    }

    @RequestMapping("/mixedNames")
    public String fullname(Map<String, Object> model) {
	putAllClientsToModel(model);

	return "grammarClientMixedNames";
    }

    private void putAllClientsToModel(Map<String, Object> model) {
	model.put("clients", clientService.listAll());
    }
}

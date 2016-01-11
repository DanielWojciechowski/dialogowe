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

import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import sample.dataAccess.pojo.Client;
import sample.dataAccess.pojo.DictRoomType;
import sample.dataAccess.service.ClientService;
import sample.dataAccess.service.DictRoomTypeService;
import sample.dataAccess.service.ReservationService;

@Controller
public class WelcomeController {
    private final ClientService clientService;
    private final DictRoomTypeService dictRoomTypeService;
    private final ReservationService reservationService;

    @Inject
    public WelcomeController(ClientService clientService, DictRoomTypeService dictRoomTypeService,
	    ReservationService reservationService) {
	this.clientService = clientService;
	this.dictRoomTypeService = dictRoomTypeService;
	this.reservationService = reservationService;
    }

    @Value("${application.message:Hello World}")
    private String message = "Hello World";

    @RequestMapping("/meh")
    public String welcome(Map<String, Object> model) {
	model.put("time", new Date());
	Client c = clientService.getByName("Daniel");
	model.put("message", c.getFirstName());

	return "welcome";
    }
    //
    // @RequestMapping("/names")
    // public String grammar(Map<String, Object> model) {
    // model.put("names",
    // clientService.listAll().stream().map(Client::getFirstName).collect(Collectors.<String>
    // toList()));
    //
    // return "grammar";
    // }

    @RequestMapping("/dictRoomType")
    public String roomTypeGrammar(Map<String, Object> model) {
	model.put("roomTypes", dictRoomTypeService.listAll().stream().map(DictRoomType::getRoomType)
		.collect(Collectors.<String> toList()));

	return "grammarDictRoomType";
    }

    /*
     * @RequestMapping("/checkReservation") public String
     * checkReservation(Map<String, Object> model, HttpServletRequest request) {
     * 
     * return "checkReservation"; } // beginningDate length roomType. zwracam
     * znalezioneRoomID // DictRoomType dictRoomType = //
     * dictRoomTypeService.getByRoomType(request.getParameter("roomType"));
     * 
     * @RequestMapping("/saveReservation") public String
     * saveReservation(Map<String, Object> model, HttpServletRequest request) {
     * // beginningDate length roomId name surname phoneNumber Reservation r =
     * new Reservation();
     * 
     * Date startDate = getStartDate(request.getParameter("beginningDate"));
     * r.setStartDate(startDate); model.put("startDate", startDate);
     * 
     * Date endDate = getEndDate(startDate,
     * Integer.valueOf(request.getParameter("length"))); r.setEndDate(endDate);
     * model.put("endDate", endDate);
     * 
     * Client c = getClient(request); r.setOwner(c);
     * 
     * r = reservationService.save(r); model.put("rID", r.getId());
     * 
     * RoomsInReservation rir = new RoomsInReservation(); rir.setReservation(r);
     * rir.setRoom(null); // TODO znalezienie room po id i dodawanie //
     * roomsinreservation
     * 
     * return "saveReservation"; }
     * 
     * private Client getClient(HttpServletRequest request) { String firstName =
     * request.getParameter("name"); String lastName =
     * request.getParameter("surname"); String phoneNumber =
     * request.getParameter("phoneNumber"); Client c =
     * clientService.getByFullNameAndPhoneNumber(firstName, lastName,
     * phoneNumber);
     * 
     * return c; }
     * 
     * private Date getStartDate(String stringDate) { Date startDate = null; try
     * { startDate = new SimpleDateFormat("yyyymmdd").parse(stringDate); } catch
     * (ParseException e) { e.printStackTrace(); }
     * 
     * return startDate; }
     * 
     * private Date getEndDate(Date startDate, int length) { Calendar cal =
     * Calendar.getInstance(); cal.setTime(startDate); cal.add(Calendar.DATE,
     * length); Date endDate = cal.getTime();
     * 
     * return endDate; }
     */
    @RequestMapping("/")
    public String welcome(Map<String, Object> model, HttpServletRequest request) {

	model.put("time", new Date());
	Client c = clientService.getByName(request.getParameter("name"));
	System.out.println(c.getFirstName());
	model.put("message", c.getFirstName());
	return "welcome";
    }
}

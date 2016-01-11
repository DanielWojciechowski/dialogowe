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

import static sample.ModelConsts.MODEL_ROOM_ID;
import static sample.ParamConsts.PARAM_BEGINNING_DATE;
import static sample.ParamConsts.PARAM_LENGTH;
import static sample.ParamConsts.PARAM_PHONE_NUMBER;
import static sample.ParamConsts.PARAM_ROOM_TYPE;
import static sample.ParamConsts.PARAM_SUBDIALOG_FIRSTNAME;
import static sample.ParamConsts.PARAM_SUBDIALOG_LASTNAME;
import static sample.ParamConsts.PARAM_SUBDIALOG_ROOM_ID;

import java.util.Date;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import sample.dataAccess.pojo.Client;
import sample.dataAccess.pojo.Reservation;
import sample.dataAccess.pojo.Room;
import sample.dataAccess.pojo.RoomsInReservation;
import sample.dataAccess.service.ClientService;
import sample.dataAccess.service.ReservationService;
import sample.dataAccess.service.RoomService;
import sample.dataAccess.service.RoomsInReservationService;

@Controller
public class ReservationController extends AbstractController {
    private final ReservationService reservationService;
    private final ClientService clientService;
    private final RoomService roomService;
    private final RoomsInReservationService roomsInReservationService;

    @Inject
    public ReservationController(ClientService clientService, ReservationService reservationService,
	    RoomService roomService, RoomsInReservationService roomsInReservationService) {
	this.clientService = clientService;
	this.reservationService = reservationService;
	this.roomService = roomService;
	this.roomsInReservationService = roomsInReservationService;
    }

    @RequestMapping("/reservationExists")
    public String reservationExists(Map<String, Object> model, HttpServletRequest request) {
	final Long reservationId = Long.valueOf(request.getParameter("reservationId"));
	Reservation reservation = reservationService.findById(reservationId);
	model.put("reservationExists", reservation.getId() != null);

	return "reservationExists";
    }

    @RequestMapping("/checkReservation")
    public String checkReservation(Map<String, Object> model, HttpServletRequest request) {
	System.out.println("checkReservation " + request.getParameterMap());
	final String roomType = request.getParameter(PARAM_ROOM_TYPE);
	final Date startDate = getStartDate(request.getParameter(PARAM_BEGINNING_DATE));
	final Date endDate = getEndDate(startDate, Integer.valueOf(request.getParameter(PARAM_LENGTH)));

	Room r = roomService.findAvailableByRoomType(roomType, startDate, endDate);
	Long rId = r.getId();
	model.put(MODEL_ROOM_ID, rId == null ? "" : rId);
	System.out.println("RoomId: " + rId);

	return "checkReservation";
    }

    @RequestMapping("/saveReservation")
    public String saveReservation(Map<String, Object> model, HttpServletRequest request) {
	printRequest("saveReservation", request);
	System.out.println("saveReservation " + request.getParameterMap());

	Reservation reservation = createReservation(model, request);
	reservation = reservationService.save(reservation);
	model.put(MODEL_ROOM_ID, reservation.getId());

	RoomsInReservation roomsInReservation = createRoomsInReservation(request, reservation);
	roomsInReservation = roomsInReservationService.save(roomsInReservation);

	return "saveReservation";
    }

    private Reservation createReservation(Map<String, Object> model, HttpServletRequest request) {
	printRequest("createReservation", request);
	Reservation reservation = new Reservation();
	final Date startDate = getStartDate(request.getParameter(PARAM_BEGINNING_DATE));
	reservation.setStartDate(startDate);

	final Date endDate = getEndDate(startDate, Integer.valueOf(request.getParameter(PARAM_LENGTH)));
	reservation.setEndDate(endDate);

	Client c = getClient(request);
	reservation.setOwner(c);

	return reservation;
    }

    private Client getClient(HttpServletRequest request) {
	final String firstName = request.getParameter(PARAM_SUBDIALOG_FIRSTNAME);
	final String lastName = request.getParameter(PARAM_SUBDIALOG_LASTNAME);
	final String phoneNumber = request.getParameter(PARAM_PHONE_NUMBER);

	return clientService.getByFullNameAndPhoneNumber(firstName, lastName, phoneNumber);
    }

    private RoomsInReservation createRoomsInReservation(HttpServletRequest request, Reservation reservation) {
	RoomsInReservation roomsInReservation = new RoomsInReservation();
	roomsInReservation.setReservation(reservation);
	Room room = roomService.findById(Long.valueOf(request.getParameter(PARAM_SUBDIALOG_ROOM_ID)));
	roomsInReservation.setRoom(room);

	return roomsInReservation;
    }
}

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
import sample.dataAccess.pojo.*;
import sample.dataAccess.service.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

import static sample.ModelConsts.MODEL_RESERVATION_ID;
import static sample.ModelConsts.MODEL_ROOM_ID;
import static sample.ParamConsts.*;

@Controller
public class ReservationController extends AbstractController {
    private final ReservationService reservationService;
    private final ClientService clientService;
    private final RoomService roomService;
    private final RoomsInReservationService roomsInReservationService;
    private final DictReservationStatusService dictReservationStatusService;

    public static final String EMPTY_ELEMENT = "-";

    @Inject
    public ReservationController(ClientService clientService, ReservationService reservationService,
                                 RoomService roomService, RoomsInReservationService roomsInReservationService,
                                 DictReservationStatusService dictReservationStatusService) {
        this.clientService = clientService;
        this.reservationService = reservationService;
        this.roomService = roomService;
        this.roomsInReservationService = roomsInReservationService;
        this.dictReservationStatusService = dictReservationStatusService;
    }

    @RequestMapping("/reservationExists")
    public String reservationExists(Map<String, Object> model, HttpServletRequest request) {
        final Long reservationId = Long.valueOf(request.getParameter(PARAM_RESERVATION_ID));
        System.out.println("Find reservation with id=" + reservationId);
        Reservation reservation = reservationService.findById(reservationId);
        model.put("foundReservationId", reservation != null ? reservation.getId() : EMPTY_ELEMENT);
        System.out.println("Found reservation:" + model.get("foundReservationId"));

        return "reservationExists";
    }

    @RequestMapping("/checkReservation")
    public String checkReservation(Map<String, Object> model, HttpServletRequest request) {
        System.out.println("checkReservation " + request.getParameterMap());
        final String roomType = request.getParameter(PARAM_ROOM_TYPE);
        final Date startDate = getStartDate(request.getParameter(PARAM_BEGINNING_DATE));
        final Date endDate = getEndDate(startDate, Integer.valueOf(request.getParameter(PARAM_LENGTH)));

        Room room = roomService.findAvailableByRoomType(roomType, startDate, endDate);
        model.put(MODEL_ROOM_ID, room == null ? EMPTY_ELEMENT : room.getId());

        return "checkReservation";
    }

    @RequestMapping("/saveReservation")
    public String saveReservation(Map<String, Object> model, HttpServletRequest request) {
        printRequest("saveReservation", request);

        Reservation reservation = createReservation(request);
        if (reservation == null) {
            model.put(MODEL_RESERVATION_ID, EMPTY_ELEMENT);
        } else {
            reservation = reservationService.save(reservation);
            model.put(MODEL_RESERVATION_ID, reservation.getId());

            RoomsInReservation roomsInReservation = createRoomsInReservation(request, reservation);
            roomsInReservationService.save(roomsInReservation);
        }
        return "saveReservation";
    }

    @RequestMapping("/confirmReservation")
    public String confirmReservation(HttpServletRequest request) {
        setNewStatus(request, DictReservationStatus.CONFIRMED);
        return "confirmReservation";
    }

    @RequestMapping("/cancelReservation")
    public String cancelReservation(HttpServletRequest request) {
        setNewStatus(request, DictReservationStatus.CANCELED);
        return "cancelReservation";
    }

    private void setNewStatus(HttpServletRequest request, String statusCode) {
        final Long reservationId = Long.valueOf(request.getParameter(PARAM_RESERVATION_ID));
        System.out.println("Change status=" + statusCode + " for reservationId=" + reservationId);
        Reservation reservation = reservationService.findById(reservationId);

        DictReservationStatus status = dictReservationStatusService.getByCode(statusCode);
        System.out.println("Found Status :" + (status == null ? "null" : status.getStatus()));
        reservation.setReservationStatus(status);
        reservationService.save(reservation);
    }

    private Reservation createReservation(HttpServletRequest request) {
        Client client = getClient(request);
        if (client == null) return null;

        Reservation reservation = new Reservation();
        final Date startDate = getStartDate(request.getParameter(PARAM_BEGINNING_DATE));
        reservation.setStartDate(startDate);

        final Date endDate = getEndDate(startDate, Integer.valueOf(request.getParameter(PARAM_LENGTH)));
        reservation.setEndDate(endDate);

        reservation.setOwner(client);

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

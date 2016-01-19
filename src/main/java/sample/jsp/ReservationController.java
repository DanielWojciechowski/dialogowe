package sample.jsp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sample.dataAccess.pojo.*;
import sample.dataAccess.service.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    private final DictRoomTypeService dictRoomTypeService;


    public static final String EMPTY_ELEMENT = "-";

    @Inject
    public ReservationController(ClientService clientService, ReservationService reservationService,
                                 RoomService roomService, RoomsInReservationService roomsInReservationService,
                                 DictReservationStatusService dictReservationStatusService, DictRoomTypeService dictRoomTypeService) {
        this.clientService = clientService;
        this.reservationService = reservationService;
        this.roomService = roomService;
        this.roomsInReservationService = roomsInReservationService;
        this.dictReservationStatusService = dictReservationStatusService;
        this.dictRoomTypeService = dictRoomTypeService;
    }

    @RequestMapping("/reservationExists")
    public String reservationExists(Map<String, Object> model, HttpServletRequest request) {
        printRequest("reservationExists", request);

        final Long reservationId = Long.valueOf(request.getParameter(PARAM_RESERVATION_ID));
        System.out.println("Find reservation with id=" + reservationId);
        Reservation reservation = reservationService.findById(reservationId);
        model.put("foundReservationId", reservation != null ? reservation.getId() : EMPTY_ELEMENT);
        System.out.println("Found reservation:" + model.get("foundReservationId"));

        return "reservationExists";
    }

    @RequestMapping("/checkReservation")
    public String checkReservation(Map<String, Object> model, HttpServletRequest request) {
        printRequest("checkReservation", request);

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
        printRequest("confirmReservation", request);
        setNewStatus(request, DictReservationStatus.CONFIRMED);
        return "confirmReservation";
    }

    @RequestMapping("/cancelReservation")
    public String cancelReservation(HttpServletRequest request) {
        printRequest("cancelReservation", request);
        setNewStatus(request, DictReservationStatus.CANCELED);
        return "cancelReservation";
    }

    @RequestMapping("/changeReservationDate")
    public String changeReservationDate(Map<String, Object> model, HttpServletRequest request) {
        printRequest("saveReservation", request);

        final Long reservationId = Long.valueOf(request.getParameter(PARAM_RESERVATION_ID));
        final Date startDate = getStartDate(request.getParameter(PARAM_BEGINNING_DATE));
        final Date endDate = getEndDate(startDate, Integer.valueOf(request.getParameter(PARAM_LENGTH)));

        Reservation reservation = reservationService.findById(reservationId);

        List<RoomsInReservation> list = roomsInReservationService.findByReservation(reservation);
        RoomsInReservation roomsInReservation = list.get(0);
        Room room = roomService.findAvailableByRoomTypeWithExclude(roomsInReservation.getRoom().getRoomType().getRoomType(), startDate, endDate, reservationId);

        if (room != null) {
            reservation.setStartDate(startDate);
            reservation.setEndDate(endDate);
            reservationService.save(reservation);
            roomsInReservation.setRoom(room);
            roomsInReservationService.save(roomsInReservation);

            model.put(MODEL_RESERVATION_ID, reservationId);
        } else {
            System.out.println("Available room not found");
            model.put(MODEL_RESERVATION_ID, EMPTY_ELEMENT);
        }

        return "changeReservationDate";
    }

    @RequestMapping("/roomPriceFullGrammar")
    public String roomPriceFullGrammar(Map<String, Object> model) {
        model.put("roomTypes", dictRoomTypeService.listAll().stream().map(DictRoomType::getRoomType).collect(Collectors.<String> toList()));
        return "roomPriceFullGrammar";
    }

    @RequestMapping("/dictRoomType")
    public String roomTypeGrammar(Map<String, Object> model) {
        model.put("roomTypes", dictRoomTypeService.listAll().stream().map(DictRoomType::getRoomType).collect(Collectors.<String> toList()));
        return "grammarDictRoomType";
    }

    @RequestMapping("/roomPriceStayLengthGrammar")
    public String roomPriceStayLengthGrammar() {
        return "roomPriceStayLengthGrammar";
    }

    @RequestMapping("/roomPriceIsSeasonGrammar")
    public String roomPriceIsSeasonGrammar() {
        return "roomPriceIsSeasonGrammar";
    }

    @RequestMapping("/roomPrice")
    public String roomPrice(HttpServletRequest request, Map<String, Object> model) {
        printRequest("roomPrice", request);

        String roomTypeString = request.getParameter("roomPrice.room_type");
        String lengthString = request.getParameter("roomPrice.stay_length");
        String seasonString = request.getParameter("roomPrice.season");

        DictRoomType dictRoomType = dictRoomTypeService.getByRoomType(roomTypeString);
        int length = Integer.parseInt(lengthString);
        boolean season = "yes".equals(seasonString);

        model.put("roomPrice", dictRoomType.getPrice() * length * (season ? 1.3d : 1.0d));

        return "roomPrice";
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

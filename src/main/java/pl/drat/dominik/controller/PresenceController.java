package pl.drat.dominik.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.drat.dominik.dao.entity.Presence;
import pl.drat.dominik.dao.entity.PresenceWindow;
import pl.drat.dominik.dao.entity.Schedule;
import pl.drat.dominik.dto.MessageDTO;
import pl.drat.dominik.dto.PresenceWindowMessageDTO;
import pl.drat.dominik.dto.request.SchedulePresenceWindowDTO;
import pl.drat.dominik.dto.request.StudentPresenceReportDTO;
import pl.drat.dominik.dto.response.PresenceDTO;
import pl.drat.dominik.service.IPService;
import pl.drat.dominik.service.PresenceService;
import pl.drat.dominik.service.UserService;
import pl.drat.dominik.util.status.PresenceReportStatus;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static pl.drat.dominik.util.status.PresenceReportStatus.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("presence")
public class PresenceController {

    @Autowired
    PresenceService presenceService;

    @Autowired
    UserService userService;

    @Autowired
    IPService ipService;

    @GetMapping("/get/{subjectId}")
    public ResponseEntity<List<PresenceDTO>> getPresenceForSubject(@PathVariable("subjectId") Long subjectId) {
        List<Presence> presences = presenceService.getPresenceBySubjectId(subjectId);
        List<PresenceDTO> presencesDTOs = new ArrayList<>();
        for (Presence presence : presences) {
            presencesDTOs.add(presence.toDTO());
        }

        return new ResponseEntity<>(presencesDTOs, HttpStatus.OK);
    }

    @GetMapping("/student/{studentId}/subject/{subjectId}")
    public ResponseEntity<List<PresenceDTO>> getPresenceForStudentForSubject(@PathVariable("studentId") Long studentId, @PathVariable("subjectId") Long subjectId) {
        List<Presence> presences = presenceService.getPresenceByStudentIdAndSubjectID(studentId, subjectId);
        List<PresenceDTO> presencesDTOs = new ArrayList<>();
        for (Presence presence : presences) {
            presencesDTOs.add(presence.toDTO());
        }

        return new ResponseEntity<>(presencesDTOs, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<PresenceDTO> createPresence(@RequestBody Presence presence) {
        Presence newPresence = presenceService.createPresence(presence);
        return new ResponseEntity<>(newPresence.toDTO(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PresenceDTO> updatePresence(@RequestBody Presence presence, @PathVariable("id") Long id) {
        Presence updatedPresence = presenceService.updatePresence(id, presence);
        return new ResponseEntity<>(updatedPresence.toDTO(), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Presence> deletePresence(@PathVariable("id") Long id) {
        presenceService.deletePresence(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/create/window")
    public ResponseEntity<PresenceWindowMessageDTO> createPresence(@RequestBody SchedulePresenceWindowDTO presenceWindow) throws ParseException {
        PresenceWindowMessageDTO presenceWindowMessageDTO = presenceService.openSchedulePresenceWindow(presenceWindow);
        return new ResponseEntity<>(presenceWindowMessageDTO, HttpStatus.OK);
    }

    @PostMapping("/student/report")
    public ResponseEntity<MessageDTO> presenceReportByStudent(@RequestBody StudentPresenceReportDTO studentReport) throws ParseException {

        if (ipService.ipIsInSchoolNetworkRange(studentReport.getStudentIpAddress())) {
            PresenceReportStatus status = presenceService.reportPresenceByStudent(studentReport);

            if (status == ADDED_NEW_PRESENCE) {
                return new ResponseEntity<>(new MessageDTO("Presence Reported With Success!"), HttpStatus.OK);

            } else if (status == INCORRECT_PRESENCE_CODE) {
                return new ResponseEntity<>(new MessageDTO("Incorrect Presence Code!"), HttpStatus.FORBIDDEN);
            } else if (status == PRESENCE_ALREADY_REPORTED) {
                return new ResponseEntity<>(new MessageDTO("Presence Already Reported!"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new MessageDTO("It is not allowed by teacher to report presence for this lesson!"), HttpStatus.FORBIDDEN);
            }
        } else {
            return new ResponseEntity<>(new MessageDTO("You have to be connected with school network!"), HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/expire/window/{subjectId}")
    public ResponseEntity<MessageDTO> expirePresenceWindow(@PathVariable("subjectId") Long subjectId) throws ParseException {
        presenceService.expirePresenceWindowForSubject(subjectId);
        return new ResponseEntity<>(new MessageDTO("Presence Window has been Expired!"), HttpStatus.OK);
    }

    @GetMapping("/get/window/{subjectId}")
    public ResponseEntity<PresenceWindowMessageDTO> getPresenceWindowForScheduleIfExist(@PathVariable("subjectId") Long subjectId) throws ParseException {
        PresenceWindow activePresenceWindow = presenceService.getActivePresenceWindow(subjectId);
        PresenceWindowMessageDTO response;
        if(activePresenceWindow != null) {
            Long presenceCode = activePresenceWindow.getSecurityCode();
            Long scheduleId = activePresenceWindow.getScheduleId();
            Schedule schedule = presenceService.getScheduleByScheduleId(scheduleId);
            String date = schedule.getDate();
            response = new PresenceWindowMessageDTO("", presenceCode, date);
        }else {
            response = new PresenceWindowMessageDTO("", 0L, "");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}


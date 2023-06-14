package tech.xavi.springfood.controller.rest.staff;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.xavi.springfood.configuration.constants.EndPoints;
import tech.xavi.springfood.model.staff.payload.StaffCreateReq;
import tech.xavi.springfood.model.staff.payload.StaffCreateRes;
import tech.xavi.springfood.service.staff.StaffService;

@RequiredArgsConstructor
@RestController
public class StaffController {

    private final StaffService staffService;

    @PostMapping(EndPoints.EP_STAFF)
    public ResponseEntity<StaffCreateRes> createStaff(
            @RequestBody StaffCreateReq request
    ){
        return new ResponseEntity<>(
                staffService.createStaff(request),
                HttpStatus.CREATED
        );
    }

    @PostMapping(EndPoints.EP_STAFF+"/test")
    public ResponseEntity<?> test(){
        return new ResponseEntity<>(
                "okk",
                HttpStatus.OK
        );
    }
}

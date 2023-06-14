package tech.xavi.springfood.service.staff;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.xavi.springfood.configuration.constants.SuccessMessage;
import tech.xavi.springfood.configuration.exception.SpringFoodError;
import tech.xavi.springfood.configuration.exception.SpringFoodException;
import tech.xavi.springfood.entity.Staff;
import tech.xavi.springfood.model.staff.payload.StaffCreateReq;
import tech.xavi.springfood.model.staff.payload.StaffCreateRes;
import tech.xavi.springfood.repository.StaffRepository;

@RequiredArgsConstructor
@Service
public class StaffService {

    private final StaffRepository staffRepository;
    private final PasswordEncoder passwordEncoder;


    public StaffCreateRes createStaff(StaffCreateReq staffCreateReq){

        if (staffRepository.findStaffByEmail(staffCreateReq.email()).isPresent()) {
            throw new SpringFoodException(
                    SpringFoodError.EmailAlreadyExists,
                    HttpStatus.BAD_REQUEST
            );
        }

        staffRepository.save(
                Staff.builder()
                        .email(staffCreateReq.email())
                        .name(staffCreateReq.name())
                        .phone(staffCreateReq.phone())
                        .password(passwordEncoder.encode(staffCreateReq.password()))
                        .staffAuthorities(staffCreateReq.authorities())
                        .build()
        );

        return StaffCreateRes.builder()
                .message(SuccessMessage.STAFF_CREATE)
                .build();

    }


}
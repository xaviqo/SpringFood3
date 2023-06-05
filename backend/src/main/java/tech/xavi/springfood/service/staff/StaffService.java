package tech.xavi.springfood.service.staff;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.xavi.springfood.model.staff.payload.StaffCreateReq;
import tech.xavi.springfood.model.staff.payload.StaffCreateRes;
import tech.xavi.springfood.repository.AccountRepository;
import tech.xavi.springfood.repository.StaffRepository;

@RequiredArgsConstructor
@Service
public class StaffService {

    private final AccountRepository accountRepository;
    private final StaffRepository staffRepository;

    public StaffCreateRes createStaff(StaffCreateReq staffCreateReq){

    }


}

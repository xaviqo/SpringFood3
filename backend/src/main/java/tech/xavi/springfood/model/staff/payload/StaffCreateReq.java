package tech.xavi.springfood.model.staff.payload;

import tech.xavi.springfood.entity.role.StaffAuthority;

import java.util.List;

public record StaffCreateReq(
        String name,
        String phone,
        String email,
        String password,
        List<StaffAuthority> authorities
) {
}

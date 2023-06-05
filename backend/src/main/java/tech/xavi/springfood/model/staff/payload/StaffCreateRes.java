package tech.xavi.springfood.model.staff.payload;

import lombok.Builder;
import tech.xavi.springfood.entity.role.StaffAuthority;

import java.util.List;

@Builder
public record StaffCreateRes(
        String staffId,
        List<StaffAuthority> authorities
) {
}

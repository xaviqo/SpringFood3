package tech.xavi.springfood.entity.role;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {
    CLIENT("CLI_"),
    STAFF("STF_"),
    ACCOUNT("ACC_")
    ;

    public final String PREFIX;
}

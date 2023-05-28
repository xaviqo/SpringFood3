package tech.xavi.springfood.configuration.idgenerator;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import tech.xavi.springfood.entity.Account;

public class AccountIdGenerator implements IdentifierGenerator {

    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) {
        return ((Account) object).getEntityPrefix() + UUIDGenerator.randomAccountUUID();
    }
}



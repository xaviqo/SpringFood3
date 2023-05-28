package tech.xavi.springfood.configuration.idgenerator;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import tech.xavi.springfood.entity.Order;

public class OrderIdGenerator implements IdentifierGenerator {

    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) {
        return ((Order) object).getEntityPrefix() + UUIDGenerator.randomOrderUUID();
    }
}



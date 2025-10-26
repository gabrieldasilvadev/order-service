package br.com.postech.soat.order.infrastructure.persistence.mapper;

import br.com.postech.soat.customer.domain.valueobject.CustomerId;
import br.com.postech.soat.order.domain.entity.Order;
import br.com.postech.soat.order.domain.valueobject.OrderId;
import br.com.postech.soat.order.infrastructure.persistence.entity.OrderEntity;
import java.util.UUID;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-26T10:01:27-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.8 (Eclipse Adoptium)"
)
public class OrderEntityMapperImpl implements OrderEntityMapper {

    @Override
    public OrderEntity toEntity(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setId( orderIdValue( order ) );
        orderEntity.setCustomerId( orderCustomerIdValue( order ) );
        orderEntity.setStatus( order.getStatus() );
        orderEntity.setTotalPrice( order.getTotalPrice() );
        orderEntity.setDiscountAmount( order.getDiscountAmount() );

        mapObservations( order, orderEntity );

        return orderEntity;
    }

    private UUID orderIdValue(Order order) {
        OrderId id = order.getId();
        if ( id == null ) {
            return null;
        }
        return id.getValue();
    }

    private UUID orderCustomerIdValue(Order order) {
        CustomerId customerId = order.getCustomerId();
        if ( customerId == null ) {
            return null;
        }
        return customerId.value();
    }
}

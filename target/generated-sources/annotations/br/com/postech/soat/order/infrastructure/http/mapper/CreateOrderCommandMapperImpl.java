package br.com.postech.soat.order.infrastructure.http.mapper;

import br.com.postech.soat.customer.domain.valueobject.CustomerId;
import br.com.postech.soat.openapi.model.PostOrdersRequestDto;
import br.com.postech.soat.order.application.command.CreateOrderCommand;
import br.com.postech.soat.order.domain.entity.OrderItem;
import br.com.postech.soat.order.domain.valueobject.Discount;
import br.com.postech.soat.order.domain.valueobject.Observation;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-26T10:01:27-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.8 (Eclipse Adoptium)"
)
public class CreateOrderCommandMapperImpl implements CreateOrderCommandMapper {

    private final OrderItemMapper orderItemMapper = OrderItemMapper.INSTANCE;
    private final DiscountMapper discountMapper = DiscountMapper.INSTANCE;

    @Override
    public CreateOrderCommand mapFrom(PostOrdersRequestDto request) {
        if ( request == null ) {
            return null;
        }

        List<OrderItem> orderItems = null;
        List<Discount> discounts = null;

        orderItems = orderItemMapper.toDomain( request.getItems() );
        discounts = discountMapper.mapFrom( request.getDiscounts() );

        CustomerId customerId = new CustomerId(request.getCustomerId());
        List<Observation> observations = NoteMapper.INSTANCE.mapFrom(request.getNotes());

        CreateOrderCommand createOrderCommand = new CreateOrderCommand( customerId, discounts, orderItems, observations );

        return createOrderCommand;
    }
}

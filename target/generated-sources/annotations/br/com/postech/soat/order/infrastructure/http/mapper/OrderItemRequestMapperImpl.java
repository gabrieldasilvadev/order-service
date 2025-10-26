package br.com.postech.soat.order.infrastructure.http.mapper;

import br.com.postech.soat.openapi.model.OrderItemDto;
import br.com.postech.soat.order.domain.entity.OrderItem;
import br.com.postech.soat.order.domain.valueobject.Discount;
import java.math.BigDecimal;
import java.util.UUID;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-26T10:01:27-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.8 (Eclipse Adoptium)"
)
public class OrderItemRequestMapperImpl implements OrderItemRequestMapper {

    @Override
    public OrderItem mapFrom(OrderItemDto orderItemDto) {
        if ( orderItemDto == null ) {
            return null;
        }

        UUID productId = null;
        String name = null;
        Integer quantity = null;
        String category = null;

        productId = orderItemDto.getProductId();
        name = orderItemDto.getName();
        quantity = orderItemDto.getQuantity();
        if ( orderItemDto.getCategory() != null ) {
            category = orderItemDto.getCategory().name();
        }

        BigDecimal price = new BigDecimal(orderItemDto.getPrice());
        Discount discount = mapDiscount(orderItemDto.getDiscount());

        OrderItem orderItem = new OrderItem( productId, name, quantity, price, category, discount );

        return orderItem;
    }
}

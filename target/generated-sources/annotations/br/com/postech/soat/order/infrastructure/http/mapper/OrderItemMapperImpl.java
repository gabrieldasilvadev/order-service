package br.com.postech.soat.order.infrastructure.http.mapper;

import br.com.postech.soat.openapi.model.OrderItemDto;
import br.com.postech.soat.order.domain.entity.OrderItem;
import br.com.postech.soat.order.domain.valueobject.Discount;
import br.com.postech.soat.order.domain.valueobject.OrderItemId;
import br.com.postech.soat.order.infrastructure.persistence.entity.OrderItemEntity;
import java.math.BigDecimal;
import java.util.UUID;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-26T10:01:27-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.8 (Eclipse Adoptium)"
)
public class OrderItemMapperImpl implements OrderItemMapper {

    private final DiscountMapper discountMapper = DiscountMapper.INSTANCE;

    @Override
    public OrderItem toDomain(OrderItemDto orderItemDto) {
        if ( orderItemDto == null ) {
            return null;
        }

        Discount discount = null;
        UUID productId = null;
        String name = null;
        Integer quantity = null;

        discount = discountMapper.mapFrom( orderItemDto.getDiscount() );
        productId = orderItemDto.getProductId();
        name = orderItemDto.getName();
        quantity = orderItemDto.getQuantity();

        BigDecimal price = new BigDecimal(orderItemDto.getPrice());
        String category = orderItemDto.getCategory() != null ? orderItemDto.getCategory().getValue() : null;

        OrderItem orderItem = new OrderItem( productId, name, quantity, price, category, discount );

        return orderItem;
    }

    @Override
    public OrderItemEntity toEntity(OrderItem orderItem, UUID orderId) {
        if ( orderItem == null && orderId == null ) {
            return null;
        }

        OrderItemEntity orderItemEntity = new OrderItemEntity();

        if ( orderItem != null ) {
            orderItemEntity.setId( orderItemIdValue( orderItem ) );
            orderItemEntity.setProductId( orderItem.getProductId() );
            orderItemEntity.setDiscountAmount( orderItemDiscountValue( orderItem ) );
            orderItemEntity.setProductName( orderItem.getName() );
            orderItemEntity.setProductQuantity( orderItem.getQuantity() );
            orderItemEntity.setProductCategory( orderItem.getCategory() );
            orderItemEntity.setUnitPrice( orderItem.getPrice() );
        }
        orderItemEntity.setOrderId( orderId );

        return orderItemEntity;
    }

    private UUID orderItemIdValue(OrderItem orderItem) {
        OrderItemId id = orderItem.getId();
        if ( id == null ) {
            return null;
        }
        return id.getValue();
    }

    private BigDecimal orderItemDiscountValue(OrderItem orderItem) {
        Discount discount = orderItem.getDiscount();
        if ( discount == null ) {
            return null;
        }
        return discount.getValue();
    }
}

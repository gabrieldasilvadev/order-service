package br.com.postech.soat.order.infrastructure.http.mapper;

import br.com.postech.soat.customer.domain.valueobject.CustomerId;
import br.com.postech.soat.openapi.model.CategoryDto;
import br.com.postech.soat.openapi.model.GetOrders200ResponseInnerDto;
import br.com.postech.soat.openapi.model.OrderItemDto;
import br.com.postech.soat.openapi.model.OrderStatusDto;
import br.com.postech.soat.openapi.model.PostOrders201ResponseDiscountsInnerDto;
import br.com.postech.soat.openapi.model.PostOrders201ResponseDto;
import br.com.postech.soat.order.domain.entity.Order;
import br.com.postech.soat.order.domain.entity.OrderItem;
import br.com.postech.soat.order.domain.valueobject.Discount;
import br.com.postech.soat.order.domain.valueobject.OrderId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-26T10:01:27-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.8 (Eclipse Adoptium)"
)
public class OrderResponseMapperImpl implements OrderResponseMapper {

    @Override
    public PostOrders201ResponseDto toResponse(Order order) {
        if ( order == null ) {
            return null;
        }

        PostOrders201ResponseDto.Builder postOrders201ResponseDto = PostOrders201ResponseDto.builder();

        postOrders201ResponseDto.orderId( orderIdValue( order ) );
        postOrders201ResponseDto.items( orderItemListToOrderItemDtoList( order.getOrderItems() ) );
        postOrders201ResponseDto.discounts( discountListToPostOrders201ResponseDiscountsInnerDtoList( order.getDiscounts() ) );

        postOrders201ResponseDto.status( OrderStatusDto.fromValue(order.getStatus().name()) );
        postOrders201ResponseDto.discountAmountTotal( order.getDiscountAmount().doubleValue() );
        postOrders201ResponseDto.subtotal( order.getOriginalPrice().doubleValue() );
        postOrders201ResponseDto.total( order.getTotalPrice().doubleValue() );

        return postOrders201ResponseDto.build();
    }

    @Override
    public OrderItemDto toOrderItemDto(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }

        OrderItemDto.Builder orderItemDto = OrderItemDto.builder();

        orderItemDto.productId( orderItem.getProductId() );
        orderItemDto.discount( toDiscountDto( orderItem.getDiscount() ) );
        orderItemDto.name( orderItem.getName() );
        orderItemDto.quantity( orderItem.getQuantity() );

        orderItemDto.category( CategoryDto.fromValue(orderItem.getCategory()) );
        orderItemDto.price( orderItem.getPrice().doubleValue() );

        return orderItemDto.build();
    }

    @Override
    public GetOrders200ResponseInnerDto toListResponse(Order order) {
        if ( order == null ) {
            return null;
        }

        GetOrders200ResponseInnerDto.Builder getOrders200ResponseInnerDto = GetOrders200ResponseInnerDto.builder();

        getOrders200ResponseInnerDto.orderId( orderIdValue( order ) );
        getOrders200ResponseInnerDto.customerId( orderCustomerIdValue( order ) );
        getOrders200ResponseInnerDto.items( orderItemListToOrderItemDtoList( order.getOrderItems() ) );

        getOrders200ResponseInnerDto.discountAmountTotal( order.getDiscountAmount().doubleValue() );
        getOrders200ResponseInnerDto.total( order.getTotalPrice().doubleValue() );
        getOrders200ResponseInnerDto.status( OrderStatusDto.fromValue(order.getStatus().name()) );

        return getOrders200ResponseInnerDto.build();
    }

    private UUID orderIdValue(Order order) {
        OrderId id = order.getId();
        if ( id == null ) {
            return null;
        }
        return id.getValue();
    }

    protected List<OrderItemDto> orderItemListToOrderItemDtoList(List<OrderItem> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderItemDto> list1 = new ArrayList<OrderItemDto>( list.size() );
        for ( OrderItem orderItem : list ) {
            list1.add( toOrderItemDto( orderItem ) );
        }

        return list1;
    }

    protected List<PostOrders201ResponseDiscountsInnerDto> discountListToPostOrders201ResponseDiscountsInnerDtoList(List<Discount> list) {
        if ( list == null ) {
            return null;
        }

        List<PostOrders201ResponseDiscountsInnerDto> list1 = new ArrayList<PostOrders201ResponseDiscountsInnerDto>( list.size() );
        for ( Discount discount : list ) {
            list1.add( toDiscountInnerDto( discount ) );
        }

        return list1;
    }

    private UUID orderCustomerIdValue(Order order) {
        CustomerId customerId = order.getCustomerId();
        if ( customerId == null ) {
            return null;
        }
        return customerId.value();
    }
}

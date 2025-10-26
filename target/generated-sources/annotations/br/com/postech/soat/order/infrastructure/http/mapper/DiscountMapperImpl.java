package br.com.postech.soat.order.infrastructure.http.mapper;

import br.com.postech.soat.openapi.model.DiscountDto;
import br.com.postech.soat.order.domain.valueobject.Discount;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-26T10:01:27-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.8 (Eclipse Adoptium)"
)
public class DiscountMapperImpl implements DiscountMapper {

    @Override
    public Discount mapFrom(DiscountDto discountDto) {
        if ( discountDto == null ) {
            return null;
        }

        BigDecimal value = new BigDecimal(discountDto.getValue());

        Discount discount = new Discount( value );

        return discount;
    }
}

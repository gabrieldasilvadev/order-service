package br.com.postech.soat.order.application.usecases;

import br.com.postech.soat.commons.infrastructure.aop.monitorable.Monitorable;
import br.com.postech.soat.order.application.command.CreateOrderCommand;
import br.com.postech.soat.order.application.repositories.OrderRepository;
import br.com.postech.soat.order.domain.entity.Order;
import br.com.postech.soat.order.infrastructure.messaging.OrderPaymentPublisher;
import br.com.postech.soat.order.infrastructure.messaging.dto.PaymentRequestedMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Monitorable
public class CreateOrderUseCase {
    private final OrderRepository orderRepository;
    private final OrderPaymentPublisher orderPaymentPublisher;

    @Value("${app.messaging.default-payment-method:PIX}")
    private String defaultPaymentMethod;

    private final Logger logger = LoggerFactory.getLogger(CreateOrderUseCase.class);

    public Order execute(CreateOrderCommand command) {
        try {
            final Order order = Order.receive(
                command.customerId(),
                command.orderItems(),
                command.discounts(),
                command.observations()
            );
            order.prepare();
            logger.info("Domain order created: {}", order);
            final Order savedOrder = orderRepository.save(order);
            publishPaymentRequest(savedOrder);
            return savedOrder;
        } catch (Exception e) {
            logger.error("Error creating order: {}", e.getMessage(), e);
            throw e;
        }
    }

    private void publishPaymentRequest(Order order) {
        try {
            PaymentRequestedMessage message = new PaymentRequestedMessage(
                order.getId().getValue(),
                order.getCustomerId().value(),
                order.getTotalPrice(),
                defaultPaymentMethod
            );
            orderPaymentPublisher.publish(message);
        } catch (Exception exception) {
            logger.error(
                "Error publishing payment request for order {}: {}",
                order.getId().getValue(),
                exception.getMessage(),
                exception
            );
        }
    }
}

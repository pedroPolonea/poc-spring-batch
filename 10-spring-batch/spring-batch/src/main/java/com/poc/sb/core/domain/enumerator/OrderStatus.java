package com.poc.sb.core.domain.enumerator;

public enum OrderStatus {
    WE_RECEIVED_ORDER("Recebemos seu pedido"),
    PAYMENT_ACCEPT("Pagamento aprovado"),
    IN_SEPARATION("Em separação"),
    DELIVERED_TO_CARRIER("Entregue à transportadora"),
    ON_MY_WAY("A caminho do destinatário"),
    DELIVERY("Entrega concluída"),
    CANCELLED_PURCHASE("Compra cancelada"),
    UNSUCCESSFUL_DELIVERY("Entrega sem Sucesso");

    private final String description;


    OrderStatus(final String description) {
        this.description = description;
    }
}

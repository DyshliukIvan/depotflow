package com.ivandyshliuk.depotflow.inventory;
import java.time.Instant;

public class StockMovement {
    private final MovementType type;
    private final int quantity;
    private final Instant occurredAt;

    public StockMovement(MovementType newType, int newQuantity, Instant newOccurredAt) {
        if (newType == null || newQuantity <= 0 || newOccurredAt == null) throw new IllegalArgumentException();
        this.type = newType;
        this.quantity = newQuantity;
        this.occurredAt = newOccurredAt;
    }

    public MovementType getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public Instant getOccurredAt() {
        return occurredAt;
    }
}

package domain.models;

import domain.enums.MovementType;

import java.time.LocalDateTime;

public class Movement extends BaseModel {
    /**
     * Box ID relation
     */
    public String boxId = null;

    /**
     * Represents the type of movement done
     */
    public MovementType movementType = MovementType.INCOME;

    /**
     * Indicates how much money was affected
     */
    public double quantity = 0;

    public Movement(
            String id,
            String boxId,
            MovementType movementType,
            double quantity,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.boxId = boxId;
        this.movementType = movementType;
        this.quantity = quantity;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return String.format("""
                 {
                     "id": "%s",
                     "boxId": "%s",\s
                     "movementType": "%s",\s
                     "quantity": "%s",\s
                     "createdAt": "%s"
                  }\s
                \s""", id, boxId, movementType, quantity, createdAt.toString());
    }
}

package domain.models;

import java.time.LocalDateTime;

public class Box extends  BaseModel{
    /**
     * Name of the box
     */
    public String name = null;

    /**
     * Description added to the box
     */
    public String description = null;

    /**
     * Indicates how much money is in the box
     */
    public double quantity = 0;

    public Box(String id, String name , String description, double quantity, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return String.format("""
                {
                    "id": "%s",
                    "name": "%s",\s
                    "description": "%s",\s
                    "quantity": "%s",\s
                    "createdAt": "%s"
                 }\s
               \s""", id, name, description, quantity, createdAt.toString());
    }
}

package domain.models;

import java.time.LocalDateTime;

/**
 * Use this abstract class for all our domain models
 */
public abstract class BaseModel {
    /**
     * Unique identifier
     */
    public String id;

    /**
     * Entity creation date and time
     */
    public LocalDateTime createdAt;
}

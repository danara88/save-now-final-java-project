package infrastructure.repositories;

import domain.enums.MovementType;
import domain.models.Movement;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MovementRepository {
    private final String _databasePath = "src/infrastructure/databases/MovementDB.json";

    public List<Movement> getMovements() {
        List<Movement> movements = new ArrayList<>();

        try {
            String jsonContent = Files.readString(Paths.get(this._databasePath));
            JSONArray jsonArray = new JSONArray(jsonContent);
            movements = this._mapToMovements(jsonArray);
        } catch (IOException e) {
            System.out.println(e);
        }
        return movements;
    }

    public boolean createMovement(Movement movement) {
        try {
            List<Movement> movements = this.getMovements();
            movements.add(movement);
            var movementsString = movements.toString();
            var jsonArray = new JSONArray(movementsString);
            Files.writeString(Paths.get(this._databasePath), jsonArray.toString(), StandardOpenOption.TRUNCATE_EXISTING);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private Movement _mapToMovement(JSONObject jsonObject) {
        return new Movement(
                jsonObject.getString("id"),
                jsonObject.getString("boxId"),
                jsonObject.getEnum(MovementType.class, "movementType"),
                jsonObject.getDouble("quantity"),
                LocalDateTime.parse(jsonObject.getString("createdAt"))
        );
    }

    private List<Movement> _mapToMovements(JSONArray jsonArray) {
        List<Movement> movements = new ArrayList<>();
        if(!jsonArray.isEmpty()) {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                movements.add(this._mapToMovement(jsonObject));
            }
        }
        return movements;
    }
}

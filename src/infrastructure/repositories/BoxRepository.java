package infrastructure.repositories;

import domain.models.Box;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BoxRepository {
    private final String _databasePath = "src/infrastructure/databases/BoxDB.json";

    public List<Box> getBoxes() {
        List<Box> boxes = new ArrayList<>();
        try {
            String jsonContent = Files.readString(Paths.get(this._databasePath));
            JSONArray jsonArray = new JSONArray(jsonContent);
            boxes = this._mapToBoxes(jsonArray);
        } catch (IOException e) {
            System.out.println(e);
        }
        return boxes;
    }

    public Box getBoxById(String id) {
        List<Box> boxes = this.getBoxes();
        var boxDB = boxes.stream().filter(box -> box.id.equals(id)).findFirst();
        return boxDB.orElse(null);
    }

    public boolean createBox(Box box) {
        try {
            List<Box> boxes = this.getBoxes();
            boxes.add(box);
            var boxesString = boxes.toString();
            var jsonArray = new JSONArray(boxesString);
            Files.writeString(Paths.get(this._databasePath), jsonArray.toString(), StandardOpenOption.TRUNCATE_EXISTING);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean updateBox(Box box) {
        try {
            var boxDB = this.getBoxById(box.id);

            if (boxDB == null) {
                return false;
            }
            List<Box> boxesResult = this.getBoxes();
            List<Box> boxes = new ArrayList<>(boxesResult.stream().filter(b -> !b.id.equals(boxDB.id)).toList());
            boxes.add(box);
            var boxesString = boxes.toString();
            var jsonArray = new JSONArray(boxesString);
            Files.writeString(Paths.get(this._databasePath), jsonArray.toString(), StandardOpenOption.TRUNCATE_EXISTING);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private Box _mapToBox(JSONObject jsonObject) {
        return new Box(
            jsonObject.getString("id"),
            jsonObject.getString("name"),
            jsonObject.getString("description"),
            jsonObject.getDouble("quantity"),
            LocalDateTime.parse(jsonObject.getString("createdAt"))
        );
    }

    private List<Box> _mapToBoxes(JSONArray jsonArray) {
        List<Box> boxes = new ArrayList<>();
        if(!jsonArray.isEmpty()) {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                boxes.add(this._mapToBox(jsonObject));
            }
        }
        return boxes;
    }
}

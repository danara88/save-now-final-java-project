package application.boxes;

import application.common.IUseCase;
import domain.enums.UseCaseResultType;
import domain.models.Box;
import domain.models.UseCaseResult;
import infrastructure.repositories.BoxRepository;

import java.util.HashMap;
import java.util.List;

public class CreateBox implements IUseCase {
    private final BoxRepository _boxRepository = new BoxRepository();
    private final Box _box;

    public CreateBox(Box box) {
        this._box = box;
    }

    @Override
    public UseCaseResult<Boolean> execute() {
        List<Box> boxesDB = _boxRepository.getBoxes();
        HashMap<String, String> errors = new HashMap<>();

        if(boxesDB.size() == 5) {
            errors.put("max", "You have reached the maximum number of boxes");
            return new UseCaseResult<>(
                    UseCaseResultType.ERROR,
                    errors,
                    null);
        }

        var result = _boxRepository.createBox(this._box);

        return new UseCaseResult<>(
            UseCaseResultType.OK,
            null,
            result);
    }
}

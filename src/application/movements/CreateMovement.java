package application.movements;

import application.common.IUseCase;
import domain.enums.MovementType;
import domain.enums.UseCaseResultType;
import domain.models.Movement;
import domain.models.UseCaseResult;
import infrastructure.repositories.BoxRepository;
import infrastructure.repositories.MovementRepository;
import share.Constants;

import java.util.HashMap;

public class CreateMovement implements IUseCase {
    private final MovementRepository _movementRepository = new MovementRepository();
    private final BoxRepository _boxRepository = new BoxRepository();
    private final Movement _movement;

    public CreateMovement(Movement movement) {
        this._movement = movement;
    }

    @Override
    public UseCaseResult<String> execute() {
        HashMap<String, String> errors = new HashMap<>();

        var boxDB = this._boxRepository.getBoxById(this._movement.boxId);

        if(boxDB == null) {
            errors.put("boxId", "Box does not exist");
            return new UseCaseResult<>(UseCaseResultType.ERROR, errors, null);
        }

        if(this._movement.movementType == MovementType.INCOME) {
            boxDB.quantity += this._movement.quantity;
        }

        if(this._movement.movementType == MovementType.WITHDRAW) {
            boxDB.quantity -= this._movement.quantity;
        }

        this._boxRepository.updateBox(boxDB);
        this._movementRepository.createMovement(this._movement);

        return new UseCaseResult<>(UseCaseResultType.OK, null, Constants.resourceCreatedSuccess);
    }
}

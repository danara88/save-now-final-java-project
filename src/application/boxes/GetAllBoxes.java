package application.boxes;

import application.common.IUseCase;
import domain.enums.UseCaseResultType;
import domain.models.Box;
import domain.models.UseCaseResult;
import infrastructure.repositories.BoxRepository;

import java.util.List;

public class GetAllBoxes implements IUseCase {
    private final BoxRepository _boxRepositoty = new BoxRepository();

    @Override
    public UseCaseResult<List<Box>> execute() {
        var boxes = this._boxRepositoty.getBoxes();

        return new UseCaseResult<>(
            UseCaseResultType.OK,
            null,
            boxes
        );
    }
}

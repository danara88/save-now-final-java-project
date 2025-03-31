package application.movements;

import application.common.IUseCase;
import domain.enums.UseCaseResultType;
import domain.models.Movement;
import domain.models.UseCaseResult;
import infrastructure.repositories.MovementRepository;

import java.util.List;
import java.util.stream.Collectors;

public class GetAllMovements implements IUseCase {
    private final MovementRepository _movementRepository = new MovementRepository();

    @Override
    public UseCaseResult <List<Movement>> execute() {
        List<Movement> movements = this._movementRepository.getMovements().stream().sorted().toList();

        return new UseCaseResult<>(
                UseCaseResultType.OK,
                null,
                movements
        );
    }
}

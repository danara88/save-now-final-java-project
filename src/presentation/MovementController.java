package presentation;

import application.boxes.GetAllBoxes;
import application.movements.CreateMovement;
import application.movements.GetAllMovements;
import domain.enums.MovementType;
import domain.enums.UseCaseResultType;
import domain.models.Movement;
import share.Constants;
import share.Utils;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.UUID;

public class MovementController {
    public void getAllMovementView() {
        System.out.println(Constants.getAllMovementsTitle);
        var getAllMovements = new GetAllMovements().execute();

        if (getAllMovements.type != UseCaseResultType.OK) {
            System.out.println(Constants.defaultErrorMessage);
            return;
        }

        var movements = getAllMovements.data;
        Locale locale = Locale.forLanguageTag("en-US");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);

        System.out.printf("%-40s %-40s %-10s %-15s %-10s%n", "ID", "Box ID", "Type", "Quantity ($)", "Created On");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
        movements.forEach((movement) -> {
            System.out.printf("%-40s %-40s %-10s  %-15s %-10s%n",
                    movement.id, movement.boxId, movement.movementType, numberFormat.format(movement.quantity), movement.createdAt.format(formatter));
        });

        System.out.println("\n");
    }

    public void createMovementView() {
        System.out.println(Constants.createMovementTitle);

        var boxes = new GetAllBoxes().execute();

        System.out.printf("%-30s %-40s%n", "Box name", "Box ID");
        System.out.println("--------------------------------------------------------");
        boxes.data.forEach(box -> {
            System.out.printf("%-30s %-40s%n", box.name, box.id);
        });
        System.out.println("\n");

        var boxIdInput = Utils.inputString("Enter box ID");
        var movementTypeInput = Utils.inputInteger("Enter Movement Type (0 -> Income, 1 -> Withdraw): ");
        MovementType movementType = switch (movementTypeInput) {
            case 0 -> MovementType.INCOME;
            case 1 -> MovementType.WITHDRAW;
            default -> null;
        };

        if (movementType == null) {
            System.out.println(Constants.defaultErrorMessage);
            return;
        }

        var quantityInput = Utils.inputDouble("Enter Quantity");

        var movement = new Movement(UUID.randomUUID().toString(), boxIdInput, movementType, quantityInput, LocalDateTime.now());

        var createMovementResult = new CreateMovement(movement).execute();

        if (createMovementResult.type != UseCaseResultType.OK) {
            System.out.println(Constants.defaultErrorMessage);
            return;
        }

        System.out.println(createMovementResult.data);
    }
}

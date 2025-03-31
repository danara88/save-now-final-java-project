package presentation;

import application.boxes.CreateBox;
import application.boxes.GetAllBoxes;
import domain.enums.UseCaseResultType;
import domain.models.Box;
import share.Constants;
import share.Utils;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.UUID;

public class BoxController {
    public void getAllBoxesView() {
        System.out.println(Constants.getAllBoxesTitle);
        var getAllBoxesResult = new GetAllBoxes().execute();

        if(getAllBoxesResult.type != UseCaseResultType.OK) {
            System.out.println(Constants.defaultErrorMessage);
            return;
        }

        var boxes = getAllBoxesResult.data;
        Locale locale = Locale.forLanguageTag("en-US");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);

        System.out.printf("%-40s %-40s %-15s%n", "ID", "Box name", "Quantity ($)");
        System.out.println("----------------------------------------------------------------------------------------------");

        boxes.forEach((box) -> {
            System.out.printf("%-40s %-40s %-15s%n",
                    box.id, box.name, numberFormat.format(box.quantity));
        });

        System.out.println("\n");
    }

    public void createBoxView() {
        System.out.println(Constants.createBoxTitle);

        String boxName = Utils.inputString("Enter box name");
        String boxDescription = Utils.inputString("Enter box description");
        double boxQuantity = Utils.inputDouble("Enter initial box quantity (type 0 if not quantity)");

        boxQuantity = boxQuantity > 0 ? boxQuantity : 0.00;

        Box inputBox = new Box(
                UUID.randomUUID().toString(),
                boxName,
                boxDescription,
                boxQuantity,
                LocalDateTime.now());

        var createBoxResult = new CreateBox(inputBox).execute();

        if(createBoxResult.type != UseCaseResultType.OK) {
            System.out.println(Constants.defaultErrorMessage);
            return;
        }

        System.out.println(Constants.resourceCreatedSuccess);
    }
}

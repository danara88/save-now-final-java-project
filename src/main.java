import presentation.BoxController;
import presentation.HomeController;
import presentation.MovementController;
import share.Constants;
import share.Utils;

import java.util.HashMap;

enum RoutePages {
    HOME_ROUTE,
    ALL_BOXES_LIST_ROUTE,
    CREATE_BOX_ROUTE,
    ALL_MOVEMENTS_ROUTE,
    CREATE_MOVEMENT_ROUTE,
    EXIT_ROUTE,
}

enum ProgramExecution {
    CONTINUE,
    EXIT
}

public class main {
    public static void main(String[] args) {
        var pagesConfig = _configurePages();
        Utils.printBannerAndMenu();
        terminal:
        while (true) {
            int selectedOption = Utils.inputInteger(Constants.enterOptionCopy);
            RoutePages page = pagesConfig.get(selectedOption);
            var programFlowExecution = _configureControllers(page);

            if (programFlowExecution == ProgramExecution.EXIT) {
                break terminal;
            }

            if(page != RoutePages.HOME_ROUTE) {
                System.out.println(Constants.menu);
            }
        }
    }

    private static HashMap<Integer, RoutePages> _configurePages() {
        HashMap<Integer, RoutePages> mapper = new HashMap<>();
        mapper.put(0, RoutePages.HOME_ROUTE);
        mapper.put(1, RoutePages.ALL_BOXES_LIST_ROUTE);
        mapper.put(2, RoutePages.CREATE_BOX_ROUTE);
        mapper.put(3, RoutePages.ALL_MOVEMENTS_ROUTE);
        mapper.put(4, RoutePages.CREATE_MOVEMENT_ROUTE);
        mapper.put(5, RoutePages.EXIT_ROUTE);
        return mapper;
    }

    private static ProgramExecution _configureControllers(RoutePages page) {
        var homeController = new HomeController();
        var boxController = new BoxController();
        var movementController = new MovementController();

        return switch (page) {
            case HOME_ROUTE -> {
                homeController.home();
                yield ProgramExecution.CONTINUE;
            }
            case ALL_BOXES_LIST_ROUTE -> {
                boxController.getAllBoxesView();
                yield ProgramExecution.CONTINUE;
            }
            case CREATE_BOX_ROUTE -> {
                boxController.createBoxView();
                yield ProgramExecution.CONTINUE;
            }
            case ALL_MOVEMENTS_ROUTE -> {
                movementController.getAllMovementView();
                yield ProgramExecution.CONTINUE;
            }
            case CREATE_MOVEMENT_ROUTE -> {
                movementController.createMovementView();
                yield ProgramExecution.CONTINUE;
            }
            case EXIT_ROUTE -> ProgramExecution.EXIT;
            default -> {
                System.out.println(Constants.unkownRoutePage + " " + page);
                yield ProgramExecution.CONTINUE;
            }
        };
    }
}

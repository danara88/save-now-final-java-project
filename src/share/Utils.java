package share;

import application.common.IUseCase;
import domain.models.UseCaseResult;
import java.util.Scanner;

public class Utils {
    /**
     * Main method to execute application use cases
     * @return UseCaseResult
     */
    public static UseCaseResult executeUseCase(IUseCase useCase) {
        return useCase.execute();
    }

    /**
     * Captures user prompt (string)
     * @return String
     */
    public static String inputString(String label) {
        System.out.println(label + Constants.defaultPromptLabel);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Captures user prompt (double)
     * @return Double
     */
    public static Double inputDouble(String label) {
        System.out.println(label + Constants.defaultPromptLabel);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextDouble();
    }

    /**
     * Captures user prompt (int)
     * @return Integer
     */
    public static Integer inputInteger(String label) {
        System.out.println(label + Constants.defaultPromptLabel);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    /**
     * Prints initial app banner and main menu
     */
    public static void printBannerAndMenu() {
        System.out.println(Constants.banner);
        System.out.println(Constants.menu);
    }
}

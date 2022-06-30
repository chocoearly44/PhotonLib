package tk.thesuperlab.photonlib.console;

import com.diogonunes.jcolor.Attribute;

import java.util.Scanner;

import static com.diogonunes.jcolor.Ansi.RESET;
import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.*;

public class ConsoleUtils {
	private static final Scanner scanner = new Scanner(System.in);
	public static Attribute[] errorFormat = new Attribute[] {RED_TEXT(), BOLD()};
	public static Attribute[] promptFormat = new Attribute[] {CYAN_TEXT(), BOLD()};
	public static Attribute[] titleFormat = new Attribute[] {WHITE_TEXT(), CYAN_BACK(), BOLD()};
	public static Attribute[] optionsTitleFormat = new Attribute[] {YELLOW_TEXT(), BOLD()};
	public static Attribute[] optionFormat = new Attribute[] {YELLOW_TEXT()};
	public static Attribute[] successFormat = new Attribute[] {WHITE_TEXT(), BLUE_BACK()};

	public ConsoleUtils() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * Used to print help screen.
	 *
	 * @param commandName
	 * @param commandDescription
	 * @hidden
	 */
	public static void printHelp(String commandName, String commandDescription) {
		// TODO handle help printing
	}

	/**
	 * Prompt user for options.
	 *
	 * @param optionsPrompt User prompt
	 * @param options       Option list
	 * @return selected option
	 */
	public static int printOptions(String optionsPrompt, String... options) {
		System.out.println(colorize(optionsPrompt, optionsTitleFormat));

		for(int i = 0; i < options.length; i++) {
			System.out.println(colorize(i + 1 + ". " + options[i], optionFormat));
		}

		boolean inputValid = false;
		int selection = 0;

		while(!inputValid) {
			System.out.print("> ");

			if(scanner.hasNextDouble()) {
				selection = scanner.nextInt();

				if(selection > options.length) {
					printError(ErrorType.INVALID_SELECTION);
				} else {
					inputValid = true;
				}
			} else {
				printError(ErrorType.NAN);
				scanner.next();
			}
		}

		return selection;
	}

	/**
	 * Print utility title
	 *
	 * @param utilityName    Name of utility
	 * @param utilityVersion Utility version
	 */
	public static void printUtilityTitle(String utilityName, String utilityVersion) {
		clearConsole();
		System.out.println(colorize(utilityName + " " + utilityVersion, titleFormat));
		System.out.println("\n");
	}

	/**
	 * Sysout equivalent
	 *
	 * @param message
	 */
	public static void printMessage(String message) {
		System.out.println(message);
	}

	/**
	 * Print section title
	 *
	 * @param title Title string
	 */
	public static void printTitle(String title) {
		System.out.println(colorize(title, successFormat));
	}

	/**
	 * Prompt user
	 *
	 * @param prompt Prompt string
	 * @return User input
	 */
	public static String prompt(String prompt) {
		System.out.println(colorize(prompt, promptFormat));
		System.out.print("> ");

		return scanner.nextLine();
	}

	/**
	 * Clears console
	 */
	public static void clearConsole() {
		System.out.print(RESET);
	}

	/**
	 * Print error to standard output
	 *
	 * @param errorMessage Error string
	 */
	public static void printError(String errorMessage) {
		System.out.println(colorize(errorMessage, errorFormat));
	}

	/**
	 * Prints error to standard output with pre-made type
	 *
	 * @param errorType Error type
	 */
	public static void printError(ErrorType errorType) {
		printError("", errorType);
	}

	/**
	 * Prints error to standard output with pre-made type and custom message
	 *
	 * @param message   Error string
	 * @param errorType Error type
	 */
	public static void printError(String message, ErrorType errorType) {
		String errorString = message + " ";

		switch(errorType) {
			case INVALID_ARG:
				errorString += "Invalid arguments.";
				break;

			case INVALID_SELECTION:
				errorString += "Invalid selection.";
				break;

			case NAN:
				errorString += "Not a number.";
				break;
		}

		System.out.println(colorize(errorString, errorFormat));
	}
}
package com.geektrust.backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import com.geektrust.backend.command.CommandInvoker;
import com.geektrust.backend.config.ApplicationConfig;
import com.geektrust.backend.exception.NoSuchCommandException;

public class App {

	// To run the application  ./gradlew run --args="sample_input/input2.txt"
	public static void main(String[] args) {
		List<String> commandLineArgs = new LinkedList<>(Arrays.asList(args));
            run(commandLineArgs);
	}
	public static void run(List<String> commandLineArgs) {
		ApplicationConfig applicationConfig = new ApplicationConfig();
		CommandInvoker commandInvoker = applicationConfig.getCommandInvoker();
		String inputFile = commandLineArgs.get(0);
		try(BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
			String line = reader.readLine();
			while (line != null) {
				List<String> tokens = Arrays.stream(line.split(" ")).collect(Collectors.toList());
				commandInvoker.executeCommand(tokens.get(0),tokens);
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException | NoSuchCommandException e) {
			e.printStackTrace();
		}
		}

}

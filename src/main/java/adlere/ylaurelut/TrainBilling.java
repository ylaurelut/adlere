package adlere.ylaurelut;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;

import com.google.gson.Gson;

import adlere.ylaurelut.engine.CustomerBillingEngine;
import adlere.ylaurelut.model.CustomerSummariesOutput;
import adlere.ylaurelut.model.TapsInput;

public class TrainBilling {

	/**
	 * Program entrypoint. Expects 2 parameters :
	 * <ul>
	 * <li>inputFile: path to the file containing all travel data</li>
	 * <li>ouputFilePath: filepath where result should be written</li>
	 * </ul>
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		validateInputParameters(args);

		String inputFilePath = args[0];
		String outputFilePath = args[1];

		Gson gson = new Gson();
		TapsInput tapsInput = gson.fromJson(new BufferedReader(new FileReader(inputFilePath)), TapsInput.class);

		CustomerBillingEngine cbe = new CustomerBillingEngine();
		CustomerSummariesOutput cso = cbe.getCustomerSummaries(tapsInput.getTaps());
		
		String result = gson.toJson(cso);
		PrintWriter outputFile = new PrintWriter(outputFilePath);
		outputFile.print(result);
		outputFile.flush();
		outputFile.close();
	}

	/**
	 * Performs all the necessary validation on parameters:
	 * - 2 and only 2 parameters,
	 * - first param is a readable file,
	 * - second param points to a writable filepath
	 */
	private static void validateInputParameters(String[] args) {
		if (args.length != 2) {
			System.out.println("ERROR : Wrong number of parameters");
			System.out.println("This program needs 2 parameters:");
			System.out.println("- <inputFile>: path to the file with travel data");
			System.out.println("- <outputFilePath>: filepath where result should be written");
			System.exit(1);
		}
		
		String inputFilePath = args[0];
		File inputFile = new File(inputFilePath);
		if(!Files.isReadable(FileSystems.getDefault().getPath(inputFile.getAbsolutePath()))) {
			System.err.println("Unable to read input file "+inputFilePath);
			System.exit(2);
		}
		
		// no easy way to check if the output file can be written.
		// We'll let an IOException fly if that happens
	}
}

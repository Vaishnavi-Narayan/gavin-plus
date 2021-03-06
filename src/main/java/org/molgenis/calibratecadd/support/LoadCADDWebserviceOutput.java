package org.molgenis.calibratecadd.support;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LoadCADDWebserviceOutput
{

	/**
	 * "chr_pos_ref_alt" to CADD PHRED score
	 * @param caddFile
	 * @return
	 * @throws FileNotFoundException
	 */
	public static Map<String, Double> load(File caddFile) throws Exception
	{
		HashMap<String, Double> caddScores;
		try (Scanner cadd = new Scanner(caddFile))
		{

			caddScores = new HashMap<>();

			String line;
			while (cadd.hasNextLine())
			{
				line = cadd.nextLine();
				if (line.startsWith("#"))
				{
					continue;
				}
				String[] split = line.split("\t", -1);
				if (split.length != 6)
				{
					throw new Exception("Expected 6 columns in CADD webservice output file, found " + split.length);
				}
				caddScores.put(split[0] + "_" + split[1] + "_" + split[2] + "_" + split[3], Double.parseDouble(split[5]));
			}
		}
		return caddScores;
	}

}
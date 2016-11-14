package com.senla.training.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Property {

	private static Holder holder;

	public static Holder getProps()  {

		InputStream inputStream = null;

		if (holder == null) {
			try {

				Properties prop = new Properties();
				inputStream = new FileInputStream(new File("Resources/config.properties"));

				if (inputStream != null) {
					prop.load(inputStream);

				}
				holder = new Holder(Boolean.valueOf(prop.getProperty("executePreorders")),
						Integer.parseInt(prop.getProperty("monthsUnwanted")),
						prop.getProperty("serializationPath"));

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					inputStream.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
		}

		return holder;
	}
}

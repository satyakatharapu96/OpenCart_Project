package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	@DataProvider(name = "LoginData")
	public String[][] getData() throws IOException {
		String path = ".\\testData\\opencart_logintestdata.xlsx";
		ExcelUtility utility = new ExcelUtility(path);
		int totalrows = utility.getRowCount("LogIn");
		int totalcols = utility.getCellCount("LogIn", 1);
		String logindata[][] = new String[totalrows][totalcols]; // created for two dem array
		for (int i = 1; i <= totalrows; i++) {
			for (int j = 0; j < totalcols; j++) {
				logindata[i - 1][j] = utility.getCellData("LogIn", i, j);
			}
		}
		return logindata;
	}

}

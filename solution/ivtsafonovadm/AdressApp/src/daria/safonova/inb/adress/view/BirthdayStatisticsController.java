package daria.safonova.inb.adress.view;

import java.util.List;
import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Locale;

import daria.safonova.inb.adress.model.SPersonD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;

public class BirthdayStatisticsController {

	@FXML
	private BarChart<String, Integer> sbarChartd;

	@FXML
	private CategoryAxis sxAxisd;

	private ObservableList<String> smonthNamesd = FXCollections.observableArrayList();

	@FXML
	private void initialize() {
		String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH)
				.getMonths();
		smonthNamesd.addAll(Arrays.asList(months));

		sxAxisd.setCategories(smonthNamesd);
	}

	public void ssetPersonDataD(List<SPersonD> persons) {
		int[] monthCounter = new int[12];
		for (SPersonD p : persons) {
			int month = p.getBirthday().getMonthValue() - 1;
			monthCounter[month]++;
		}

		XYChart.Series<String, Integer> series = new XYChart.Series<>();

		for (int i = 0; i < monthCounter.length; i++) {
			series.getData().add(
					new XYChart.Data<>(smonthNamesd.get(i), monthCounter[i]));
		}

		sbarChartd.getData().add(series);
	}
}

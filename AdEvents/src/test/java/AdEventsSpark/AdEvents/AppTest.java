package AdEventsSpark.AdEvents;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.SparkSession;
import org.junit.Test;

import com.adevents.SparkApplication;
import com.adevents.model.InputEvent;
import com.adevents.model.OutputEvent;

public class AppTest {

	@Test
	public void createSparkSessionTest() {
		SparkConf sparkConf = new SparkConf().setAppName("Ad Events Analysis")
				.setMaster("local[*]").set("spark.executor.memory","20g");
		SparkSession expectedSpark = SparkSession.builder().config(sparkConf).getOrCreate();
		SparkSession actualSpark = SparkApplication.createSparkSession();
		assertEquals(expectedSpark, actualSpark);
	}

	@Test
	public void AdEventAnalysisTest() {	
		SparkSession spark = SparkApplication.createSparkSession();

		OutputEvent outEvent = new OutputEvent();
		outEvent.setId("abc");
		outEvent.setPageUrl("google");
		outEvent.setNextPageUrl("quora");
		outEvent.setTimestamp("123");
		outEvent.setVisitorId("myself");
		outEvent.setType("view");

		OutputEvent outEventTwo = new OutputEvent();
		outEventTwo.setId("abc2");
		outEventTwo.setPageUrl("quora");
		outEventTwo.setNextPageUrl("null");
		outEventTwo.setTimestamp("1234");
		outEventTwo.setVisitorId("myself");
		outEventTwo.setType("view");

		OutputEvent outEventThree = new OutputEvent();
		outEventThree.setId("abc3");
		outEventThree.setPageUrl("nba");
		outEventThree.setNextPageUrl("null");
		outEventThree.setTimestamp("1234");
		outEventThree.setVisitorId("player");
		outEventThree.setType("view");

		InputEvent inEvent = new InputEvent();
		inEvent.setId("abc");
		inEvent.setPageUrl("google");
		inEvent.setTimestamp("123");
		inEvent.setVisitorId("myself");
		inEvent.setType("view");

		InputEvent inEventTwo = new InputEvent();
		inEventTwo.setId("abc2");
		inEventTwo.setPageUrl("quora");
		inEventTwo.setTimestamp("1234");
		inEventTwo.setVisitorId("myself");
		inEventTwo.setType("view");

		InputEvent inEventThree = new InputEvent();
		inEventThree.setId("abc3");
		inEventThree.setPageUrl("nba");
		inEventThree.setTimestamp("1234");
		inEventThree.setVisitorId("player");
		inEventThree.setType("view");

		List<OutputEvent> testOutputData = new ArrayList<>();
		testOutputData.add(outEvent);
		testOutputData.add(outEventTwo);
		testOutputData.add(outEventThree);

		List<InputEvent> testInputData = new ArrayList<>();
		testInputData.add(inEvent);
		testInputData.add(inEventTwo);
		testInputData.add(inEventThree);

		Dataset<InputEvent> testInputDataset = spark.createDataFrame(testInputData, InputEvent.class).as(Encoders.bean(InputEvent.class));
		Dataset<OutputEvent> actualResult = SparkApplication.adEventAnalysis(spark, testInputDataset);
		List<OutputEvent> actualData = actualResult.collectAsList();
		if(actualData.size()==testOutputData.size()) {
			for(int i=0;i<actualData.size();i++) {
				if(testOutputData.get(i).equals(actualData.get(i))) {
					assertEquals(testOutputData.get(i).toString(), actualData.get(i).toString());
				}
			}
		}
	}
}
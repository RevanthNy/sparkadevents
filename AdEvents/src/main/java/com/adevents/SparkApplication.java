package com.adevents;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import com.adevents.model.InputEvent;
import com.adevents.model.OutputEvent;

public class SparkApplication 
{
	public static void main(String[] args)
	{
		BasicConfigurator.configure();
		SparkSession spark = createSparkSession();
		//JavaSparkContext javaSparkContext = new JavaSparkContext(spark.sparkContext());
		String inputFilePath = args[0];
		String outputFilePath = args[1];
		Dataset<InputEvent> inputEvents =
				spark.read().format("json")
				.load(inputFilePath)
				.as(Encoders.bean(InputEvent.class));
		Dataset<OutputEvent> result = adEventAnalysis(spark, inputEvents);
		result.show(100);
		result.coalesce(1).write().json(outputFilePath);
	}

	public static SparkSession createSparkSession() {
		SparkConf sparkConf = new SparkConf().setAppName("Ad Events Analysis")
				.setMaster("local[*]").set("spark.executor.memory","20g");
		SparkSession spark = SparkSession.builder().config(sparkConf).getOrCreate();
		return spark;
	}

	public static Dataset<OutputEvent> adEventAnalysis(SparkSession spark, Dataset<InputEvent> inputEvents) {
		Dataset<InputEvent> filteredInputEvents = inputEvents.filter(inputEvents.col("visitorId").isNotNull());
		Dataset<InputEvent> orderedInputEvents = filteredInputEvents.orderBy("visitorId", "timestamp");
		List<InputEvent> collectedData = orderedInputEvents.collectAsList();
		List<OutputEvent> outputEvents = new ArrayList<OutputEvent>();
		for(int i=1;i<=collectedData.size();i++) {
			OutputEvent outEvent = new OutputEvent();
			outEvent.setId(collectedData.get(i-1).getId());
			outEvent.setPageUrl(collectedData.get(i-1).getPageUrl());
			outEvent.setTimestamp(collectedData.get(i-1).getTimestamp());
			outEvent.setType(collectedData.get(i-1).getType());
			outEvent.setVisitorId(collectedData.get(i-1).getVisitorId());
			if(i == collectedData.size()) {
				outEvent.setNextPageUrl("null");
			} else {
				if(collectedData.get(i-1).getVisitorId().equals(collectedData.get(i).getVisitorId())) {
					outEvent.setNextPageUrl(collectedData.get(i).getPageUrl());
				}else
					outEvent.setNextPageUrl("null");
			}
			outputEvents.add(outEvent);
		}
		Dataset<Row> initialResult = spark.createDataFrame(outputEvents, OutputEvent.class);
		Dataset<OutputEvent> result  = initialResult.select("id", "pageUrl", "timestamp", "type", "visitorId", "nextPageUrl").as(Encoders.bean(OutputEvent.class));
		return result;
		//result.coalesce(1).write().csv("/Users/revanthnyalakonda/Downloads/ad-events/results/csvFile");
		//result.coalesce(1).javaRDD().map(x -> x.toString()).saveAsTextFile("/Users/revanthnyalakonda/Downloads/ad-events/results/textFile");
	}
}

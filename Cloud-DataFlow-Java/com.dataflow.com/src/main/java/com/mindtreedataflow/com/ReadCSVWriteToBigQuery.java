package com.mindtreedataflow.com;

import java.util.ArrayList;
import java.util.List;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.io.gcp.bigquery.BigQueryIO;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.api.services.bigquery.model.TableFieldSchema;
import com.google.api.services.bigquery.model.TableReference;
import com.google.api.services.bigquery.model.TableRow;
import com.google.api.services.bigquery.model.TableSchema;

public class ReadCSVWriteToBigQuery {
	private static final Logger LOG = LoggerFactory.getLogger(ReadCSVWriteToBigQuery.class);
	private static String HEADERS = "ID,Code,Value,Date";

	public static class FormatForBigquery extends DoFn<String, TableRow> {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String[] columnNames = HEADERS.split(",");

		@ProcessElement
		public void processElement(ProcessContext c) {
			TableRow row = new TableRow();
			String[] parts = c.element().split(",");

			if (!c.element().contains(HEADERS)) {
				for (int i = 0; i < parts.length; i++) {
					// No typy conversion at the moment.
					row.set(columnNames[i], parts[i]);
				}
				c.output(row);
			}
		}

		/** Defines the BigQuery schema used for the output. */

		static TableSchema getSchema() {
			List<TableFieldSchema> fields = new ArrayList<>();
			// Currently store all values as String
			fields.add(new TableFieldSchema().setName("ID").setType("STRING"));
			fields.add(new TableFieldSchema().setName("Code").setType("STRING"));
			fields.add(new TableFieldSchema().setName("Value").setType("STRING"));
			fields.add(new TableFieldSchema().setName("Date").setType("STRING"));

			return new TableSchema().setFields(fields);
		}
	}

	public static final void main(String args[]) throws Exception {
		String sourceFilePath = "gs://dataflow-myfirstproject/input/Book2.csv";
		String tempLocationPath = "gs://dataflow-myfirstproject/temp/";
		boolean isStreaming = false;
		TableReference tableRef = new TableReference();
		// Replace this with your own GCP project id
		tableRef.setProjectId("lexical-sol-217609");
		tableRef.setDatasetId("sample_ds");
		tableRef.setTableId("sample");
		// Create the pipeline.
		PipelineOptions options = PipelineOptionsFactory.fromArgs(args).withValidation().create();
	
		// This is required for BigQuery
		options.setTempLocation(tempLocationPath);
		options.setJobName("csvtobigquery");
		 

		Pipeline p = Pipeline.create(options);
		p.apply("Read CSV File", TextIO.read().from(sourceFilePath))
				.apply("Log Message", ParDo.of(new DoFn<String, String>() {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@ProcessElement
					public void processElement(ProcessContext c) {
						LOG.info("Processing row: " + c.element());
						c.output(c.element());
					}
				})).apply("Convert to BigQuery TableRow", ParDo.of(new FormatForBigquery()))
				.apply("Write into BigQuery",
						BigQueryIO.writeTableRows().to(tableRef).withSchema(FormatForBigquery.getSchema())
								.withCreateDisposition(BigQueryIO.Write.CreateDisposition.CREATE_IF_NEEDED)
								.withWriteDisposition(isStreaming ? BigQueryIO.Write.WriteDisposition.WRITE_APPEND
										: BigQueryIO.Write.WriteDisposition.WRITE_TRUNCATE));

			p.run().waitUntilFinish();

	}

}
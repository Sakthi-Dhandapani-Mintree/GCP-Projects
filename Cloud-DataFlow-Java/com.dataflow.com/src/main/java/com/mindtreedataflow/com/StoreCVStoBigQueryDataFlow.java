package com.mindtreedataflow.com;

import java.util.Arrays;

import org.apache.beam.runners.dataflow.DataflowRunner;
import org.apache.beam.runners.dataflow.options.DataflowPipelineOptions;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.io.gcp.bigquery.BigQueryIO;
import org.apache.beam.sdk.io.gcp.bigquery.BigQueryIO.Write.CreateDisposition;
import org.apache.beam.sdk.io.gcp.bigquery.BigQueryIO.Write.WriteDisposition;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;

import com.google.api.services.bigquery.model.TableRow;

public class StoreCVStoBigQueryDataFlow {
	public static class StringToRowConverter extends DoFn<String, TableRow>{
		private String [] columnNames;
		private boolean isFirstRow = true;
		
		@ProcessElement
		public void processElement(ProcessContext pc) {
			TableRow tableRow = new TableRow();
			String [] parts = pc.element().split(",");
			if(isFirstRow) {
				columnNames = Arrays.copyOf(parts, parts.length);
				isFirstRow = false;
			}else {
				for(int i = 0;i<parts.length;i++) {
					tableRow.set(columnNames[i], parts[i]);
				}
				pc.output(tableRow);
			}
		}
	}
	public static String sourceFilePath = "gs://dataflow-myfirstproject/input/Book2.csv";
	String tempLocationPath = "gs://dataflow-myfirstproject/temp/";

	public static void main(String[] args) {
		DataflowPipelineOptions options = PipelineOptionsFactory.fromArgs(args).withValidation()
				.as(DataflowPipelineOptions.class);
		options.setZone("asia-south1-c");
		options.setProject("lexical-sol-217609");
		options.setJobName("xxxx");
		options.setStagingLocation("gs://dataflow-myfirstproject/temp/");
		 options.setRunner(DataflowRunner.class);
	
		
		Pipeline p = Pipeline.create(options);

		p.apply("ReadLines", TextIO.read().from(sourceFilePath))
				.apply("ConverToBQRow", ParDo.of(new StringToRowConverter()))
				.apply("WriteToBq",
						BigQueryIO.writeTableRows().to("lexical-sol-217609:sample_ds.dataflow_table")
								.withWriteDisposition(WriteDisposition.WRITE_APPEND)
								.withCreateDisposition(CreateDisposition.CREATE_NEVER));
		p.run().waitUntilFinish();
	}

}

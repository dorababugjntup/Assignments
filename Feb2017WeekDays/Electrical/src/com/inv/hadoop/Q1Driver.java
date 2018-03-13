package com.inv.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class Q1Driver extends Configured implements Tool{
	@Override
	public int run(String[] arg0) throws Exception {
		
		Configuration conf=this.getConf();
		
		System.out.println("===="+conf.get("mapred.textoutputformat.separator"));
		conf.set("mapred.textoutputformat.separator","|");
		
		Job job=Job.getInstance(conf);
		job.setJobName("Electrical Q1");
		
		job.setJarByClass(Q1Driver.class);
		job.setMapperClass(Q1Mapper.class);
		job.setNumReduceTasks(0);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		FileInputFormat.addInputPath(job, new Path(arg0[0]));
		FileOutputFormat.setOutputPath(job, new Path(arg0[1]));
		
		return job.waitForCompletion(true)?0:1;
		
	}

	public static void main(String[] args) throws Exception {
		System.exit(ToolRunner.run(new Q1Driver(), args));
	}
	
}

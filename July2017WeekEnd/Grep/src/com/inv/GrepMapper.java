package com.inv;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class GrepMapper extends Mapper<LongWritable, Text, Text, NullWritable>{
	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		if(value.toString().toLowerCase().contains("hadoop")){
			//case insensitive
			//Hadoop HADOOP hadoop 
			context.write(value, NullWritable.get());
		}
	}
}

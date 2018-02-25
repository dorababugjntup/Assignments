package com.inv;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.map.RegexMapper;

public class WordCountMapper  extends Mapper<Text, Text, Text, Text>{
	// source-> override implement methods
	@Override
	protected void map(Text key, Text value, Context context)
			throws IOException, InterruptedException {
		
		context.write(new Text(key.toString()+"####"), value);
		
		
	}

}











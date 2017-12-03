package com.inv;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCountMapper extends 
	Mapper<LongWritable, Text, Text	, IntWritable>{
	
	ArrayList<String> ls = new ArrayList<String>();
	IntWritable one=new IntWritable(1);
	Text pair=new Text();
	@Override
	protected void map(LongWritable key, Text value, 
				Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
	
		StringTokenizer words=new StringTokenizer(value.toString(), " ");
		while(words.hasMoreTokens()){
			ls.add(words.nextToken());
		
		}		
	}
	@Override
	protected void cleanup(Context context)
			throws IOException, InterruptedException {
		for (int i = 0; i < ls.size()-1; i++) {
			pair.set(ls.get(i)+" "+ls.get(i+1));
			context.write(pair,one);			
	
		}
	}

}

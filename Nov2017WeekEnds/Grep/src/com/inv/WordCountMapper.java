package com.inv;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCountMapper extends 
	Mapper<LongWritable, Text, Text	, NullWritable>{
	
	String searchword="";
	
	@Override
	protected void setup(Context context)
			throws IOException, InterruptedException {
		Configuration conf=context.getConfiguration();
		 searchword=conf.get("sword");
		System.out.println("******* SEARCH WORD IS="+searchword);
		System.out.println("******* blocksize="+conf.get("dfs.blocksize"));
		System.out.println("******* name node address="+conf.get("fs.defaultFS"));
		
	
	}
	@Override
	protected void map(LongWritable key, Text value, 
				Mapper<LongWritable, Text, Text, NullWritable>.Context context)
			throws IOException, InterruptedException {
			
			
			
			
		if(value.toString().toLowerCase().contains(searchword.toLowerCase())){
			context.write(value, NullWritable.get());
		}
		
		
	}
	

}

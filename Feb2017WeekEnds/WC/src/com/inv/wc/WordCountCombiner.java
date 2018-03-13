package com.inv.wc;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WordCountCombiner extends Reducer<Text, IntWritable, Text, IntWritable>{

	
	
	
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {

			int sum=0;
			
			for(IntWritable val:values){
				sum=sum+val.get();
			}
			context.write(key, new IntWritable(sum));
			//System.out.println("+++++I am inside Reduce reduce method");
	
	}
	
}





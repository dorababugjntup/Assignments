package com.inv;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class Q1Mapper extends Mapper<LongWritable, Text, Text, Text>{
	@Override
	protected void setup(Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		context.write(new Text("year"), new Text("min"+"\t"+"max"));	
		context.write(new Text("----------------------"),new Text( ""));
	}
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
	    //year,Jan,Feb,Mar,Apr,May,Jun,Jul,Aug,Sep,Oct,Nov,Dec,Avg
		//1979,23,23,2,43,24,25,26,26,26,26,25,26,25
		String vals[]=value.toString().split(",");
		String year=vals[0];
		int min=99999;
		int max=-99999;
		//take it from first element, dont take the average

		for (int i = 1; i < vals.length-1; i++) {
			int consumption=Integer.parseInt(vals[i]);
			if(min>consumption){
				min=consumption;				
			}
			if(max<consumption){
				max=consumption;				
			}
		}
	
		context.write(new Text(year), new Text(min+"\t"+max));
	}
	@Override
	protected void cleanup(Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		context.write(new Text("----------------------"),new Text( ""));
	
	}
	
}









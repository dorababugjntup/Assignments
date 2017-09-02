package com.inv;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;


public class GrepDriver extends Configured implements Tool{

	//COmmand line arguments
	//-Dsword=level  -DCOLOR=AAAA    -DCOLOR2=BBBB   /home/hadoop/wiki.txt   /home/hadoop/OutFiles/grep_conf
	
	/*
	 * Generic options supported are
-conf <configuration file>     specify an application configuration file
-D <property=value>            use value for given property
-fs <local|namenode:port>      specify a namenode
-jt <local|resourcemanager:port>    specify a ResourceManager
-files <comma separated list of files>    specify comma separated files to be copied to the map reduce cluster
-libjars <comma separated list of jars>    specify comma separated jar files to include in the classpath.
-archives <comma separated list of archives>    specify comma separated archives to be unarchived on the compute machines.

	 * (non-Javadoc)
	 * @see org.apache.hadoop.util.Tool#run(java.lang.String[])
	 */
	
	@Override
	public int run(String[] arg0) throws Exception {
		
		for (int i = 0; i < arg0.length; i++) {
			System.out.println("+++in RUN methid args["+i+"]=="+arg0[i]);
		}
		
//Configuration conf=new Configuration();
		Configuration conf=this.getConf();
		
		//conf.set("sword","spark");
	
		//conf.set("COLOR","RED");		
		//conf.set("mapreduce.job.reduces", "0");
		conf.set("mapreduce.job.name", "GREP CODE");
		
		
		Job job=Job.getInstance(conf);
		
		job.setJarByClass(GrepDriver.class);
		job.setMapperClass(GrepMapper.class);
		
		//job.setNumReduceTasks(0);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(NullWritable.class);
		
		FileInputFormat.addInputPath(job, new Path(arg0[0]));
		FileOutputFormat.setOutputPath(job, new Path(arg0[1]));
		
		return job.waitForCompletion(true)?0:1;
		
	}
	
	
	
	public static void main(String[] args) throws Exception {
		
		
		for (int i = 0; i < args.length; i++) {
			System.out.println("+++in Main methid args["+i+"]=="+args[i]);
		}
		
		int res=ToolRunner.run(new GrepDriver(), args);
		System.exit(res);
				
	}


}

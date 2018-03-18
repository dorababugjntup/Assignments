import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.GzipCodec;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class Q1Driver extends Configured implements Tool {
	@Override
	public int run(String[] arg0) throws Exception {
		// args0 ratings args1 is firsjob output, args2 is  final job op
		 Configuration conf=this.getConf();
		 
		 conf.set("mapreduce.output.fileoutputformat.compress", "true");
		 conf.set("mapreduce.output.fileoutputformat.compress.codec", "org.apache.hadoop.io.compress.GzipCodec");
		 conf.set("mapreduce.map.output.compress", "true");
		 Job job1=Job.getInstance(conf,"MovieLens");
		 job1.setJarByClass(Q1Driver.class);;
		 job1.setMapperClass(Q1Mapper.class);
		 job1.setReducerClass(Q1Reducer.class);
		 
		 job1.setOutputKeyClass(Text.class);
		 job1.setOutputValueClass(IntWritable.class);
		 
		 FileSystem fs=FileSystem.get(conf);
		 if(fs.exists(new Path(arg0[1]))){
			 fs.delete(new Path(arg0[1]), true);
		 }
		 if(fs.exists(new Path(arg0[2]))){
			 fs.delete(new Path(arg0[2]), true);
		 }
		 
		 
		 FileInputFormat.addInputPath(job1, new Path(arg0[0]));
		 FileOutputFormat.setOutputPath(job1, new Path(arg0[1]));
		// boolean status=job1.waitForCompletion(true);
		 if (job1.waitForCompletion(true) ) // if first job is complete
		 {
			 Job job2=Job.getInstance(conf,"MovieLens");
			 job2.setJarByClass(Q2Driver.class);;
			 job2.setMapperClass(Q2Mapper.class);
			 job2.setReducerClass(Q2Reducer.class);
			 
			 job2.addCacheFile(new URI("/home/hadoop/Music/Classes/MovieLens-Work/ml-1m/movies.dat"));
			 
			 //when job input and job outout both are diff
			 job2.setOutputKeyClass(Text.class);
			 job2.setOutputValueClass(IntWritable.class);
			 //when map output and job outout both are diff use below line
			 job2.setMapOutputKeyClass(IntWritable.class);
			 job2.setMapOutputValueClass(Text.class);
			 
			 
			 
			 
			 FileInputFormat.addInputPath(job2, new Path(arg0[1]));
			 
			 FileOutputFormat.setOutputPath(job2, new Path(arg0[2]));
			//FileOutputFormat.setOutputCompressorClass(job2, GzipCodec.class);
			 return job2.waitForCompletion(true)?0:1;	
		 }
		 else
			 return 1; // if first job is not complete
		 
		 
		 
		 
		
	}
	public static void main(String[] args) throws Exception {
		
		
		// /home/hadoop/Music/Classes/MovieLens-Work/ml-1m/ratings.dat    
		// /home/hadoop/Music/Classes/MovieLens-Work/ml-1m/Op1  
		// /home/hadoop/Music/Classes/MovieLens-Work/ml-1m/Op2
		
		System.exit(ToolRunner.run(new Q1Driver(), args));
	}

}

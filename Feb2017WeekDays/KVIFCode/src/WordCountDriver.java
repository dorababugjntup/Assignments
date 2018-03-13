import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCountDriver {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		
		Configuration conf=new Configuration();
		
		conf.set("mapreduce.input.keyvaluelinerecordreader.key.value.separator", ",");
		conf.set("mapreduce.output.textoutputformat.separator", "||||");
		
		Job job=Job.getInstance(conf, "Sample WC program");
		
		job.setJarByClass(WordCountDriver.class);
		job.setMapperClass(WordCountMapper.class);
		job.setReducerClass(WordCountReducer.class);
		
		job.setNumReduceTasks(0);
		
		job.setMapOutputKeyClass(Text.class );
		job.setMapOutputValueClass(Text.class);
		
//		job.setOutputKeyClass(Text.class);
//		job.setOutputValueClass(Text.class);
		
		//job.setInputFormatClass(cls);
		
		job.setInputFormatClass(KeyValueTextInputFormat.class);
		
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		//FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		
		boolean status=job.waitForCompletion(true);
		
		System.exit(status?0:1);
		
	}
	
}

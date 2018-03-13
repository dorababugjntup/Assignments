import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class JoinDriver {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {
		
		Configuration conf=new Configuration();
		conf.set("mapreduce.output.textoutputformat.separator", ",");
		Job job=Job.getInstance(conf, "ReduceSide Join program");
		
		
		job.addCacheFile(new URI("file:///home/hadoop/YARNBOX/WORKSPACES/Sept2016WEBatch_GitRepo/SET2/MapReduceCode/Joins-MapReduce/input/dept"));
		
		
		job.setJarByClass(JoinDriver.class);
		job.setMapperClass(EmpMapper.class);

		job.setNumReduceTasks(0);
		
		job.setMapOutputKeyClass(Text.class );
		job.setMapOutputValueClass(Text.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		

		FileInputFormat.addInputPath(job, new Path(args[0]));
		
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		
		boolean status=job.waitForCompletion(true);
		
		System.exit(status?0:1);
		
	}
	
}

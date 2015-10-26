package ECEhadoop;

import java.io.IOException;
import java.util.*;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.util.*;

public class Tp1Ex1 {

   public static class Map extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {
     private Text word = new Text();
     private IntWritable balance = new IntWritable();

     public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
       String line = value.toString();
       StringTokenizer tokenizer = new StringTokenizer(line,";");
       String tmp = new String();
       //Get Ride of information first name
       tokenizer.nextToken();
       //Get information : city
       word.set(tokenizer.nextToken());
       //Get ride of information age
       tokenizer.nextToken();
       //Get information : bank_balance
       tmp = tokenizer.nextToken();
       if(tmp.compareTo("bank_balance") == 0)
        {}
      else {
        balance.set(Integer.decode(tmp).intValue());
        //Map city and balance
        output.collect(word, balance);
      }
     }
   }

   public static class Reduce extends MapReduceBase implements Reducer<Text, IntWritable, Text, IntWritable> {
     public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
       int sum = 0;
       while (values.hasNext()) {
         sum += values.next().get();
       }
       output.collect(key, new IntWritable(sum));
     }
   }

   public static void main(String[] args) throws Exception {
     JobConf conf = new JobConf(Tp1Ex1.class);
     conf.setJobName("tp1ex1");

     conf.setOutputKeyClass(Text.class);
     conf.setOutputValueClass(IntWritable.class);

     conf.setMapperClass(Map.class);
     conf.setCombinerClass(Reduce.class);
     conf.setReducerClass(Reduce.class);

     conf.setInputFormat(TextInputFormat.class);
     conf.setOutputFormat(TextOutputFormat.class);

     conf.set("mapred.textoutputformat.separator", ";");

     FileInputFormat.setInputPaths(conf, new Path(args[0]));
     FileOutputFormat.setOutputPath(conf, new Path(args[1]));

     JobClient.runJob(conf);
   }
}

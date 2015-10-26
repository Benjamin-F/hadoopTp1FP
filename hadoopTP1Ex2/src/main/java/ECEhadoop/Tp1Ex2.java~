package org.myorg;

import java.io.IOException;
import java.util.*;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.util.*;

public class Tp1Ex2 {

   public static class Map extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {
     private Text ageClass = new Text();
     private IntWritable nbOfFriends = new IntWritable();

     public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
       String line = value.toString();
       StringTokenizer tokenizer = new StringTokenizer(line,";");
       String tmp = new String();
       int age;

       //Get Ride of information first name and city
       tokenizer.nextToken();
       tokenizer.nextToken();

       //Get information : age
       tmp = tokenizer.nextToken();
       if(tmp.compareTo("age") == 0)
        {}
      else {
        age = Integer.decode(tmp).intValue();
        if(age <= 5)
          tmp = new String("0 - 5");
        if(age >= 6 && age <= 12 )
          tmp = new String("6 - 12");
        if(age >= 13 && age <= 17)
          tmp = new String("13 - 17");
        if(age >= 18 && age <= 25)
          tmp = new String("18 - 25");
        if(age >= 26 && age <= 35)
          tmp = new String("26 - 35");
        if(age >= 36 && age <= 45)
          tmp = new String("36 - 45");
        if(age >= 46 && age <= 60)
          tmp = new String("46 - 60");
        if(age > 60)
          tmp = new String("60 + ");
        ageClass.set(tmp);
      }

       //Get ride of information bank_balance
       tokenizer.nextToken();

       //Get information : nbOfFriends
       tmp = tokenizer.nextToken();
       if(tmp.compareTo("number_of_friends") == 0)
        {}
      else {
        nbOfFriends.set(Integer.decode(tmp).intValue());
        //Map age and number_of_friends
        output.collect(ageClass, nbOfFriends);
      }
     }
   }

   public static class Reduce extends MapReduceBase implements Reducer<Text, IntWritable, Text, IntWritable> {
     public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
       int sum = 0;
       int nbOfValues = 0;
       int mean;
       while (values.hasNext()) {
         sum += values.next().get();
         nbOfValues++;
       }
       mean = sum/nbOfValues;
       output.collect(key, new IntWritable(mean));
     }
   }

   public static void main(String[] args) throws Exception {
     JobConf conf = new JobConf(Tp1Ex2.class);
     conf.setJobName("tp1ex2");

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

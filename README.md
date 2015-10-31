# TP1 Hadoop
##Exercise 1

First, we generate the mvn project
```
mvn -B archetype:generate   -DarchetypeGroupId=org.apache.maven.archetypes   -DgroupId=ECEhadoop   -DartifactId=hadoopTP1Ex1
```
We place our .java file in the directory: /hadoopTP1Ex1/src/main/java/ECEhadoop

Then, we run the compilation command in the hadoopTP1Ex1 directory
```
mvn compile
```

Finally, creation of the package
```
mvn package
```


Then we launch the hadoop job
```
hadoop jar hadoopTP1Ex1.jar ECEhadoop.Tp1Ex1 /res/mapred_assignment/file.csv output-tp1ex1
```

Finally, we export the result into a .csv file
```
hdfs dfs -copyToLocal output-tp1ex1/part-00000 resultatEx1.csv
```

##Question : Is it useful to use reducer as combiner class?

Using reducer this way improves the applications's performances. The step where the key is bind to several values is skipped. This step is not necessary in our case.

##Exercise 2

To create the maven project, we use the same commands as in the first exercise
```
mvn -B archetype:generate   -DarchetypeGroupId=org.apache.maven.archetypes   -DgroupId=ECEhadoop   -DartifactId=hadoopTP1Ex2
```
And we place our .java file in the directory: /hadoopTP1Ex2/src/main/java/ECEhadoop

We compile and create the package
```
mvn compile
mvn package
```

Run hadoop and export result
```
hadoop jar hadoopTP1Ex2.jar ECEhadoop.Tp1Ex2 /res/mapred_assignment/file.csv output-tp1ex2
hdfs dfs -copyToLocal output-tp1ex2/part-00000 resultatEx2.csv
```


# TP1 Hadoop
##Exercise 1

First, we generate the mvn project
```
mvn -B archetype:generate   -DarchetypeGroupId=org.apache.maven.archetypes   -DgroupId=ECEhadoop   -DartifactId=hadoopTP1Ex1
```

Then, we run the compilation command in the hadoopTP1Ex1 directory
```
mvn compile
```

Finally, creation of the package
```
mvn package
```
##Question : Is it useful to use reducer as combiner class?

Using reducer this way improves the applications's performances. The step where the key is bind to several values is skipped. This step is not necessary in our case.

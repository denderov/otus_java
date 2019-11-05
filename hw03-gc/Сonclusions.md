-Xms1024m
-Xmx1024m
-XX:+PrintSafepointStatistics
-XX:PrintSafepointStatisticsCount=1
-XX:+HeapDumpOnOutOfMemoryError
-XX:HeapDumpPath=./logs/dump
-XX:+UseSerialGC

time elapsed: 131
time elapsed: 134


#### G1

###### 2 CPUs

time elapsed: 105

time elapsed: 160

Garbage collector: 

Name = 'G1 Young Generation', Collections = 662, Total time spent = 1 minute

Garbage collector: 

Name = 'G1 Old Generation', Collections = 41, Total time spent = 24.130 seconds

#### CMS

###### 2 CPUs

time elapsed: 146

time elapsed: 163

Garbage collector: 

Name = 'ParNew', Collections = 198, Total time spent = 1 minute

Garbage collector: 

Name = 'ConcurrentMarkSweep', Collections = 105, Total time spent = 1 minute

#### ParallelGC

###### 2 CPUs

time elapsed: 144

time elapsed: 182

Garbage collector: 

Name = 'PS MarkSweep', Collections = 99, Total time spent = 24.153 seconds

Garbage collector:
 
Name = 'PS Scavenge', Collections = 389, Total time spent = 2 minutes

#### SerialGC

###### 2 CPUs

time elapsed: 98

Garbage collector: 

Name = 'Copy', Collections = 193, Total time spent = 34.637 seconds

Garbage collector: 

Name = 'MarkSweepCompact', Collections = 96, Total time spent = 49.196 seconds



#### G1

time elapsed: 255

Garbage collector: 

Name = 'G1 Young Generation', Collections = 940, Total time spent = 2 minutes

Garbage collector: 

Name = 'G1 Old Generation', Collections = 39, Total time spent = 40.107 seconds

time elapsed: 294

Garbage collector: 

Name = 'G1 Young Generation', Collections = 1,020, Total time spent = 2 minutes

Garbage collector: 

Name = 'G1 Old Generation', Collections = 50, Total time spent = 50.632 seconds

#### CMS

time elapsed: 319

Garbage collector: 

Name = 'ParNew', Collections = 405, Total time spent = 2 minutes

Garbage collector: 

Name = 'ConcurrentMarkSweep', Collections = 105, Total time spent = 2 minutes

time elapsed: 327

Garbage collector: 

Name = 'ParNew', Collections = 401, Total time spent = 2 minutes

Garbage collector: 

Name = 'ConcurrentMarkSweep', Collections = 102, Total time spent = 2 minutes

#### ParallelGC

time elapsed: 173

Garbage collector: 

Name = 'PS MarkSweep', Collections = 100, Total time spent = 26.091 seconds

Garbage collector: 

Name = 'PS Scavenge', Collections = 390, Total time spent = 2 minutes

time elapsed: 179

Garbage collector: 

Name = 'PS MarkSweep', Collections = 100, Total time spent = 32.271 seconds

Garbage collector:
 
Name = 'PS Scavenge', Collections = 389, Total time spent = 2 minutes

#### SerialGC

time elapsed: 119

Garbage collector: 

Name = 'Copy', Collections = 195, Total time spent = 43.692 seconds

Garbage collector: 

Name = 'MarkSweepCompact', Collections = 97, Total time spent = 58.432 seconds

time elapsed: 168

Garbage collector: 

Name = 'Copy', Collections = 198, Total time spent = 1 minute

Garbage collector: 

Name = 'MarkSweepCompact', Collections = 98, Total time spent = 1 minute
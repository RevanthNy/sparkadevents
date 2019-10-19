# sparkadevents
Steps to run the application in Local Spark (completely works and preferred):

Prereqs: Installed Java, spark and have the environment path setup for both

Download or clone the application from the repository

Open the downloaded spark folder and enter the directory using terminal and enter the bin folder inside it

Run the following command to run the application "spark-submit --class com.adevents.SparkApplication --master local --driver-memory 2g --executor-memory 2g path-to-jar/AdEvents-0.0.1-SNAPSHOT-with-dependencies.jar input-file-path output-file-folder"

open the output-file-folder to see the processed output events as json file

Steps to run the application on Docker:

Prereqs: Installed Docker in the local environment

Download or clone the application from the repository

Open the downloaded folder and enter the root folder of the application AdEvents

Open the Dockerfile and edit the line 9 to set the ENV SPARK_APPLICATION_ARGS "input-file-paht output-file-path" to the local input and output files folder specific to docker

run the command to build the application in Docker "docker build --rm=true -t application-name ."

run the command "docker run -e ENABLE_INIT_DAEMON=false --link spark-master:spark-master -d application-name"

see the result files in docker

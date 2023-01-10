# bhvx-test-task-emails

# Build
```
mvn clean install
```

# Run
```
mvn exec:java -Dexec.args="--input=some_input.zip --format=zip --output=out/" -Dexec.jvmArgs="-Xmx256m"
```
* Working directory is root of the project
* Output directory is created if not exists
* If not specified, output directory will be "output/"
* If output files already exist, application will terminate with exception
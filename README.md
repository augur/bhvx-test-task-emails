# bhvx-test-task-emails

# Build
```
mvn clean install
```

# Run
```
mvn exec:java -Dexec.args="--input=some_input.zip --format=some_fmt.yaml --output=out/" -Dexec.jvmArgs="-Xmx256m"
```
* Working directory is root of the project
* Output directory is created if not exists
* If not specified, output directory will be "output/"
* If output files already exist, application will terminate with exception

# YAML format file

Examples of format structure file:

* Return contents of zip file:
```yaml
zip
```

* Return attachments of eml files that stored in zip file:
```yaml
zip:
  - eml
```

* Return contents of zip files that are attached to eml files that stored in zip file:
```yaml
zip:
  - eml:
      - zip
```

* Return contents of zip files and attachments of eml files that are attached to eml files that stored in zip file:
```yaml
zip:
  - eml:
    - zip
    - eml
```
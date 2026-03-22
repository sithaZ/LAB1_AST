# Run JUnit Tests in Lab01

## Step 1: Go to the Lab01 folder

```bash
cd D:\ITC_SoftwareEngineering_Year3_S2\Automated Software Testing\Lab\Lab01
```

## Step 2: Compile the tests again

Once you are inside the Lab01 folder, run the compilation command below:

```bash
javac -d bin -cp "lib/junit-platform-console-standalone-1.14.3.jar;src" src/Lab01/User.java src/Lab01/UserTest.java
```

## Step 3: Run the tests

If the previous command succeeds without any error message, run the tests using this command:

```bash
java -jar lib/junit-platform-console-standalone-1.14.3.jar --class-path bin --scan-class-path
```
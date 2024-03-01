# Programming assignment 3

The application is developed with java 17 and maven, using socket programming to establish connection between clients and server. 

### download the datasets, unzip and give read write and execute access to the datasets
```bash
chmod 777 /path/to/dataset 
```

### Installation and setup

Install jdk and maven, and add them to the path folder.

```bash
sudo apt install openjdk-17-jdk maven
```

```bash
git clone https://github.com/neurobazaar/csc435-winter2024-pa3
```


### compilation

```bash
cd /path/to/csc435-winter2024-pa3/app-java/
mvn clean compile
mvn package
```
# running the server
give the server port number to start the server as an argument. The server will run only with localhost/127.0.0.1

```bash
mvn exec:java -Dexec.mainClass="csc435.app.FileRetrievalServer" -Dexec.args="$port_number"
```

## list
``` bash
>list
```
## quit
```
>quit
```

# running the client


```bash
mvn exec:java -Dexec.mainClass="csc435.app.FileRetrievalClient"
```
## connecting 
to connect with the server use the below command, with hostname(127.0.0.1) and arg2 as the server port number. 

```bash
>connect 127.0.0.1 $arg2
```
## indexing
```bash
>index /path/to/dataset_folder
```
## searching
```bash
>search $arg1
>search $arg1 AND $arg2
```

## quitting
```bash
>quit
```


## credits
created by apalanis@depaul.edu(Arun Kumar palanisamy) for Programming assignment3. Instructor name: Alexandru Orhean. 


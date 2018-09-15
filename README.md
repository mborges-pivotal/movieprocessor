# Movie Processor
Spring Cloud Data Flow processor for DVD data files

```
app register --name movie --type processor --uri file:///Users/marceloborges/pivotal/personal/movieprocessor/target/movieprocessor-0.0.1-SNAPSHOT.jar

stream create --name movie2 --definition "file --mode=lines --filename-pattern='*.txt' --directory=/Users/marceloborges/pivotal/workshops/movie/input | movie | httpclient --url-expression='http://localhost:8080/add?name='+#jsonPath(payload,'DVD_Title')+'&upc='+#jsonPath(payload,'UPC') | log"
```
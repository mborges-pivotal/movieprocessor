# Movie Processor
Spring Cloud Data Flow processor for DVD data files

```
app register --name movie --type processor --uri file:///Users/marceloborges/pivotal/personal/movieprocessor/target/movieprocessor-0.0.1-SNAPSHOT.jar

stream create --name movie --definition "file --mode=lines --filename-pattern='*.txt' --directory=/Users/marceloborges/pivotal/workshops/movie/input | movie | httpclient --url-expression='http://movies.apps.pcf.homelab.borgescloud.com/add?name='+#jsonPath(payload,'DVD_Title')+'&upc='+#jsonPath(payload,'UPC') | log"

ftp --filename-pattern=*.txt --remote-dir=/home/movies --password=Welcome1 --host=ftp.homelab.borgescloud.com --username=ftp-guest --mode=lines --auto-create-local-dir=true --delete-remote-files=true --markers-json=false | movie | httpclient --url-expression='http://movies.apps.pcf.homelab.borgescloud.com/add?name='+#jsonPath(payload,'DVD_Title')+'&upc='+#jsonPath(payload,'UPC') | log

ftp --mode=lines --password=Welcome1 --filename-pattern='*.txt' --host=54.244.182.38 --remote-dir=/files --markers-json=false --username=tikbum | movie | httpclient --url-expression='http://movies.apps.pcf.homelab.borgescloud.com/add?name='+#jsonPath(payload,'DVD_Title')+'&upc='+#jsonPath(payload,'UPC') | log


movie_load=ftp --delete-remote-files=true --mode=lines --password=Welcome1 --filename-pattern='*.txt' --host=54.244.182.38 --remote-dir=/files --markers-json=false --username=tikbum | movie | httpclient --url-expression='https://movies-v1.cfapps.io/add?name='+#jsonPath(payload,'DVD_Title')+'&upc='+#jsonPath(payload,'UPC') | log
:movie_load.movie > movies-count: counter

ftp --delete-remote-files=true --mode=lines --password=Welcome1 --filename-pattern='*.txt' --host=54.244.182.38 --remote-dir=/files --markers-json=false --username=tikbum | movie | httpclient --url-expression='https://movies-v1.cfapps.io/add?name='+#jsonPath(payload,'DVD_Title')+'&upc='+#jsonPath(payload,'UPC') --http-method=GET | log

ftp --mode=lines --password=Welcome1 --filename-pattern='*.txt' --delete-remote-files=true --host=54.244.182.38 --remote-dir=/files --markers-json=false --username=tikbum | filter --expression=payload.length<=0 | movie | log

```
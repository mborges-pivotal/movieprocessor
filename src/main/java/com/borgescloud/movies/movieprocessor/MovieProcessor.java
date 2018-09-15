package com.borgescloud.movies.movieprocessor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.Transformer;

@EnableBinding(Processor.class)
public class MovieProcessor {

	private static int NAME = 0;
	private static int UPC = 11;

	/**
     * "DVD_Title","Studio","Released","Status","Sound","Versions","Price","Rating","Year","Genre","Aspect","UPC","DVD_ReleaseDate","ID","Timestamp","Updated"
     * "RBG (Blu-ray)","Magnolia Pictures",,"Out","2.0","LBX, 16:9,
     * BLU-RAY",$29.98,"PG","2018","Documentary","1.85:1","8.7696401632e+1",8/28/2018
     * 0:00:00,312176,8/28/2018 0:00:00,"1"
     * 
     * stream create --name movie2 --definition "file --mode=lines --filename-pattern='*.txt' --directory=/Users/marceloborges/pivotal/workshops/movie/input | movie | httpclient --url-expression='http://localhost:8080/add?name='+#jsonPath(payload,'DVD_Title')+'&upc='+#jsonPath(payload,'UPC') | log"
     * 
     * @param line
     * @return
     * @throws UnsupportedEncodingException
     */
	@Transformer(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
	public Object transform(String line) throws UnsupportedEncodingException {

        String[] fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        
        String name = fields[NAME].substring(1,fields[NAME].length()-1);

		String result = String.format("{\"DVD_Title\": \"%s\", \"UPC\": %s}", URLEncoder.encode(name, StandardCharsets.UTF_8.toString()), fields[UPC]);
		return result;
	}
}
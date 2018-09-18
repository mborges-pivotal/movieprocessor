package com.borgescloud.movies.movieprocessor;

import static org.junit.Assert.assertEquals;

import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieprocessorApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void testMovieProcessor() {
		assertEquals("{\"DVD_Title\": \"WWE%3A+30+Years+Of+SummerSlam\", \"UPC\": \"6.5119195694e+1\"}",
				new MovieProcessor().transform(
						"\"WWE: 30 Years Of SummerSlam\",\"WWE Home Video\",,\"Out\",\"5.1\",\"LBX, 16:9\",$24.98,\"NR\",\"2017\",\"Special Interest\",\"1.78:1\",\"6.5119195694e+1\",8/28/2018 0:00:00,312200,8/28/2018 0:00:00,\"1\""));
		assertEquals("{\"DVD_Title\": \"RBG+%28Blu-ray%29\", \"UPC\": \"8.7696401632e+1\"}",
				new MovieProcessor().transform(
						"\"RBG (Blu-ray)\",\"Magnolia Pictures\",,\"Out\",\"2.0\",\"LBX, 16:9, BLU-RAY\",$29.98,\"PG\",\"2018\",\"Documentary\",\"1.85:1\",\"8.7696401632e+1\",8/28/2018 0:00:00,312176,8/28/2018 0:00:00,\"1\""));
	}
}

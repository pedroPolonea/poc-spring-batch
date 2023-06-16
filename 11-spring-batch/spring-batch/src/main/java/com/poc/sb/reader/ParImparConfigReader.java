package com.poc.sb.reader;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Slf4j
@Configuration
public class ParImparConfigReader implements ItemReader<Integer> {

    @Override
    public Integer read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        log.info("read");

        return new Random().nextInt();
    }
}

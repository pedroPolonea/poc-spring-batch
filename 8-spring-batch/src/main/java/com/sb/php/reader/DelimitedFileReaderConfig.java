package com.sb.php.reader;

import com.sb.php.domain.CovidDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.validation.BindException;

import java.time.LocalDate;

@Slf4j
@Configuration
public class DelimitedFileReaderConfig {

    @Bean
    @StepScope
    public FlatFileItemReader<CovidDTO> delimitedFile(
            @Value("#{jobParameters['fileName']}") Resource resource){

        log.info("delimitedFile, I=Begin, resource={}", resource);
        return new FlatFileItemReaderBuilder<CovidDTO>()
                .name("fixedWidthFile")
                .linesToSkip(1)
                .lineMapper(lineMapper())
                .resource(resource)
                .build();

    }

    @Bean
    public LineMapper<CovidDTO> lineMapper() {
        DefaultLineMapper<CovidDTO> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();

        tokenizer.setDelimiter(",");
        tokenizer.setNames("Date_reported",
                "Country_code",
                "Country",
                "WHO_region",
                "New_cases",
                "Cumulative_cases",
                "New_deaths",
                "Cumulative_deaths");
        tokenizer.setStrict(false);

        LineFieldSetMapper lineFieldSetMapper = new LineFieldSetMapper();

        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(new LineFieldSetMapper());

        return lineMapper;

    }

    class LineFieldSetMapper implements FieldSetMapper<CovidDTO> {

        @Override
        public CovidDTO mapFieldSet(FieldSet fieldSet) throws BindException {
            return CovidDTO.builder()
                    .country(fieldSet.readString("Country"))
                    .countryCode(fieldSet.readString("Country_code"))
                    .whoRegion(fieldSet.readString("Country"))
                    .cumulativeCases(fieldSet.readInt("Cumulative_cases"))
                    .cumulativeDeaths(fieldSet.readInt("Cumulative_deaths"))
                    .newCases(fieldSet.readInt("New_cases"))
                    .newDeaths(fieldSet.readInt("New_deaths"))
                    .dateReported(LocalDate.parse(fieldSet.readString("Date_reported")))
                    .build();
        }
    }
}

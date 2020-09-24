package com.sp.php.reader;

import com.sp.php.domain.CharactersDTO;
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

@Slf4j
@Configuration
public class DelimitedFileReaderConfig {

    @Bean
    @StepScope
    public FlatFileItemReader<CharactersDTO> delimitedFile(
            @Value("#{jobParameters['fileName']}") Resource resource){

        log.info("delimitedFile, I=Begin, resource={}", resource);
        return new FlatFileItemReaderBuilder<CharactersDTO>()
                .name("fixedWidthFile")
                .linesToSkip(1)
                .lineMapper(lineMapper())
                .resource(resource)
                .build();

    }

    @Bean
    public LineMapper<CharactersDTO> lineMapper() {
        DefaultLineMapper<CharactersDTO> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();

        tokenizer.setDelimiter(",");
        tokenizer.setNames(
                "name",
                "height",
                "mass",
                "hair_color",
                "skin_color",
                "eye_color",
                "birth_year",
                "gender",
                "homeworld",
                "species"
        );
        tokenizer.setStrict(false);

        LineFieldSetMapper lineFieldSetMapper = new LineFieldSetMapper();

        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(new LineFieldSetMapper());

        return lineMapper;

    }

    class LineFieldSetMapper implements FieldSetMapper<CharactersDTO> {

        @Override
        public CharactersDTO mapFieldSet(FieldSet fieldSet) throws BindException {
            return CharactersDTO.builder()
                    .birthYear(fieldSet.readString("birth_year"))
                    .eyeColor(fieldSet.readString("eye_color"))
                    .gender(fieldSet.readString("gender"))
                    .hairColor(fieldSet.readString("hair_color"))
                    .height(fieldSet.readInt("height"))
                    .homeworld(fieldSet.readString("homeworld"))
                    .mass(fieldSet.readInt("mass"))
                    .skinColor(fieldSet.readString("skin_color"))
                    .name(fieldSet.readString("name"))
                    .species(fieldSet.readString("species"))
                    .build();
        }
    }
}

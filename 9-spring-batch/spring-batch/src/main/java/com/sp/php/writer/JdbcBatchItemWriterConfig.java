package com.sp.php.writer;


import com.sp.php.domain.CharactersDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;

;

@Slf4j
@Configuration
public class JdbcBatchItemWriterConfig {

    @Bean
    @StepScope
    public JdbcBatchItemWriter<CharactersDTO> jdbcBatchItemWriter(@Qualifier("appDataBase") DataSource dataSource){
        log.info("JdbcBatchItemWriterConfig.jdbcBatchItemWriter, I=Begin");
        return new JdbcBatchItemWriterBuilder<CharactersDTO>()
                .dataSource(dataSource)
                .sql("insert into characters(name, skinColor, eyeColor, hairColor, birthYear, gender, species, url, homeworld, height, mass) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")
                .itemPreparedStatementSetter(setValues())
                .build();
    }

    private ItemPreparedStatementSetter<CharactersDTO> setValues() {
        return new ItemPreparedStatementSetter<CharactersDTO>() {
            @Override
            public void setValues(CharactersDTO charactersDTO, PreparedStatement ps) throws SQLException {
                ps.setString(1, charactersDTO.getName());
                ps.setString(2, charactersDTO.getSkinColor());
                ps.setString(3, charactersDTO.getEyeColor());
                ps.setString(4, charactersDTO.getHairColor());
                ps.setString(5, charactersDTO.getBirthYear());
                ps.setString(6, charactersDTO.getGender());
                ps.setString(7, charactersDTO.getSpecies());
                ps.setString(8, charactersDTO.getUrl());
                ps.setString(9, charactersDTO.getHomeworld());
                ps.setInt(10, charactersDTO.getHeight());
                ps.setInt(11, charactersDTO.getMass());
            }
        };
    }
}

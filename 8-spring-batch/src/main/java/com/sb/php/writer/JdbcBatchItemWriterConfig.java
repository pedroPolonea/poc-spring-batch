package com.sb.php.writer;

import com.sb.php.domain.CovidDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Slf4j
@Configuration
public class JdbcBatchItemWriterConfig {

    @Bean
    @StepScope
    public JdbcBatchItemWriter<CovidDTO> jdbcBatchItemWriter(@Qualifier("appDataBase") DataSource dataSource){
        log.info("JdbcBatchItemWriterConfig.jdbcBatchItemWriter, I=Begin");
        return new JdbcBatchItemWriterBuilder<CovidDTO>()
                .dataSource(dataSource)
                .sql("insert into covid(Date_reported, Country_code, Country, WHO_region, New_cases, Cumulative_cases, New_deaths, Cumulative_deaths) values (?, ?, ?, ?, ?, ?, ?, ?)")
                .itemPreparedStatementSetter(setValues())
                .build();
    }

    private ItemPreparedStatementSetter<CovidDTO> setValues() {
        return new ItemPreparedStatementSetter<CovidDTO>() {
            @Override
            public void setValues(CovidDTO covidDTO, PreparedStatement ps) throws SQLException {
                ps.setDate(1, Date.valueOf(covidDTO.getDateReported()));
                ps.setString(2, covidDTO.getCountryCode());
                ps.setString(3, covidDTO.getCountry());
                ps.setString(4, covidDTO.getWhoRegion());
                ps.setInt(5, covidDTO.getNewCases());
                ps.setInt(6, covidDTO.getCumulativeCases());
                ps.setInt(7, covidDTO.getNewDeaths());
                ps.setInt(8, covidDTO.getCumulativeDeaths());
            }
        };
    }
}

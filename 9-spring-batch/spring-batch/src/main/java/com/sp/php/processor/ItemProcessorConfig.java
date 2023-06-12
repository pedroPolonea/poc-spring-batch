package com.sp.php.processor;

import com.sp.php.config.SwapiClient;
import com.sp.php.domain.CharacterDTO;
import com.sp.php.domain.CharactersDTO;
import com.sp.php.domain.PeopleDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Slf4j
@Configuration
public class ItemProcessorConfig {
    @Bean
    @StepScope
    public ItemProcessor<CharactersDTO, CharactersDTO> itemProcessorStep(SwapiClient swapiClient){
        log.info("ItemProcessor.itemProcessorStep, I=Begin");
        return item -> {
            PeopleDTO people = swapiClient.getPeople(item.getName());

            log.info("ItemProcessor.itemProcessorStep, item={}, people={}", item, people);
            if(Objects.nonNull(people)) {
                item.setUrl(
                        people.getResults()
                                .stream()
                                .map(CharacterDTO::getUrl)
                                .findFirst()
                                .orElseGet(() -> null)
                );
                return item;
            }

            return item;
        };
    }

}

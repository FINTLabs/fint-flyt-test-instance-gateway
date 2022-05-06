package no.fintlabs;

import no.fintlabs.model.instance.Document;
import no.fintlabs.model.instance.Instance;
import no.fintlabs.model.instance.InstanceField;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class RandomGenerationService {

    public Instance generateRandomInstance(Random random, int numOfDocuments, int numOfFields) {
        return Instance
                .builder()
                .formId(generateRandomString(random, 10, true, true))
                .documents(generateRandomDocuments(random, numOfDocuments))
                .fields(generateRandomFields(random, numOfFields))
                .uri(generateRandomUri(random))
                .build();
    }

    private List<Document> generateRandomDocuments(Random random, int numOfDocuments) {
        return IntStream.range(0, numOfDocuments)
                .mapToObj(i -> Document
                        .builder()
                        .format(generateRandomString(random, 10, true, false))
                        .uri(generateRandomUri(random))
                        .build())
                .collect(Collectors.toList());
    }

    private Map<String, InstanceField> generateRandomFields(Random random, int numOfFields) {
        return IntStream.range(0, numOfFields)
                .mapToObj(i -> InstanceField
                        .builder()
                        .name(generateRandomString(random, 10, true, false))
                        .value(generateRandomString(random, 10, true, false))
                        .build())
                .collect(Collectors.toMap(InstanceField::getName, Function.identity()));
    }

    public String generateRandomString(Random random, int length, boolean letters, boolean numbers) {
        return RandomStringUtils.random(
                length,
                0,
                0,
                letters,
                numbers,
                null,
                random
        );
    }

    public String generateRandomUri(Random random) {
        return generateRandomString(random, 10, true, false);
    }

}

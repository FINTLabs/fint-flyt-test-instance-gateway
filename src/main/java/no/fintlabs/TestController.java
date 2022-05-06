package no.fintlabs;

import no.fintlabs.flyt.kafka.headers.InstanceFlowHeaders;
import no.fintlabs.kafka.event.error.ErrorCollection;
import no.fintlabs.model.instance.Instance;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/api/test/instance-gateway")
public class TestController {

    private final IncomingInstanceEventProducerService incomingInstanceEventProducerService;
    private final ErrorEventProducerService errorEventProducerService;
    private final RandomGenerationService randomGenerationService;

    public TestController(
            IncomingInstanceEventProducerService incomingInstanceEventProducerService,
            ErrorEventProducerService errorEventProducerService,
            RandomGenerationService randomGenerationService
    ) {
        this.incomingInstanceEventProducerService = incomingInstanceEventProducerService;
        this.errorEventProducerService = errorEventProducerService;
        this.randomGenerationService = randomGenerationService;
    }

    @PostMapping("send-incoming-instance-event")
    public ResponseEntity<?> sendInstance(
            @RequestParam() Optional<Long> seed,
            @RequestParam() Optional<String> orgId,
            @RequestParam() Optional<String> service,
            @RequestParam() Optional<String> sourceApplication,
            @RequestParam() Optional<String> sourceApplicationInstanceId,
            @RequestParam() Optional<String> sourceApplicationIntegrationId,
            @RequestParam() Optional<String> correlationId,
            @RequestBody() Optional<Instance> instance
    ) {
        Random random = seed.map(Random::new).orElse(new Random());

        incomingInstanceEventProducerService.sendIncomingInstance(
                InstanceFlowHeaders
                        .builder()
                        .orgId(orgId.orElse(randomHeaderString(random)))
                        .service(service.orElse(randomHeaderString(random)))
                        .sourceApplication(sourceApplication.orElse(randomHeaderString(random)))
                        .sourceApplicationInstanceId(sourceApplicationInstanceId.orElse(randomHeaderString(random)))
                        .sourceApplicationIntegrationId(sourceApplicationIntegrationId.orElse(randomHeaderString(random)))
                        .correlationId(correlationId.orElse(randomHeaderString(random)))
                        .build(),
                instance.orElse(
                        randomGenerationService.generateRandomInstance(
                                random,
                                random.nextInt(0, 11),
                                random.nextInt(0, 11)
                        )
                )
        );
        return ResponseEntity.ok().build();
    }

    @PostMapping("send-error-event")
    public ResponseEntity<?> sendErrorEvent(
            @RequestParam() Optional<Long> seed,
            @RequestParam() Optional<String> orgId,
            @RequestParam() Optional<String> service,
            @RequestParam() Optional<String> sourceApplication,
            @RequestParam() Optional<String> sourceApplicationInstanceId,
            @RequestParam() Optional<String> sourceApplicationIntegrationId,
            @RequestParam() Optional<String> correlationId,
            @RequestBody() ErrorCollection errorCollection
    ) {
        Random random = seed.map(Random::new).orElse(new Random());

        errorEventProducerService.sendErrorEvent(
                InstanceFlowHeaders
                        .builder()
                        .orgId(orgId.orElse(randomHeaderString(random)))
                        .service(service.orElse(randomHeaderString(random)))
                        .sourceApplication(sourceApplication.orElse(randomHeaderString(random)))
                        .sourceApplicationInstanceId(sourceApplicationInstanceId.orElse(randomHeaderString(random)))
                        .sourceApplicationIntegrationId(sourceApplicationIntegrationId.orElse(randomHeaderString(random)))
                        .correlationId(correlationId.orElse(randomHeaderString(random)))
                        .build(),
                errorCollection
        );
        return ResponseEntity.ok().build();
    }

    private String randomHeaderString(Random random) {
        return randomGenerationService.generateRandomString(random, 10, true, false);
    }

}

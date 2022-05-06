package no.fintlabs;

import no.fintlabs.flyt.kafka.event.error.InstanceFlowErrorEventProducer;
import no.fintlabs.flyt.kafka.event.error.InstanceFlowErrorEventProducerRecord;
import no.fintlabs.flyt.kafka.headers.InstanceFlowHeaders;
import no.fintlabs.kafka.event.error.ErrorCollection;
import no.fintlabs.kafka.event.error.topic.ErrorEventTopicNameParameters;
import no.fintlabs.kafka.event.error.topic.ErrorEventTopicService;
import org.springframework.stereotype.Service;

@Service
public class ErrorEventProducerService {

    private final InstanceFlowErrorEventProducer instanceFlowErrorEventProducer;
    private final ErrorEventTopicNameParameters errorEventTopicNameParameters;

    public ErrorEventProducerService(
            InstanceFlowErrorEventProducer instanceFlowErrorEventProducer,
            ErrorEventTopicService errorEventTopicService
    ) {
        this.instanceFlowErrorEventProducer = instanceFlowErrorEventProducer;
        this.errorEventTopicNameParameters = ErrorEventTopicNameParameters
                .builder()
                .errorEventName("instance-processing")
                .build();
        errorEventTopicService.ensureTopic(errorEventTopicNameParameters, 0);
    }

    public void sendErrorEvent(InstanceFlowHeaders instanceFlowHeaders, ErrorCollection errorCollection) {
        instanceFlowErrorEventProducer.send(
                InstanceFlowErrorEventProducerRecord
                        .builder()
                        .topicNameParameters(errorEventTopicNameParameters)
                        .instanceFlowHeaders(instanceFlowHeaders)
                        .errorCollection(errorCollection)
                        .build()
        );
    }

}

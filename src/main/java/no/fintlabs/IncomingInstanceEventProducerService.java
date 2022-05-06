package no.fintlabs;

import lombok.extern.slf4j.Slf4j;
import no.fintlabs.flyt.kafka.event.InstanceFlowEventProducer;
import no.fintlabs.flyt.kafka.event.InstanceFlowEventProducerFactory;
import no.fintlabs.flyt.kafka.event.InstanceFlowEventProducerRecord;
import no.fintlabs.flyt.kafka.headers.InstanceFlowHeaders;
import no.fintlabs.kafka.event.topic.EventTopicNameParameters;
import no.fintlabs.kafka.event.topic.EventTopicService;
import no.fintlabs.model.instance.Instance;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class IncomingInstanceEventProducerService {

    private final InstanceFlowEventProducer<Instance> incomingInstanceEventProducer;
    private final EventTopicNameParameters newInstanceEventTopicNameParameters;

    public IncomingInstanceEventProducerService(
            InstanceFlowEventProducerFactory instanceFlowEventProducerFactory,
            EventTopicService eventTopicService) {
        this.incomingInstanceEventProducer = instanceFlowEventProducerFactory.createProducer(Instance.class);
        this.newInstanceEventTopicNameParameters = EventTopicNameParameters.builder()
                .eventName("incoming-instance")
                .build();
        eventTopicService.ensureTopic(newInstanceEventTopicNameParameters, 0);
    }

    public void sendIncomingInstance(InstanceFlowHeaders instanceFlowHeaders, Instance instance) {
        incomingInstanceEventProducer.send(
                InstanceFlowEventProducerRecord.<Instance>builder()
                        .topicNameParameters(newInstanceEventTopicNameParameters)
                        .instanceFlowHeaders(instanceFlowHeaders)
                        .value(instance)
                        .build()
        );
    }

}

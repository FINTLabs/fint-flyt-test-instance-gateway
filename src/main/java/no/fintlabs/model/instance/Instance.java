package no.fintlabs.model.instance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Instance {

    private String formId;

    private List<Document> documents;

    private Map<String, InstanceField> fields;

    private String uri;

}

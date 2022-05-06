package no.fintlabs.model.instance;

import lombok.Builder;
import lombok.Data;

@Builder
public @Data
class Document {

    private String format;
    private String uri;

}

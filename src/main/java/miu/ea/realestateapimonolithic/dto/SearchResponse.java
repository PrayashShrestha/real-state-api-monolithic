package miu.ea.realestateapimonolithic.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SearchResponse {
    private boolean success;
    private Object data;
    private int totalPages;
    private long totalElements;
}

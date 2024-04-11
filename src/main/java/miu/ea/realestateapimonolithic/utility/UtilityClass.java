package miu.ea.realestateapimonolithic.utility;

import miu.ea.realestateapimonolithic.dto.PropertyDto;
import miu.ea.realestateapimonolithic.dto.SearchResponse;

import java.util.List;

public final class UtilityClass {

    public static <T> SearchResponse toSearchResponse(List<T> list){
        int totalPage = list.size()/5;
        return SearchResponse.builder()
                .success(true)
                .data(list)
                .totalPages(totalPage)
                .totalElements(list.size())
                .build();
    }
}

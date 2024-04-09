package miu.ea.realestateapimonolithic.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyPhotoDto {
    private Long id;

    private String name;
    private String imageUrl;
    private String imageId;
}

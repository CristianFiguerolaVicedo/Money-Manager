package com.cristian.moneymanager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {

    private Long id;
    private Long profileId;
    private String name;
    private String type;
    private String icon;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

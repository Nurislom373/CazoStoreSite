package com.st2emx.online_store.dto.feedback;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class FeedbackDto {
    private Long id;
    private String message;
}

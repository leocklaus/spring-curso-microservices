package lck.microservices.creditevaluator.api.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientEvaluationDTO {
    List<ApprovedCard> approvedCards;
}

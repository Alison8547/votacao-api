package com.br.voting.dto.response;

import com.br.voting.domain.enums.MessageVote;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoteResponse {

    private LocalDateTime dateVote;
    private MessageVote messageVote;
}

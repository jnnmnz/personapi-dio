package one.digitalinnovation.peopleapi.dto.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageResponseDTO {
    public String message;
}

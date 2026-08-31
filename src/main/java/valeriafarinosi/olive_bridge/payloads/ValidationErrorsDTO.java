package valeriafarinosi.olive_bridge.payloads;

import java.time.LocalDateTime;
import java.util.List;

public record ValidationErrorsDTO(String message, List<String> errors, LocalDateTime timestamp) {
}

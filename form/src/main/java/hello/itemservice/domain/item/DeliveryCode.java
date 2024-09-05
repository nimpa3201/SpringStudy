package hello.itemservice.domain.item;

import lombok.AllArgsConstructor;
import lombok.Data;


/***
 * Fast : 빠른 배송
 * Normal : 일반배송
 * Slow : 느린 배송
 */
@Data
@AllArgsConstructor
public class DeliveryCode {
    private String code;
    private String displayName;
}

package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class PaymentModelEnum {


    @Getter
    @AllArgsConstructor
    public enum PaymentTypeEnum{

        WX_PAYMENT("WXPAY","微信"),
        ALI_PAYMENT("ALIPAY","支付宝");

        private String code;
        private String name;

        public static String getValueByCode(String code){
            for(PaymentTypeEnum paymentTypeEnum:PaymentTypeEnum.values()){
                if(code.equals(paymentTypeEnum.getCode())){
                    return paymentTypeEnum.getName();
                }
            }
            return  null;
        }

    }

    @Getter
    @AllArgsConstructor
    public enum TradeStatusEnum{

         PAYING("PAYING","支付中"),
         SUCCESS("SUCCESS","支付成功"),
         PAY_ERROR("PAYERROR","支付失败"),
         REFUND("REFUND","已退款"),
         PARTIAL_REFUND("PARTIAL_REFUND","部分已退款"),
         PENDING("PENDING","退款中"),
         REFUND_ERROR("REFUND_ERROR","退款失败");

         private String code;
         private String name;

        public static String getValueByCode(String code){
            for(TradeStatusEnum tradeStatusEnum:TradeStatusEnum.values()){
                if(code.equals(tradeStatusEnum.getCode())){
                    return tradeStatusEnum.getName();
                }
            }
            return  "未知状态";
        }

    }
}

package com.breeze.idus.user.service.vo;

import com.breeze.core.code.Gender;
import com.breeze.core.util.FormatValidator;
import com.breeze.idus.order.service.vo.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class User {
    private Long id;

    private String name;

    private String nickname;

    private String password;

    private String phoneNumber;

    private String email;

    private Gender gender;

    private LastAction lastAction;

    @Setter
    private Order lastOrder;

    public boolean valid() {
        return validName() &&
                validNickname() &&
                validPassword() &&
                validPhoneNumber() &&
                validEmail();
    }

    public boolean validName() {
        return FormatValidator.validFormat(this.name, "^[ㄱ-ㅎ가-힣a-zA-Z]{1,20}$");
    }

    public boolean validNickname() {
        return FormatValidator.validFormat(this.nickname, "^[a-z]{1,30}$");
    }

    public boolean validPassword() {
        return FormatValidator.validFormat(this.password, "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{10,}$");
    }

    public boolean validPhoneNumber() {
        return FormatValidator.validPhoneNumber(this.phoneNumber);
    }

    public boolean validEmail() {
        return FormatValidator.validEmail(this.email);
    }
}

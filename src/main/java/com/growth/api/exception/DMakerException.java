package com.growth.api.exception;

import lombok.Getter;

@Getter
public class DMakerException extends RuntimeException {
    private DMakerErrorCode dMakerErrorCode;
    private String detailMessage;

    public DMakerException(DMakerErrorCode dMakerErrorCode) {
        super(dMakerErrorCode.getDescription());
        this.dMakerErrorCode = dMakerErrorCode;
        this.detailMessage = dMakerErrorCode.getDescription();
    }

    public DMakerException(DMakerErrorCode dMakerErrorCode,
                           String detailMessage) {
        super(dMakerErrorCode.getDescription());
        this.dMakerErrorCode = dMakerErrorCode;
        this.detailMessage = detailMessage;
    }
}

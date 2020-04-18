package com.seawaterbt.ssm.module.global;


import com.seawaterbt.ssm.core.common.http.ResultModel;

public class MqException extends RuntimeException {
    private ResultModel resultModel;

    public MqException(ResultModel resultModel) {
        super();
        this.resultModel = resultModel;
    }

    public MqException(Throwable throwable) {
        super(throwable);
    }

    public ResultModel getResultModel() {
        return resultModel;
    }

    public void setResultModel(ResultModel resultModel) {
        this.resultModel = resultModel;
    }
}

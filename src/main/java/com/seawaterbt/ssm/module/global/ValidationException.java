package com.seawaterbt.ssm.module.global;

import com.seawaterbt.ssm.core.common.http.ResultModel;

public class ValidationException extends RuntimeException {

    private ResultModel resultModel;

    public ValidationException(ResultModel resultModel) {
        super();
        this.resultModel = resultModel;
    }

    public ResultModel getResultModel() {
        return resultModel;
    }

    public void setResultModel(ResultModel resultModel) {
        this.resultModel = resultModel;
    }
}

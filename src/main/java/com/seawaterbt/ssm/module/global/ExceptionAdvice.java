package com.seawaterbt.ssm.module.global;

import com.seawaterbt.ssm.core.common.http.ResultModel;
import com.seawaterbt.ssm.module.system.service.ISysExpLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.session.UnknownSessionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {

    @Resource
    private ISysExpLogService sysExpLogService;

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResultModel handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("缺少请求参数", e);
        return new ResultModel(StateCode.MISS_PARAM);
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResultModel handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("参数解析失败", e);
        return new ResultModel(StateCode.NOTREADABLE_PARAM);
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultModel handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("参数验证失败", e);
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String field = error.getField();
        String code = error.getDefaultMessage();
        String message = String.format("%s:%s", field, code);
        return new ResultModel(StateCode.NotValid_param.getCode(), "参数验证失败=" + message);
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ResultModel handleBindException(BindException e) {
        log.error("参数绑定失败", e);
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String field = error.getField();
        String code = error.getDefaultMessage();
        String message = String.format("%s:%s", field, code);
        return new ResultModel(StateCode.BindError_param.getCode(), "参数绑定失败=" + message);
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResultModel handleServiceException(ConstraintViolationException e) {
        log.error("参数验证失败", e);
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        ConstraintViolation<?> violation = violations.iterator().next();
        String message = violation.getMessage();
        return new ResultModel(StateCode.ViolationERROR_Param.getCode(), "参数验证失败" + message);
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(javax.validation.ValidationException.class)
    public ResultModel handleValidationException(javax.validation.ValidationException e) {
        log.error("参数验证失败", e);
        return new ResultModel(StateCode.ViolationERROR_Param);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(LockedAccountException.class)
    public ResultModel lockedAccountException(LockedAccountException e) {
        log.error("账户被锁定", e);
        return new ResultModel(StateCode.ACCOUNT_LOCK);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(AuthenticationException.class)
    public ResultModel credentialsException(AuthenticationException e) {
        log.error("用户名或密码错误", e);
        return new ResultModel(StateCode.ACCOUNT_PASS_ERROR);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(AuthorizationException.class)
    public ResultModel AuthorizationException(AuthorizationException e) {
        log.error("没有权限访问");
        return new ResultModel(StateCode.ACCESS_DENIED);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(UnauthorizedException.class)
    public ResultModel UnauthorizedException(UnauthorizedException e) {
        log.error("没有权限访问");
        return new ResultModel(StateCode.ACCESS_DENIED);
    }

    @ExceptionHandler(value = UnknownSessionException.class)
    @ResponseBody
    public ResultModel UnknownSessionException(HttpServletRequest httpServletRequest, Exception e) {
        log.error("用户没有登陆");
        return new ResultModel(StateCode.NO_LOGIN);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultModel defaultExceptionHandle(HttpServletRequest httpServletRequest, Exception e) {
        if (e instanceof ValidationException) {
            ValidationException validationException = (ValidationException) e;
            ResultModel resultModel = validationException.getResultModel();
            return resultModel;
        }
        if (e instanceof NoHandlerFoundException) {
            return new ResultModel(StateCode.NOT_FOUND);
        }
        if (e instanceof HttpMessageNotReadableException) {
            return new ResultModel(StateCode.NOTREADABLE_PARAM);
        }

        this.addExpRecordLog(e);
        log.info("服务端异常捕获：", e);
        return new ResultModel(StateCode.ERROR);
    }

    private void addExpRecordLog(Exception e) {
        try {
            sysExpLogService.doAdd(e);
        } catch (Exception e1) {
            log.error("日志数据保存失败：", e);
        }
    }

}

package cn.dblearn.blog.common.exception;

import cn.dblearn.blog.common.exception.enums.ExceptionEnum;
import lombok.Data;

/**
 * MyException
 *
 * @author bobbi
 * @date 2018/10/07 13:54
 * @email 571002217@qq.com
 * @description 自定义异常
 */
@Data
public class MyException extends RuntimeException{
    private String msg;
    private int code = 500;

    public MyException(){
        super(ExceptionEnum.UNKNOWN.getMsg());
        this.msg=ExceptionEnum.UNKNOWN.getMsg();
    }


    public MyException(ExceptionEnum eEnum,Throwable e){
        super(eEnum.getMsg(),e);
        this.msg=eEnum.getMsg();
        this.code=eEnum.getCode();
    }

    public MyException(ExceptionEnum eEnum){
        this.msg=eEnum.getMsg();
        this.code=eEnum.getCode();
    }

    public MyException(String exception){
       this.msg=exception;
    }

}

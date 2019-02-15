package cn.dblearn.blog.common.exception;

import cn.dblearn.blog.common.exception.enums.ErrorEnum;
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
        super(ErrorEnum.UNKNOWN.getMsg());
        this.msg=ErrorEnum.UNKNOWN.getMsg();
    }


    public MyException(ErrorEnum eEnum,Throwable e){
        super(eEnum.getMsg(),e);
        this.msg=eEnum.getMsg();
        this.code=eEnum.getCode();
    }

    public MyException(ErrorEnum eEnum){
        this.msg=eEnum.getMsg();
        this.code=eEnum.getCode();
    }

    public MyException(String exception){
       this.msg=exception;
    }

}

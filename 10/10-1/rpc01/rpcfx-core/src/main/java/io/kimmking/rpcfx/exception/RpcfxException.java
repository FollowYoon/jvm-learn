package io.kimmking.rpcfx.exception;

/**
 * @author qindong
 * @date 2021/11/15 14:54
 */
public class RpcfxException extends RuntimeException {

    private String message;

    public RpcfxException(){
        super();
    }

    public RpcfxException(String message){
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}

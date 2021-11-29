package io.kimmking.rpcfx.api;

import lombok.Data;

@Data
public class RpcfxRequest {
  private String serviceClass;
  private String method;
  private Object[] params;

  //分组
  private String group;
  //版本
  private String version;

}

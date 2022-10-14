package com.weguard.websocket;

import java.io.IOException;
import java.util.HashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import net.sf.json.JSONObject;


@ServerEndpoint(value="/wuziqisocket") 
public class WebSocket {
  //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
  private static HashMap<String,WebSocket> webSocketMap = new HashMap<String,WebSocket>();
  //与某个客户端的连接会话，需要通过它来给客户端发送数据
  private Session session;

  private static int index = 0;

  private int mykey = 0;
   
  /**
   * 连接建立成功调用的方法
   * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
 * @throws IOException 
   */
  @OnOpen
  public void onOpen( Session session){
      this.session = session;
      index++;
      try {
          Result result = new Result();
          if(index%2==0){
              WebSocket socket1 = webSocketMap.get((index-1)+"");
              if(socket1!=null){
                  result.setBout(true);
                  result.setMessage("系统：游戏开始，请您先掷骰！");
                  result.setColor("black");
                  JSONObject json1 = JSONObject.fromObject(result);
                  socket1.sendMessage(json1.toString());
                  //对先掷骰的对象发送数据结束
                  result.setMessage("系统：游戏开始，请等待对手掷骰！");
                  result.setBout(false);
                  result.setColor("white");
                  this.sendMessage(JSONObject.fromObject(result).toString());
                  //对后出手的发送消息结束
              }else{
                  index--;
                  result.setMessage("系统：等待玩家匹配！");
                  this.sendMessage(JSONObject.fromObject(result).toString());
              }
          }else{
              result.setMessage("系统：等待玩家匹配！");
              this.sendMessage(JSONObject.fromObject(result).toString());
          }
          this.mykey = index;
          webSocketMap.put(mykey+"", this);     //加入map中
          System.out.println(webSocketMap.size());
    } catch (Exception e) {
        e.printStackTrace();
    }
  }
   
  /**
   * 连接关闭调用的方法
 * @throws IOException 
   */
  @OnClose
  public void onClose(){
      webSocketMap.remove(mykey+"");  //从set中删除
      try {
          WebSocket socket = null;
          if(mykey%2==0){
              socket = webSocketMap.get((mykey-1)+"");
          }else{
              socket = webSocketMap.get((mykey+1)+"");
          }
          if(socket!=null){
              Result result = new Result();
              result.setMessage("你的对手已离开！");
              socket.sendMessage(JSONObject.fromObject(result).toString());
          }
    } catch (Exception e) {
        e.printStackTrace();
    }
  }
  /**
   * 收到客户端消息后调用的方法
   * @param message 客户端发送过来的消息
   * @param session 可选的参数
   */
  @OnMessage
  public void onMessage(String message) {
      System.out.println(message);
      JSONObject json = JSONObject.fromObject(message);
      Result result = (Result) JSONObject.toBean(json,Result.class);
      try {
          WebSocket socket = null;
          if(mykey%2==0){
              socket = webSocketMap.get((mykey-1)+"");
          }else{
              socket = webSocketMap.get((mykey+1)+"");
          }
          if(socket!=null){
              if(result.getXy()!=null&&!"".equals(result.getXy())){//有坐标表示为落子，反之则为发送信息
                  this.sendMessage(message);
                  result.setBout(true);//对手的bout改为true，表示接下来可以掷骰
                  result.setMessage("系统：对方已掷骰，正在等待您掷骰！");
                  socket.sendMessage(JSONObject.fromObject(result).toString());
              }else{
                  Result newResult = new Result();
                  newResult.setMessage("自己："+result.getMessage());
                  this.sendMessage(JSONObject.fromObject(newResult).toString());
                  newResult.setMessage("对方："+result.getMessage());
                  socket.sendMessage(JSONObject.fromObject(newResult).toString());
              }
          }
          
          
    } catch (Exception e) {
        e.printStackTrace();
    }
  }
   
  /**
   * 发生错误时调用
   * @param session
   * @param error
   */
  @OnError
  public void onError(Session session, Throwable error){
      System.out.println("连接断开");
//      error.printStackTrace();
  }
  /**
   * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
   * @param message
   * @throws IOException
   */
  public void sendMessage(String message) throws IOException{
      this.session.getBasicRemote().sendText(message);
      //this.session.getAsyncRemote().sendText(message);
  }
}
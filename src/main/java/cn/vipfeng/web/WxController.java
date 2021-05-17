package cn.vipfeng.web;

import cn.vipfeng.utils.WechatUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

@RequestMapping("/wx")
@Controller
public class WxController {

    @RequestMapping("/check")
    public void CheckSignature(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取Get请求携带参数
        String content=request.getQueryString();
        System.out.println(content);
        if(content.startsWith("signature")){
            //检查消息是否来自微信服务器
            String echostr= WechatUtil.CheckSignature(content);

            //返回echostr给微信服务器
            OutputStream os=response.getOutputStream();
            os.write(URLEncoder.encode(echostr,"UTF-8").getBytes());
            os.flush();
            os.close();
        }
    }
}

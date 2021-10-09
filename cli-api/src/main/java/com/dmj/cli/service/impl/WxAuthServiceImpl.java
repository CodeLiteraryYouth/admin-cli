package com.dmj.cli.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.common.constant.GlobalConstants;
import com.dmj.cli.common.constant.WxConstant;
import com.dmj.cli.common.core.MagicCore;
import com.dmj.cli.common.enums.ResultStatusCode;
import com.dmj.cli.common.redis.RedisUtils;
import com.dmj.cli.domain.UserInfo;
import com.dmj.cli.domain.UserLoginLog;
import com.dmj.cli.domain.dto.wx.WxEventDTO;
import com.dmj.cli.domain.vo.wx.WxQrcodeVO;
import com.dmj.cli.service.WxAuthService;
import com.dmj.cli.service.api.UserInfoService;
import com.dmj.cli.service.api.UserLoginLogService;
import com.dmj.cli.util.str.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author zd
 */
@Service
@Slf4j
public class WxAuthServiceImpl implements WxAuthService {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserLoginLogService userLoginLogService;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public BaseResult<WxQrcodeVO> getQrcode() {
        String token=getToken();
        if (StringUtils.isBlank(token)) {
            //做三次重试
            for(int i=0; i<3 ;i++) {
                token=getToken();
            }
            if (StringUtils.isBlank(token)) {
                return BaseResult.fail(ResultStatusCode.TOKEN_ERROR);
            }
        }
        String codeUrl=WxConstant.QRCODE_URL.replace("TOKEN",token);
        String sceneId = IdUtil.getSnowflake().nextIdStr();
        Map<String,Object> param=new HashMap<>();
        param.put("expire_seconds",3600);
        param.put("action_name",WxConstant.ACTION.QR_STR_SCENE.name());
        JSONObject info = new JSONObject();
        JSONObject scen = new JSONObject();
        scen.put("scene_str", sceneId);
        info.put("scene", scen);
        param.put("action_info", info);
        try {
            String result=HttpUtil.post(codeUrl,JSONUtil.toJsonStr(param));
            log.info("获取公众号二维码信息为:{}",result);
            WxQrcodeVO wxQrcodeVO= JSONUtil.toBean(result,WxQrcodeVO.class);
            wxQrcodeVO.setSceneId(sceneId);
            return BaseResult.success(wxQrcodeVO);
        } catch (Exception e) {
            log.error("获取二维码异常",e.getCause());
            return BaseResult.fail(e.getCause());
        }
    }

    /**
     * 获取access_token
     * @return
     */
    private String getToken() {
        Map<String,Object> params=new HashMap<>();
        params.put("grant_type",WxConstant.GRANT_TYPE.client_credential.name());
        params.put("appid",WxConstant.APP_ID);
        params.put("secret",WxConstant.APP_SECRET);
        String result=HttpUtil.get(WxConstant.ACCESS_TOKEN_URL,params);
        JSONObject jsonObject=JSONUtil.toBean(result,JSONObject.class);
        String value=jsonObject.getStr("access_token");
        return value;
    }

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public BaseResult<UserInfo> getUserBySceneId(Long sceneId) {
        Assert.notNull(sceneId,"sceneId is null");
        String result=redisUtils.get(sceneId.toString()).toString();
        UserInfo userInfo=null;
        if (StringUtils.isBlank(result)) {
            userInfo=userInfoService.getOne(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getSceneId,sceneId));
            if (Objects.isNull(userInfo)) {
                return BaseResult.fail(ResultStatusCode.LOGIN_ERROR);
            } else {
                return BaseResult.success(userInfo);
            }
        } else {
            userInfo=JSONUtil.toBean(result,UserInfo.class);
            return BaseResult.success(userInfo);
        }
    }

    @Override
    public void checkToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream=response.getOutputStream();
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echoStr = request.getParameter("echostr");
        try {
            //对token时间戳排序进行加密验证
            String token=WxConstant.SERVER_TOKEN;
            String[] arr = {token, timestamp, nonce};
            Arrays.sort(arr);
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] digest = md.digest(StringUtils.join(arr).getBytes());
            String temp = byteToStr(digest);
            if (!(temp.toLowerCase()).equals(signature)) {
                echoStr = GlobalConstants.SUCCESS;
            }
        } catch (Exception e) {
            echoStr=GlobalConstants.FAIL;
            log.error("校验token异常",e.getCause());
        } finally {
            outputStream.write(echoStr.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            outputStream.close();
        }

    }

    private String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    private String byteToHexStr(byte mByte) {
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A','B', 'C', 'D', 'E', 'F' };
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4)& 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];
        String s = new String(tempArr);
        return s;
    }

    @Override
    public void getUserInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream=response.getOutputStream();
        Map<String, String> map = getResultMap(request);
        log.info("微信推送的事件信息为:{}",JSON.toJSONString(map));
        WxEventDTO wxEventDTO=BeanUtil.mapToBean(map, WxEventDTO.class,false, CopyOptions.create());
        if (Objects.isNull(wxEventDTO)) {
            outputStream.write(GlobalConstants.FAIL.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            outputStream.close();
            return;
        }
        //scene_id首次关注会有前缀，去掉就和生成二维码时带的一致了
        if(wxEventDTO.getEventKey().contains(MagicCore.UNDER_LINE)) {
            wxEventDTO.setEventKey(wxEventDTO.getEventKey().split(MagicCore.UNDER_LINE)[1]);
        }
        saveUserIno(wxEventDTO);
        outputStream.write(GlobalConstants.SUCCESS.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();

    }

    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    private void saveUserIno(WxEventDTO wxEventDTO) {
        UserInfo userInfo=null;
        //第一次关注公众号
        if (WxConstant.EVENT_TYPE.subscribe.name().equals(wxEventDTO.getEvent())) {
            Map<String,Object> params=new HashMap<>();
            params.put("access_token",getToken());
            params.put("openid",wxEventDTO.getFromUserName());
            params.put("lang",GlobalConstants.ZH_CN);
            String result=HttpUtil.get(WxConstant.USER_INFO_URL,params);
            log.info("关注公众号的用户信息为:{}",result);
            JSONObject jsonObject=JSONUtil.toBean(result,JSONObject.class);
            int subscribe=jsonObject.getInt("subscribe");
            //判断用户是否已经关注
            if (WxConstant.SUBSCRIBE==subscribe) {
                userInfo = new UserInfo();
                userInfo.setCity(jsonObject.getStr("city"));
                userInfo.setAvatarUrl(jsonObject.getStr("headimgurl"));
                userInfo.setCountry(jsonObject.getStr("country"));
                userInfo.setProvince(jsonObject.getStr("province"));
                userInfo.setGender(jsonObject.getInt("sex") == 1 ? "男" : "女");
                userInfo.setLanguage(jsonObject.getStr("language"));
                userInfo.setOpenId(jsonObject.getStr("openid"));
                userInfo.setNickName(jsonObject.getStr("nickname"));
                userInfo.setCreateTime(DateUtil.date());
                userInfo.setEvent(wxEventDTO.getEvent());
                userInfo.setLoginStatus(true);
                userInfo.setSceneId(wxEventDTO.getEventKey());
                userInfoService.save(userInfo);
            }
        }
        //非第一次登录公众号
        if (WxConstant.EVENT_TYPE.SCAN.name().equals(wxEventDTO.getEvent())) {
            //清楚上次的登录缓存信息
            userInfo=userInfoService.getOne(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getOpenId,wxEventDTO.getFromUserName()));
            redisUtils.del(userInfo.getSceneId());
            userInfo.setSceneId(wxEventDTO.getEventKey());
            userInfo.setLoginStatus(true);
            userInfo.setEvent(wxEventDTO.getEvent());
            userInfoService.updateById(userInfo);
            redisUtils.set(wxEventDTO.getEventKey(),userInfo);
        }

        //取消订阅
        if (WxConstant.EVENT_TYPE.unsubscrib.name().equals(wxEventDTO.getEvent())) {
            //清楚上次的登录缓存信息
            userInfo=userInfoService.getOne(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getOpenId,wxEventDTO.getFromUserName()));
            redisUtils.del(userInfo.getSceneId());
            userInfo.setSceneId(wxEventDTO.getEventKey());
            userInfo.setLoginStatus(false);
            userInfo.setEvent(wxEventDTO.getEvent());
            userInfoService.updateById(userInfo);
        }

        //记录登录信息
        if (WxConstant.EVENT_TYPE.subscribe.name().equals(wxEventDTO.getEvent()) || WxConstant.EVENT_TYPE.SCAN.name().equals(wxEventDTO.getEvent())) {
            UserLoginLog userLoginLog=new UserLoginLog();
            userLoginLog.setEventKey(wxEventDTO.getEventKey());
            userLoginLog.setLoginType(GlobalConstants.LOGIN_TYPE.WX.name());
            userLoginLog.setLoginTime(LocalDateTime.now());
            userLoginLog.setEvent(wxEventDTO.getEvent());
            userLoginLog.setLoginOpenIg(userInfo.getOpenId());
            userLoginLog.setMsgType(wxEventDTO.getMsgType());
            userLoginLog.setTicket(wxEventDTO.getTicket());
            userLoginLogService.save(userLoginLog);
            //存储当前登录信息
            redisUtils.set(wxEventDTO.getEventKey(),JSONUtil.toJsonStr(userInfo),7200);
        }
    }

    private Map<String,String> getResultMap(HttpServletRequest request) throws IOException, DocumentException {
        HashMap<String, String> map = new HashMap<String,String>();
        SAXReader reader = new SAXReader();

        InputStream ins = request.getInputStream();
        Document doc = reader.read(ins);

        Element root = doc.getRootElement();
        @SuppressWarnings("unchecked")
        List<Element> list = (List<Element>)root.elements();

        for(Element e:list){
            map.put(e.getName(), e.getText());
        }
        ins.close();
        return map;

    }

    public static void main(String[] args) {
        Map<String,Object> params=new HashMap<>();
        params.put("grant_type",WxConstant.GRANT_TYPE.client_credential.name());
        params.put("appid",WxConstant.APP_ID);
        params.put("secret",WxConstant.APP_SECRET);
        String result=HttpUtil.get(WxConstant.ACCESS_TOKEN_URL,params);
        System.out.println(result);
    }
}

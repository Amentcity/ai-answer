package com.answer.manager;

import com.answer.common.ErrorCode;
import com.answer.exception.BusinessException;
import com.zhipu.oapi.ClientV4;
import com.zhipu.oapi.Constants;
import com.zhipu.oapi.service.v4.model.*;
import io.reactivex.Flowable;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用AI调用能力
 */
@Component
public class AiManager {

    @Resource
    private ClientV4 clientV4;

    // 稳定的随机数
    public static final float STABLE_TEMPERATURE = 0.05f;

    // 不稳定的随机数
    public static final float UNSTABLE_TEMPERATURE = 0.99f;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 通用同步请求 （答案较稳定）
     *
     * @param systemMessage 系统消息（预设词）
     * @param userMessage   用户消息
     * @return 结果
     */
    public String doSyncUnStableTemperature(String systemMessage, String userMessage) {
        return doRequest(systemMessage, userMessage, Boolean.FALSE, UNSTABLE_TEMPERATURE);
    }

    /**
     * 通用同步请求 （答案较稳定）
     *
     * @param systemMessage 系统消息（预设词）
     * @param userMessage   用户消息
     * @return 结果
     */
    public String doSyncStableTemperature(String systemMessage, String userMessage) {
        return doRequest(systemMessage, userMessage, Boolean.FALSE, STABLE_TEMPERATURE);
    }

    /**
     * 通用同步请求
     *
     * @param systemMessage 系统消息（预设词）
     * @param userMessage   用户消息
     * @param temperature   随机数（）
     * @return 结果
     */
    public String doSyncRequest(String systemMessage, String userMessage, Float temperature) {
        return doRequest(systemMessage, userMessage, Boolean.FALSE, temperature);
    }

    /**
     * 通用请求（简化消息传递）
     *
     * @param systemMessage 系统消息（预设词）
     * @param userMessage   用户消息
     * @param stream        是否流式
     * @param temperature   随机值
     * @return 结果
     */
    public String doRequest(String systemMessage, String userMessage, Boolean stream, Float temperature) {
        List<ChatMessage> chatMessageList = new ArrayList<>();
        ChatMessage systemChatMessage = new ChatMessage(ChatMessageRole.SYSTEM.value(), systemMessage);
        chatMessageList.add(systemChatMessage);
        ChatMessage userChatMessage = new ChatMessage(ChatMessageRole.USER.value(), userMessage);
        chatMessageList.add(userChatMessage);
        return doRequest(chatMessageList, stream, temperature);
    }

    /**
     * 通用请求
     *
     * @param messages    消息
     * @param stream      是否流式
     * @param temperature 随机值
     * @return String 结果
     */
    public String doRequest(List<ChatMessage> messages, Boolean stream, Float temperature) {
        // 构建请求
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                .model(Constants.ModelChatGLM4)
                .stream(stream)
                .temperature(temperature)
                .invokeMethod(Constants.invokeMethod)
                .messages(messages)
                .build();
        try {
            ModelApiResponse invokeModelApiResp = clientV4.invokeModelApi(chatCompletionRequest);
            return invokeModelApiResp.getData().getChoices().get(0).toString();
        } catch (Exception e) {
            logger.error("exception message:", e);
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, e.getMessage());
        }
    }

    /**
     * 通用流式请求，简化消息传递
     * @param systemMessage 系统消息
     * @param userMessage 用户消息
     * @param temperature 随机值
     * @return 结果
     */
    public Flowable<ModelData> doStreamRequest(String systemMessage, String userMessage, Float temperature) {
        List<ChatMessage> chatMessageList = new ArrayList<>();
        ChatMessage systemChatMessage = new ChatMessage(ChatMessageRole.SYSTEM.value(), systemMessage);
        chatMessageList.add(systemChatMessage);
        ChatMessage userChatMessage = new ChatMessage(ChatMessageRole.USER.value(), userMessage);
        chatMessageList.add(userChatMessage);
        return doStreamRequest(chatMessageList,temperature);
    }

    /**
     * 通用流式请求
     *
     * @param messages    消息
     * @param temperature 随机值
     * @return String 结果
     */
    public Flowable<ModelData> doStreamRequest(List<ChatMessage> messages, Float temperature) {
        // 构建请求
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                .model(Constants.ModelChatGLM4)
                .stream(Boolean.TRUE)
                .temperature(temperature)
                .invokeMethod(Constants.invokeMethod)
                .messages(messages)
                .build();
        try {
            ModelApiResponse invokeModelApiResp = clientV4.invokeModelApi(chatCompletionRequest);
            return invokeModelApiResp.getFlowable();
        } catch (Exception e) {
            logger.error("exception message:", e);
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, e.getMessage());
        }
    }
}

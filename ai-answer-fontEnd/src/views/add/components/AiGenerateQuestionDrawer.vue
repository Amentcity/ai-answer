<script setup lang="ts">
import {reactive, ref} from 'vue'
import { aiGenerateQuestion } from '@/servers/api/questionController.ts'
import message from '@arco-design/web-vue/es/message'

const visible = ref(false)

const handleClick = () => {
  visible.value = true
}
const handleOk = () => {
  visible.value = false
}
const handleCancel = () => {
  visible.value = false
}

interface Props {
  appId: string
  onSuccess?: (result: API.QuestionContentDTO[]) => void
  onSSESuccess?:(result: API.QuestionContentDTO) => void
  onSSEStart?:(event:Event)=>void
  onSSEClose?:(event:Event)=>void
}

const props = withDefaults(defineProps<Props>(), {
  appId: () => {
    return ''
  },
})

const form = reactive({
  optionNumber: 2,
  questionNumber: 10,
} as API.AiGenerateQuestionRequest)

const handleSubmit = async () => {
  if (!props.appId) {
    return
  }
  submitting.value = true
  const res = await aiGenerateQuestion({
    appId: Number(props.appId),
    ...form,
  })
  if (res.data.code === 0 && res.data.data) {
    if (props.onSuccess) {
      props.onSuccess(res.data.data)
    } else {
      message.success('生成题目成功')
    }
    // 关闭抽屉
    handleCancel()
  } else {
    message.error('操作失败' + res.data.message)
  }
  submitting.value = false
}

const submitting = ref(false);

const sseSubmitting = ref(false);

/**
 * 提交（实时生成）
 */
const handleSSESubmit=()=>{
  if (!props.appId)return;
  sseSubmitting.value=true;
  // 创建 SSE 请求
  const eventSource = new EventSource(
    // todo 手动填写完整的后端地址
    `http://localhost:8001/api/question/ai_generate/sse?appId=${props.appId}&questionNumber=${form.questionNumber}&optionNumber=${form.optionNumber}`
  );

  let first =true;
  // 接收消息
  eventSource.onmessage = function (event) {
    if (first){
      props.onSSEStart?.(event);
      handleCancel();
      first = !first;
    }
    props.onSSESuccess?.(JSON.parse(event.data));
  }

  eventSource.onopen= function (event) {
    console.log("建立连接");
    props.onSSEStart?.(event);
    handleCancel();
  }
  // 报错或关闭时触发
  eventSource.onerror= function (event) {
    if (event.eventPhase===EventSource.CLOSED){
      props.onSSEClose?.(event);
      console.log("关闭连接");
      eventSource.close()
    }
  }
  sseSubmitting.value=false;
}
</script>

<template>
  <a-button type="primary" @click="handleClick">AI 生成题目</a-button>
  <a-drawer width="50%" :visible="visible" @ok="handleOk" @cancel="handleCancel" unmountOnClose>
    <template #title> AI 生成题目</template>
    <div>
      <a-form
        :model="form"
        :style="{ width: '480px' }"
        label-align="left"
        auto-label-width
        @submit="handleSubmit"
      >
        <a-form-item field="id" label="应用id">
          <label>{{ appId }}</label>
        </a-form-item>
        <a-form-item field="questionNumber" label="题目数量">
          <a-input-number
            :min="0"
            :max="20"
            v-model="form.questionNumber"
            placeholder="请输入选项数量"
          />
        </a-form-item>
        <a-form-item field="optionNumber" label="选项数量">
          <a-input-number
            v-model="form.optionNumber"
            :min="0"
            :max="6"
            placeholder="请输入选项数量"
          />
        </a-form-item>
        <a-form-item>
          <a-space>

            <a-button :loading="submitting" type="primary" html-type="submit" style="width: 120px">
              {{ submitting?"生成中":"一键生成" }}
            </a-button>
            <a-button :loading="sseSubmitting" @click="handleSSESubmit" style="width: 120px">
              {{ sseSubmitting?"生成中":"实时生成" }}
            </a-button>
          </a-space>
        </a-form-item>
      </a-form>
    </div>
  </a-drawer>
</template>

<style scoped></style>

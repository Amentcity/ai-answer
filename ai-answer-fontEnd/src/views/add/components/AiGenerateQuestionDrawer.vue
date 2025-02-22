<script setup lang="ts">
import { ref } from 'vue'
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
}

const props = withDefaults(defineProps<Props>(), {
  appId: () => {
    return ''
  },
})

const form = ref({
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
    ...form.value,
  })
  if (res.data.code === 0 && res.data.data.length > 0) {
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

const submitting = ref(false)
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
          <label>{{appId}}</label>
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
          <a-button :loading="submitting" type="primary" html-type="submit" style="width: 120px">
            一键生成
          </a-button>
        </a-form-item>
      </a-form>
    </div>
  </a-drawer>
</template>

<style scoped></style>

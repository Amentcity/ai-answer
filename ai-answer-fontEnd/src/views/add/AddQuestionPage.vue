<script setup lang="ts">
import { ref, watchEffect } from 'vue'
import {
  editQuestion,
  addQuestion,
  listQuestionVoByPage,
} from '@/servers/api/questionController.ts'
import { Message } from '@arco-design/web-vue'
import message from '@arco-design/web-vue/es/message'
import { useRouter } from 'vue-router'
import AiGenerateQuestionDrawer from '@/views/add/components/AiGenerateQuestionDrawer.vue'

const props = defineProps({
  appId: {
    type: String, // 或者 Number，根据实际需求
  },
})

// 题目内容结构（理解为题目列表）
const questionContent = ref<API.QuestionContentDTO[]>([])

const title = ref<string>('')

const router = useRouter()
//
const oldDataId = ref<number>()

/**
 * 加载数据
 */
const loadData = async () => {
  if (!props.appId) {
    return
  }
  const res = await listQuestionVoByPage({
    appId: Number(props.appId),
    current: 1,
    pageSize: 1,
    sortField: 'create_time',
    sortOrder: 'descend',
  })
  if (res.data.code === 0 && res.data.data?.records) {
    if (res.data.data.records[0]) {
      oldDataId.value = res.data.data.records[0].id ?? undefined
      questionContent.value = res.data.data?.records[0].questionContent ?? []
    }
  } else {
    message.error('获取数据失败，' + res.data.message)
  }
}

// 添加问题
const addQuestionItem = () => {
  if (title.value === null || title.value === '') {
    return Message.info('请先输入题目')
  }
  questionContent.value.unshift({
    title: title.value,
    options: [
      {
        key: undefined,
        value: undefined,
        score: undefined,
        result: undefined,
      },
    ],
  })
}

// 添加选项
const addOption = (question: API.QuestionContentDTO) => {
  question.options?.push({
    key: undefined,
    value: undefined,
    result: undefined,
    score: undefined,
  })
}

const deleteQuestion = (optionItem: number) => {
  questionContent.value.splice(optionItem, 1)
}

//
const deleteOption = (question: API.QuestionContentDTO, optionItem: number) => {
  question.options?.splice(optionItem, 1)
}

/**
 * 提交
 */
const handleSubmit = async () => {
  if (!props.appId || !questionContent.value) {
    return
  }
  let res
  // 如果是修改
  if (oldDataId.value) {
    res = await editQuestion({
      id: oldDataId.value,
      questionContent: questionContent.value,
    })
  } else {
    // 创建
    res = await addQuestion({
      appId: Number(props.appId),
      questionContent: questionContent.value,
    })
  }
  if (res.data.code === 0) {
    message.success('操作成功，即将跳转到应用详情页')
    setTimeout(() => {
      router.push(`/app/detail/${props.appId}`)
    }, 3000)
  } else {
    message.error('操作失败，' + res.data.message)
  }
}

/**
 * Ai 生成题目成功后执行
 */
const onAiGenerateSuccess = ( result: API.QuestionContentDTO[]) => {
  questionContent.value = [...questionContent.value, ...result]
}

watchEffect(() => {
  loadData()
})
</script>

<template>
  <div id="addQuestionPage">
    <h2>设置题目</h2>
    <p>应用id: {{ appId }}</p>
    <a-form-item label="请输入题目">
      <a-input v-model="title" allow-clear />
      <a-space size="medium">
        <a-button type="primary" @click="addQuestionItem">添加题目</a-button>
        <!-- AI 生成抽屉 -->
        <AiGenerateQuestionDrawer
          :appId="appId"
          :onSuccess="onAiGenerateSuccess"
        />
      </a-space>
    </a-form-item>
    <a-form :model="questionContent" layout="horizontal">
      <a-collapse :bordered="false">
        <a-collapse-item
          v-for="(question, index) in questionContent"
          :v-if="questionContent.length != 0"
          :header="question.title"
          :key="index"
          style="margin-bottom: 10px"
        >
          <template #extra>
            <a-button type="primary" status="danger" size="mini" @click.stop="deleteQuestion(index)"
              >删除题目
            </a-button>
          </template>
          <div v-for="(option, optionItem) in question.options" :key="optionItem">
            <a-row class="grid-demo" style="margin-bottom: 16px">
              <a-col flex="auto">
                <a-row :gutter="24">
                  <a-col :sm="24" :md="12" :lg="6">
                    <a-form-item label="选项key">
                      <a-input v-model="option.key" placeholder="请输入选项键" />
                    </a-form-item>
                  </a-col>
                  <a-col :sm="24" :md="12" :lg="6">
                    <a-form-item label="选项值">
                      <a-input v-model="option.value" placeholder="请输入选项值" />
                    </a-form-item>
                  </a-col>
                  <a-col :sm="24" :md="12" :lg="6">
                    <a-form-item label="选项结果">
                      <a-input v-model="option.result" placeholder="请输入选项结果" />
                    </a-form-item>
                  </a-col>
                  <a-col :sm="24" :md="12" :lg="6">
                    <a-form-item label="选项得分">
                      <a-input-number
                        :hide-button="true"
                        v-model="option.score"
                        placeholder="请输入选项得分"
                      />
                    </a-form-item>
                  </a-col>
                </a-row>
              </a-col>
              <a-col flex="40px">
                <a-button
                  style="margin-left: 10px"
                  size="small"
                  type="primary"
                  status="danger"
                  @click="deleteOption(question, optionItem)"
                  >删除
                </a-button>
              </a-col>
            </a-row>
          </div>
          <a-button type="primary" @click="addOption(question)">添加选项</a-button>
        </a-collapse-item>
      </a-collapse>
    </a-form>
    <div style="margin: 0 auto; text-align: center">
      <a-button size="large" type="primary" @click="handleSubmit">提交</a-button>
    </div>
  </div>
  {{ questionContent }}
</template>
<style scoped>
.formItem {
  padding: 0;
}
</style>

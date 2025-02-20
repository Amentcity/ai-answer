<script setup lang="ts">
import { ref } from 'vue'
import ScoringResultTable from "@/views/add/components/ScoringResultTable.vue";
import {addScoringResult, editScoringResult} from "@/servers/api/scoringResultController.ts";
import message from "@arco-design/web-vue/es/message";

const props = defineProps({
  appId: {
    type: String, // 或者 Number，根据实际需求
  },
})

// 表单参数
const form = ref({
  resultDesc: '',
  resultName: '',
  resultPicture: '',
} as API.ScoringResultAddRequest)

const updateId = ref<number>();

const tableRef = ref();

const doUpdate = (scoringResult: API.ScoringResultVO) => {
  updateId.value = scoringResult.id;
  form.value = scoringResult;
};

/**
 * 提交
 */
const handleSubmit = async () => {
  if (!props.appId) {
    return;
  }
  let res;
  // 如果是修改
  if (updateId.value) {
    res = await editScoringResult({
      id: updateId.value as number,
      ...form.value,
    });
  } else {
    // 创建
    res = await addScoringResult({
      appId: Number(props.appId),
      ...form.value,
    });
  }
  if (res.data.code === 0) {
    message.success("操作成功");
  } else {
    message.error("操作失败，" + res.data.message);
  }
  if (tableRef.value) {
    tableRef.value.loadData();
    updateId.value = undefined;
  }
};

// const show = ref(true)
</script>

<template>
  <div id="addScoringResultPage" class="addScoringResultPage">
    <h2>评分结果</h2>
    <a-form :model="form">
      <a-row :gutter="24">
        <a-col :sm="24" :md="12" :lg="8">
          <a-form-item label="应用id">
            <label>{{ props.appId }}</label>
          </a-form-item>
        </a-col>
        <a-col :sm="24" :md="12" :lg="8">
          <a-form-item label="结果名称">
            <a-input v-model="form.resultName" placeholder="请输入结果名称" />
          </a-form-item>
        </a-col>
        <a-col :sm="24" :md="12" :lg="8">
          <a-form-item label="结果描述">
            <a-input v-model="form.resultDesc" placeholder="请输入结果名称" />
          </a-form-item>
        </a-col>
        <a-col :sm="24" :md="12" :lg="8">
          <a-form-item label="结果图标">
            <a-input v-model="form.resultPicture" placeholder="请输入结果图标" />
          </a-form-item>
        </a-col>
        <a-col :sm="24" :md="12" :lg="8">
          <a-form-item label="结果集">
            <a-input-tag
              v-model="form.resultProp"
              :style="{ width: '320px' }"
              placeholder="请输出结果集，按回车确认"
              allow-clear
            />
          </a-form-item>
        </a-col>
        <a-col :sm="24" :md="12" :lg="8">
          <a-form-item label="得分范围">
            <a-input-number :hide-button="true" v-model="form.resultScoreRange" placeholder="请输入结果得分范围" />
          </a-form-item>
        </a-col>
        <a-col :sm="24" :md="12" :lg="8">
          <a-button type="primary" @click="handleSubmit">提交</a-button>
        </a-col>
      </a-row>
    </a-form>
    <h2>评分结果列表</h2>
    <ScoringResultTable :appId="String(props.appId)" :doUpdate="doUpdate" ref="tableRef" />
  </div>
</template>

<style scoped></style>

<script setup lang="ts">
import { computed, ref, watchEffect } from 'vue'
import { getAppAnswerCount, getAppAnswerResultCount } from '@/servers/api/appStatisticController.ts'
import message from '@arco-design/web-vue/es/message'
import 'echarts'
import VChart from 'vue-echarts'

const appAnswerCountList = ref<API.AppAnswerContentDTO[]>([])
const appAnswerResultCountList = ref<API.AppAnswerResultContentDTO[]>([])

/**、
 * 加载数据
 */
const loadAppAnswerCountData = async () => {
  const res = await getAppAnswerCount()
  if (res.data.code === 0) {
    appAnswerCountList.value = res.data.data || []
  } else {
    message.error('数据获取失败' + res.data.message)
  }
}

const loadAppAnswerResultCountData = async (appId: number) => {
  if (!appId) {
    return
  }
  const res = await getAppAnswerResultCount({
    appId: appId,
  })
  if (res.data.code === 0) {
    appAnswerResultCountList.value = res.data.data || []
  } else {
    message.error('数据获取失败' + res.data.message)
  }
}

/**
 * 参数改变时，触发数据重新加载
 */
watchEffect(() => {
  loadAppAnswerCountData()
})

watchEffect(() => {
  loadAppAnswerResultCountData(Number(''))
})

// 统计选项
const appAnswerCountOptions = computed(() => {
  return {
    xAxis: {
      type: 'category',
      data: appAnswerCountList.value.map((item) => item.appId),
      name: '应用id',
    },
    yAxis: {
      type: 'value',
      name: '用户答案数',
    },
    series: [
      {
        data: appAnswerCountList.value.map((item) => item.answerContent),
        type: 'bar',
      },
    ],
  }
})

/**
 * 统计选项
 */
const appAnswerResultCountOptions = computed(() => {
  return {
    tooltip: {
      trigger: 'item',
    },
    legend: {
      orient: 'vertical',
      left: 'left',
    },
    series: [
      {
        name: '应用答案结果分布',
        type: 'pie',
        radius: '50%',
        data: appAnswerResultCountList.value.map((item) => {
          return { value: item.resultContent, name: item.resultName }
        }),
        emphasis: {
          itemStyle: {
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: 'rgba(0, 0, 0, 0.5)',
          },
        },
      },
    ],
  }
})
</script>

<template>
  <div id="appStatisticPage">
    <h2>热门应用统计</h2>
    <v-chart :option="appAnswerCountOptions" style="height: 300px" />
    <h2>应用结果统计</h2>
    <div class="search-bar">
      <a-input-search :style="{width: '320px'}" placeholder="输入appId" button-text="搜索" size="large" search-button @search="(value) => loadAppAnswerResultCountData(Number(value))"/>
    </div>
    <div style="margin-bottom: 16px"/>
    <v-chart :option="appAnswerResultCountOptions" style="height: 300px" />
  </div>
</template>

<style scoped></style>

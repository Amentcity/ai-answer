<script setup lang="ts">
import { computed, ref, watchEffect } from 'vue'
import message from '@arco-design/web-vue/es/message'
import { getAppVoById } from '@/servers/api/appController.ts'
import { dayjs } from '@arco-design/web-vue/es/_utils/date'
import { userLoginStore } from '@/stores/counter.ts'
import { APP_SCORING_STRATEGY_MAP, APP_TYPE_MAP } from '@/constant/app.ts'
import ShareModal from "@/components/ShareModal.vue";

// const router = useRouter();
const data = ref<API.AppVO>({})

interface Props {
  id: number,
}

const props = withDefaults(defineProps<Props>(), {
  id: ()=>{
    return 0;
  }
})

/**
 * 获取登陆用户
 */
const useLoginStore = userLoginStore();
const loginUserId = useLoginStore.loginUser.id;

// 是否为本人创建的
const isMy = computed(()=>{
  return loginUserId && loginUserId === data?.value.userId
})

/**
 * 加载数据
 */
const loadData = async () => {
  if (!props.id) return;
  const res = await getAppVoById({
    id: props.id,
  })
  if (res.data.code === 0) {
    if (res.data.data){
      data.value = res.data.data;
    }
  } else {
    message.error('获取数据失败，' + res.data.message)
  }
}

const shareModalRef = ref();

// 分享弹窗的引用
const shareLink = `${window.location.protocol}//${window.location.host}/app/detail/${props.id}`;

// 分享
const doShare = (e:Event) => {
  if (shareModalRef.value) {
    shareModalRef.value.openModal();
  }
  // 阻止冒泡跳转到详情页
  e.stopPropagation();
}

watchEffect(() => {
  loadData()
})
</script>

<template>
  <div id="AppDetailPage">
    <a-card>
      <a-row class="grid-demo" style="margin-bottom: 16px;">
        <a-col flex="auto" class="content-wrapper">
          <h2>{{data.appName}}</h2>
          <p>{{data.appDesc}}</p>
          <p>应用类型：{{APP_TYPE_MAP[data.appType]}}</p>
          <p>评分策略：{{APP_SCORING_STRATEGY_MAP[data.scoringStrategy]}}</p>
          <p>
            <a-space>
              作者：
              <div :style="{ display: 'flex', alignItems: 'center', color: '#1D2129' }">
              <a-avatar
                :size="24"
                :image-url="data.user?.userAvatar"
                :style="{ marginRight: '8px' }"
              />
              <a-typography-text
              >{{ data.user?.userName ?? "无名" }}
              </a-typography-text>
            </div>
            </a-space></p>
          <p>创建时间：{{dayjs(data.createTime).format("YYYY-MM-DD HH:mm:ss")}}</p>
          <a-space size="large">
            <a-button type="primary" :href="`/answer/do/${id}`">开始答题</a-button>
            <a-button type="primary" @click="doShare">分享应用</a-button>
            <a-button type="primary" :href="`/add/question/${id}`">设置题目</a-button>
            <a-button type="primary" :href="`/add/scoring_result/${id}`">设置评分</a-button>
            <a-button v-if="isMy" :href="`/add/app/${id}`">修改应用</a-button>
          </a-space>
        </a-col>
        <a-col flex="320px">
          <a-image width="100%" :src="data.appIcon" />
        </a-col>
      </a-row>
    </a-card>
  </div>
  <ShareModal tile="应用分享" :link="shareLink" ref="shareModalRef"/>
</template>

<style scoped>
#AppDetailPage {

}

.content-wrapper p{
  margin-bottom: 16px;
}
</style>

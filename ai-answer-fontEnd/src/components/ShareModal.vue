<script setup lang="ts">
import QRCode from 'qrcode'
import { ref } from 'vue'

interface Props {
  // 分享链接
  link: string
  // 弹窗标题
  tile: string
}


/**
 * 给组件指定初始值
 */
const props = withDefaults(defineProps<Props>(), {
  link: ()=>{
    return "";
  },
  tile: ()=>{
    return "分享";
  }
})

const codeImg = ref()

// 二维码生成
// With promises
QRCode.toDataURL(props.link)
  .then((url) => {
    console.log(url)
    codeImg.value = url
  })
  .catch((err) => {
    console.error('生成二维码失败' + err.message)
  })

// // With async/await
// const generateQR = async (text:string) => {
//   try {
//     console.log(await QRCode.toDataURL(text))
//   } catch (err) {
//     console.error(err)
//   }
// }

const visible = ref(false)

// 打开弹窗
const openModal = () => {
  visible.value = true
}

// 关闭弹窗
const closeModal = () => {
  visible.value = false
}

defineExpose({
  openModal
})
</script>

<template>
  <a-modal v-model:visible="visible" :footer="false" @cancel="closeModal">
    <template #title>
      {{ tile }}
    </template>
    <h4 style="margin-top: 0">复制分享链接</h4>
    <a-typography-paragraph copyable>{{ link }}</a-typography-paragraph>
    <h4>手机扫码查看</h4>
    <a-image :src="codeImg" />
  </a-modal>
</template>

<style scoped></style>

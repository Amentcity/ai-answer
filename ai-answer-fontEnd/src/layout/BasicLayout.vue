<script setup lang="ts">

import GlobalHeader from '@/components/GlobalHeader.vue'
</script>

<template>
  <div id="basicLayout">
    <a-layout style="height: 100vh">
      <a-layout-header class="header">
        <GlobalHeader />
      </a-layout-header>
      <a-layout-content class="content">
        <router-view />
      </a-layout-content>
      <a-layout-footer class="footer">

      </a-layout-footer>
    </a-layout>
  </div>
</template>

<style scoped>
#basicLayout {
  background: linear-gradient(to right, #fefefe, #fff);
  margin-bottom: 20px;
  padding: 20px;
}

#basicLayout .content {
  max-width: 80%;
  width: 100%;
  box-sizing: border-box;
  margin: 0 auto 20px;
  padding: 20px;
  background: linear-gradient(#fefefe, #fff);
}

.footer {
  background: #efefef;
  padding: 16px;
  text-align: center;
}
</style>

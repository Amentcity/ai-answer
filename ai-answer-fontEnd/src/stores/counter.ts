import { ref } from 'vue'
import { defineStore } from 'pinia'
import { getLoginUser } from '@/servers/api/userController.ts'

export const userLoginStore = defineStore('loginUser', () => {
  const loginUser=ref<API.LoginUserVO>({
    userName: '用户未登录',
  })

  function setLoginUser(newLoginUser:API.LoginUserVO) {
    loginUser.value = newLoginUser
  }

  async function fetchLoginUser() {
    const res = await  getLoginUser();
    if (res.data.code === 0 && res.data.data) {
      loginUser.value = res.data.data;
    }
  }

  return { loginUser, setLoginUser, fetchLoginUser }
})

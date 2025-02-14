import {View, Image} from '@tarojs/components'
import Taro from "@tarojs/taro";
import {AtButton} from 'taro-ui'
import "taro-ui/dist/style/components/button.scss" // 按需引入
import headerBg from '../../assets/img.png'
import './index.scss'
import GlobalFooter from "../../compoents/GlobalFooter";
import questions from '../../data/questions.json'
import questionResult from '../../data/questionResult.json'
import {getBestQuestionResult} from "../../utils/bizUtils";

/**
 * 测试结果页面
 */
export default ()=> {

  const answerList = Taro.getStorageSync("answerList");
  if (!answerList || answerList.length < 1) {
    Taro.showToast({
      title: '答案为空',
      icon: "error",
      duration: 3000,
    })
  }

  // 获取测试结果
  const result = getBestQuestionResult(answerList,questions,questionResult);

    return (
      <View className='resultPage'>
        <View className='at-article__h1 title'>{result.resultName}</View>
        <View className='at-article__h2 subTitle'>{result.resultDesc}</View>
        <AtButton type='primary' circle className='enterBtn' onClick={()=>{
          Taro.reLaunch({
            url: '/pages/index/index',
          })
        }}
        >
          返回主页
        </AtButton>
        <Image className='headerBg' src={headerBg} />
        <GlobalFooter />
      </View>
    )
}

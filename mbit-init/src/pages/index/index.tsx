import {View, Image} from '@tarojs/components'
import {AtButton} from 'taro-ui'
import Taro from "@tarojs/taro";
import "taro-ui/dist/style/components/button.scss" // 按需引入
import headerBg from '../../assets/img.png'
import './index.scss'
import GlobalFooter from "../../compoents/GlobalFooter";


export default ()=> {
    return (
      <View className='indexPage'>
        <View className='at-article__h1 title'>MBTI 性格测试</View>
        <View className='at-article__h2 subTitle'>仅需要一小会就能得到到你的性格特点</View>
        <AtButton type='primary' circle className='enterBtn' onClick={()=>{
          // eslint-disable-next-line @typescript-eslint/no-unused-expressions
          Taro.navigateTo({
            url: '/pages/doQuestion/index',
          })
        }}
        >
          开始答题
        </AtButton>
        <Image className='headerBg' src={headerBg} />
        <GlobalFooter />
      </View>
    )
}

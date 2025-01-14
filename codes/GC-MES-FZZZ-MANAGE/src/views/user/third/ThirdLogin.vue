<template>
  <div>
    <div class="user-login-other">
      <span style="color: #1890FF;">其他登录方式</span>
<!--      <a @click="onThirdLogin('github')" title="github"><a-icon class="item-icon" type="github"></a-icon></a>
      <a @click="onThirdLogin('wechat_enterprise')" title="企业微信"> <icon-font class="item-icon" type="icon-qiyeweixin3" /></a>
      <a @click="onThirdLogin('dingtalk')" title="钉钉"><a-icon class="item-icon" type="dingding"></a-icon></a>
      <a @click="onThirdLogin('wechat_open')" title="微信"><a-icon class="item-icon" type="wechat"></a-icon></a>-->
      <!--      人脸识别-->
      <el-button @click="onTake" size="small" class="btn">人脸识别</el-button>
      <el-dialog style="text-align: center;" title="拍照上传" :visible.sync="visible" @close="onCancel" width="1065px">
        <div class="box">
          <video id="videoCamera" class="canvas" :width="videoWidth" :height="videoHeight" autoPlay></video>
          <canvas id="canvasCamera" class="canvas" :width="videoWidth" :height="videoHeight"></canvas>
        </div>
        <div slot="footer">
          <el-button @click="drawImage" icon="el-icon-camera" size="small" >拍照</el-button>
          <el-button v-if="open" @click="getCompetence" icon="el-icon-video-camera" size="small">打开摄像头</el-button>
          <el-button v-else @click="stopNavigator" icon="el-icon-switch-button" size="small">关闭摄像头</el-button>
          <el-button @click="resetCanvas" icon="el-icon-refresh" size="small">重置</el-button>
          <el-button @click="onCancel" icon="el-icon-circle-close" size="small">取消
          </el-button>
          <el-button @click="onUpload" :loading="loading" type="primary" icon="el-icon-upload2" size="small">识别登录</el-button>
        </div>
      </el-dialog>
    </div>
    <!-- 第三方登录绑定账号密码输入弹框 -->
    <a-modal title="请输入密码" :visible="thirdPasswordShow" @ok="thirdLoginCheckPassword" @cancel="thirdLoginNoPassword">
      <a-input-password placeholder="请输入密码" v-model="thirdLoginPassword" />
    </a-modal>

    <!-- 第三方登录提示是否绑定账号弹框 -->
    <a-modal :footer="null" :closable="false" :visible="thirdConfirmShow" :class="'ant-modal-confirm'">
      <div class="ant-modal-confirm-body-wrapper">
        <div class="ant-modal-confirm-body">
          <a-icon type="question-circle" style="color:#faad14"/>
          <span class="ant-modal-confirm-title">提示</span>
          <div class="ant-modal-confirm-content">
            已有同名账号存在,请确认是否绑定该账号？
          </div>
        </div>
        <div class="ant-modal-confirm-btns">
          <a-button @click="thirdLoginUserCreate" :loading="thirdCreateUserLoding">创建新账号</a-button>
          <a-button @click="thirdLoginUserBind" type="primary">确认绑定</a-button>
        </div>
      </div>
    </a-modal>

    <!-- 第三方登录绑定手机号 -->
    <a-modal :visible="bindingPhoneModal" :class="'ant-modal-confirm'">
      <template slot="footer">
        <a-button key="submit" type="primary" @click="thirdHandleOk">
          确定
        </a-button>
      </template>
      <div class="ant-modal-confirm-body-wrapper">
        <a-form-model-item>
          <span>绑定手机号</span>
        </a-form-model-item>
        <a-form-model-item>
          <a-input
            size="large"
            type="text"
            placeholder="手机号"
            v-model="thirdPhone">
            <a-icon slot="prefix" type="mobile" :style="{ color: 'rgba(0,0,0,.25)' }"/>
          </a-input>
        </a-form-model-item>

        <a-row :gutter="16">
          <a-col class="gutter-row" :span="16">
            <a-form-model-item>
              <a-input
                size="large"
                type="text"
                placeholder="请输入验证码"
                v-model="thirdCaptcha">
                <a-icon slot="prefix" type="mail" :style="{ color: 'rgba(0,0,0,.25)' }"/>
              </a-input>
            </a-form-model-item>
          </a-col>
          <a-col class="gutter-row" :span="8">
            <a-button
              class="getCaptcha"
              tabindex="-1"
              :disabled="thirdState.smsSendBtn"
              @click.stop.prevent="getThirdCaptcha"
              v-text="!thirdState.smsSendBtn && '获取验证码' || (thirdState.time+' s')"></a-button>
          </a-col>
        </a-row>
      </div>
    </a-modal>
  </div>
</template>

<script>
  import { JeecgThirdLoginMixin } from '@views/user/third/JeecgThirdLoginMixin'
  import { Icon } from 'ant-design-vue';
  import { postAction } from '@/api/manage'
  import {mapActions} from "vuex";
  import { timeFix } from "@/utils/util";

  const IconFont = Icon.createFromIconfontCN({
    // scriptUrl: '//at.alicdn.com/t/font_2316098_umqusozousr.js',
    scriptUrl: '/cdn/font-icon/font_2316098_umqusozousr.js',
  });
  //拍照上传组件
  //父组件通过函数 getImg 获取照片路径,如 @getImg="getImg"
  // const Address = require('../utils/url');//图片上传地址
  export default {
    name: 'thirdLogin',
    data(){
      return{
        imgSrc: "",
        visible: false,//弹窗
        loading: false,//上传按钮加载
        open: false,//控制摄像头开关
        thisVideo: null,
        thisContext: null,
        thisCancas: null,
        videoWidth: 500,
        videoHeight: 400,
        url: {
          face:"/sys/facelogin"
        },
      }
    },
    mixins: [JeecgThirdLoginMixin],
    components: {
      IconFont,
    },
    methods: {
      ...mapActions(['FaceLogin']),
      onTake() {
        this.visible = true;
        this.getCompetence();
      },
      onCancel() {
        this.visible = false;
        this.resetCanvas();
        this.stopNavigator();
      },
      //base64转成文件后上传

      onUpload() {
        if (this.imgSrc) {
          const file = this.imgSrc; // 把整个base64给file
          let loginParams = {
            file: file,
            remember_me: true,
          }
          console.log("登录参数", loginParams)
          this.FaceLogin(loginParams).then((res) => {
            this.visible = false;
            this.resetCanvas();
            this.stopNavigator();
            this.$emit('success', res.result);
            //this.$message.success('登录成功');
            this.$router.push({ path: "/dashboard/analysis" }).catch(()=>{
              console.log('登录跳转首页出错,这个错误从哪里来的')
            })
            this.$notification.success({
              message: '欢迎',
              description: `${timeFix()}，欢迎回来`,
            });
          }).catch((err) => {
            //this.$message.warning(err.message);
            this.$message.warning("人脸不匹配");
            this.$emit('fail', err)
          });
        }
        else {
          this.$notify({
            title: '警告',
            message: '请点击拍照',
            type: 'warning'
          });
        }
      },
      // 调用摄像头权限
      getCompetence() {
        //必须在model中render后才可获取到dom节点,直接获取无法获取到model中的dom节点
        this.$nextTick(() => {
          const _this = this;
          this.open = false;//切换成关闭摄像头
          this.thisCancas = document.getElementById('canvasCamera');
          this.thisContext = this.thisCancas.getContext('2d');
          this.thisVideo = document.getElementById('videoCamera');
          // 旧版本浏览器可能根本不支持mediaDevices，我们首先设置一个空对象
          if (navigator.mediaDevices === undefined) {
            navigator.mediaDevices = {}
          }
          // 一些浏览器实现了部分mediaDevices，我们不能只分配一个对象
          // 使用getUserMedia，因为它会覆盖现有的属性。
          // 这里，如果缺少getUserMedia属性，就添加它。
          if (navigator.mediaDevices.getUserMedia === undefined) {
            navigator.mediaDevices.getUserMedia = function (constraints) {
              // 首先获取现存的getUserMedia(如果存在)
              let getUserMedia = navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.getUserMedia;
              // 有些浏览器不支持，会返回错误信息
              // 保持接口一致
              if (!getUserMedia) {
                return Promise.reject(new Error('getUserMedia is not implemented in this browser'))
              }
              // 否则，使用Promise将调用包装到旧的navigator.getUserMedia
              return new Promise(function (resolve, reject) {
                getUserMedia.call(navigator, constraints, resolve, reject)
              })
            }
          }
          const constraints = {
            audio: false,
            video: {width: _this.videoWidth, height: _this.videoHeight, transform: 'scaleX(-1)'}
          };
          navigator.mediaDevices.getUserMedia(constraints).then(function (stream) {
            // 旧的浏览器可能没有srcObject
            if ('srcObject' in _this.thisVideo) {
              _this.thisVideo.srcObject = stream
            } else {
              // 避免在新的浏览器中使用它，因为它正在被弃用。
              _this.thisVideo.src = window.URL.createObjectURL(stream)
            }
            _this.thisVideo.onloadedmetadata = function (e) {
              _this.thisVideo.play()
            }
          }).catch(err => {
            this.$notify({
              title: '警告',
              message: '没有开启摄像头权限或浏览器版本不兼容.',
              type: 'warning'
            });
          });
        });
      },
      //绘制图片
      drawImage() {
        // 点击，canvas画图
        this.thisContext.drawImage(this.thisVideo, 0, 0, this.videoWidth, this.videoHeight);
        // 获取图片base64链接
        this.imgSrc = this.thisCancas.toDataURL('image/png');
      },
      //base64转文件
      dataURLtoFile(dataurl, filename) {
        let arr = dataurl.split(',');
        let mime = arr[0].match(/:(.*?);/)[1];
        let bstr = atob(arr[1]);
        let n = bstr.length;
        let u8arr = new Uint8Array(n);
        while (n--) {
          u8arr[n] = bstr.charCodeAt(n)
        }
        return new File([u8arr], filename, {type: mime})
      },
      //清空画布
      clearCanvas(id) {
        let c = document.getElementById(id);
        let cxt = c.getContext("2d");
        cxt.clearRect(0, 0, c.width, c.height);
      },
      //重置画布
      resetCanvas() {
        this.imgSrc = "";
        this.clearCanvas('canvasCamera');
      },
      //关闭摄像头
      stopNavigator() {
        if (this.thisVideo && this.thisVideo !== null) {
          this.thisVideo.srcObject.getTracks()[0].stop();
          this.open = true;//切换成打开摄像头
        }
      },
    },
    beforeDestroy() {
      this.stopNavigator()
    }
  }
</script>

<style lang="less" scoped>
  /*人脸识别*/

  .btn{border: none;background-color: transparent;padding-bottom: 0;color: #fff;}
  /*.face{font-size: 28px;color:#C0C2C4;}*/
  .btn:hover{color: #1890FF;}
  .box {
    display: flex;
    justify-content: space-between;
  }

  .canvas {
    border: 1px solid #e8e8e8;
  }
  .user-login-other {
    text-align: left;
    margin-top: 24px;
    line-height: 22px;

    .item-icon {
      font-size: 24px;
      color: rgba(0, 0, 0, .2);
      margin-left: 16px;
      vertical-align: middle;
      cursor: pointer;
      transition: color .3s;

      &  :hover {
        color: #1890ff;
      }
    }
    .register {
      float: right;
    }
  }
</style>
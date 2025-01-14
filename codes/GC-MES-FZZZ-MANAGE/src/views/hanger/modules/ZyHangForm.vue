<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="车间" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="workshopId">
              <j-select-depart placeholder="请选择车间" v-model="model.workshopId" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="品牌" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="brand">
              <a-input v-model="model.brand" placeholder="请输入品牌"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="型号" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="model">
              <a-input v-model="model.model" placeholder="请输入型号"  ></a-input>
            </a-form-model-item>
          </a-col>
<!--          <a-col :span="24">-->
<!--            <a-form-model-item label="编号" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="code">-->
<!--              <a-input v-model="model.code" placeholder="请输入编号"  ></a-input>-->
<!--            </a-form-model-item>-->
<!--          </a-col>-->
          <a-col :span="24">
            <a-form-model-item label="工站数" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="pointnumber">
              <a-input-number v-model="model.pointnumber" placeholder="请输入工站数" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="购买时间" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="buydate">
<!--              <a-input v-model="model.buydate" placeholder="请输入购买时间"  ></a-input>-->
              <j-date placeholder="请选择购买时间" v-model="model.buydate"  style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="经办人" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="agent">
<!--              <a-input v-model="model.agent" placeholder="请输入经办人"  ></a-input>-->
              <j-select-user-by-dep v-model="model.agent" :multi="false"/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="状态" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="status">
<!--              <a-input v-model="model.status" placeholder="请输入状态"></a-input>-->
              <j-dict-select-tag v-model="model.status" dictCode="devicestatus1"
                                 placeholder="设备状态"></j-dict-select-tag>
            </a-form-model-item>
          </a-col>
<!--          <a-col :span="24">-->
<!--            <a-form-model-item label="二维码" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="qrcode">-->
<!--              <a-input v-model="model.qrcode" placeholder="请输入二维码"></a-input>-->
<!--            </a-form-model-item>-->
<!--          </a-col>-->
          <a-col :span="24">
            <a-form-model-item label="维护周期(天)" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="maintenancecycle">
              <a-input-number v-model="model.maintenancecycle" placeholder="请输入维护周期" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="管理人" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="administrator">
<!--              <a-input v-model="model.administrator" placeholder="请输入管理人"></a-input>-->
              <j-select-user-by-dep v-model="model.administrator" :multi="false"/>
<!--              <j-dict-select-tag type="list" v-model="model.administrator"-->
<!--                                 placeholder="请选择管理人" :dict-code="enterprisesManager"/>-->
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="上次维护时间" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="maintenancedate">
<!--              <a-input v-model="model.maintenancedate" placeholder="请输入上次维护时间"  ></a-input>-->
              <j-date placeholder="请选择上次维护时间" v-model="model.maintenancedate"  style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="图片" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="img">
<!--              <a-input v-model="model.img" placeholder="请输入图片"  ></a-input>-->
              <j-image-upload isMultiple  v-model="model.img" ></j-image-upload>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="3d模型" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="img3d">
              <a-input v-model="model.img3d" placeholder="请输入3d模型"  ></a-input>
            </a-form-model-item>
          </a-col>
        </a-row>
      </a-form-model>
    </j-form-container>
  </a-spin>
</template>

<script>

import { httpAction, getAction } from '@/api/manage'
import { validateDuplicateValue } from '@/utils/util'

export default {
  name: 'ZyHangForm',
  components: {
  },
  props: {
    //表单禁用
    disabled: {
      type: Boolean,
      default: false,
      required: false
    }
  },
  data () {
    return {
      // enterprisesManager: "sys_user,realname,id",
      model:{
        status:0,
      },
      labelCol: {
        xs: { span: 24 },
        sm: { span: 5 },
      },
      wrapperCol: {
        xs: { span: 24 },
        sm: { span: 16 },
      },
      confirmLoading: false,
      validatorRules: {
        workshopId: [
          { required: true,message: "车间不能为空"},
          { pattern: /^.{0,36}$/, message: '长度过长!'},
        ],
        brand: [
          { required: false},
          { pattern: /^.{0,36}$/, message: '长度过长!'},
        ],
        model: [
          { required: false},
          { pattern: /^.{0,36}$/, message: '长度过长!'},
        ],
        // code: [
        //   { required: true, message: '请输入编号!'},
        //   // { pattern: /^.{6,6}$/, message: '长度过长!'},
        //   { pattern: /^(?=DG)[0-9]{6}$/, message: '长度过长!'},
        //   // /^[0-9A-Za-z]{6}$/
        //
        // ],
        pointnumber: [
          { required: true, message: '请输入工站数!'},
          // { pattern: /^-?\d+$/, message: '请输入整数!'},
          {pattern: /^[1-9]{0,100}$/, message: '请输入正整数'}

        ],
        agent: [
          { required: false},
          { pattern: /^.{0,36}$/, message: '长度过长!'},
        ],
        status: [
          { required: false},
          { pattern: /^.{1}$/, message: '长度过长!'},
        ],
        qrcode: [
          { required: false},
          // { pattern: /^[A-Z|a-z]+$/, message: '请输入字母!'},
          { pattern: /^.{0,256}$/, message: '长度过长!'},
        ],
        maintenancecycle: [
          { required: false},
          // { pattern: /^-?\d+$/, message: '请输入整数!'},
          {pattern: /^[1-9]{0,100}$/, message: '请输入正整数'}

        ],
        img: [
          { required: false},
          { pattern: /^.{0,300}$/, message: '长度过长!'},
        ],
        img3d: [
          { required: false},
          { pattern: /^.{0,300}$/, message: '长度过长!'},
        ],
      },
      url: {
        add: "/org.jeecg.modules.hanger/zyHang/add",
        edit: "/org.jeecg.modules.hanger/zyHang/edit",
        queryById: "/org.jeecg.modules.hanger/zyHang/queryById"
      }
    }
  },
  computed: {
    formDisabled(){
      return this.disabled
    },
  },
  created () {
    //备份model原始值
    this.modelDefault = JSON.parse(JSON.stringify(this.model));
  },
  methods: {
    add () {
      this.edit(this.modelDefault);
    },
    edit (record) {
      this.model = Object.assign({}, record);
      this.visible = true;
    },
    submitForm () {
      const that = this;
      // 触发表单验证
      this.$refs.form.validate(valid => {
        if (valid) {
          that.confirmLoading = true;
          let httpurl = '';
          let method = '';
          if(!this.model.id){
            httpurl+=this.url.add;
            method = 'post';
          }else{
            httpurl+=this.url.edit;
            method = 'put';
          }
          httpAction(httpurl,this.model,method).then((res)=>{
            if(res.success){
              that.$message.success(res.message);
              that.$emit('ok');
            }else{
              that.$message.warning(res.message);
            }
          }).finally(() => {
            that.confirmLoading = false;
          })
        }

      })
    },
  }
}
</script>
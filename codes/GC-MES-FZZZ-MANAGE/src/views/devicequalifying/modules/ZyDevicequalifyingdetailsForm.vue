<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="方案" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="qualifyingid">
              <j-dict-select-tag type="list" v-model="model.devicequalifyingid" dictCode="zy_devicequalifying,name,id" placeholder="请选择方案" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="序号" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="number">
              <a-input-number v-model="model.number" placeholder="请输入序号" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="机器" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="deviceid">
              <j-dict-select-tag type="list" v-model="model.deviceid" dictCode="zy_device,code,id" placeholder="请选择机器" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="工位" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="station">
              <a-input v-model="model.station" placeholder="请输入工位"  ></a-input>
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
    name: 'ZyDevicequalifyingdetailsForm',
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
        model:{
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
        },
        url: {
          add: "/devicequalifying/zyDevicequalifyinglist/add",
          edit: "/devicequalifying/zyDevicequalifyinglist/edit",
          queryById: "/devicequalifying/zyDevicequalifyinglist/queryById"
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
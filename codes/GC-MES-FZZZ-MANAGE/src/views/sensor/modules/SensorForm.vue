<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="传感器编号" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="sensorCode">
              <a-input v-model="model.sensorCode" placeholder="请输入传感器编号"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="车间" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="workshopId">
              <j-tree-select v-model="model.workshopId"
                             dict="sys_depart,depart_name,id"
                             pidField="parent_id"
                             placeholder="请选择车间"
                             condition='{"org_category":"2"}'
                             hasChildField="1"
              >
              </j-tree-select>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="网络地址" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="sensorIp">
              <a-input v-model="model.sensorIp" placeholder="请输入网络地址"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="工位" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="station">
              <!--<j-popup-->
                <!--v-model="model.station"-->
                <!--field="station"-->
                <!--org-fields="id,station_name,depart_id"-->
                <!--dest-fields="stationId,station,workshopId"-->
                <!--code="findgw"-->
                <!--:multi="true"-->
                <!--@input="popupCallback"-->
                <!--/>-->
              <j-search-select-tag v-model="model.stationId" dict="station,station_name,id"  />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="进出标记" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="iiOsign">
              <j-dict-select-tag type="list" v-model="model.iiOsign" dictCode="ioinset" placeholder="请选择进出标记" />
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
    name: 'SensorForm',
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
           sensorCode: [
              { required: true, message: '请输入传感器编号!'},
           ],
           workshopId: [
              { required: true, message: '请输入车间!'},
           ],
           sensorIp: [
              { required: true, message: '请输入网络地址!'},
           ],
           iiOsign: [
              { required: true, message: '请输入进出标记!'},
           ],
        },
        url: {
          add: "/sensor/sensor/add",
          edit: "/sensor/sensor/edit",
          queryById: "/sensor/sensor/queryById"
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
      popupCallback(value,row){
         this.model = Object.assign(this.model, row);
      },
    }
  }
</script>
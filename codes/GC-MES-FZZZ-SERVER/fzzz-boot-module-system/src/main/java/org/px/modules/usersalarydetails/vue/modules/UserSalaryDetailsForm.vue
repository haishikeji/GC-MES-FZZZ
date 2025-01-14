<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form :form="form" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-item label="员工工号" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['workNo']" placeholder="请输入员工工号"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="员工ID" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['userId']" placeholder="请输入员工ID"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="员工姓名" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-popup
                v-decorator="['userName', validatorRules.userName]"
                :trigger-change="true"
                org-fields="id,realname,work_no"
                dest-fields="userId,userName,workNo"
                code="selectstaff"
                @callback="popupCallback"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="薪资规则" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-popup
                v-decorator="['ruleName', validatorRules.ruleName]"
                :trigger-change="true"
                org-fields="rule_factor,rule_money,rule_name"
                dest-fields="ruleFactor,ruleMoney,ruleName"
                code="selectrule"
                @callback="popupCallback"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="薪资要素" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['ruleFactor']" placeholder="请输入薪资要素"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="薪资金额" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['ruleMoney']" placeholder="请输入薪资金额"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="薪资日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-date placeholder="请选择薪资日期" v-decorator="['salaryDate', validatorRules.salaryDate]" :trigger-change="true" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="个税扣款" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['tax', validatorRules.tax]" placeholder="请输入个税扣款"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="五险一金" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['insurancesFund', validatorRules.insurancesFund]" placeholder="请输入五险一金"></a-input>
            </a-form-item>
          </a-col>
          <a-col v-if="showFlowSubmitButton" :span="24" style="text-align: center">
            <a-button @click="submitForm">提 交</a-button>
          </a-col>
        </a-row>
      </a-form>
    </j-form-container>
  </a-spin>
</template>

<script>

  import { httpAction, getAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import { validateDuplicateValue } from '@/utils/util'
  import JFormContainer from '@/components/jeecg/JFormContainer'
  import JDate from '@/components/jeecg/JDate'  

  export default {
    name: 'UserSalaryDetailsForm',
    components: {
      JFormContainer,
      JDate,
    },
    props: {
      //流程表单data
      formData: {
        type: Object,
        default: ()=>{},
        required: false
      },
      //表单模式：true流程表单 false普通表单
      formBpm: {
        type: Boolean,
        default: false,
        required: false
      },
      //表单禁用
      disabled: {
        type: Boolean,
        default: false,
        required: false
      }
    },
    data () {
      return {
        form: this.$form.createForm(this),
        model: {},
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
          userName: {
            rules: [
              { required: true, message: '请输入员工姓名!'},
            ]
          },
          ruleName: {
            rules: [
              { required: true, message: '请输入薪资规则!'},
            ]
          },
          salaryDate: {
            rules: [
              { required: true, message: '请输入薪资日期!'},
            ]
          },
          tax: {
            rules: [
              { required: true, message: '请输入个税扣款!'},
              { pattern: /^(([1-9][0-9]*)|([0]\.\d{0,2}|[1-9][0-9]*\.\d{0,2}))$/, message: '请输入正确的金额!'},
            ]
          },
          insurancesFund: {
            rules: [
              { required: true, message: '请输入五险一金!'},
              { pattern: /^(([1-9][0-9]*)|([0]\.\d{0,2}|[1-9][0-9]*\.\d{0,2}))$/, message: '请输入正确的金额!'},
            ]
          },
        },
        url: {
          add: "/usersalarydetails/userSalaryDetails/add",
          edit: "/usersalarydetails/userSalaryDetails/edit",
          queryById: "/usersalarydetails/userSalaryDetails/queryById"
        }
      }
    },
    computed: {
      formDisabled(){
        if(this.formBpm===true){
          if(this.formData.disabled===false){
            return false
          }
          return true
        }
        return this.disabled
      },
      showFlowSubmitButton(){
        if(this.formBpm===true){
          if(this.formData.disabled===false){
            return true
          }
        }
        return false
      }
    },
    created () {
      //如果是流程中表单，则需要加载流程表单data
      this.showFlowData();
    },
    methods: {
      add () {
        this.edit({});
      },
      edit (record) {
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model,'workNo','userId','userName','ruleName','ruleFactor','ruleMoney','salaryDate','tax','insurancesFund'))
        })
      },
      //渲染流程表单数据
      showFlowData(){
        if(this.formBpm === true){
          let params = {id:this.formData.dataId};
          getAction(this.url.queryById,params).then((res)=>{
            if(res.success){
              this.edit (res.result);
            }
          });
        }
      },
      submitForm () {
        const that = this;
        // 触发表单验证
        this.form.validateFields((err, values) => {
          if (!err) {
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
            let formData = Object.assign(this.model, values);
            console.log("表单提交数据",formData)
            httpAction(httpurl,formData,method).then((res)=>{
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
      popupCallback(row){
        this.form.setFieldsValue(pick(row,'workNo','userId','userName','ruleName','ruleFactor','ruleMoney','salaryDate','tax','insurancesFund'))
      },
    }
  }
</script>
<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form :form="form" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-item label="员工工号" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['workNo', validatorRules.workNo]" placeholder="请输入员工工号"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="员工姓名" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['userName', validatorRules.userName]" placeholder="请输入员工姓名"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="员工部门" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-select-depart v-decorator="['department', validatorRules.department]" multi/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="性别" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-dict-select-tag type="list" v-decorator="['sex', validatorRules.sex]" :trigger-change="true" dictCode="sex" placeholder="请选择性别"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="计划产量" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['planYield']" placeholder="请输入计划产量"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="实际产量" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['actualYield']" placeholder="请输入实际产量"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="合格产量" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['qualifiedQuantity']" placeholder="请输入合格产量"></a-input>
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
  import JSelectDepart from '@/components/jeecgbiz/JSelectDepart'
  import JDictSelectTag from "@/components/dict/JDictSelectTag"

  export default {
    name: 'UserWorkloadForm',
    components: {
      JFormContainer,
      JSelectDepart,
      JDictSelectTag,
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
          workNo: {
            rules: [
              { required: true, message: '请输入员工工号!'},
            ]
          },
          userName: {
            rules: [
              { required: true, message: '请输入员工姓名!'},
            ]
          },
          department: {
            rules: [
              { required: true, message: '请输入员工部门!'},
            ]
          },
          sex: {
            rules: [
              { required: true, message: '请输入性别!'},
            ]
          },
        },
        url: {
          add: "/userworkload/userWorkload/add",
          edit: "/userworkload/userWorkload/edit",
          queryById: "/userworkload/userWorkload/queryById"
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
          this.form.setFieldsValue(pick(this.model,'workNo','userName','department','sex','planYield','actualYield','qualifiedQuantity'))
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
        this.form.setFieldsValue(pick(row,'workNo','userName','department','sex','planYield','actualYield','qualifiedQuantity'))
      },
    }
  }
</script>
<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>

          <a-col :span="24">
            <a-form-model-item label="用户" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="username">
              <j-popup
                style="max-width:none; width: 100%"
                v-model="model.username"
                field="username"
                org-fields="id,realname"
                dest-fields="userId,username"
                code="findcust"
                :multi="true"
                @input="popupCallback"
                />
            </a-form-model-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="客户类型" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="customerType">
              <j-dict-select-tag type="radio" v-model="model.customerType" dictCode="khlx" placeholder="请选择客户类型" style="max-width:none; width: 200%"/>
            </a-form-model-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="发票类型" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="invoiceType">
              <j-dict-select-tag type="radio" v-model="model.invoiceType" dictCode="fplx" placeholder="请选择发票类型" style="max-width:none; width: 200%"/>
            </a-form-model-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="名称/姓名" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="name">
              <a-input v-model="model.name" placeholder="请输入名称/姓名"  style="max-width:none; width: 200%"></a-input>
            </a-form-model-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="纳税人识别号" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="taxIdentificationNumber">
              <a-input v-model="model.taxIdentificationNumber" placeholder="请输入纳税人识别号"  style="max-width:none; width: 200%"></a-input>
            </a-form-model-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="地址" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="address">
              <a-textarea v-model="model.address" rows="4" placeholder="请输入地址" style="max-width:none; width: 200% ; height: 55px"/>
            </a-form-model-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="24" v-if="this.model.customerType==2">
            <a-form-model-item label="电话" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="telephone">
              <a-input v-model="model.telephone" placeholder="请输入电话"  style="max-width:none; width: 200%"></a-input>
            </a-form-model-item>
          </a-col>
        </a-row>

        <a-row>
          <a-col :span="24"  v-if="this.model.customerType==2">
            <a-form-model-item label="账号" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="accountNumber">
              <a-input v-model="model.accountNumber" placeholder="请输入账号"  style="max-width:none; width: 200%"></a-input>
            </a-form-model-item>
          </a-col>
        </a-row>


<!--        <a-row>
          <a-col :span="24">
            <a-form-model-item label="开户行" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="bankDeposit">
              <a-input style="max-width:none; width: 200%" v-model="model.bankDeposit" placeholder="请输入开户行"  disabled></a-input>
            </a-form-model-item>
          </a-col>
        </a-row>-->

        <a-row>
          <a-col :span="24" v-if="this.model.customerType==2">
            <a-form-model-item label="开户行" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="bankzh">
              <j-popup
                v-model="model.bankzh"
                field="bankzh"
                org-fields="id,bankname"
                dest-fields="bankDeposit,bankzh"
                code="findyh"
                :multi="true"
                @input="popupCallback"
              />
            </a-form-model-item>
          </a-col>
        </a-row>

        <a-row>
          <a-col :span="24">
            <a-form-model-item label="排序" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="sort">
              <a-input-number v-model="model.sort" placeholder="请输入排序" style="max-width:none; width: 200%" />
            </a-form-model-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="创建日期" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="createTime">
              <j-date placeholder="请选择创建日期"  v-model="model.createTime" :show-time="true" date-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" disabled/>
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
  import store from '@/store'

  export default {
    name: 'CustomerInvoiceForm',
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
          invoiceType : 2,
          customerType : 1,
          name : store.getters.userInfo.realname

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
          accountNumber: [
            { required: true, message: '请输入账号!'},
          ],
           username: [
              { required: true, message: '请输入用户!'},
           ],
           customerType: [
              { required: true, message: '请输入客户类型!'},
           ],
           invoiceType: [
              { required: true, message: '请输入发票类型!'},
           ],
           name: [
              { required: true, message: '请输入名称/姓名!'},
           ],
           telephone: [
              { required: false},
              { pattern: /^1[3456789]\d{9}$/, message: '请输入正确的手机号码!'},
           ],
 /*         sort: [
           /!* { required: true, message: '请输入胸围!'},*!/
            {pattern: /^\d*\.?\d$/,message:'请输入大于0的数,保留一位小数'}
          ],*/


          sort: [
            { required: false},
            { validator: (rule, value, callback) => validateDuplicateValue('customer_invoice', 'sort', value, this.model.id, callback)},
          ],
        },
        url: {
          add: "/customerinvoice/customerInvoice/add",
          edit: "/customerinvoice/customerInvoice/edit",
          queryById: "/customerinvoice/customerInvoice/queryById"
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
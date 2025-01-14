<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="仓库" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="storehouseId">
              <j-search-select-tag v-model="model.storehouseId" dict="starehouse,name,id"  />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="物品类型" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="itemType">
              <j-dict-select-tag type="list" v-model="model.itemType" dictCode="goods_category" placeholder="请选择物品类型" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24" v-if="this.model.itemType==1">
            <a-form-model-item label="物品编码" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="itemCode">
              <j-popup
                v-model="model.itemCode"
                field="itemCode"
                org-fields="fabric_number"
                dest-fields="itemCode"
                code="findml"
                :multi="true"
                @input="popupCallback"
              />
            </a-form-model-item>
          </a-col>


          <a-col :span="24" v-if="this.model.itemType==2">
            <a-form-model-item label="物品编码" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="itemCode">
              <j-popup
                v-model="model.itemCode"
                field="itemCode"
                org-fields="nums"
                dest-fields="itemCode"
                code="ckfindfl"
                :multi="true"
                @input="popupCallback"
              />
            </a-form-model-item>
          </a-col>



          <a-col :span="24" v-if="this.model.itemType==3">
            <a-form-model-item label="物品编码" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="itemCode">
              <j-popup
                v-model="model.itemCode"
                field="itemCode"
                org-fields="code"
                dest-fields="itemCode"
                code="ckfindsb"
                :multi="true"
                @input="popupCallback"
              />
            </a-form-model-item>
          </a-col>


          <a-col :span="24" v-if="this.model.itemType==4">
            <a-form-model-item label="物品编码" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="itemCode">
              <j-popup
                v-model="model.itemCode"
                field="itemCode"
                org-fields="nums"
                dest-fields="itemCode"
                code="kcfindgj"
                :multi="true"
                @input="popupCallback"
              />
            </a-form-model-item>
          </a-col>


          <a-col :span="24" v-if="this.model.itemType==5">
            <a-form-model-item label="物品编码" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="itemCode">
              <j-popup
                v-model="model.itemCode"
                field="itemCode"
                org-fields="goods_code"
                dest-fields="itemCode"
                code="kcfindsp"
                :multi="true"
                @input="popupCallback"
              />
            </a-form-model-item>
          </a-col>



          <a-col :span="24">
            <a-form-model-item label="存量" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="amount">
              <a-input-number v-model="model.amount" placeholder="请输入存量" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="单位" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="unit">
              <a-input v-model="model.unit" placeholder="请输入单位"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="管理员" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="adminname">
              <j-popup
                v-model="model.adminname"
                field="adminname"
                org-fields="id,realname"
                dest-fields="adminId,adminname"
                code="findck"
                :multi="true"
                @input="popupCallback"
              />
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
    name: 'StorageForm',
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
          itemType : "1"
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
          add: "/storage/storage/add",
          edit: "/storage/storage/edit",
          queryById: "/storage/storage/queryById"
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